package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public String call() {
        try {
            String diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);
            return "0";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "123";
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
