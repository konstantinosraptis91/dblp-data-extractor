package kraptis91.dblp.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
public class ModelExtractorTest {

  private final String xmlString =
      "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!DOCTYPE dblp SYSTEM \"dblp-2019-11-22.dtd\">\n"
          + "<dblp>\n"
          + "    <phdthesis mdate=\"2016-05-04\" key=\"phd/dk/Heine2010\">\n"
          + "        <author>Carmen Heine</author>\n"
          + "        <title>Modell zur Produktion von Online-Hilfen.</title>\n"
          + "        <year>2010</year>\n"
          + "        <school>Aarhus University</school>\n"
          + "        <pages>1-315</pages>\n"
          + "        <isbn>978-3-86596-263-8</isbn>\n"
          + "        <ee>http://d-nb.info/996064095</ee>\n"
          + "    </phdthesis>\n"
          + "    <phdthesis mdate=\"2020-02-12\" key=\"phd/Hoff2002\">\n"
          + "        <author>Gerd Hoff</author>\n"
          + "        <title>Ein Verfahren zur thematisch spezialisierten Suche im Web und seine Realisierung im Prototypen HomePageSearch</title>\n"
          + "        <year>2002</year>\n"
          + "        <school>University of Trier, Germany</school>\n"
          + "        <ee>http://ubt.opus.hbz-nrw.de/volltexte/2004/146/</ee>\n"
          + "        <ee>https://nbn-resolving.org/urn:nbn:de:hbz:385-1468</ee>\n"
          + "        <ee>http://d-nb.info/971713243</ee>\n"
          + "    </phdthesis>\n"
          + "    <phdthesis mdate=\"2020-03-12\" key=\"phd/Seltzer92\">\n"
          + "        <author>Margo I. Seltzer</author>\n"
          + "        <title>File System Performance and Transaction Support.</title>\n"
          + "        <year>1992</year>\n"
          + "        <school>University of California at Berkeley</school>\n"
          + "        <ee type=\"oa\">http://db.cs.berkeley.edu/papers/ERL-M93-01.pdf</ee>\n"
          + "    </phdthesis>\n"
          + "    <phdthesis mdate=\"2020-02-12\" key=\"phd/Rothkugel2002\">\n"
          + "        <author>Steffen Rothkugel</author>\n"
          + "        <title>Towards Middleware Support for Mobile and Cellular Networks: core problems and illustrated approaches.</title>\n"
          + "        <year>2002</year>\n"
          + "        <school>Univ. Trier, FB 4, Informatik</school>\n"
          + "        <ee>http://ubt.opus.hbz-nrw.de/volltexte/2004/210/</ee>\n"
          + "        <ee>https://nbn-resolving.org/urn:nbn:de:hbz:385-2109</ee>\n"
          + "        <ee>http://d-nb.info/971737843</ee>\n"
          + "    </phdthesis>\n"
          + "    <phdthesis mdate=\"2002-01";

  @Test
  public void testExtractYearFromYearXMLElement() {
    // System.out.println(ModelExtractor.extractValueFromYearXMLElement("<year>2010</year>"));
    String year = ModelExtractor.extractValueFromYearXMLElementAsString("<year>2010</year>");
    Assert.assertNotNull(year);
    Assert.assertEquals("2010", year);
  }

  @Test
  public void testExtractYearList() throws Exception {
    List<String> yearList = ModelExtractor.extractYearList(xmlString);
    System.out.println(yearList);
  }

  @Test
  public void testCreateYearMap() throws Exception {
    List<String> yearList = ModelExtractor.extractYearList(xmlString);
    System.out.println(yearList);

    Map<String, Integer> yearMap = ModelExtractor.createYearMap(yearList);
    PublicationPrintUtils.printYearMap(yearMap);
  }
}
