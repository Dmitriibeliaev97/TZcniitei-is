package org.example.hierarchy;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
/**
 * Класс создания объекта из файла AS_ADM_HIERARCHY
 */
@XmlRootElement(name = "Items")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Item {
    @XmlAttribute(name = "ID")
    private String ID;
    @XmlAttribute(name = "OBJECTID")
    private String OBJECTID;
    @XmlAttribute(name = "PARENTOBJID")
    private String PARENTOBJID;
    @XmlAttribute(name = "CHANGEID")
    private String CHANGEID;
    @XmlAttribute(name = "PREVID")
    private String PREVID;
    @XmlAttribute(name = "NEXTID")
    private String NEXTID;
    @XmlAttribute(name = "UPDATEDATE")
    private String UPDATEDATE;
    @XmlAttribute(name = "STARTDATE")
    private String STARTDATE;
    @XmlAttribute(name = "ENDDATE")
    private String ENDDATE;
    @XmlAttribute(name = "ISACTIVE")
    private String ISACTIVE;

}
