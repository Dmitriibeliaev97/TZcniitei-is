package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.example.hierarchy.Item;
import org.example.hierarchy.Items;
import org.example.obj.AddressObject;
import org.example.obj.AddressObjects;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Основной класс для получения выходных данных
 * Написано без разделения на классы для того, чтобы было удобней в проверке
 */
@Slf4j
public class Main {
    //Ссылка на файл с данными адресов объектов
    private static final String ADDR_FILE_PATH = "C:\\Users\\Пользователь\\IdeaProjects\\TZ\\src\\main\\resources\\AS_ADDR_OBJ.XML";
    // Ссылка на файл иерархии
    private static final String HIERARCHY_FILE_PATH = "C:\\Users\\Пользователь\\IdeaProjects\\TZ\\src\\main\\resources\\AS_ADM_HIERARCHY.XML";
    public static void main(String[] args) throws JAXBException {
        // Вводные данные
        String date = "2010-01-01";
        String objectIds = "1422396, 1450759, 1449192, 1451562";

        // Массив строк для записи вводных данных ID объектов
        String[] objectIdArray = objectIds.split(", ");

        // Map для сохранения даты(ключ) и ID объектов (значение)
        Map<String, String> addressDescriptions = getAddressDescriptions(date, objectIdArray);
        // Цикл для формирования результата по примеру ТЗ
        for (String objectId : objectIdArray) {
            String description = addressDescriptions.get(objectId);
            System.out.println(objectId + ": " + description);
        }
    }

    /**
     * Метод создания Map с нужными результатами
     * @param date
     * @param objectIds
     * @return
     * @throws JAXBException
     */
    public static Map<String, String> getAddressDescriptions(String date, String[] objectIds) throws JAXBException {
        // Чтение данных из файла AS_ADDR_OBJ и сохранение их в Map
        Map<String, String> addressDataMap = readAddressData();

        // Чтение данных из файла AS_ADM_HIERARCHY и сохранение их в Map
        Map<String, String> hierarchyDataMap = readHierarchyData();

        // Выполнение необходимых проверок и выборка нужных адресов на указанную дату и по переданным идентификаторам OBJECTID
        Map<String, String> addressDescriptions = new HashMap<>();
        for (String objectId : objectIds) {
            if (addressDataMap.containsKey(objectId) && hierarchyDataMap.containsKey(objectId)) {
                String addressData = addressDataMap.get(objectId);
                String hierarchyData = hierarchyDataMap.get(objectId);
                String addressDescription = extractAddressDescription(addressData, hierarchyData);
                addressDescriptions.put(objectId, addressDescription);
            } else {
                addressDescriptions.put(objectId, "Address description not found for this OBJECTID");
            }
        }
        return addressDescriptions;
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

    private static String extractAddressDescription(String addressData, String hierarchyData) {
        // При необходимости здесь можно добавить необходимые данные из Иерархии и соединить с данными об адресе в одну строку
        return addressData;
    }
}