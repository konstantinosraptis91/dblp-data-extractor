package kraptis91.dblp.data.model.schema;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 2/4/21.
 */
@XmlRootElement(name = "phdthesis")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"author", "title", "year", "school", "pages", "isbn", "eeList", "note"})
public class PhdThesis {

    @XmlAttribute(name = "mdate")
    private String mDate;
    @XmlAttribute(name = "key")
    private String key;
    @XmlElement (name = "author")
    private String author;
    @XmlElement
    private String title;
    @XmlElement
    private String year;
    @XmlElement
    private String school;
    @XmlElement
    private String pages;
    @XmlElement
    private String isbn;
    @XmlElement(name = "ee")
    private List<String> eeList;
    @XmlElement
    private Note note;

    public PhdThesis() {

    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getEeList() {
        if (Objects.isNull(eeList)) {
            eeList = new ArrayList<>();
        }
        return eeList;
    }

    public void setEeList(List<String> eeList) {
        this.eeList = eeList;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Publication{" +
            "mDate='" + mDate + '\'' +
            ", key='" + key + '\'' +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            ", year='" + year + '\'' +
            ", school='" + school + '\'' +
            ", pages='" + pages + '\'' +
            ", isbn='" + isbn + '\'' +
            ", eeList=" + eeList +
            ", note=" + note +
            '}';
    }
}
