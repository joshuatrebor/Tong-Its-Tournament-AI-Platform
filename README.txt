7-Flowers
---README---

--INCLUDED FILES--
1. Agent.java
2. Board.java
3. Card.java
4. CardSet.java
5. Deck.java
6. Player.java
7. Sapaw.java
8. TongIts.java

--SAMPLE AGENTS--
1. DebuggingAgent.java (This agent, instead of doing intelligent move, ask input from the user for testin purposes)

--CREATING AN AGENT CLASS--
1. create a class named with your agent's name
2. implement the Agent interface (open "Agent.java" for more info)
3. define all of the declared methods (chowCard, sapawCard, throwCard)
	Example:
		//This is a sample agent class
		public class AgentSuperman implements Agent{

			public CardSet chowCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount){
				//Your code here
			}

			public ArrayList<Sapaw> sapawCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount){
				//Your code here
			}

			public Card throwCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount){
				//Your code here
			}
		}
		//end
*be sure to define all of the declared methods inside the agent interface
4. Save your agent class on the same folder with Agent.java
5. Compile your Agent class

--EXPLANATION OF CLASS USED--
1. Card class -> contains info about the card (value, suit) as int
	Suits:
		1 = Spade
		2 = Heart
		3 = Diamond
		4 = Club
	Value:
		1 = A
		2 = 2 ... 10 = 10
		11 = Jack
		12 = Queen
		13 = King
	Methods Exposed:
		public int getValue() - returns the value of a card
   		public int getSuit() - returns the suit of the card
2. CardSet class -> contains an array of cards. used as houses or the player's hand (set of cards)
	*CardSets are used to store set of Cards
	Methods Exposed:
		public ArrayList<Card> getCards() - returns an ArrayList of Card stored in it
		public int getCardCount() - returns the number of cards in it
		public void addCard(Card card) - takes an instance of Card as a parameter and adds it to its inner ArrayList of Card
		public void sortCards() - sorts the card by value then by suit
		public String toString() - returns info as string
3. Sapaw class -> stores two CardSets (house, sapaw). Sapaw is used to return the details of a Sapaw move.
	*house(bahay) is a CardSet from the board that stores combination cards.
	*sapaw is a CardSet that stores Cards from the player's hand to be sapawed to the 
	Example:
		house(J-Heart, J-Diamond, J-Spade) <- sapaw(J-Club)	*the J-Club to be sapawed to a trio of Jacks
		house(A-Heart, 2-Heart, 3-Heart) <- sapaw(4-Heart, 5-Heart) *the 4 and 5 of hearts to be sapawed to A,2,3 of hearts

--EXPLANATION OF CHOWCARD, SAPAWCARD, THROWCARD METHOD PARAMETERS--
*The parameters to the methods chowCard, sapawCard, throwCard are used to give the AI agent all of the necessary and accessible info about its environment (game board)
1. CardSet hand - This CardSet is the set of Cards in the Agent player's hand
2. Stack<Card> thrownCards - This Stack of Card stores all of the cards that are thrown by all player's from bottom to top.
	*top of the stack contains the card thrown by the previous player which can be chowed if possible
3. ArrayList<CardSet> houses - This ArrayList of CardSet stores all of the house(bahay) that are already brought down to the board by all players
	Example: (7-Club, 8-Club, 9-Club, 10-Club), (A-Hearts, A-Diamond, A-Spade), (J-Club, J-Diamond, J-Spade, J-Hearts)
4. int deckCount - stores the number of remaining cards on the deck
5. int leftCount - stores the number of remaining cards on the of hand of the player to your left
6. int rightCount - stores the number of remaining cards on the of hand of the player to your right

--USING THE DEBUGGINGAGENT--
	*The DebuggingAgent is used to test your agent. Instead of doing smart moves, it ask input from the user
	*The game ask for three agents to start the game. You can set the DebuggingAgent as the two of the agents and your agent as the other so that you can play against your agent
1. Compile "DebuggingAgent.java"
2. Run the game

--RUNNING THE GAME--
1. Be sure all agents are compiled
2. Open cmd
3. Set JDK path and file directory
4. Run the "Run.bat" file
5. The game will ask for input three time (PlayerA, PlayerB, PlayerC). Enter you agent's name (Case-sensitive)
6. Ta-da!

*You can inspect "DebuggingAgent.java" on how the "agent that ask input from the user" is created
Happy Coding :D