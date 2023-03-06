package hexlet.code;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Tree {
    static boolean isEqualValues(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return obj1 == null && obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }
    static Map<String, List<Object>> statusAndValues(String status, Object value1, Object value2) {
        Map<String, List<Object>> map = new HashMap<>();
        List<Object> list = new LinkedList<>();
        list.add(value1);
        list.add(value2);
        map.put(status, list);
        return map;
    }
    public static TreeMap<String, Map<String, List<Object>>> genDifferences(
            Set<String> keys, Map<String, Object> data1, Map<String, Object> data2) throws Exception {

        TreeMap<String, Map<String, List<Object>>> differences = new TreeMap<>(Comparator.naturalOrder());
        for (String key: keys) {
            if (!data1.containsKey(key)) {
                differences.put(key, statusAndValues("added", "", data2.get(key)));
            } else if (!data2.containsKey(key)) {
                differences.put(key, statusAndValues("removed", data1.get(key), ""));
            } else if (isEqualValues(data1.get(key), data2.get(key))) {
                differences.put(key, statusAndValues("unchanged", data1.get(key), data2.get(key)));
            } else {
                differences.put(key, statusAndValues("updated", data1.get(key), data2.get(key)));
            }
        }
        return differences;
    }
}
