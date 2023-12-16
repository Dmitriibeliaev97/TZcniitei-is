package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.example.hierarchy.Item;
import org.example.hierarchy.Items;
import org.example.obj.AddressObject;
import org.example.obj.AddressObjects;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AddressHierarchy {
    private static final String ADDR_FILE_PATH = "C:\\Users\\Пользователь\\IdeaProjects\\TZ\\src\\main\\resources\\AS_ADDR_OBJ.XML";
    // Ссылка на файл иерархии
    private static final String HIERARCHY_FILE_PATH = "C:\\Users\\Пользователь\\IdeaProjects\\TZ\\src\\main\\resources\\AS_ADM_HIERARCHY.XML";
    public static void main(String[] args){
        try {
            Map<String, AddressObject> addressDataMap = new HashMap<>();
            System.out.println("Debug: addressDataMap size after loading data: " + addressDataMap.size()); // Для отладки
            Map<String, String> hierarchyDataMap = readHierarchyData();
            Map<String, String> addressDescriptionsTask2 = readAddressData();
            System.out.println("Debug: addressDescriptionsTask2 size: " + addressDescriptionsTask2.size()); // Для отладки


            // Строим дерево иерархии
            for (Map.Entry<String, String> entry : hierarchyDataMap.entrySet()) {
                String objectId = entry.getKey();
                String parentObjectId = entry.getValue();

                AddressObject addressObject = addressDataMap.getOrDefault(objectId,
                        new AddressObject(objectId, addressDescriptionsTask2.get(objectId)));
                addressDataMap.put(objectId, addressObject);

                if (!objectId.equals(parentObjectId)) {
                    AddressObject parentAddressObject = addressDataMap.getOrDefault(parentObjectId,
                            new AddressObject(parentObjectId));
                    addressObject.setParent(parentAddressObject);
                    parentAddressObject.getChildren().add(addressObject);
                    addressDataMap.put(parentObjectId, parentAddressObject);
                }
            }

            // Теперь у каждого объекта AddressObject есть ссылка на родителя
            // Формируем полные адреса для элементов с типом "проезд"
            List<String> fullAddresses = addressDataMap.values().stream()
                    .filter(addressObject -> "проезд".equals(addressObject.getTYPENAME())) //
                    .map(addressObject -> addressObject.getFullAddress(addressDataMap)) // передаем карту в метод
                    .sorted()
                    .toList();
            // Вывод полных адресов для отладки
            if (fullAddresses.isEmpty()) {
                System.out.println("Debug: No 'проезд' addresses found.");
            } else {
                System.out.println("Debug: Full addresses list size: " + fullAddresses.size());
            }
            // Выводим результат
            fullAddresses.forEach(System.out::println);

        } catch (JAXBException e) {
            System.err.println("Ошибка обработки XML данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод получения Map с нужными значениями из файла AS_ADDR_OBJ
     * @return
     * @throws JAXBException
     */
    private static Map<String, String> readAddressData() throws JAXBException {
        Map<String, String> addressDataMap = new HashMap<>();
        JAXBContext context = JAXBContext.newInstance(AddressObjects.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        AddressObjects addressObjects = (AddressObjects) unmarshaller.unmarshal(new File(ADDR_FILE_PATH));

        if (addressObjects.getAddressObjectList() != null) {
            for (AddressObject addressObject : addressObjects.getAddressObjectList()) {
                addressDataMap.put(addressObject.getOBJECTID(), addressObject.toString());
            }
        }
        return addressDataMap;
    }

    /**
     * Метод получения Map с нужными значениями из файла AS_ADM_HIERARCHY
     * @return
     * @throws JAXBException
     */
    private static Map<String, String> readHierarchyData() throws JAXBException {
        Map<String, String> hierarchyDataMap = new HashMap<>();
        JAXBContext context = JAXBContext.newInstance(Items.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Items items = (Items) unmarshaller.unmarshal(new File(HIERARCHY_FILE_PATH));

        for (Item item : items.getItemsList()) {
            hierarchyDataMap.put(item.getOBJECTID(), item.toString());
        }
        return hierarchyDataMap;
    }

}
