package kraptis91.dblp.data;

import jakarta.xml.bind.JAXBException;
import kraptis91.dblp.data.model.Publications;
import kraptis91.dblp.data.schema.utils.SchemaUtil;

import java.io.OutputStream;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 12/4/21.
 */
public class XMLPublicationsWriter {

    private final Publications publications;

    public XMLPublicationsWriter(Publications publications) {
        this.publications = publications;
    }

    public void createXML(OutputStream os) throws JAXBException {
        SchemaUtil.getMarshaller().marshal(publications, os);
    }

}
