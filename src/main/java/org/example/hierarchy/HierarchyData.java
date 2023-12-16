package org.example.hierarchy;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;
@Data
public class HierarchyData {
    @XmlElement(name = "ADRITEM")
    private List<Item> itemList;
}
