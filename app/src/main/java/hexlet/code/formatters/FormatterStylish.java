package hexlet.code.formatters;

import java.util.Map;

public class FormatterStylish {
    /*static String valueToString(String key, Object value) {
        String resultValue;
        if (value == null) {
            resultValue = "null";
        } else {
            resultValue = value.toString();
        }
        return key + ": " + resultValue;
    }
    public static String stylish(Map<String, String> keys, Map<String, Object> data1, Map<String, Object> data2) {
        var result = new StringBuilder();

        for (Map.Entry<String, String> entry: keys.entrySet()) {
            String key = entry.getKey();

            switch (entry.getValue()) {
                case "added":
                    result.append("\n  + " + valueToString(key, data2.get(key)));
                    break;
                case "removed":
                    result.append("\n  - " + valueToString(key, data1.get(key)));
                    break;
                case "unchanged":
                    result.append("\n    " + valueToString(key, data1.get(key)));
                    break;
                case "updated":
                    result.append("\n  - " + valueToString(key, data1.get(key)));
                    result.append("\n  + " + valueToString(key, data2.get(key)));
                    break;
                default:
                    break;
            }
        }
        return "{" + result + "\n}";
    }*/
    public static String stylish(Map<String, String> keys, Map<String, Object> data1, Map<String, Object> data2) {
        var result = new StringBuilder();

        for (Map.Entry<String, String> entry: keys.entrySet()) {
            String key = entry.getKey();

            switch (entry.getValue()) {
                case "added":
                    result.append("\n  + " + key + ": " + data2.get(key));
                    break;
                case "removed":
                    result.append("\n  - " +  key + ": " + data1.get(key));
                    break;
                case "unchanged":
                    result.append("\n    " + key + ": " + data1.get(key));
                    break;
                case "updated":
                    result.append("\n  - " + key + ": " + data1.get(key));
                    result.append("\n  + " + key + ": " + data2.get(key));
                    break;
                default:
                    break;
            }
        }
        return "{" + result + "\n}";
    }
}
