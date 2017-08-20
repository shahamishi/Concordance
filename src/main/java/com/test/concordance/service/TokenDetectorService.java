package com.test.concordance.service;

import com.test.concordance.common.FreqSentNo;

import java.util.List;
import java.util.Map;

/**
 * Created by ashah on 7/17/17.
 */
public interface TokenDetectorService {
    /**
     * This method returns the list of tokens based upon the implementation strategy
     * @param sentence from which tokens have to be separated out
     * @return list of tokens
     */
    List<String> getTokens(String sentence);

    /**
     * This method returns the concordance map based upon the implementation strategy
     * @param sentences from which concordance has to be figured out
     * @return concordance result in the form of token and FreqSentNo object containing occurrence and sentence numbers in which the token appeared
     */
    Map<String,FreqSentNo> getTokensWithFreqSentNo(List<String> sentences);
}
