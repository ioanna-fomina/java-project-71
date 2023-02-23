package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {
    static String expectStylishString;
    static String expectPlainString;
    static File jsonFile1;
    static File jsonFile2;
    static File yamlFile1;
    static File yamlFile2;

    @BeforeAll
    static void beforeAll() throws Exception {
        jsonFile1 = Paths.get("src/test/resources/oneTest.json").toFile();
        jsonFile2 = Paths.get("src/test/resources/twoTest.json").toFile();

        yamlFile1 = Paths.get("src/test/resources/oneTest.yml").toFile();
        yamlFile2 = Paths.get("src/test/resources/twoTest.yml").toFile();

        expectStylishString = new String(Files.readAllBytes(Paths.get("src/test/resources/expectStylish.txt")));
        expectPlainString = new String(Files.readAllBytes(Paths.get("src/test/resources/expectPlain.txt")));
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
}
