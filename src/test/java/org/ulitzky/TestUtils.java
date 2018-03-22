package org.ulitzky;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * Created by lulitzky on 21.03.18.
 */
public class TestUtils {

    public static String createTestInputFile(final long numberOfLines) throws IOException {
        File tempFile = File.createTempFile("tempFile-", ".txt");
        tempFile.deleteOnExit();
        Path path = Paths.get(tempFile.getAbsolutePath());

        for(int i=1; i <= numberOfLines; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("name").append(i).append("@testmail.com").append(";");
            sb.append("firstName").append(i).append(";");
            sb.append("lastName").append(i).append(";");
            Files.write(path, Arrays.asList(sb.toString()), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        }

        return tempFile.getAbsolutePath();
    }
}
