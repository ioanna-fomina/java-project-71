package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Formatter {
    public static String buildFormat(TreeMap<String, Map<String, List<Object>>> tree, String format) throws Exception {
        String result;

        switch (format) {
            case "stylish":
                result = StylishFormatter.stylish(tree);
                break;
            case "plain":
                result = PlainFormatter.plain(tree);
                break;
            case "json":
                result = JsonFormatter.json(tree);
                break;
            default:
                throw new Exception("This format is not available");
        }
        return result;
    }
}
