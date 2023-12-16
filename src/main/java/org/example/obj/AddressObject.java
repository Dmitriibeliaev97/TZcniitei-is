package org.example.obj;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс создания объекта из файла AS_ADDR_OBJ
 */
@XmlRootElement(name = "AddressObject")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class AddressObject implements Serializable {
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
    @Getter
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
    @XmlElement(name = "ADROBJECT", type = AddressObject.class)
    private List<AddressObject> children;
    private AddressObject parent; // родительский объект в иерархии

    public AddressObject(String objectId, String s) {
    }

    public AddressObject(String parentObjectId) {
    }

    @Override
    public String toString() {
        return TYPENAME + " " + NAME;
    }

    public List<AddressObject> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public String getFullAddress(Map<String, AddressObject> addressDataMap) {
        // Возвращает полный адрес, переходя по иерархии вверх до корня
        // Рassumption: root object has the same id and parent id
        StringBuilder fullAddress = new StringBuilder(this.NAME);
        AddressObject current = this;
        while (current.parent != null) {
            current = current.parent;
            fullAddress.insert(0, current.NAME + ", ");
        }
        return fullAddress.toString();
    }

}
