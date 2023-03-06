package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    static String valueToString(String key, Object value) {
        String resultValue;
        if (value == null) {
            resultValue = "null";
        } else {
            resultValue = value.toString();
        }
        return key + ": " + resultValue;
    }
    public static String stylish(Map<String, Map<String, List<Object>>> tree) {
        var result = new StringBuilder();

        for (Map.Entry<String, Map<String, List<Object>>> entry: tree.entrySet()) {
            String key = entry.getKey();

            Map<String, List<Object>> map = entry.getValue();
            for (Map.Entry<String, List<Object>> statusAndValues: map.entrySet()) {
                switch (statusAndValues.getKey()) {
                    case "added" -> result.append("\n  + " + valueToString(key, statusAndValues.getValue().get(1)));
                    case "removed" -> result.append("\n  - " + valueToString(key, statusAndValues.getValue().get(0)));
                    case "unchanged" -> result.append("\n    " + valueToString(key, statusAndValues.getValue().get(0)));
                    case "updated" -> {
                        result.append("\n  - " + valueToString(key, statusAndValues.getValue().get(0)));
                        result.append("\n  + " + valueToString(key, statusAndValues.getValue().get(1)));
                    }
                    default -> {
                    }
                }
            }
        }
        return "{" + result + "\n}";
    }
}
