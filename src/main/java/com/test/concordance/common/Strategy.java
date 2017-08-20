package com.test.concordance.common;

import com.test.concordance.provider.SimpleStrategyProvider;
import com.test.concordance.provider.StanfordEDUStrategyProvider;
import com.test.concordance.provider.StrategyProvider;

/**
 * This class defines Strategy through which the concordance has to be figured out. This class helps maintain any new upcoming strategy.
 * Created by ashah on 7/18/17.
 */
public enum Strategy {
    /**
     * SimpleStrategy
     */
    SimpleStrategy(SimpleStrategyProvider.getInstance()),

    /**
     * StanfordEDUStrategy
     */
    StanfordEDUStrategy(StanfordEDUStrategyProvider.getInstance());

    /**
     * StrategyProvider to figure out the concordance through given strategy
     */
    private StrategyProvider strategyProvider;

    /**
     * Constructor to define Strategy with given strategy provider
     * @param strategyProvider
     */
    Strategy(StrategyProvider strategyProvider){
        this.strategyProvider = strategyProvider;
    }

    /**
     * @return strategy provider for a given Strategy
     */
    public StrategyProvider getStrategyProvider(){
        return this.strategyProvider;
    }

}
