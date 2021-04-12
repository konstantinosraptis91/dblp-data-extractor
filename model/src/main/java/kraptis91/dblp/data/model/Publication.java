package kraptis91.dblp.data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 4/4/21.
 */
@XmlRootElement(name = "publication")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publication {

    private String title;
    private String year;

    public Publication() {

    }

    private Publication(Builder builder) {
        setTitle(builder.title);
        setYear(builder.year);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Publication{" +
            "title='" + title + '\'' +
            ", year='" + year + '\'' +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String title;
        private String year;

        public Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder year(String year) {
            this.year = year;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }
}
