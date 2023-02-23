package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

    static boolean isEqualValues(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return obj1 == null && obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }
    public static LinkedList<TreeMap> parse(File file1, File file2, String formatFile) throws Exception {
        TreeMap<String, Object> data1 = new TreeMap<>(getData(file1, formatFile));
        TreeMap<String, Object> data2 = new TreeMap<>(getData(file2, formatFile));

        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());

        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        TreeMap<String, String> keysWithStatus = new TreeMap<>(Comparator.naturalOrder());
        for (String key: keys) {
            if (!data1.containsKey(key)) {
                keysWithStatus.put(key, "added");
            } else if (!data2.containsKey(key)) {
                keysWithStatus.put(key, "removed");
            } else if (isEqualValues(data1.get(key), data2.get(key))) {
                keysWithStatus.put(key, "unchanged");
            } else {
                keysWithStatus.put(key, "updated");
            }
        }
        LinkedList<TreeMap> list = new LinkedList<>(List.of(keysWithStatus, data1, data2));
        return list;
    }
}
