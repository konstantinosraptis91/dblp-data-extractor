package kraptis91.dblp.data.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 4/4/21.
 */
public class Publication {

    private String title;
    private String year;

    public Publication() {

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
}
