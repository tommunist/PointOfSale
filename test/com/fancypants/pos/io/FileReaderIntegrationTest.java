package com.fancypants.pos.io;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileReaderIntegrationTest {

    @Test
    public void shouldReadTextFromFileOnClassPath() throws IOException {
        FileReader fileReader = new FileReader("test-file_reader_simple.txt");
        String line1 = fileReader.readLine();
        assertThat(line1, is("test me"));

        String line2 = fileReader.readLine();
        assertThat(line2, is("aaaand again"));

        String lastLine = fileReader.readLine();
        assertThat(lastLine, is(nullValue()));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldIndicateErrorWhenFileCannotBeFound() throws IOException {
        new FileReader("me_so_crazy.txt").readLine();

    }
}
