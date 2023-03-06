package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JsonFormatter {
    static void fieldToJson(StringBuilder diff, String key, String type,
                            Object value1, Object value2) throws Exception {
        diff.append("\"key\":\"" + key + ",\"type\":\"" + type + "\",");
        if (type.equals("changed")) {
            diff.append("\"value1\":\"" + value1.toString() + "\",");
            diff.append("\"value2\":\"" + value2.toString() + "\",");
        } else {
            diff.append("\"value\":\"" + value1.toString() + "\"");
        }
    }
    static void fieldToJson(StringBuilder diff, String key, String type, Object value) throws Exception {
        fieldToJson(diff, key, type, value, "");
    }
    public static String json(TreeMap<String, Map<String, List<Object>>> tree) throws Exception {
        var diff = new StringBuilder();
        diff.append("[");
        for (Map.Entry<String, Map<String, List<Object>>> entry: tree.entrySet()) {
            diff.append("{");
            String key = entry.getKey();

            Map<String, List<Object>> map = entry.getValue();
            for (Map.Entry<String, List<Object>> statusAndValues: map.entrySet()) {
                switch (statusAndValues.getKey()) {
                    case "added" -> fieldToJson(diff, key, "added", statusAndValues.getValue().get(1));
                    case "removed" -> fieldToJson(diff, key, "deleted", statusAndValues.getValue().get(0));
                    case "unchanged" -> fieldToJson(diff, key, "unchanged", statusAndValues.getValue().get(0));
                    case "updated" -> fieldToJson(
                            diff, key, "changed",
                            statusAndValues.getValue().get(0), statusAndValues.getValue().get(1));
                    default -> {
                    }
                }
                diff.append("}");
                if (!key.equals(tree.lastKey())) {
                    diff.append(", ");
                }
            }
        }
        diff.append("]");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff.toString());
    }
}
