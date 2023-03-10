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
    static Map<String, Object> getData(String pathString) throws Exception {
        Path filepath = getAbsolutePath(pathString);
        File file = filepath.toFile();
        if (!Files.exists(filepath)) {
            throw new Exception("File '" + file + "' does not exist");
        }
        String formatFile = pathString.substring(pathString.lastIndexOf(".") + 1);
        return Parser.parse(file, formatFile);
    }
    public static String generate(String pathString1, String pathString2, String format) throws Exception {
        Map<String, Object> data1 = getData(pathString1);
        Map<String, Object> data2 = getData(pathString2);

        TreeMap<String, Map<String, List<Object>>> tree = Tree.genDifferences(data1, data2);
        String formattedResult = Formatter.format(tree, format);

        return formattedResult;
    }
    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }
}
