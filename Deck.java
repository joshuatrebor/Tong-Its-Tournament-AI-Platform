import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Castañeda on 9/4/2016.
 */
public class Deck {

    private Stack<Card> cards = new Stack<>();

    public Deck() {
        initDeck();
        shuffleDeck();
    }

    /**
     * This method initialize and populates the deck
     */
    private void initDeck(){
        //Testing code only
        for(int i = 1; i <= 13; i++){
            for(int j = 1; j <= 4; j++){
                cards.add(new Card(i, j));
            }
        }
    }

    /**
     * This method shuffles the deck
     */
    private void shuffleDeck(){
        Random rnd = new Random();
        for(int i = 0; i < cards.size(); i++){
            Card tmp = cards.get(i);
            int rndNum = rnd.nextInt(cards.size()-1);
            cards.set(i, cards.get(rndNum));
            cards.set(rndNum, tmp);
        }
    }

    /**
     * This methods gets a card from the top of the deck
     * @return
     */
    public Card getCard(){
        return cards.pop();
    }

    /**
     * This method returns the card count of the deck
     * @return
     */
    public int getCount(){
        return cards.size();
    }

    /**
     * This method returns all the cards
     * @return
     */
    public Stack<Card> getCards(){
        return cards;
    }

    //DEBUGGING METHODS HERE

    /**
     * This method lists all contents of the deck
     */
    public void printDeck(){
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            System.out.print(card.getValue() + "  ");
            switch(card.getSuit()){
                case 1 : {
                    System.out.println("Spade  " + (card.getColor() == 1 ? "Red" : "Black"));
                    break;
                }
                case 2 : {
                    System.out.println("Heart  " + (card.getColor() == 1 ? "Red" : "Black"));
                    break;
                }
                case 3 : {
                    System.out.println("Diamond  " + (card.getColor() == 1 ? "Red" : "Black"));
                    break;
                }
                case 4 : {
                    System.out.println("Club  " + (card.getColor() == 1 ? "Red" : "Black"));
                    break;
                }
            }
        }
        System.out.println("\nTotal: " + cards.size());
    }
}
