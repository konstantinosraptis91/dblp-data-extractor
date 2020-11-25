package kraptis91.dblp.data;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class GetFromXMLTaskTest {

  private InputStream is;
  private InputStream isSmallXml;

  @Before
  public void setUp() throws FileNotFoundException {

    is =
        new FileInputStream(
            Paths.get("C:\\Users\\konst\\Downloads\\dblp-2020-10-01.xml").toString());

    isSmallXml =
        new FileInputStream(Paths.get("C:\\Users\\konst\\Downloads\\part-of-xml-2.xml").toString());
  }

  @Test
  public void testReadNBytesAsString() throws Exception {

    GetFromXMLTask getFromXMLTask = new GetFromXMLTask();

    String theXML = getFromXMLTask.readNBytesAsString(is, 1024 * 10 * 10);

    System.out.println(theXML);
  }

  @Test
  public void testReadFirstPHDThesisAsString() throws Exception {

    GetFromXMLTask getFromXMLTask = new GetFromXMLTask();

    String theXML = getFromXMLTask.readFirstPHDThesisAsString(is);

    System.out.println(theXML);
  }

  @Test
  public void testParsePublicationsPerYearForSmallXML() throws Exception {
    GetFromXMLTask getFromXMLTask = new GetFromXMLTask();

    String xmlString = getFromXMLTask.readNBytesAsString(isSmallXml, isSmallXml.available());
    List<String> yearList = ModelExtractor.extractYearList(xmlString);
    System.out.println("Size of year list: " + yearList.size());
    Map<String, Integer> yearMap = ModelExtractor.createYearMap(yearList);
    PublicationPrintUtils.printYearMap(yearMap);

    int total = 0;

    for (String year : yearMap.keySet()) {
      total += yearMap.get(year);
    }

    System.out.println("Total: " + total);
  }

  @Test
  public void testReadPartiallyWithXML() throws Exception {
    GetFromXMLTask getFromXMLTask = new GetFromXMLTask();
    Map<String, Integer> yearMap = getFromXMLTask.readPartiallyWithJaxb(is);
    PublicationPrintUtils.printYearMap(yearMap);
  }
}
