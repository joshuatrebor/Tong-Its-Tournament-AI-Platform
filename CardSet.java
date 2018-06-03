import java.util.ArrayList;

/**
 * Created by Castañeda on 9/4/2016.
 */
public class CardSet {

    private ArrayList<Card> cards = new ArrayList<>();

    /**
     * This method returns array of cards
     * @return Card
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * This method returns the count of cards
     * @return
     */
    public int getCardCount(){
        return cards.size();
    }

    /**
     * This method adds a card to the card set
     * @param card
     */
    public void addCard(Card card){
        cards.add(card);
        sortCards();
    }

    /**
     * Self Explanatory
     */
    public void sortCards(){
        cards.sort((card1, card2) -> {
            if(card1.getValue()*(card1.getSuit()+20) > card2.getValue()*(card2.getSuit()+20))
                return 1;
            else if(card1.getValue()*(card1.getSuit()+20) < card2.getValue()*(card2.getSuit()+20))
                return -1;
            else
                return 0;
        });
    }

    @Override
    public String toString(){
        String cardsString = "";
        for(Card card : cards){
            cardsString += card + ", ";
        }
        return cardsString;
    }
}
