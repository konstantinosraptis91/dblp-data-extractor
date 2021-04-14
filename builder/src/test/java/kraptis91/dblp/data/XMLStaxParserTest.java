package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 2/4/21.
 */
public class XMLStaxParserTest {

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
            new FileInputStream(fileName);

        Assertions.assertNotNull(isBigXml);
    }

    @Test
    public void testExtractPublicationsPerYearWithStAXForTextList() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStAXForTextList(isSmallXml,
                List.of("Database"));

        String filename = "publications_stax.xml";
        FileOutputStream os = new FileOutputStream(
            SystemUtils.getUserHome() + "/Downloads/" + filename);

        XMLPublicationsWriter writer = new XMLPublicationsWriter(dto.getPublications());
        // writer.createXML(System.out);
        writer.createXML(os);

        // dto.printYearMapInAscendingOrder();
        // dto.printTotalPublications();
    }

    @Test
    public void testExtractTotalPublications() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        int phdthesis = xmlParser.extractTotalPublications(isSmallXml);
        System.out.println("Total number of phdthesis: " + phdthesis);
    }

    @Test
    public void testExtractPublicationsPerYearWithStAXForTextList2() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStAXForTextList2(isBiggerXml,
                List.of("distributed"));
        dto.printYearMapInAscendingOrder();
        dto.printTotalPublications();
    }

    @Test
    public void testExtractPublicationsPerYearWithStAXForTextList3() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStAXForTextList3(isBiggerXml,
                List.of("Database"));

        String filename = "publications_stax.xml";
        FileOutputStream os = new FileOutputStream(
            SystemUtils.getUserHome() + "/Downloads/" + filename);

        XMLPublicationsWriter writer = new XMLPublicationsWriter(dto.getPublications());
        // writer.createXML(System.out);
        writer.createXML(os);

        // dto.printYearMapInAscendingOrder();
        // dto.printTotalPublications();
    }

}
