package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JsonFormatter {
    public static String json(TreeMap<String, Map<String, List<Object>>> tree) throws Exception {
        List<Map<String, Object>> diff = new LinkedList<>();

        for (Map.Entry<String, Map<String, List<Object>>> entry: tree.entrySet()) {
            Map<String, Object> mapDiff = new LinkedHashMap<>();
            String key = entry.getKey();

            Map<String, List<Object>> map = entry.getValue();
            for (Map.Entry<String, List<Object>> statusAndValues: map.entrySet()) {
                switch (statusAndValues.getKey()) {
                    case "added" -> {
                        mapDiff.put("key", key);
                        mapDiff.put("type", "added");
                        mapDiff.put("value", statusAndValues.getValue().get(1));
                    }
                    case "removed" -> {
                        mapDiff.put("key", key);
                        mapDiff.put("type", "deleted");
                        mapDiff.put("value", statusAndValues.getValue().get(0));
                    }
                    case "unchanged" -> {
                        mapDiff.put("key", key);
                        mapDiff.put("type", "unchanged");
                        mapDiff.put("value", statusAndValues.getValue().get(0));
                    }
                    case "updated" -> {
                        mapDiff.put("key", key);
                        mapDiff.put("type", "changed");
                        mapDiff.put("value1", statusAndValues.getValue().get(0));
                        mapDiff.put("value2", statusAndValues.getValue().get(1));
                    }
                    default -> { }
                }
            }
            diff.add(mapDiff);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
