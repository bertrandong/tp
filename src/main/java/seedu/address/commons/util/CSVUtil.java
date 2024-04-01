package seedu.address.commons.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    public static void saveCSVFile(Path filePath, List<String[]> data) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filePath.toFile());
        data.stream().map(x -> convertToCSVFormat(x)).forEach(pw::println);
    }

    private static String convertToCSVFormat(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }

}
