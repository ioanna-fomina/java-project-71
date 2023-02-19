package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    static Map<String, Object> parse(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        Map<String, Object> result = new LinkedHashMap<>();

        for (String key: keys) {
            if (!data1.containsKey(key)) {
                result.put("+ " + key, data2.get(key));
            } else if (!data2.containsKey(key)) {
                result.put("- " + key, data1.get(key));
            } else if (data1.get(key).equals(data2.get(key))) {
                result.put("  " + key, data1.get(key));
            } else {
                result.put("- " + key, data1.get(key));
                result.put("+ " + key, data2.get(key));
            }
        }
        return result;
    }
    static Map<String, Object> getMap(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(file, Map.class);
        return map;
    }
    static String entryToString(Map.Entry<String, Object> item) {
        return item.getKey() + ": " + item.getValue().toString();
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
        Map<String, Object> result = parse(getMap(file1), getMap(file2));
        String resultString = result.entrySet().stream()
                .map(Differ::entryToString)
                .collect(Collectors.joining("\n  "));
        return "{\n" + resultString + "\n}";
    }
}
