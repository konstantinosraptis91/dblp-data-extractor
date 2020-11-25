package kraptis91.dblp.data.model;

import javax.xml.bind.annotation.XmlRootElement;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
@XmlRootElement(name = "phdthesis")
public class Publication {

  private String year;

  public Publication() {}

  public Publication(String year) {
    this.year = year;
  }

  public String getYear() {
    return year;
  }

  @Override
  public String toString() {
    return "Publication{" + "year='" + year + '\'' + '}';
  }
}
