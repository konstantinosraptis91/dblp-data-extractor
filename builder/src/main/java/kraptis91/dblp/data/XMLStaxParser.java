package kraptis91.dblp.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.Publication;
import kraptis91.dblp.data.model.PublicationsPerYearDto;
import kraptis91.dblp.data.model.schema.PhdThesis;
import kraptis91.dblp.data.schema.utils.SchemaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static kraptis91.dblp.data.model.schema.SchemaProperties.*;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 2/4/21.
 */
public class XMLStaxParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLStaxParser.class.getName());

    public static final Set<String> ELEMENT_SET = createElementSet();

    private static Set<String> createElementSet() {
        Set<String> elementSet = new HashSet<>();
        elementSet.add(PHD_THESIS);
        elementSet.add(ARTICLE);
        elementSet.add(INPROCEEDINGS);
        elementSet.add(PROCEEDINGS);
        elementSet.add(BOOK);
        elementSet.add(INCOLLECTION);
        elementSet.add(MASTER_THESIS);
        elementSet.add(WWW);
        elementSet.add(PERSON);
        elementSet.add(DATA);
        return elementSet;
    }

    public int extractTotalPublications(@NotNull InputStream xmlStream) throws Exception {

        int publications = 0;

        try (BufferedInputStream bis = new BufferedInputStream(xmlStream)) {

            // create xml event reader for input stream
            final QName pubQName = new QName("phdthesis");
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            final XMLEventReader reader = xmlInputFactory.createXMLEventReader(bis);
            XMLEvent xmlEvent;

            // loop though the xml stream
            while (reader.hasNext()) {

                xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()
                    && (xmlEvent.asStartElement()).getName().equals(pubQName)) {

                    publications++;
                }
            }

        }

        return publications;
    }

    /**
     * Create the publications per year dto by using StAX.
     *
     * @param xmlStream
     * @return
     * @throws Exception
     */
    public PublicationsPerYearDto extractPublicationsPerYearWithStAXForTextList(@NotNull InputStream xmlStream,
                                                                                @NotEmpty List<String> textList)
        throws Exception {

        final PublicationsPerYearDto dto = new PublicationsPerYearDto();

        try (BufferedInputStream bis = new BufferedInputStream(xmlStream)) {

            // create xml event reader for input stream
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            final XMLEventReader reader = xmlInputFactory.createXMLEventReader(bis);
            XMLEvent xmlEvent;
            Publication publication = null;
            int discarded = 0;

            // loop though the xml stream
            while (reader.hasNext()) {

                xmlEvent = reader.nextEvent();
                // check the event is a Document start element
                if (xmlEvent.isStartElement()) {

                    StartElement startElement = xmlEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {

                        case TITLE:
                            xmlEvent = reader.nextEvent();

                            try {
                                String title = xmlEvent.asCharacters().getData().trim();
                                // System.out.println("Title: " + title);

                                for (String text : textList) {
                                    // if (title.contains(text)) {
                                    if (title.toLowerCase().contains(text.trim().toLowerCase())) {
                                        // if (StringUtils.containsIgnoreCase(title, text)) {
                                        publication = new Publication();
                                        publication.setTitle(title);
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                discarded++;
                                // LOGGER.error(e.getMessage());
                            }
                            break;

                        case YEAR:
                            xmlEvent = reader.nextEvent();
                            if (!Objects.isNull(publication)) {
                                String year = xmlEvent.asCharacters().getData().trim();
                                publication.setYear(year);
                            }
                            break;
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (ELEMENT_SET.contains(endElement.getName().getLocalPart())
                        && !Objects.isNull(publication)) {
                        dto.putToYearMap(publication.getYear());
                        publication = null;
                    }
                }
            }

            System.out.println("Discarded phdthesis: " + discarded);
        }

        return dto;
    }

    /**
     * Create the publications per year dto by using StAX.
     *
     * @param xmlStream
     * @return
     * @throws Exception
     */
    public PublicationsPerYearDto extractPublicationsPerYearWithStAXForTextList2(@NotNull InputStream xmlStream,
                                                                                 @NotEmpty List<String> textList)
        throws Exception {

        final QName pubQName = new QName("phdthesis");

        final PublicationsPerYearDto dto = new PublicationsPerYearDto();

        try (BufferedInputStream bis = new BufferedInputStream(xmlStream)) {

            // create xml event reader for input stream
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            final XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bis);
            XMLEvent xmlEvent;
            int discarded = 0;

            // loop though the xml stream
            while ((xmlEvent = xmlEventReader.peek()) != null) {

                // check the event is a Document start element
                if (xmlEvent.isStartElement() && ((StartElement) xmlEvent).getName().equals(pubQName)) {

                    try {
                        // unmarshall the publication value
                        final PhdThesis phdThesis = SchemaUtil.getUnmarshaller()
                            .unmarshal(xmlEventReader, PhdThesis.class).getValue();

                        for (String text : textList) {
                            if (phdThesis.getTitle().contains(text)) {
                                dto.putToYearMap(phdThesis.getYear());
                                break;
                            }
                        }
                        // System.out.println(publication);
                    } catch (Exception ex) {
                        discarded++;
                        // LOGGER.error(ex.getMessage(), ex);
                    }

                } else {
                    xmlEventReader.next();
                }
            }
            System.out.println("Discarded phdthesis: " + discarded);
        }

        return dto;
    }

}
