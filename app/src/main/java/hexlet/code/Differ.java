package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

public class Differ {
    public static String generate(String pathString1, String pathString2, String format) throws Exception {
        Path filepath1 = Paths.get(pathString1).toAbsolutePath().normalize();
        Path filepath2 = Paths.get(pathString2).toAbsolutePath().normalize();
        File file1 = filepath1.toFile();
        File file2 = filepath2.toFile();
        if (!Files.exists(filepath1)) {
            throw new Exception("File '" + file1 + "' does not exist");
        }
        if (!Files.exists(filepath2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }
        String formatFile = pathString1.substring(pathString1.lastIndexOf(".") + 1);

        List<TreeMap> result = Parser.parse(file1, file2, formatFile);
        String formattedResult = Formatter.buildFormat(result, format);

        return formattedResult;
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }
}
