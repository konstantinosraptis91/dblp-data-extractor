package kraptis91.dblp.data;

import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.PublicationsPerYearDto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 27/11/2020. */
public class ResultTextParser {

  /**
   * Create the publications per year dto by using BufferedReader.
   *
   * @param xmlStream
   * @return
   * @throws Exception
   */
  public PublicationsPerYearDto parsePublicationsDto(@NotNull InputStream xmlStream)
      throws Exception {

    final PublicationsPerYearDto publications = new PublicationsPerYearDto();
    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(xmlStream));

    String line;

    // loop though the xml stream
    while ((line = bufferedReader.readLine()) != null) {

      String[] results = line.split("\\s+");
      publications.getYearMap().put(results[0], Integer.parseInt(results[1]));
      // System.out.println(line);
    }
    // close stream
    bufferedReader.close();

    return publications;
  }
}
