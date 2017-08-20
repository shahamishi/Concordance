package com.test.concordance.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashah on 7/13/17.
 */
public class FreqSentNo {
    /**
     * Shows number of occurrences in a given text
     */
    private int occurrence;

    /**
     * Shows the sentenceNumber in which a token appeared
     */
    private List<Integer> sentenceNumber;

    /**
     * @return occurrence for a given token
     */
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * @param occurrence occurrence to set
     */
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    /**
     * @return sentenceNumber(s) in which the token appeared
     */
    public List<Integer> getSentenceNumber() {
        return sentenceNumber;
    }

    /**
     * Constuctor to set occurrence and sentenceNumber(s)
     * @param occurrence number of occurrence for a given token
     * @param sentenceNumber Sentence number(s) in which a given token appeared
     */
    public FreqSentNo(int occurrence,int sentenceNumber){
        this.occurrence = occurrence;
        List<Integer> list = new ArrayList<Integer>();
        list.add(sentenceNumber);
        this.sentenceNumber=list;
    }

}
