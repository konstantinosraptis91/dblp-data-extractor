package kraptis91.dblp.data.model.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 2/4/21.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

    @XmlAttribute
    private String type;
    @XmlValue
    private String value;

    public Note() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Note{" +
            "type='" + type + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
