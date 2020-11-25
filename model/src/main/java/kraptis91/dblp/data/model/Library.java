package kraptis91.dblp.data.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
@XmlRootElement(name = "dblp")
public class Library {

  private List<Publication> publicationList;

  public Library() {}

  public List<Publication> getPublicationList() {
    return publicationList;
  }

  public void setPublicationList(List<Publication> publicationList) {
    this.publicationList = publicationList;
  }

  @Override
  public String toString() {
    return "Library{" + "publicationList=" + publicationList + '}';
  }
}
