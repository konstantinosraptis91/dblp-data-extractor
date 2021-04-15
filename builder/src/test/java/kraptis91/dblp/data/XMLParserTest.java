package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import kraptis91.dblp.data.schema.utils.SchemaUtil;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class XMLParserTest {

    private static InputStream isBigXml;
    private final InputStream isSmallXml =
        XMLParserTest.class.getResourceAsStream("/part-of-xml.xml");
    private final InputStream isBiggerXml =
        XMLParserTest.class.getResourceAsStream("/isBigger.xml");

    @BeforeAll
    public static void setUp() throws FileNotFoundException {
        String fileName = SystemUtils.getUserDir()
            .getParentFile().getParent() +
            "/dblp-2021-02-01.xml";
        System.out.println("FileName: " + fileName);

        isBigXml =
            new FileInputStream(SystemUtils.getUserDir()
                .getParentFile().getParent() +
                "/dblp-2021-02-01.xml");

        Assertions.assertNotNull(isBigXml);
    }

    /**
     * Use this way to get a chunk of the "big" xml or read a small xml as String.
     *
     * @throws Exception
     */
    // @Disabled
    @Test
    public void testReadNBytesAsString() throws Exception {

        XMLParser xmlParser = new XMLParser();

        String theXML = xmlParser.readNBytesAsString(isBigXml, 1024 * 1000 * 110);
        // String theXML = xmlParser.readNBytesAsString(isSmallXml, isSmallXml.available());

        String filename = "publications_text_plain.xml";
        FileOutputStream os = new FileOutputStream(
            SystemUtils.getUserHome() + "/Downloads/" + filename);
        os.write(theXML.getBytes(StandardCharsets.UTF_8));

        // System.out.println(theXML);
    }

    @Test
    public void testPrintLinesAfterStartAnchor() throws Exception {

        XMLParser xmlParser = new XMLParser();

        xmlParser.printLinesAfterStartAnchor(isBigXml);
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
            xmlParser.extractPublicationsPerYear(isBigXml);
        publicationsPerYearDto.printYearMapInAscendingOrder();
    }

    /**
     * Use this way to read the original "big" xml.
     *
     * @throws Exception
     */
    @Test
    public void testExtractPublicationsPerYearWithAnchorsForTextList() throws Exception {

        XMLParser xmlParser = new XMLParser();

        PublicationsPerYearDto publicationsPerYearDto =
            xmlParser.extractPublicationsPerYearWithAnchorsForTextList(isSmallXml,
                List.of("distributed"));
        publicationsPerYearDto.printYearMapInAscendingOrder();
    }

    @Test
    public void testExtractPublicationsPerYearWithStartAnchorsForTextList() throws Exception {

        XMLParser xmlParser = new XMLParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStartAnchorsForTextList(isBigXml,
                List.of(
//                    "distributed computing",
//                    "distributed computation",
//                    "distributed calculation",
//                    "distributed data processing",
//                    "SOA-based system",
//                    "distributed programming",
//                    "distributed system",
//                    "distributed computer",
//                    "distributed machine",
//                    "distributed algorithm",
//                    "horizontal scalability",
//                    "teleprocessing",
//                    "distributed database",
//                    "distributed rendering",
//                    "distributed image processing",
//                    "distributed video processing",
//                    "distributed processing",
//                    "distributed analytics",
//                    "distributed application",
//                    "distributed architecture",
//                    "hdfs",
                    // "hadoop",
//                    "scala",
                    // "spark",
//                    "5 v of data",
//                    "java RMI"
                    "distributed"

                ));
        dto.printYearMapInAscendingOrder();
        // dto.printCSV();

        String filename = "publication.xml";
        FileOutputStream os = new FileOutputStream(
             SystemUtils.getUserHome() + "/Downloads/" + filename);

        // SchemaUtil.getMarshaller().marshal(dto.getPublications(), System.out);
        XMLPublicationsWriter writer = new XMLPublicationsWriter(dto.getPublications());
        writer.createXML(os);
        dto.printTotalPublications();
    }

    @Test
    public void testExtractTotalLines() throws Exception {

        XMLParser xmlParser = new XMLParser();

        int lines = xmlParser.extractTotalLines(isBigXml);
        System.out.println("Total number of lines: " + lines);
    }

    @Test
    public void testExtractTotalPublications() throws Exception {

        XMLParser xmlParser = new XMLParser();

        int phdthesis = xmlParser.extractTotalPublications(isBigXml);
        System.out.println("Total number of phdthesis: " + phdthesis);
    }

}
