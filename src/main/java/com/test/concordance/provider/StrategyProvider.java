package com.test.concordance.provider;

import com.test.concordance.common.FreqSentNo;

import java.util.Map;

/**
 * Created by ashah on 7/18/17.
 */
public interface StrategyProvider {
    /**
     * This method returns the concordance map based upon the implementation strategy
     * @param fileContent Content of the file from which concordance has to be figured out
     * @return concordance result in the form of token and FreqSentNo object containing occurrence and sentence numbers in which the token appeared
     */
     Map<String,FreqSentNo> getConcordance(String fileContent);
}
