package com.test.concordance.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ashah on 7/18/17.
 */
public class InputFileReader {

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(OutputFileWriter.class);

    /**
     * instance of the InputFileReader to read the file content
     */
    private static InputFileReader instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected InputFileReader() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return InputFileReaderce class's singleton instance
     */
    public static InputFileReader getInstance() {
        if(instance == null) {
            synchronized(InputFileReader.class) {
                if(instance == null) {
                    instance = new InputFileReader();
                }
            }
        }
        return instance;
    }

    /**
     * Method to read the content of a file from the given path
     * @param filePath path of the file to read
     * @return String file content
     * @throws IOException
     */
    public String readFileContent(String filePath) throws IOException {

        log.info("Starting to read the file from the path: " + filePath);

        FileReader in = null;
        BufferedReader br= null;
        StringBuffer fileContent = new StringBuffer();

        try{
        in = new FileReader(filePath);
        br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            fileContent.append(line);
            }
        } catch (IOException e) {
            log.error(filePath +" can't be read. The exception is "+"\n" + e.getMessage());
            throw e;
        }finally {
            in.close();
        }

        log.info(filePath +" successfully read.");
        return fileContent.toString();
    }

}
