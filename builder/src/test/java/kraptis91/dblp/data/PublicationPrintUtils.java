package kraptis91.dblp.data;

import java.util.Map;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 25/11/2020.
 */
public class PublicationPrintUtils {

    public static void printYearMap(Map<String, Integer> yearMap) {
        yearMap.forEach((k, v) -> System.out.printf("%s %d\n", k, v));
    }

}
