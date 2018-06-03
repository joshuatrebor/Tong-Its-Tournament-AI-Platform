import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Castañeda on 9/4/2016.
 */
public interface Agent {

    /**
     * This method interface is used to decide what to chow, the deck top or the thrown card
     * returns CardSet that will match the thrown card inorder to chow the thrown card
     * returns null if going to chow the top of deck
     * @param hand  this is the CardSet (Array of cards) that you have in hand
     * @param thrownCards   this is the stack of cards that are already thrown
     * @param houses    this is the array of CardSet (array of array of cards) or the houses
     * @param deckCount this is the number of remaining cards in the deck
     * @param leftCount this is the number of cards on the player on your left
     * @param rightCount    this is the number of cards on the player on your right
     * @return
     */
    public CardSet chowCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount);

    /**
     * This method interface is used to do sapaw moves or putting down a house
     * returns an array of Sapaw
     * each Sapaw must contain the house to be Sapawed and the CardSet of what to sapaw
     * if house to be sapawed is set to null, will just bring down a house (CardSet)
     * @param hand  this is the CardSet (Array of cards) that you have in hand
     * @param thrownCards   this is the stack of cards that are already thrown
     * @param houses    this is the array of CardSet (array of array of cards) or the houses
     * @param deckCount this is the number of remaining cards in the deck
     * @param leftCount this is the number of cards on the player on your left
     * @param rightCount    this is the number of cards on the player on your right
     * @return
     */
    public ArrayList<Sapaw> sapawCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount);

    /**
     * This method interface is used to decide what card to throw
     * this returns a card from the player's hand
     * @param hand  this is the CardSet (Array of cards) that you have in hand
     * @param thrownCards   this is the stack of cards that are already thrown
     * @param houses    this is the array of CardSet (array of array of cards) or the houses
     * @param deckCount this is the number of remaining cards in the deck
     * @param leftCount this is the number of cards on the player on your left
     * @param rightCount    this is the number of cards on the player on your right
     * @return
     */
    public Card throwCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount);

}
