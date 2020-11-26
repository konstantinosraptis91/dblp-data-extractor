package kraptis91.dblp.data.model;

import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 26/11/2020. */
public class PublicationsPerYearDto {

  private Map<String, Integer> yearMap;

  public void setYearMap(Map<String, Integer> yearMap) {
    this.yearMap = yearMap;
  }

  public Map<String, Integer> getYearMap() {
    if (yearMap == null) {
      yearMap = new HashMap<>();
    }
    return yearMap;
  }

  public void printYearMap() {
    System.out.printf("%s %s\n", "Year", "Number of publications");
    getYearMap().forEach((k, v) -> System.out.printf("%s %d\n", k, v));
  }

  public void putToYearMap(@NotNull String year) {
    if (!getYearMap().containsKey(year)) {
      getYearMap().put(year, 1);
    } else { // year + 1
      getYearMap().put(year, getYearMap().get(year) + 1);
    }
  }

  public void printYearMapInAscendingOrder() {
    System.out.printf("%s %s\n", "Year", "Number of publications");
    getYearMap().entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new))
        .forEach((k, v) -> System.out.printf("%s %d\n", k, v));
  }

  public static void printYearMap(Map<String, Integer> yearMap) {
    System.out.printf("%s %s\n", "Year", "Number of publications");
    yearMap.forEach((k, v) -> System.out.printf("%s %d\n", k, v));
  }
}
