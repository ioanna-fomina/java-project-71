package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
//import java.nio.file.Files;

//import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class DifferTest {
    static String expectJsonString;
    static File fileJson1;
    static File fileJson2;


    @Test
    public void testDiffer() throws Exception {
        expectJsonString = """
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
                  + seven: 7
                  + ten: 10
                  + two: two
                }""";
        Path path1 = Paths.get("src/test/resources/oneTest.json");
        Path path2 = Paths.get("src/test/resources/twoTest.json");
        fileJson1 = path1.toFile();
        fileJson2 = path2.toFile();
        String actual = Differ.generate(fileJson1, fileJson2);
        System.out.println(expectJsonString);
        System.out.println(actual);
        assertEquals(expectJsonString, actual);
    }
}
