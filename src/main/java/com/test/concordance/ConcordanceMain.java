package com.test.concordance;


import com.test.concordance.common.*;

import java.io.*;
import java.util.*;


/**
 * Created by ashah on 7/11/17.
 */
public class ConcordanceMain {


    public static void main(String[] args) throws IOException {

        //Example using StanfordEDU Strategy
        String filePath = "input/concordance_test.txt";
        printConcordance(filePath, Strategy.StanfordEDUStrategy);

        //Example using Simple Strategy
        filePath = "input/United_States";
        printConcordance(filePath, Strategy.SimpleStrategy);

    }

    /**
     * This method prints the concordace to a file.
     * @param filePath The path of the file from which the concordance has to be figured out
     * @param strategy Strategy to use to figure out the concordance
     * @throws IOException
     */
    private static void printConcordance(String filePath,Strategy strategy) throws IOException{
        if(filePath != null && filePath != "" && filePath != " " && strategy!=null) {
            //Extracts the name of the file from the give file path
            String fileName = Utility.getFileName(filePath);

            //Extracts the strategy name to figure out the output file location
            String strategyname = strategy.getStrategyProvider().getClass().getSimpleName();

            //Name of the output file in which the concordance result would be written
            String outputFileName = "output/" + strategyname + "_Output/" + fileName + "_output";

            //The content of the file in the String format
            String fileContent = InputFileReader.getInstance().readFileContent(filePath);

            //Gets the concordance using the strategy defined by the user
            Map<String, FreqSentNo> concordanceMap = strategy.getStrategyProvider().getConcordance(fileContent);

            //Writes the concordance output to a file
            OutputFileWriter.writeToAFile(concordanceMap, outputFileName);
        }
    }





}
