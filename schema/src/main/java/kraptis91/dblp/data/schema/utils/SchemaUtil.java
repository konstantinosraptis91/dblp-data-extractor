package kraptis91.dblp.data.schema.utils;

import kraptis91.dblp.data.model.internal.Note;
import kraptis91.dblp.data.model.internal.Publication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/11/2020.
 */
public final class SchemaUtil {

    private static final Logger LOGGER = Logger.getLogger(SchemaUtil.class.getName());

    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(Publication.class, Note.class);
        } catch (JAXBException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Marshaller getMarshaller() throws JAXBException {
        Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.ISO_8859_1.name());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    public static Unmarshaller getUnmarshaller() throws JAXBException {
        return JAXB_CONTEXT.createUnmarshaller();
    }
}
