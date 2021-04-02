package kraptis91.dblp.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.PublicationsPerYearDto;
import kraptis91.dblp.data.model.internal.Publication;
import kraptis91.dblp.data.schema.utils.SchemaUtil;
import kraptis91.dblp.data.utils.InputStreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020.
 */
public class XMLParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLParser.class.getName());

    /**
     * Create a String representation of the xml InputStream for the specified bytes.
     *
     * @param xmlStream
     * @param bytesToRead
     * @return
     * @throws IOException
     */
    public String readNBytesAsString(@NotNull InputStream xmlStream, @Min(256) int bytesToRead)
        throws IOException {

        final StringBuilder stringBuilder = new StringBuilder();
        final byte[] contents = new byte[bytesToRead];
        int bytesRead;

        try (BufferedInputStream bis = new BufferedInputStream(xmlStream)) {

            while ((bytesRead = bis.read(contents)) != -1) {

                stringBuilder.append(new String(contents, 0, bytesRead, StandardCharsets.ISO_8859_1));

                // stop reading when
                if (bytesRead >= bytesToRead) {
                    break;
                }
            }

        }
        return stringBuilder.toString();
    }

    /**
     * Create the publications per year dto by using StAX.
     *
     * @param xmlStream
     * @return
     * @throws Exception
     */
    public PublicationsPerYearDto extractPublicationsPerYearWithStAX(@NotNull InputStream xmlStream)
        throws Exception {

        final QName qName = new QName("year");
        final PublicationsPerYearDto publications = new PublicationsPerYearDto();
        InputStream bis = InputStreamUtils.getBufferedInputStream(xmlStream);

        // create xml event reader for input stream
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bis);
        XMLEvent e = null;

        // loop though the xml stream
        while ((e = xmlEventReader.peek()) != null) {

            // check the event is a Document start element
            if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {

                // unmarshall the year value
                String year =
                    SchemaUtil.getUnmarshaller().unmarshal(xmlEventReader, String.class).getValue();
                // System.out.println(year);
                publications.putToYearMap(year);

            } else {
                xmlEventReader.next();
            }
        }
        // close stream
        bis.close();

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

        final QName pubQName = new QName("phdthesis");

        final PublicationsPerYearDto dto = new PublicationsPerYearDto();

        try (BufferedInputStream bis = new BufferedInputStream(xmlStream)) {

            // create xml event reader for input stream
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            final XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bis);
            XMLEvent xmlEvent;

            // loop though the xml stream
            while ((xmlEvent = xmlEventReader.peek()) != null) {

                // check the event is a Document start element
                if (xmlEvent.isStartElement() && ((StartElement) xmlEvent).getName().equals(pubQName)) {

                    try {
                        // unmarshall the publication value
                        final Publication publication = SchemaUtil.getUnmarshaller()
                            .unmarshal(xmlEventReader, Publication.class).getValue();

                        for (String text : textList) {
                            if (publication.getTitle().contains(text)) {
                                dto.putToYearMap(publication.getYear());
                                break;
                            }
                        }
                        // System.out.println(publication);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }

                } else {
                    xmlEventReader.next();
                }
            }
        }

        return dto;
    }

    /**
     * Create the publications per year dto by using BufferedReader.
     *
     * @param xmlStream
     * @return
     * @throws Exception
     */
    public PublicationsPerYearDto extractPublicationsPerYear(@NotNull InputStream xmlStream)
        throws Exception {

        final PublicationsPerYearDto publications = new PublicationsPerYearDto();
        final BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(xmlStream), 16384);

        String line;

        // loop though the xml stream
        while ((line = bufferedReader.readLine()) != null) {

            if (line.contains("<year>")) {
                String year = line.substring(line.indexOf("<year>") + 6, line.indexOf("</year>"));
                // System.out.println(year);
                publications.putToYearMap(year);
            }

            // System.out.println(line);
        }
        // close stream
        bufferedReader.close();

        return publications;
    }

    /**
     * Create the publications per year dto by using BufferedReader.
     *
     * @param xmlStream The xml stream
     * @return
     * @throws IOException
     */
    public PublicationsPerYearDto extractPublicationsPerYearWithAnchorsForTextList(@NotNull InputStream xmlStream,
                                                                                   @NotEmpty List<String> textList)
        throws IOException {

        final PublicationsPerYearDto publications = new PublicationsPerYearDto();

        try (final BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(xmlStream), 16384)) {

            String line;
            final String yearStartAnchor = "<year>";
            final String yearEndAnchor = "</year>";
            final String titleStartAnchor = "<title>";
            final String titleEndAnchor = "</title>";

            // loop though the xml stream
            while ((line = bufferedReader.readLine()) != null) {

                // This is the title line
                if (line.contains("<title>")) {

                    // Check if the title thematic area match
                    for (String text : textList) {

                        int titleStart = line.indexOf(titleStartAnchor) + titleStartAnchor.length();
                        int titleEnd = line.indexOf(titleEndAnchor);
                        String title = line.substring(titleStart, titleEnd);

                        String nextLine;
                        if (title.contains(text)
                            && (nextLine = bufferedReader.readLine()) != null) {

                            // Get the next line, which is the year
                            int yearStart = nextLine.indexOf(yearStartAnchor) + yearStartAnchor.length();
                            int yearEnd = nextLine.indexOf(yearEndAnchor);
                            String year = nextLine.substring(yearStart, yearEnd);
                            publications.putToYearMap(year);

                            break;
                        }
                    }
                }
            }
        }

        return publications;
    }
}
