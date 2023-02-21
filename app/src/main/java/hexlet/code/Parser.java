package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Parser {
    static Map<String, Object> getData(File file, String format) throws Exception {
        ObjectMapper mapper = null;
        switch (format) {
            case "json":
                mapper = new ObjectMapper();
                break;
            case "yml":
                mapper = new YAMLMapper();
                break;
            default:
                break;
        }

        Map<String, Object> map = mapper.readValue(file, Map.class);
        return map;
    }

    private static boolean isEqualValues(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return obj1 == null && obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }
    public static Map<String, Object> parse(File file1, File file2, String format) throws Exception {
        Map<String, Object> data1 = getData(file1, format);
        Map<String, Object> data2 = getData(file2, format);
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        Map<String, Object> result = new LinkedHashMap<>();

        for (String key: keys) {
            if (!data1.containsKey(key)) {
                result.put("+ " + key, data2.get(key));
            } else if (!data2.containsKey(key)) {
                result.put("- " + key, data1.get(key));
            } else if (isEqualValues(data1.get(key), data2.get(key))) {
                result.put("  " + key, data1.get(key));
            } else {
                result.put("- " + key, data1.get(key));
                result.put("+ " + key, data2.get(key));
            }
        }
        return result;
    }

}
