package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public class FormatterPlain {
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
    public static String plain(TreeMap<String, String> keys, Map<String, Object> data1, Map<String, Object> data2) {
        var result = new StringBuilder();

        for (Map.Entry<String, String> entry: keys.entrySet()) {
            String key = entry.getKey();

            switch (entry.getValue()) {
                case "added":
                    result.append(addedToString(key, data2.get(key)));
                    break;
                case "removed":
                    result.append(removedToString(key));
                    break;
                case "updated":
                    result.append(updatedToString(key, data1.get(key), data2.get(key)));
                    break;
                default:
                    break;
            }
            if ((!key.equals(keys.lastKey()) && !entry.getValue().equals("unchanged"))) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
