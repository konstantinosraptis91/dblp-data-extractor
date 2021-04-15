package kraptis91.dblp.data.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 12/4/21.
 */
@XmlRootElement(name = "publications")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publications {

    @XmlElement(name = "publication")
    private List<Publication> publicationList;

    public Publications() {
    }

    public List<Publication> getPublicationList() {
        if (Objects.isNull(publicationList)) {
            publicationList = new ArrayList<>();
        }
        return publicationList;
    }
}
