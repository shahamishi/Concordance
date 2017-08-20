package com.test.concordance.service.serviceImpl;

/**
 * Created by ashah on 7/17/17.
 */

import com.test.concordance.service.SentenceDetectorService;
import edu.stanford.nlp.simple.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StanfordEDUSentenceDetectorService implements SentenceDetectorService {

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(StanfordEDUSentenceDetectorService.class);

    /**
     * instance of the StanfordEDUSentenceDetectorService to detect the sentences using StanfordEDU Strategy
     */
    private static StanfordEDUSentenceDetectorService instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected StanfordEDUSentenceDetectorService() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return StanfordEDUSentenceDetectorService class's singleton instance
     */
    public static StanfordEDUSentenceDetectorService getInstance() {
        if(instance == null) {
            synchronized(StanfordEDUSentenceDetectorService.class) {
                if(instance == null) {
                    instance = new StanfordEDUSentenceDetectorService();
                }
            }
        }
        return instance;
    }

    /**
     * This method detects the sentences from the given content using StanfordEDU Strategy
     * @param fileContent Content of the file from which the sentences have to be segmented out
     * @return List of sentences
     */
    public List<String> getSentences(String fileContent){

       log.info("Starting to detect the sentences using StanfordEDU Strategy.");
       Document document = new Document(fileContent);
        //Way of detecting the sentences from the given file content.
       List<Sentence> eduSentenceList = document.sentences();
       List<String> sentenceList = new ArrayList<String>();
        for(Sentence sentence: eduSentenceList){
            //extracting the sentence object's plain text and adding into the list of sentences.
            sentenceList.add(sentence.text());
        }
        log.info("Sentence Detection performed successfully.");
        return  sentenceList;
    }
}
