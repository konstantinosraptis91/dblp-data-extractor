package kraptis91.dblp.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.PublicationsPerYearDto;
import kraptis91.dblp.data.schema.utils.SchemaUtil;
import kraptis91.dblp.data.utils.InputStreamUtils;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
public class XMLParser {

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

    InputStream bis = InputStreamUtils.getBufferedInputStream(xmlStream);
    StringBuilder stringBuilder = new StringBuilder();

    byte[] contents = new byte[bytesToRead];
    int bytesRead;

    while ((bytesRead = bis.read(contents)) != -1) {

      stringBuilder.append(new String(contents, 0, bytesRead));

      // stop reading when
      if (bytesRead >= bytesToRead) {
        break;
      }
    }
    // close stream
    bis.close();

    return stringBuilder.toString();
  }

  /**
   * Create the publications per year dto.
   *
   * @param xmlStream
   * @return
   * @throws Exception
   */
  public PublicationsPerYearDto extractPublicationsPerYear(@NotNull InputStream xmlStream)
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
}
