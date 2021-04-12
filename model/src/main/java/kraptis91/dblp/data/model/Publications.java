package kraptis91.dblp.data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
