import javax.swing.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Castañeda on 9/12/2016.
 */
public class DebuggingAgent implements Agent {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public CardSet chowCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount) {
        String message = "CHOW MOVE\r\nChoose set of cards separated by comma (n if chow from deck):\r\n";
        if(!thrownCards.isEmpty())
            message += "Top Card: " + thrownCards.peek() + "\r\n";
        for(int i = 0; i < hand.getCards().size(); i++)
            message += i + ": " + hand.getCards().get(i) + "\r\n";

        String strCards[] = JOptionPane.showInputDialog(message).split(",");

        if(strCards[0].equals("n"))
            return null;
        CardSet chowSet = new CardSet();
        for(String str : strCards)
            chowSet.addCard(hand.getCards().get(Integer.parseInt(str)));

        return chowSet;
    }

    @Override
    public ArrayList<Sapaw> sapawCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount) {
        ArrayList<Sapaw> sapaws = new ArrayList<>();
        int response = JOptionPane.showConfirmDialog(null, "SAPAW MOVE\r\nDo you want to sapaw?", "TongIts", JOptionPane.YES_NO_OPTION);
        while(response == JOptionPane.YES_OPTION){
            String message = "Choose Cardset to sapaw, separated by commas:\r\n";
            for(int i = 0; i < hand.getCardCount(); i++){
                message += i + ": " + hand.getCards().get(i) + "\r\n";
            }
            String strCards[] = JOptionPane.showInputDialog(message).split(",");
            CardSet sapawCards = new CardSet();
            for(String str : strCards)
                sapawCards.addCard(hand.getCards().get(Integer.parseInt(str)));
            message = "Choose house (n bringing down house):\r\n";
            for(int i = 0; i < houses.size(); i++)
                message += i + ": " + houses.get(i) + "\r\n";
            String c = JOptionPane.showInputDialog(message);
            if(c.equals("n")){
                sapaws.add(new Sapaw(null, sapawCards));
            }
            else{
                int choice = Integer.parseInt(c);
                Sapaw sapaw = new Sapaw(houses.get(choice), sapawCards);
                sapaws.add(sapaw);
            }
            response = JOptionPane.showConfirmDialog(null, "SAPAW MOVE\r\nDo you want to sapaw?", "TongIts", JOptionPane.YES_NO_OPTION);
        }
        return sapaws;
    }

    @Override
    public Card throwCard(CardSet hand, Stack<Card> thrownCards, ArrayList<CardSet> houses, int deckCount, int leftCount, int rightCount) {
        String message = "THROW MOVE\r\nChoose card to throw:\r\n";
        for(int i = 0; i < hand.getCardCount(); i++){
            message += i + ": " + hand.getCards().get(i) + "\r\n";
        }
        int choice = Integer.parseInt(JOptionPane.showInputDialog(message));
        return hand.getCards().get(choice);
    }
}
