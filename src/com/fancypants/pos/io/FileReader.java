package com.fancypants.pos.io;

import java.io.*;

public class FileReader {
    private String filename;
    private BufferedReader reader;

    public FileReader(String filename) {
        this.filename = filename;
    }

    public String readLine() throws IOException {
        if (reader == null) {
            createReader();
        }
        String line = reader.readLine();
        if (line == null) {
            reader.close();
        }
        return line;
    }

    private void createReader() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(filename);
        if (resourceAsStream == null) {
            throw new FileNotFoundException("Could not find file on classpath: " + filename);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);

        reader = new BufferedReader(inputStreamReader);
    }

}
