import java.util.StringTokenizer;

/**
 * Created by Castañeda on 9/4/2016.
 */
public class Card {
    private int value;
    private int color;
    private int suit;
    private String suitString;

    /**
     * Card Class
     * @param value
     * @param suit
     */
    public Card(int value, int suit) {
        this.value = value;
        this.color = suit == 2 || suit == 3 ? 1 : 2;
        this.suit = suit;
        switch (suit){
            case 1: {
                //suitString = "Spade";
                suitString = "Spade";
                break;
            }
            case 2: {
                //suitString = "Heart";
                suitString = "Heart";
                break;
            }
            case 3: {
                //suitString = "Diamond";
                suitString = "Diamond";
                break;
            }
            case 4: {
                //suitString = "Club";
                suitString = "Club";
                break;
            }
        }
    }

    /**
     * This method returns the value of Card
     * 1 is for Ace
     * 11 is for Jack
     * 12 is for Queen
     * 13 is for King
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * This method returns the color of the card
     * 1 is for Red
     * 2 is for Black
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     * This method returns the suit of the card
     * 1 is for Spade
     * 2 is for Heart
     * 3 is for Diamond
     * 4 is for Club
     * @return
     */
    public int getSuit() {
        return suit;
    }

    @Override
    public String toString(){
        switch(value){
            case 1: {
                return "A-" + suitString;
            }
            case 11: {
                return "J-" + suitString;
            }
            case 12: {
                return "Q-" + suitString;
            }
            case 13: {
                return "K-" + suitString;
            }
            default: {
                return value + "-" + suitString;
            }
        }
    }
}
