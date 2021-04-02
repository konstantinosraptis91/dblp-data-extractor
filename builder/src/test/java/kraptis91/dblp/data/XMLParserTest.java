package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class XMLParserTest {

    private static InputStream isBigXml;
    private final InputStream isSmallXml =
        XMLParserTest.class.getResourceAsStream("/part-of-xml.xml");

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

        String theXML = xmlParser.readNBytesAsString(isSmallXml, 1024 * 10);
        // String theXML = xmlParser.readNBytesAsString(isSmallXml, isSmallXml.available());

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
    public void testExtractPublicationsPerYearWithAnchorsForTextList() throws Exception {

        XMLParser xmlParser = new XMLParser();

        PublicationsPerYearDto publicationsPerYearDto =
            xmlParser.extractPublicationsPerYearWithAnchorsForTextList(isBigXml,
                List.of("distributed"));
        publicationsPerYearDto.printYearMapInAscendingOrder();
    }

    @Test
    public void testExtractPublicationsPerYearWithStAXForTextList() throws Exception {

        XMLParser xmlParser = new XMLParser();

        PublicationsPerYearDto dto =
            xmlParser.extractPublicationsPerYearWithStAXForTextList(isSmallXml,
            List.of("distributed"));
        dto.printYearMapInAscendingOrder();
        dto.printTotalPublications();
    }

}
