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
import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
     * Converts a local resource filename into a path dependent on the runtime
     * environment.
     *
     * @param filename The local path of the resource within /src/main/resources/.
     * @return An input stream of the file.
     */
    private static InputStream filenameToStream(String filename) {
        return Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(filename);
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

        try (BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(xmlStream, StandardCharsets.ISO_8859_1))) {
            // new InputStreamReader(
            // new FileInputStream("/Users/kraptis/Documents/Java Projects/dblp/dblp-data-extractor/builder/src/main/resources/part-of-xml.xml"),
            // StandardCharsets.ISO_8859_1))) {
            // "ISO-8859-1"))) {

            // create xml event reader for input stream
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setXMLResolver(
                (publicID, systemID, baseURI, namespace) -> {
                    System.out.println("SystemID: " + systemID);
                    return XMLStaxParser.class.getResourceAsStream("/dblp-2019-11-22.dtd");
                    //return XMLStaxParser.filenameToStream(systemID);
                    // return XMLStaxParser.filenameToStream(
                    //     "/Users/kraptis/Documents/Java Projects/dblp/dblp-data-extractor/builder/src/main/resources/dblp-2019-11-22.dtd");
                });
            // final XMLEventReader reader = xmlInputFactory.createXMLEventReader(bufferedReader);
            final XMLEventReader reader = xmlInputFactory.createXMLEventReader(xmlStream, StandardCharsets.ISO_8859_1.name());
            XMLEvent xmlEvent;
            Publication publication = null;
            String author = null;
            int discarded = 0;

            // loop though the xml stream
            while (reader.hasNext()) {

                xmlEvent = reader.nextEvent();
                // check the event is a Document start element
                if (xmlEvent.isStartElement()) {

                    StartElement startElement = xmlEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {

                        case AUTHOR:
                            xmlEvent = reader.nextEvent();

                            try {
                                author = xmlEvent.asCharacters().getData().trim();
                                System.out.println("Author: " + author);

                            } catch (Exception e) {
                                // discarded++;
                                // LOGGER.error(e.getMessage());
                            }
                            break;

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
                        publication.setAuthor(author);
                        dto.addPublication(publication);
                        publication = null;
                        author = null;
                    }
                }
            }

            System.out.println("Discarded elements: " + discarded);
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

    /**
     * Create the publications per year dto by using StAX.
     *
     * @param xmlStream
     * @return
     * @throws Exception
     */
    public PublicationsPerYearDto extractPublicationsPerYearWithStAXForTextList3(@NotNull InputStream xmlStream,
                                                                                 @NotEmpty List<String> textList)
        throws Exception {

        final PublicationsPerYearDto dto = new PublicationsPerYearDto();
        // create xml event reader for input stream
        final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setXMLResolver(
            (publicID, systemID, baseURI, namespace) -> {
                System.out.println("SystemID: " + systemID);
                // return XMLStaxParser.class.getResourceAsStream("/dblp-2019-11-22.dtd");
                return XMLStaxParser.filenameToStream(systemID);
                // return XMLStaxParser.filenameToStream(
                //    "/Users/kraptis/Documents/Java Projects/dblp/dblp-data-extractor/builder/src/main/resources/dblp-2019-11-22.dtd");
            });
        final XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlStream, StandardCharsets.ISO_8859_1.name());
        Publication publication = null;
        String author = null;
        int discarded = 0;

        // loop though the xml stream
        while (xmlStreamReader.hasNext()) {

            int xmlEvent = xmlStreamReader.next();
            // check the event is a Document start element
            if (xmlEvent == XMLStreamConstants.START_ELEMENT) {

                switch (xmlStreamReader.getLocalName()) {

                    case AUTHOR:

                        try {
                            author = xmlStreamReader.getElementText().trim();
                            // System.out.println("Author: " + author);

                        } catch (XMLStreamException e) {
                            // discarded++;
                            // LOGGER.error(e.getMessage());
                        }
                        break;

                    case TITLE:

                        try {
                            String title = xmlStreamReader.getElementText().trim();
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

                        if (!Objects.isNull(publication)) {
                            String year = xmlStreamReader.getElementText().trim();
                            publication.setYear(year);
                        }
                        break;
                }
            }

            if (xmlEvent == XMLStreamConstants.END_ELEMENT) {

                if (ELEMENT_SET.contains(xmlStreamReader.getLocalName())
                    && !Objects.isNull(publication)) {

                    dto.putToYearMap(publication.getYear());
                    publication.setAuthor(author);
                    dto.addPublication(publication);
                    publication = null;
                    author = null;
                }
            }
        }

        xmlStreamReader.close();
        xmlStream.close();
        System.out.println("Discarded elements: " + discarded);

        return dto;
    }

}
