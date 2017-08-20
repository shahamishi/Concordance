# Concordance

## Purpose:
This project prints the concordance from the given file. 

## Definition:
Concordance is an alphabetical list of all word occurrences, labled with word frequencies. Also it is labeling each word with the sentence numbers in which each occurrence appeared.

## Input:
The project requires two input.
1) The file from which the concordance has to be created.
2) Strategy to use. Currently it supports two strategies. Preferred Strategy is StanfordEDUStrategy. Detailed explanation on the strategies is given below.

## Output:
The project prints the concordance in the file. The file can be found under a folder called output/<Strategy>Provider_Output with the name as <input_file_name>_output (The extension of the file name would be ignored). 


## Available Strategies:

Currently there are two strategies available.

### StanfordEDU Strategy:

Stanford CoreNLP provides a set of human language technology tools. It can give the base forms of words, their parts of speech, whether they are names of companies, people, etc., normalize dates, times, and numeric quantities, mark up the structure of sentences in terms of phrases and syntactic dependencies, indicate which noun phrases refer to the same entities, indicate sentiment, extract particular or open-class relations between entity mentions, get the quotes people said, etc.

StanfordEDU Strategy is the preferred Strategy.

### Simple Strategy : 

An in-house strategy which does the NLP. It supports sentence segmentation and tokenization. The sentence segmentation is pretty basic and done using the punctuation marks such as ".", "?" and "!". The tokenization is done using whitespace " ".

### Drawback using Simple Strategy : 

The Simple Strategy is not capable of handling tokens (words) having punctuation marks. For example "Ms." , "Mr." , "i.e." etc. Also it is an in-house strategy and there may be limitations on expanding the strategy.

## Research Done:
Since the Simple Strategy is not capable of handling the punctuation marks, I did research on the available open source NLP tools. I tried to do POC with below NLP Tools.
1) OpenNLP (https://opennlp.apache.org/)
2) Google Cloud Natural Language API (https://siftery.com/google-cloud-natural-language-api)
3) Stanford's Core NLP Suite (https://stanfordnlp.github.io/CoreNLP/)

Out of the three above NLP Tools, I decided to go with the Stanford's Core NLP Suite as,
OpenNLP has a limitation of considering punctuation marks as part of token itself.
Google Cloud Natural Language API requires google cloud platform access and the credentials have to be shared across as I was asked to share the working project with BridgeWater. Although it can be put in a secret file using open source secret managing service like Vault etc, I decided to keep it simple for this purpose.

## Walk-through:

### ConcordanceMain 
ConcordanceMain is the entry point having main method. I have provided two examples with  different input files and different strategies being used. From the ConcordanceMain class, printConcordance is an uber method to call all the individual methods to get the sentences/tokens segmented using the given strategy and finally getting the concordance printed in the file.

### package com.bridgewater.concordance.common:
This package contains the common classes which can be used to read from a file, write to a file, defining the concordance definition, defining the strategy and some general purpose utility functionalities. 
Defining the strategy restricts the user to mix and match the sentence and token detection services. It is good to keep that control under a developer's hand to decide which strategy uses which sentence/token detection service.

### package com.bridgewater.concordance.provider:
This package contains the Interface StrategyProvider. This is an extendable selling point of the project where new strategies can be added if required and the API contract would remain the same. Currently there are two providers available where one is preferred as mentioned above.

### package com.bridgewater.concordance.service:
This package contains the Interfaces to define the services like SentenceDetectorService and TokenDetectorService. This can also be extended by adding new services.

### package com.bridgewater.concordance.service.serviceImpl:
This package contains the implementation classes for the above services. Currently there are two available implementation for both Sentence detection and token detection. 

## Approaches taken:

 I tried to use incremental approach where I first started with the skalaton of the project and once the skalaton was ready, I tried to deep dive in individual segments of the project.

 When I first started with the NLP, I tried creating my own strategy to detect the sentences and tokens from the given file. But I ran into an issue where the tokens were not getting identified properly and so it was not matching with the required output. 

 Since NLP is a pretty common requirement these days, I thought of exploring it on the internet as well and I could find few of them as open source. I tried doing POC with three, ran in to some issues/concerns and finally I settled down with one.

 In the final version of the project, even though the Simple Strategy does not give the output as the required format, I decided to keep it as it shows the extension points.

## Next Steps:

The project is quite modularized and it can easily be converted to APIs.
This project has some corner cases involved but we can have proper Unit Test Framework associated with more detailed test cases involved.

