package com.test.concordance.service.serviceImpl;

import com.test.concordance.common.FreqSentNo;
import com.test.concordance.service.TokenDetectorService;
import com.sun.tools.javac.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ashah on 7/13/17.
 */
public class SimpleTokenDetectorService implements TokenDetectorService {

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(SimpleTokenDetectorService.class);

    /**
     * instance of the SimpleTokenDetectorService to detect the tokens using Simple Strategy
     */
    private static SimpleTokenDetectorService instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected SimpleTokenDetectorService() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return SimpleTokenDetectorService class's singleton instance
     */
    public static SimpleTokenDetectorService getInstance() {
        if(instance == null) {
            synchronized(SimpleTokenDetectorService.class) {
                if(instance == null) {
                    instance = new SimpleTokenDetectorService();
                }
            }
        }
        return instance;
    }

    /**
     * This method detects the tokens from the given sentence using Simple Strategy
     * @param sentence from which the tokens have to be separated out
     * @return List of tokens
     */
    public List<String> getTokens(String sentence){
        List<String> tokens = new ArrayList<String>();
        tokens = Arrays.asList(sentence.split(" "));
        return tokens;
    }

    /**
     * This method returns the concordance using the Simple Strategy
     * @param sentences from which tokens would be separated out
     * @return concordance result in the form of token and FreqSentNo object containing occurrence and sentence numbers in which the token appeared
     */
    public Map<String,FreqSentNo> getTokensWithFreqSentNo(List<String> sentences){

        log.info("Starting to detect the concordanceMap based upon Simple Strategy");

        String[] tokens = null;
        int sentenceNumber=0;
        FreqSentNo freqSentNo;
        Map<String,FreqSentNo> concordanceMap = new TreeMap<String, FreqSentNo>();
        //Logic to avoid punctuation marks as separate tokens
        Pattern punctuation = Pattern.compile("\\G\\p{Punct}");
        for(String sentence: sentences){
            sentenceNumber++;
            //Tokens are split based on the space (" ")
            tokens = sentence.split(" ");
            for(String token: tokens){
                Matcher m = punctuation.matcher(token);
                if(!m.find()) {
                    if (concordanceMap.get(token) != null && !token.equals("") && !token.equals("\n")) {
                        //If the token has already occurred in the previous sentences, increment the occurrence by one and update the sentence numbers list with the current sentence
                        freqSentNo = concordanceMap.get(token);
                        freqSentNo.setOccurrence(freqSentNo.getOccurrence() + 1);
                        freqSentNo.getSentenceNumber().add(sentenceNumber);
                    } else {
                        //If the token is appearing for the first time, create an entry of the token in the concordance map with the current sentence number
                        concordanceMap.put(StringUtils.toLowerCase(token), new FreqSentNo(1, sentenceNumber));
                    }
                }
            }
        }
        log.info("Token detection performed successfully");
        return  concordanceMap;
    }


}
