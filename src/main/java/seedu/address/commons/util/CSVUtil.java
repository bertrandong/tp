package seedu.address.commons.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    public static void saveCSVFile(Path filePath, List<String[]> data, String[] headers) throws FileNotFoundException, IOException {
        File writeFile = filePath.toFile();
        boolean isFileEmpty = writeFile.length() == 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true));
        for (String[] dataLine : data) {
            String writeLine = convertToCSVFormat(dataLine);
            formatingCSVLineForCompletedOrders(bw, isFileEmpty, writeLine, headers);
        }
        bw.close();
    }

    private static String convertToCSVFormat(String[] data) {
        String str = Stream.of(data).collect(Collectors.joining(","));
        return str;
    }

    private static void formatingCSVLineForCompletedOrders(BufferedWriter writer, boolean isFileEmpty, String data
            , String[] headers) throws IOException {
        if (isFileEmpty) {
            String headerString = convertToCSVFormat(headers);
            writer.append(headerString);
            writer.newLine();
            writer.append(data);
        } else {
            writer.newLine();
            writer.append(data);
        }
    }

}
