package com.test.concordance.service;

import java.util.List;

/**
 * Created by ashah on 7/17/17.
 */
public interface SentenceDetectorService {
    /**
     * This method returns the list of sentences based upon the implementation strategy
     * @param fileContent from which Sentences have to be separated out
     * @return list of sentences
     */
    List<String> getSentences(String fileContent);
}
