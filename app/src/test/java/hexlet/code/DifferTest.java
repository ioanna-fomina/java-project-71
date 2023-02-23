package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Paths;

public class DifferTest {
    static String expectStylishString;
    static String expectPlainString;
    static File jsonFile1;
    static File jsonFile2;
    static File yamlFile1;
    static File yamlFile2;

    @BeforeAll
    static void beforeAll() {
        expectStylishString = """
                {
                  - keyEight: [16, mar, 1998]
                  + keyEight: [16, 6, 1996]
                  - keyFive: 5
                  + keyFive: [5, 55, 5]
                    keyFour: true
                  - keyNine: false
                  + keyNine: true
                    keyOne: valueOne
                  - keySeven: [false, true, true, true]
                  - keySix: [1, 2, 3, 5]
                  + keySix: [1, 2, 3, 4]
                  - keyThree: null
                  + keyThree: false
                  - keyTwo: 2
                  - obj: {nestedKey=value, isNested=true}
                  + obj: {nestedKey=value2, isNested2=true}
                  + seven: 7
                  + ten: 10
                  + two: two
                }""";
        expectPlainString = """
                Property 'keyEight' was updated. From [complex value] to [complex value]
                Property 'keyFive' was updated. From 5 to [complex value]
                Property 'keyNine' was updated. From false to true
                Property 'keySeven' was removed
                Property 'keySix' was updated. From [complex value] to [complex value]
                Property 'keyThree' was updated. From null to false
                Property 'keyTwo' was removed
                Property 'obj' was updated. From [complex value] to [complex value]
                Property 'seven' was added with value: 7
                Property 'ten' was added with value: 10
                Property 'two' was added with value: 'two'""";
        jsonFile1 = Paths.get("src/test/resources/oneTest.json").toFile();
        jsonFile2 = Paths.get("src/test/resources/twoTest.json").toFile();

        yamlFile1 = Paths.get("src/test/resources/oneTest.yml").toFile();
        yamlFile2 = Paths.get("src/test/resources/twoTest.yml").toFile();
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
