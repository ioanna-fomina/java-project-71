package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PlainFormatter {
    static String valueToString(Object value) {
        String string;
        if (value == null) {
            string = "null";
        } else if (value.toString().contains("[") | value.toString().contains("{")) {
            string = "[complex value]";
        } else if (value.getClass().equals(String.class)) {
            string = "'" + value + "'";
        } else {
            string = value.toString();
        }
        return string;
    }
    static String addedToString(String key, Object value) {
        String valueStr = valueToString(value);
        return String.format("Property '%s' was added with value: %s", key, valueStr);
    }
    static String removedToString(String key) {
        return String.format("Property '%s' was removed", key);
    }
    static String updatedToString(String key, Object value1, Object value2) {
        String value1Str = valueToString(value1);
        String value2Str = valueToString(value2);
        return String.format("Property '%s' was updated. From %s to %s", key, value1Str, value2Str);
    }
    public static String plain(TreeMap<String, Map<String, List<Object>>> tree) {
        var result = new StringBuilder();

        for (Map.Entry<String, Map<String, List<Object>>> entry: tree.entrySet()) {
            String key = entry.getKey();

            Map<String, List<Object>> map = entry.getValue();
            for (Map.Entry<String, List<Object>> statusAndValues: map.entrySet()) {
                switch (statusAndValues.getKey()) {
                    case "added" -> result.append(addedToString(key, statusAndValues.getValue().get(1)));
                    case "removed" -> result.append(removedToString(key));
                    case "updated" -> result.append(updatedToString(
                            key, statusAndValues.getValue().get(0), statusAndValues.getValue().get(1)));
                    default -> {
                    }
                }
                if ((!key.equals(tree.lastKey()) && !statusAndValues.getKey().equals("unchanged"))) {
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }
}
