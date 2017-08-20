package com.test.concordance.service.serviceImpl;

import com.test.concordance.service.SentenceDetectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashah on 7/13/17.
 */
public class SimpleSentenceDetectorService implements SentenceDetectorService {

    /**
     * Logger instance to log the activities
     */
    static Logger log = LoggerFactory.getLogger(SimpleSentenceDetectorService.class);

    /**
     * instance of the SimpleSentenceDetectorService to detect the sentences using Simple Strategy
     */
    private static SimpleSentenceDetectorService instance = null;

    /**
     * Blank constructor to avoid the instantiation from outside
     */
    protected SimpleSentenceDetectorService() {
        // Exists only to defeat instantiation.
    }

    /**
     * Singleton intance provider method
     * @return SimpleSentenceDetectorService class's singleton instance
     */
    public static SimpleSentenceDetectorService getInstance() {
        if(instance == null) {
            synchronized(SimpleSentenceDetectorService.class) {
                if(instance == null) {
                    instance = new SimpleSentenceDetectorService();
                }
            }
        }
        return instance;
    }

    /**
     * This method detects the sentences from the given content using Simple Strategy
     * @param fileContent Content of the file from which the sentences have to be segmented out
     * @return List of sentences
     */
    public List<String> getSentences(String fileContent){

        log.info("Starting to detect the sentences using Simple Strategy.");
        List<String> splitStrings= new ArrayList<String>();
        //First it splits the sentences on the basis of character (.)
        String[] dotSplitStrings = fileContent.split("\\.");
        String[] questionSplitStrings=null;
        String[] exclamationSplitStrings=null;
        for(String dotSplitString: dotSplitStrings){
            //Secondly it splits the sentences on the basis of character (?)
            questionSplitStrings = dotSplitString.split("\\?");
            for(String questionSplitString:questionSplitStrings){
                //Thirdly it splits the sentences on the basis of character (!)
                exclamationSplitStrings = questionSplitString.split("!");
                for(String exclationSplitString: exclamationSplitStrings){
                    splitStrings.add(exclationSplitString);
                }
            }
        }
        log.info("Sentence Detection performed successfully.");

        //Returns the individual sentences split above
        return splitStrings;
    }

}
