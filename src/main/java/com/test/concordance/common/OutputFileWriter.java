package com.test.concordance.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by ashah on 7/18/17.
 */
public class OutputFileWriter {

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(OutputFileWriter.class);

    /**
     * instance of the OutputFileWriter to write the concordance output to a file
     */
    private static OutputFileWriter instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected OutputFileWriter() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return OutputFileWriter class's singleton instance
     */
    public static OutputFileWriter getInstance() {
        if(instance == null) {
            synchronized(OutputFileWriter.class) {
                if(instance == null) {
                    instance = new OutputFileWriter();
                }
            }
        }
        return instance;
    }

    /**
     * Method to write the concordance to a given file name at a given path
     * @param map concordance frequency
     * @param fileName file name with the path where the content has to be written
     * @throws IOException
     */
    public static void writeToAFile(Map<String,FreqSentNo> map,String fileName) throws IOException {
        StringJoiner joiner;
        BufferedWriter writer = null;
        try {
            log.info("Starting writing to a file "+ fileName);
            writer = new BufferedWriter(new java.io.FileWriter(fileName));
            for(Map.Entry<String,FreqSentNo> entry:map.entrySet()){

                //Logic to convert the list content in a comma separated values.
                joiner =new StringJoiner(",");
                for (Integer integer : entry.getValue().getSentenceNumber()) {
                    joiner.add(integer.toString());
                }

                //Logic to format the keys with space in 50 Character length.
                StringBuffer keyWithSpaces = new StringBuffer(entry.getKey());
                for(int i=0;i<50-entry.getKey().length();i++){
                    keyWithSpaces.append(" ");
                }

                //Logic to write the keys, their occurrence and the sentence number(s) in which they appeared in the given format
                writer.write(keyWithSpaces.toString()+ "{" + entry.getValue().getOccurrence() + ":" + joiner.toString() + "}"+"\n");

            }} catch (IOException e) {
            log.error("Exception occurred while writing to a file. The exception is " + "\n" + e.getMessage());
            throw e;
        }finally{
            writer.close();
        }
        log.info(fileName + " is successfully written.");
    }

}
