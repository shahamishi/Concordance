package com.test.concordance.provider;

import com.test.concordance.common.FreqSentNo;
import com.test.concordance.service.serviceImpl.SimpleSentenceDetectorService;
import com.test.concordance.service.serviceImpl.SimpleTokenDetectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ashah on 7/18/17.
 */
public class SimpleStrategyProvider implements StrategyProvider {
    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(SimpleStrategyProvider.class);

    /**
     * instance of the SimpleStrategyProvider to figure out the concordance using Simple Strategy
     */
    private static SimpleStrategyProvider instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected SimpleStrategyProvider() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return SimpleStrategyProvider class's singleton instance
     */
    public static SimpleStrategyProvider getInstance() {
        if(instance == null) {
            synchronized(SimpleStrategyProvider.class) {
                if(instance == null) {
                    instance = new SimpleStrategyProvider();
                }
            }
        }
        return instance;
    }

    /**
     * StrategyProvider interface's method implementation using Simple Strategy.
     * This method uses sentence segmentation (detection) and tokenization both provided by Simple Strategy
     * @param fileContent Content of the file from which the concordance has to be figured out
     * @return concordance result in the form of token and FreqSentNo object containing occurrence and sentence numbers in which the token appeared
     */
    public  Map<String,FreqSentNo> getConcordance(String fileContent){

        SimpleSentenceDetectorService sentenceDetector= SimpleSentenceDetectorService.getInstance();
        //Gets the sentences from the given fileContent using Simple Strategy.
        List<String> sentences = sentenceDetector.getSentences(fileContent);

        log.info("From the given content,segmented sentences using the Simple Strategy are : " + sentences.toString());

        SimpleTokenDetectorService simpleTokenDetector = SimpleTokenDetectorService.getInstance();
        List<String> tokens = new ArrayList<String>();
        for(String sentence: sentences){
            //Gets the tokens out of the sentences using the Simple Strategy.
            tokens.addAll(simpleTokenDetector.getTokens(sentence));
        }

        log.info("From the sentences,tokenized words using the Simple Strategy  are : "+ tokens.toString());

        Map<String,FreqSentNo> simpleTokensMap = simpleTokenDetector.getTokensWithFreqSentNo(sentences);

        return simpleTokensMap;
    }

}
