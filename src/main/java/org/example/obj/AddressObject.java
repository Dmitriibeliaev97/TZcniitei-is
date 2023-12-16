package org.example.obj;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Класс создания объекта из файла AS_ADDR_OBJ
 */
@XmlRootElement(name = "AddressObject")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AddressObject {
    @XmlAttribute(name = "ID")
    private String ID;
    @XmlAttribute(name = "OBJECTID")
    private String OBJECTID;
    @XmlAttribute(name = "OBJECTGUID")
    private String OBJECTGUID;
    @XmlAttribute(name = "CHANGEID")
    private String CHANGEID;
    @XmlAttribute(name = "NAME")
    private String NAME;
    @XmlAttribute(name = "TYPENAME")
    private String TYPENAME;
    @XmlAttribute(name = "LEVEL")
    private String LEVEL;
    @XmlAttribute(name = "OPERTYPEID")
    private String OPERTYPEID;
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
    @XmlAttribute(name = "ISACTUAL")
    private String ISACTUAL;
    @XmlAttribute(name = "ISACTIVE")
    private String ISACTIVE;

    @Override
    public String toString() {
        return TYPENAME + " " + NAME;
    }

}
