# TFIDF_MapReduce
Considering a “Term” in a document and using MapReduce Java for implementing it. 
This code is implemented in 4 phases 
1st Phase the code will generate the output as  words and documentname with the individual count. 
2nd Phase the code will generate the output as the documentname with their count 
3rd Phase will give the total count of the words in the document. 
4th Phase is a stand alone java program which will do the TFIDF calculation.

1st 3 phase run on MapReduce Cluster and the output of each phase is important and given as input to the TfIDF.java code 
which does the calculation and generates the outputFile with TFIDF calculated for each word.
