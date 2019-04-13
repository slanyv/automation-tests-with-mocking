package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

    public static String readFileAsString(String relativePath) {

        Path currentDir = Paths.get("");
        String absolutePath = currentDir.toAbsolutePath() + relativePath;

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(absolutePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
