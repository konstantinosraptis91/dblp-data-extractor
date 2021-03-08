package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class XMLParserTest {

  //  private InputStream isBigXml;
  private final InputStream isSmallXml =
      XMLParserTest.class.getResourceAsStream("/part-of-xml.xml");

  public void setUp() throws FileNotFoundException {
    //    isBigXml =
    //        new FileInputStream(DirectoryUtils.getDefaultDownloadsDirectory() +
    // "/dblp-2020-10-01.xml");
  }

  /**
   * Use this way to get a chunk of the "big" xml or read a small xml as String.
   *
   * @throws Exception
   */
  @Disabled
  @Test
  public void testReadNBytesAsString() throws Exception {

    XMLParser xmlParser = new XMLParser();

    // String theXML = xmlParser.readNBytesAsString(isBigXml, 1024 * 10);
    String theXML = xmlParser.readNBytesAsString(isSmallXml, isSmallXml.available());

    System.out.println(theXML);
  }

  /**
   * Use this way to read the original "big" xml.
   *
   * @throws Exception
   */
  @Disabled
  @Test
  public void testExtractPublicationsPerYearWithStAX() throws Exception {

    XMLParser xmlParser = new XMLParser();

    PublicationsPerYearDto publicationsPerYearDto =
        xmlParser.extractPublicationsPerYearWithStAX(isSmallXml);
    publicationsPerYearDto.printYearMapInAscendingOrder();
  }

  /**
   * Use this way to read the original "big" xml.
   *
   * @throws Exception
   */
  @Test
  public void testExtractPublicationsPerYear() throws Exception {

    XMLParser xmlParser = new XMLParser();

    PublicationsPerYearDto publicationsPerYearDto =
        xmlParser.extractPublicationsPerYear(isSmallXml);
    publicationsPerYearDto.printYearMapInAscendingOrder();
  }

  /**
   * Use this way to read the original "big" xml.
   *
   * @throws Exception
   */
  @Test
  public void testExtractPublicationsPerYearWithAnchors() throws Exception {

    XMLParser xmlParser = new XMLParser();

    PublicationsPerYearDto publicationsPerYearDto =
        xmlParser.extractPublicationsPerYearWithAnchors(isSmallXml);
    publicationsPerYearDto.printYearMapInAscendingOrder();
  }
}
