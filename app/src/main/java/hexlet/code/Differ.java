package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    static String getFormatFile(Path path) {
        String filename = path.getFileName().toString();
        int pointIndex = filename.lastIndexOf(".");
        return filename.substring(pointIndex + 1);
    }
    public static String generate(File file1, File file2, String format) throws Exception {
        Path path1 = Paths.get(file1.toURI()).toAbsolutePath().normalize();
        Path path2 = Paths.get(file2.toURI()).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + file1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }
        String formatFile = getFormatFile(path1);

        List<TreeMap> result = Parser.parse(file1, file2, formatFile);
        String formattedResult = Formatter.buildFormat(result, format);

        return formattedResult;
    }
}
