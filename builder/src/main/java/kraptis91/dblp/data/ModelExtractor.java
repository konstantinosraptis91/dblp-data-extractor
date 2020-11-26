package kraptis91.dblp.data;

import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.PublicationsPerYearDto;

import java.util.Map;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
public class ModelExtractor {

  /**
   * Create a publications per year dto object from yearMap.
   *
   * @param yearMap
   * @return
   */
  public static PublicationsPerYearDto extractPublicationsPerYearDto(
      @NotNull Map<String, Integer> yearMap) {
    PublicationsPerYearDto publicationsPerYearDto = new PublicationsPerYearDto();
    publicationsPerYearDto.setYearMap(yearMap);
    return publicationsPerYearDto;
  }
}
