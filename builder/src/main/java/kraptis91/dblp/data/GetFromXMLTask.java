package kraptis91.dblp.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.Library;
import kraptis91.dblp.data.model.Publication;
import kraptis91.dblp.data.schema.utils.SchemaUtil;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
public class GetFromXMLTask {

  public String readNBytesAsString(@NotNull InputStream xmlStream, @Min(256) int bytesToRead)
      throws IOException {

    InputStream bis = getBufferedInputStream(xmlStream);
    StringBuilder stringBuilder = new StringBuilder();

    byte[] contents = new byte[bytesToRead];
    int bytesRead;
    // bis.mark(bytesToRead);

    while ((bytesRead = bis.read(contents)) != -1) {

      stringBuilder.append(new String(contents, 0, bytesRead));

      if (bytesRead >= bytesToRead) {
        break;
      }
    }
    // bis.reset();
    // bis.close();

    // return clearCRLF(stringBuilder.toString());
    return stringBuilder.toString();
  }

  public String readFirstPHDThesisAsString(@NotNull InputStream xmlStream) throws IOException {
    String startElement = "<phdthesis>";
    String stopElement = "</phdthesis>";
    BufferedReader br = new BufferedReader(new InputStreamReader(xmlStream));

    StringBuilder sb = new StringBuilder();
    String currentLine;

    int numberOfLines = 0;

    while ((currentLine = br.readLine()) != null) {
      numberOfLines++;

      if (currentLine.contains(startElement)) {

        sb.append(currentLine);
        sb.append("\n");

      } else if (currentLine.contains(stopElement)) {

        sb.append(stopElement);
        sb.append("\n");
        break;
      }

      sb.append(currentLine);
      sb.append("\n");
    }
    br.close();
    System.out.println("Number of lines: " + numberOfLines);
    return sb.toString();
  }

  public Map<String, Integer> parsePublicationsPerYear(@NotNull InputStream xmlStream)
      throws IOException {

    Map<String, Integer> publicationMap = new HashMap<>();
    InputStream bis = getBufferedInputStream(xmlStream);
    int numberOfBytes = xmlStream.available();
    int chunk = 1024 * 24;

    // lets read 10 chunks
    for (int i = 0; i < 1000; i++) {
      String xmlChunk = readNBytesAsString(bis, chunk);
      List<String> yearList = ModelExtractor.extractYearList(xmlChunk);
      publicationMap.putAll(ModelExtractor.createYearMap(yearList));
    }

    bis.close();

    return publicationMap;
  }

  public static String clearCRLF(@NotNull String stringToClear) {
    return stringToClear.replace("\n", "").replace("\r", "");
  }

  public static InputStream getBufferedInputStream(@NotNull InputStream xmlStream) {
    // A marked input stream required
    InputStream bis;
    if (xmlStream.markSupported()) {
      bis = xmlStream;
    } else {
      bis = new BufferedInputStream(xmlStream);
    }
    return bis;
  }

  public Map<String, Integer> readPartiallyWithJaxb(InputStream is) throws Exception {
    final QName qName = new QName("year");

    InputStream bis = getBufferedInputStream(is);

    // create xml event reader for input stream
    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(bis);

    XMLEvent e = null;
    final Map<String, Integer> yearMap = new HashMap<>();

    // loop though the xml stream
    while ((e = xmlEventReader.peek()) != null) {

      // check the event is a Document start element
      if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {

        // unmarshall the document
        String year =
            SchemaUtil.getUnmarshaller().unmarshal(xmlEventReader, String.class).getValue();
        // System.out.println(year);

        if (!yearMap.containsKey(year)) {
          yearMap.put(year, 1);
        } else {
          yearMap.put(year, yearMap.get(year) + 1);
        }

      } else {
        xmlEventReader.next();
      }
    }
    bis.close();

    return yearMap;
  }
}
