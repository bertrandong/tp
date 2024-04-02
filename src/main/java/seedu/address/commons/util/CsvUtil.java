package seedu.address.commons.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts a array of data and it's corresponding header into a csv file.
 */
public class CsvUtil {

    /**
     * Writes the array of data and its corresponding header into a csv file.
     * @param filePath Path for the csv file to be written.
     * @param data array of data to be stored.
     * @param headers Headers corresponding to the data to be stored.
     * @throws IOException
     */
    public static void saveCsvFile(Path filePath, List<String[]> data, String[] headers) throws IOException {
        File writeFile = filePath.toFile();
        boolean isFileEmpty = writeFile.length() == 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true));
        for (String[] dataLine : data) {
            String writeLine = convertToCsvFormat(dataLine);
            formatingCsvLineForCompletedOrders(bw, isFileEmpty, writeLine, headers);
        }
        bw.close();
    }

    private static String convertToCsvFormat(String[] data) {
        String str = Stream.of(data).collect(Collectors.joining(","));
        return str;
    }

    private static void formatingCsvLineForCompletedOrders(BufferedWriter writer, boolean isFileEmpty, String data,
                                                           String[] headers) throws IOException {
        if (isFileEmpty) {
            String headerString = convertToCsvFormat(headers);
            writer.append(headerString);
            writer.newLine();
            writer.append(data);
        } else {
            writer.newLine();
            writer.append(data);
        }
    }

}
