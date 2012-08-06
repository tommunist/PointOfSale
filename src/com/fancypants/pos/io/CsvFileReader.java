package com.fancypants.pos.io;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvFileReader {

    public static final String COMMA_DELIMITER = ",";
    private FileReader fileReader;

    public CsvFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public List<String> readLine() throws IOException {
        String csvLine = fileReader.readLine();
        if (csvLine != null) {
            String[] values = csvLine.split(COMMA_DELIMITER);
            return Arrays.asList(values);
        }
        return null;
    }
}
