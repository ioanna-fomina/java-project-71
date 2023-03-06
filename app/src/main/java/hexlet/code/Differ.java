package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    static Path getAbsolutePath(String str) {
        return Paths.get(str).toAbsolutePath().normalize();
    }
    public static String generate(String pathString1, String pathString2, String format) throws Exception {
        Path filepath1 = getAbsolutePath(pathString1);
        Path filepath2 = getAbsolutePath(pathString2);
        File file1 = filepath1.toFile();
        File file2 = filepath2.toFile();
        if (!Files.exists(filepath1)) {
            throw new Exception("File '" + file1 + "' does not exist");
        }
        if (!Files.exists(filepath2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }
        String formatFile = pathString1.substring(pathString1.lastIndexOf(".") + 1);

        TreeMap<String, Map<String, List<Object>>> tree = Parser.parse(file1, file2, formatFile);
        String formattedResult = Formatter.buildFormat(tree, format);

        return formattedResult;
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }
}
