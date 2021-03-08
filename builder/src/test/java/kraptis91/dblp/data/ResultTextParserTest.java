package kraptis91.dblp.data;

import kraptis91.dblp.data.model.PublicationsPerYearDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 27/11/2020. */
public class ResultTextParserTest {

  InputStream isFast = ResultTextParserTest.class.getResourceAsStream("/results/fast.txt");
  InputStream isSlow = ResultTextParserTest.class.getResourceAsStream("/results/slow.txt");

  /**
   * Check if results are the same.
   *
   * @throws Exception
   */
  @Test
  public void testExtractPublicationsPerYear() throws Exception {

    ResultTextParser textParser = new ResultTextParser();
    PublicationsPerYearDto fastDto = textParser.parsePublicationsDto(isFast);
    PublicationsPerYearDto slowDto = textParser.parsePublicationsDto(isSlow);

    Assertions.assertEquals(slowDto.getYearMap(), fastDto.getYearMap());
  }
}
