package com.test.concordance.provider;

import com.test.concordance.common.FreqSentNo;
import com.test.concordance.service.serviceImpl.StanfordEDUSentenceDetectorService;
import com.test.concordance.service.serviceImpl.StanfordEDUTokenDetectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ashah on 7/18/17.
 */
public class StanfordEDUStrategyProvider implements StrategyProvider{

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(SimpleStrategyProvider.class);

    /**
     * instance of the StanfordEDUStrategyProvider to figure out the concordance using StanfordEDU Strategy
     */
    private static StanfordEDUStrategyProvider instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected StanfordEDUStrategyProvider() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return StanfordEDUStrategyProvider class's singleton instance
     */
    public static StanfordEDUStrategyProvider getInstance() {
        if(instance == null) {
            synchronized(StanfordEDUStrategyProvider.class) {
                if(instance == null) {
                    instance = new StanfordEDUStrategyProvider();
                }
            }
        }
        return instance;
    }
    /**
     * StrategyProvider interface's method implementation using StanfordEDU Strategy.
     * This method uses sentence segmentation (detection) and tokenization both provided by StanfordEDU Strategy
     * @param fileContent Content of the file from which the concordance has to be figured out
     * @return concordance result in the form of token and FreqSentNo object containing occurrence and sentence numbers in which the token appeared
     */
    public  Map<String,FreqSentNo> getConcordance(String fileContent){

        StanfordEDUSentenceDetectorService stanfordEDUSentenceDetectorService = StanfordEDUSentenceDetectorService.getInstance();
        //Gets the sentences from the given fileContent using Simple Strategy.
        List<String> sentenceList = stanfordEDUSentenceDetectorService.getSentences(fileContent);

        log.info("From the given content,segmented sentences using the StanfordEDU Strategy are : " + sentenceList.toString());

        StanfordEDUTokenDetectorService stanfordEDUTokenDetectorService = StanfordEDUTokenDetectorService.getInstance();

        List<String> tokens = new ArrayList<String>();
        for(String sentence: sentenceList){
            //Gets the tokens out of the sentences using the Simple Strategy.
            tokens.addAll(stanfordEDUTokenDetectorService.getTokens(sentence));
        }

        log.info("From the sentences,tokenized words using the Simple Strategy  are : " + tokens.toString());

        Map<String,FreqSentNo> StanfordTokensMap = stanfordEDUTokenDetectorService.getTokensWithFreqSentNo(sentenceList);
        return StanfordTokensMap;
    }

}
