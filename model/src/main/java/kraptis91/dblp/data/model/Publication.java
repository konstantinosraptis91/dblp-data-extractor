package kraptis91.dblp.data.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 4/4/21.
 */
@XmlRootElement(name = "publication")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publication {

    private String title;
    private String year;
    // @XmlTransient
    private String author;

    public Publication() {

    }

    private Publication(Builder builder) {
        setTitle(builder.title);
        setYear(builder.year);
        setAuthor(builder.author);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String title;
        private String year;
        private String author;

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

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }

}
