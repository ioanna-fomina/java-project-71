package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    static String expectStylishString;
    static String expectPlainString;
    static String expectJsonString;
    static Path jsonFile1;
    static Path jsonFile2;
    static Path yamlFile1;
    static Path yamlFile2;

    @BeforeAll
    static void beforeAll() throws Exception {
        jsonFile1 = Paths.get("src/test/resources/oneTest.json");
        jsonFile2 = Paths.get("src/test/resources/twoTest.json");

        yamlFile1 = Paths.get("src/test/resources/oneTest.yml");
        yamlFile2 = Paths.get("src/test/resources/twoTest.yml");

        expectStylishString = Files.readString(Paths.get("src/test/resources/expectStylish.txt"));
        expectPlainString = Files.readString(Paths.get("src/test/resources/expectPlain.txt"));
        expectJsonString = Files.readString(Paths.get("src/test/resources/expectJson.json"));
    }

    @Test
    public void testDifferJsonStylish() throws Exception {
        String actual = Differ.generate(jsonFile1, jsonFile2, "stylish");
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
        String actual = Differ.generate(yamlFile1, yamlFile2, "stylish");
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
