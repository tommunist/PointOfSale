package com.fancypants.pos.io;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CsvFileReaderIntegrationTest {

    @Test
    public void shouldReadCsvFilesOneLineAtATime() throws IOException {
        CsvFileReader csvFileReader = new CsvFileReader(new FileReader("test-csv_file_reader_one_liner.csv"));
        List<String> csvValues = csvFileReader.readLine();
        assertThat(csvValues.size(), equalTo(3));
        assertThat(csvValues.get(0), equalTo("i"));
        assertThat(csvValues.get(1), equalTo("have"));
        assertThat(csvValues.get(2), equalTo("commas"));
    }
}
