package hexlet.code;

import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Formatter {
    public static String buildFormat(List<TreeMap> maps, String format) throws Exception {
        TreeMap<String, String> keys = maps.get(0);
        TreeMap<String, Object> data1 = maps.get(1);
        TreeMap<String, Object> data2 = maps.get(2);

        String result;

        switch (format) {
            case "stylish":
                result = FormatterStylish.stylish(keys, data1, data2);
                break;
            case "plain":
                result = FormatterPlain.plain(keys, data1, data2);
                break;
            default:
                throw new Exception("This format is not available");
        }
        return result;
    }
}
