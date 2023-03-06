package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Parser {
    static Map<String, Object> parseJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Map.class);
    }
    static Map<String, Object> parseYaml(File file) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(file, Map.class);
    }

    public static TreeMap<String, Map<String, List<Object>>> parse(
            File file1, File file2, String formatFile) throws Exception {
        Map<String, Object> data1;
        Map<String, Object> data2;
        switch (formatFile) {
            case "json" -> {
                data1 = parseJson(file1);
                data2 = parseJson(file2);
            }
            case "yml" -> {
                data1 = parseYaml(file1);
                data2 = parseYaml(file2);
            }
            default -> throw new Exception(String.format("unknown file format: %s", formatFile));
        }

        Set<String> keys = new HashSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        return Tree.genDifferences(keys, data1, data2);
    }
}
