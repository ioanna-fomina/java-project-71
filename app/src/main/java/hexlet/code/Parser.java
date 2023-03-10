package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Parser {
    static Map<String, Object> parseJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Map.class);
    }
    static Map<String, Object> parseYaml(File file) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(file, Map.class);
    }

    public static Map<String, Object> parse(File file, String formatFile) throws Exception {
        Map<String, Object> data;
        switch (formatFile) {
            case "json" -> {
                data = parseJson(file);
            }
            case "yml" -> {
                data = parseYaml(file);
            }
            default -> throw new Exception(String.format("unknown file format: %s", formatFile));
        }
        return data;
    }
}
