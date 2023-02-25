package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

public class Differ {
    static String getFormatFile(Path path) {
        String filename = path.getFileName().toString();
        int pointIndex = filename.lastIndexOf(".");
        return filename.substring(pointIndex + 1);
    }
    public static String generate(String path1, String path2, String format) throws Exception {
        Path filepath1 = Paths.get(path1).toAbsolutePath().normalize();
        Path filepath2 = Paths.get(path2).toAbsolutePath().normalize();
        File file1 = filepath1.toFile();
        File file2 = filepath2.toFile();
        if (!Files.exists(filepath1)) {
            throw new Exception("File '" + file1 + "' does not exist");
        }
        if (!Files.exists(filepath2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }
        String formatFile = getFormatFile(filepath1);

        List<TreeMap> result = Parser.parse(file1, file2, formatFile);
        String formattedResult = Formatter.buildFormat(result, format);

        return formattedResult;
    }
}
