package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            new FileInputStream(SystemUtils.getUserDir()
                .getParentFile().getParent() +
                "/dblp-2021-02-01.xml");

        Assertions.assertNotNull(isBigXml);
    }

    @Test
    public void testExtractPublicationsPerYearWithStAXForTextList() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStAXForTextList(isBigXml,
                List.of("distributed"));
        dto.printYearMapInAscendingOrder();
        dto.printTotalPublications();
    }

    @Test
    public void testExtractTotalPublications() throws Exception {

        XMLStaxParser xmlParser = new XMLStaxParser();

        int phdthesis = xmlParser.extractTotalPublications(isBigXml);
        System.out.println("Total number of phdthesis: " + phdthesis);
    }

}
