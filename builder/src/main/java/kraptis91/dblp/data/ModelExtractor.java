package kraptis91.dblp.data;

import jakarta.validation.constraints.NotNull;
import kraptis91.dblp.data.model.Publication;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020. */
public class ModelExtractor {

  private static final String EXTRACT_YEAR_REGEX = ".*<\\S*year.*?>(.*?)</\\S*year>.*";

  public static String extractValueFromYearXMLElementAsString(String xmlString) {

    Matcher matcher =
        Pattern.compile(EXTRACT_YEAR_REGEX, Pattern.DOTALL & Pattern.MULTILINE).matcher(xmlString);

    if (matcher.find()) {
      final String year = matcher.group(1);
      return year;
    }

    return null;
  }

  public static Publication extractValueFromYearXMLElement(String xmlString) {
    String year = extractValueFromYearXMLElementAsString(xmlString);
    return year != null ? new Publication(year) : null;
  }

  public static List<String> extractYearList(@NotNull String xmlString) {

    Matcher matcher =
        Pattern.compile(EXTRACT_YEAR_REGEX, Pattern.DOTALL & Pattern.MULTILINE).matcher(xmlString);

    final List<String> yearList = new ArrayList<>();

    while (matcher.find()) {
      for (int i = 1; i <= matcher.groupCount(); i++) {
        yearList.add(matcher.group(i));
      }
    }

    return yearList;
  }

  public static Map<String, Integer> createYearMap(@NotNull List<String> yearList) {
    final Map<String, Integer> yearMap = new HashMap<>();

    for (String year : yearList) {

      if (!yearMap.containsKey(year)) {
        yearMap.put(year, 1);
      } else {
        yearMap.put(year, yearMap.get(year) + 1);
      }
    }

    return yearMap;
  }
}
