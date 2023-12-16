package org.example.hierarchy;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;
/**
 * Класс для создания списка объектов
 */
@XmlRootElement(name = "ITEMS")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Items {
    @XmlElement(name = "ITEM")
    private List<Item> itemsList;
}
