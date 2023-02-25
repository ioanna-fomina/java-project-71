package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class FormatterJson {
    static void fieldToJson(JsonGenerator generator, String key, String type,
                            Object value1, Object value2) throws Exception {
        generator.writeObjectField("key", key);
        generator.writeObjectField("type", type);
        if (type.equals("changed")) {
            generator.writeObjectField("value1", value1);
            generator.writeObjectField("value2", value2);
        } else {
            generator.writeObjectField("value", value1);
        }
    }
    static void fieldToJson(JsonGenerator generator, String key, String type, Object value) throws Exception {
        fieldToJson(generator, key, type, value, "0");
    }
    public static String json(TreeMap<String, String> keys, Map<String, Object> data1,
                              Map<String, Object> data2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("result.json");
        JsonGenerator generator = mapper.createGenerator(jsonFile, JsonEncoding.UTF8);

        generator.writeRaw("[");
        for (Map.Entry<String, String> entry: keys.entrySet()) {
            generator.writeStartObject();
            String key = entry.getKey();
            switch (entry.getValue()) {
                case "added" -> fieldToJson(generator, key, "added", data2.get(key));
                case "removed" -> fieldToJson(generator, key, "deleted", data1.get(key));
                case "unchanged" -> fieldToJson(generator, key, "unchanged", data1.get(key));
                case "updated" -> fieldToJson(generator, key, "changed", data1.get(key), data2.get(key));
                default -> { }
            }
            generator.writeEndObject();
            if (!key.equals(keys.lastKey())) {
                generator.writeRaw(",");
            }
        }
        generator.writeRaw("]");
        generator.writeRaw("\n");
        generator.close();
        Path filepath = Paths.get(jsonFile.toURI()).toAbsolutePath().normalize();
        return Files.readString(filepath);
    }
}
