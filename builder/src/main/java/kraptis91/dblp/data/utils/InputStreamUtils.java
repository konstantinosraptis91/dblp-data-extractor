package kraptis91.dblp.data.utils;

import jakarta.validation.constraints.NotNull;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/11/2020.
 */
public class InputStreamUtils {

    public static InputStream getBufferedInputStream(@NotNull InputStream xmlStream) {

        InputStream bis;
        if (xmlStream.markSupported()) {
            bis = xmlStream;
        } else {
            bis = new BufferedInputStream(xmlStream);
        }
        return bis;
    }

}
