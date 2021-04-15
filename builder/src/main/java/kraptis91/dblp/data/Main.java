package kraptis91.dblp.data;

import com.google.common.io.Files;
import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.core.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 14/4/21.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {

        System.setProperty("entityExpansionLimit", "10000000");

        try {
            String fileName = "/Users/kraptis/Documents/Java Projects/dblp/dblp-2021-02-01.xml";
            File file = new File(fileName);
            InputStream isBigXML = Files.asByteSource(file).openStream();

            InputStream isSmallXml =
                Main.class.getResourceAsStream("/sup-part-of-xml.xml");

            InputStream isBiggerXml =
                Main.class.getResourceAsStream("/isBigger.xml");


            XMLStaxParser xmlParser = new XMLStaxParser();

            PublicationsPerYearDto dto =
                xmlParser.extractPublicationsPerYearWithStAXForTextList4(isBigXML,
                    // List.of("database", "spezialisierten", "penning"));
                    List.of(
                        "distributed architectures",
                        "distributed computing",
                        "distributed computation",
                        "distributed calculation",
                        "distributed data processing",
                        "distributed processing",
                        "SOA-based system",
                        "distributed programming",
                        "distributed system",
                        "distributed computer",
                        "distributed machine",
                        "distributed algorithm",
                        "horizontal scalability",
                        "teleprocessing",
                        "distributed database",
                        "distributed rendering",
                        "distributed image processing",
                        "distributed video processing",
                        "distributed processing",
                        "distributed analytics",
                        "distributed application",
                        "distributed architecture",
                        "distributed service",
                        "distributed microservice",
                        "distributed operating system",
                        "distributed memory",
                        "distributed information systems",
                        "distributed storage system",
                        "distributed file system",
                        "distributed multimedia applications",
                        "distributed framework",
                        "distributed real-time system",
                        "distributed ada",
                        "CORBA"
                    ));

            String filename = "publications_stax.xml";
            FileOutputStream os = new FileOutputStream(
                SystemUtils.getUserHome() + "/Downloads/" + filename);

            XMLPublicationsWriter writer = new XMLPublicationsWriter(dto.getPublications());
            // writer.createXML(System.out);
            writer.createXML(os);
            os.close();

            // dto.printYearMapInAscendingOrder();
            dto.printTotalPublications();
            dto.printCSV();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
