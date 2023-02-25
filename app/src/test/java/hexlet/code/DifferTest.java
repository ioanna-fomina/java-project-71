package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {
    private static String expectStylishString;
    private static String expectPlainString;
    private static String expectJsonString;
    private static String jsonFile1;
    private static String jsonFile2;
    private static String yamlFile1;
    private static String yamlFile2;

    @BeforeAll
    public static void beforeAll() throws Exception {
        jsonFile1 = "src/test/resources/file1.json";
        jsonFile2 = "src/test/resources/file2.json";

        yamlFile1 = "src/test/resources/file1.yml";
        yamlFile2 = "src/test/resources/file2.yml";

        expectStylishString = Files.readString(Paths.get("src/test/resources/result_stylish.txt"));
        expectPlainString = Files.readString(Paths.get("src/test/resources/result_plain.txt"));
        expectJsonString = Files.readString(Paths.get("src/test/resources/result_json.json"));
    }

    @Test
    public void testDifferJsonStylish() throws Exception {
        String actual = Differ.generate(jsonFile1, jsonFile2);
        System.out.println(expectStylishString);
        System.out.println(actual);
        assertEquals(expectStylishString, actual);
    }

    @Test
    public void testDifferJsonPlain() throws Exception {
        String actual = Differ.generate(jsonFile1, jsonFile2, "plain");
        System.out.println(expectPlainString);
        System.out.println(actual);
        assertEquals(expectPlainString, actual);
    }

    @Test
    public void testDifferJsonJson() throws Exception {
        String actual = Differ.generate(jsonFile1, jsonFile2, "json");
        System.out.println(expectJsonString);
        System.out.println(actual);
        assertEquals(expectJsonString, actual);
    }

    @Test
    public void testDifferYamlStylish() throws Exception {
        String actual = Differ.generate(yamlFile1, yamlFile2);
        System.out.println(expectStylishString);
        System.out.println(actual);
        assertEquals(expectStylishString, actual);
    }

    @Test
    public void testDifferYamlPlain() throws Exception {
        String actual = Differ.generate(yamlFile1, yamlFile2, "plain");
        System.out.println(expectPlainString);
        System.out.println(actual);
        assertEquals(expectPlainString, actual);
    }

    @Test
    public void testDifferYamlJson() throws Exception {
        String actual = Differ.generate(yamlFile1, yamlFile2, "json");
        System.out.println(expectJsonString);
        System.out.println(actual);
        assertEquals(expectJsonString, actual);
    }
}
