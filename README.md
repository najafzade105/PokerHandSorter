# PokerHandSorter
Poker hand sorter is a test project which evaluates poker hands and shows the winner based on the input hands.
Each hand should have 5 cards and game has two players.

For executing the jar artifact of this project, before running you should copy the jar file into the destination directory with  and then run the jar which is possible using this command:
**cat poker-hands.txt | java -jar PokerHandSorter.jar F poker-hands.txt**

You can also use the command line input for reading lines from command line: 
**cat poker-hands.txt | java -jar PokerHandSorter.jar**  

Please notice that you should provide relative address for the file, for example if the jar file anf test data are both located in /Users/test/ you can only use the file name. But if they are in different directories the full path should be provided.