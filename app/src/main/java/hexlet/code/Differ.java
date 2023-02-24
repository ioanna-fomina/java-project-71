package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.TreeMap;

public class Differ {
    static String getFormatFile(Path path) {
        String filename = path.getFileName().toString();
        int pointIndex = filename.lastIndexOf(".");
        return filename.substring(pointIndex + 1);
    }
    public static String generate(Path path1, Path path2, String format) throws Exception {
        File file1 = path1.toFile();
        File file2 = path2.toFile();
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
