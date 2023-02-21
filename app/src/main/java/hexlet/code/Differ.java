package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import java.util.stream.Collectors;

public class Differ {
    static String entryToString(Map.Entry<String, Object> item) {
        String key = item.getKey();
        String value = "";
        if (item.getValue() == null) {
            value = "null";
        } else {
            value = item.getValue().toString();
        }
        return key + ": " + value;
    }
    /*static Path getFile(Map<String, Object> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("resultJson.json"), map);
        return Path.of("resultJson.json");
    }*/
    static String getFormat(Path path) {
        String filename = path.getFileName().toString();
        int pointIndex = filename.lastIndexOf(".");
        return filename.substring(pointIndex + 1);
    }
    public static String generate(File file1, File file2) throws Exception {
        Path path1 = Paths.get(file1.toURI()).toAbsolutePath().normalize();
        Path path2 = Paths.get(file2.toURI()).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + file1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }
        String formatFile = getFormat(path1);

        Map<String, Object> result = Parser.parse(file1, file2, formatFile);
        String resultString = result.entrySet().stream()
                .map(Differ::entryToString)
                .collect(Collectors.joining("\n  "));
        return "{\n  " + resultString + "\n}";
        //return Files.readString(getFile(result));
    }
}
