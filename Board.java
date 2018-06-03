import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Castañeda on 9/4/2016.
 */
public class Board {

    private Player players[] = new Player[3];
    private Deck deck;
    private Stack<Card> topCard;
    private ArrayList<CardSet> houses;
    private int moveCount = 1;

    public int turn = 3;
    public Player winner;
    public boolean tongits = false;

    public Board(Player playerA, Player playerB, Player playerC){
        players[0] = playerA;
        players[1] = playerB;
        players[2] = playerC;

        deck = new Deck();
        topCard = new Stack<>();
        houses = new ArrayList<>();
    }


    /**
     * This method pops a card from the deck or topCard
     * @param fromDeck
     * @return
     */
    public Card chowCard(boolean fromDeck) {
        if (fromDeck)
            return deck.getCard();
        else
            return topCard.pop();
    }

    /**
     * This method peeks at the topCard
     * @return
     */
    public Card getTopCard(){
        return topCard.peek();
    }

    /**
     * This method returns the stack of topCards
     * @return
     */
    public Stack<Card> getTopCards(){
        return getTopCards();
    }

    /**
     * This method returns an array of houses
     * @return
     */
    public ArrayList<CardSet> getHouses(){
        return houses;
    }

    /**
     * This method returns the player on index index
     * @param index
     * @return
     */
    public Player getPlayer(int index){
        return players[index];
    }

    public int getDeckCount(){
        return deck.getCount();
    }


    //MOVING METHODS HERE
    private boolean chowCard(Player player, CardSet chowSet){
        try{
            if(chowSet != null){
                if(topCard.isEmpty()){
                    player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] TRYING TO CHOW FROM EMPTY THROWN CARDS");
                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] TRYING TO CHOW FROM EMPTY THROWN CARDS");
                    return false;
                }
                else{
                    int valid = 2;
                    Card thrownCard = topCard.peek();
                    if(chowSet.getCards().stream().mapToInt(e->e.getValue()).average().getAsDouble() == (double)chowSet.getCards().get(0).getValue()){
                        valid = 1;
                    }
                    chowSet.sortCards();
                    if(valid == 1){
                        for(Card card : chowSet.getCards()){
                            if(thrownCard.getValue() != card.getValue()){
                                player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID CHOW MOVE");
                                System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID CHOW MOVE");
                                return false;
                            }
                        }
                        chowSet.getCards().add(thrownCard);
                        houses.add(chowSet);
                        player.getHand().getCards().removeAll(chowSet.getCards());
                        System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] CHOWED THE THROWN CARD");
                        return true;
                    }
                    else{
                        for(Card card : chowSet.getCards()){
                            Card card2 = thrownCard;
                            if(card2.getValue() == card.getValue() + 1 && card2.getSuit() == card.getSuit()){
                                chowSet.getCards().add(thrownCard);
                                if(checkSequence(chowSet)){
                                    houses.add(chowSet);
                                    player.getHand().getCards().removeAll(chowSet.getCards());
                                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] CHOWED THE THROWN CARD");
                                    return true;
                                }
                            }
                            if(card2.getValue() == card.getValue() - 1 && card2.getSuit() == card.getSuit()){
                                chowSet.getCards().add(thrownCard);
                                if(checkSequence(chowSet)){
                                    houses.add(chowSet);
                                    player.getHand().getCards().removeAll(chowSet.getCards());
                                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] CHOWED THE THROWN CARD");
                                    return true;
                                }
                            }
                        }
                        player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID CHOW MOVE");
                        System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID CHOW MOVE");
                        return false;
                    }
                }
            }
            else{
                Card c = deck.getCard();
                player.addCard(c);
                System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] CHOWED " + c + " FROM THE DECK");
            }
        }catch(Exception e){
            player.addError(e.getMessage());
            System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] " + e.getMessage());
            return false;
        }
        if(isDisqualified(player)){
            System.out.println(player + " is DISQUALIFIED");
            System.exit(0);
        }
        return true;
    }

    private boolean sapawCard(Player player, Sapaw sapaw){
        for(Card card : sapaw.getSapaw().getCards()){
			if(!player.getHand().getCards().contains(card)){
				System.out.println("asd");
				return false;
			}
		}
		try{
            if(sapaw.getHouse() == null && sapaw.getSapaw().getCards().size() >= 3){
                if(checkSequence(sapaw.getSapaw()) || checkSimilar(sapaw.getSapaw())){
                    player.getHand().getCards().removeAll(sapaw.getSapaw().getCards());
                    houses.add(sapaw.getSapaw());
                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] BROUGHT DOWN " + sapaw.getSapaw());
                    return true;
                }
                else{
                    player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID HOUSE " + sapaw.getSapaw());
                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID HOUSE " + sapaw.getSapaw());
                    return false;
                }
            }
            else{
                for(CardSet house : houses){
                    CardSet tempSet = new CardSet();
                    tempSet.getCards().addAll(house.getCards());
                    tempSet.getCards().addAll(sapaw.getSapaw().getCards());
                    if(checkSequence(tempSet) || checkSimilar(tempSet)){
                        player.getHand().getCards().removeAll(sapaw.getSapaw().getCards());
                        house.getCards().addAll(sapaw.getSapaw().getCards());
                        System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] SAPAWED A CARD(S)");
                        return true;
                    }
                }
                player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID SAPAW MOVE");
                System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] INVALID SAPAW MOVE");
            }
        }catch(Exception e){
            player.addError(e.getMessage());
            System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] " + e.getMessage());
            return false;
        }
        if(isDisqualified(player)){
            System.out.println(player + " is DISQUALIFIED");
            System.exit(0);
        }
        return false;
    }

    private boolean throwCard(Player player, Card card){
        try{
            for(CardSet house : houses){
                CardSet tempSet = new CardSet();
                tempSet.getCards().addAll(house.getCards());
                tempSet.addCard(card);
                if(checkSequence(tempSet) || checkSequence(tempSet)){
                    player.addError(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] TRYING TO THROW A SAPAW CARD");
                    System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] TRYING TO THROW A SAPAW CARD");
                    return false;
                }
            }
            player.getHand().getCards().remove(card);
            topCard.push(card);
            System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[SUCCESS] THROWS " + card);
        }catch(Exception e){
            player.addError(e.getMessage());
            System.out.println(player.getPlayerName() + " MOVE# " + moveCount + ":\t[ERROR] " + e.getMessage());
            return false;
        }
        if(isDisqualified(player)){
            System.out.println(player + " is DISQUALIFIED");
            System.exit(0);
        }
        return true;
    }

    private boolean checkSimilar(CardSet set){
        if(set.getCards().stream().mapToInt(e->e.getValue()).sum() == set.getCards().get(0).getValue()*set.getCardCount()){
            return true;
        }
        return false;
    }

    private boolean checkSequence(CardSet set){
        set.sortCards();
        Card firstCard = set.getCards().get(0);
        int ctr = 0;
        int value = firstCard.getValue();
        for(Card card : set.getCards()){
            if(value == card.getValue() && card.getSuit() == firstCard.getSuit())
                ctr++;
            value++;
        }
        if(ctr == set.getCardCount())
            return true;
        return false;
    }

    public void updatePlayers(){
        for(Player player : players){
            player.calcHand();
        }
    }
	
	private static boolean firstMove = true;
	
    public void move(){
        int i = turn;
        System.out.print(this);
        System.out.println(players[i%3].getPlayerName() + "'s TURN\r\n" + players[i%3]);
        if(!firstMove){
			while(!chowCard(players[i%3],
                players[i%3].getAgent().chowCard(
                        players[i%3].getHand(),
                        topCard,
                        houses,
                        deck.getCount(),
                        players[(i-1)%3].getCardCount(),
                        players[(i+1)%3].getCardCount())));
		players[i%3].calcHand();
        winner = checkWinner();
		}
		firstMove = false;
        if(winner != null){
            System.out.println(winner + "TONGITS!");
        } else{
            ArrayList<Sapaw> sapaws = players[i%3].getAgent().sapawCard(
                    players[i%3].getHand(),
                    topCard,
                    houses,
                    deck.getCount(),
                    players[(i-1)%3].getCardCount(),
                    players[(i+1)%3].getCardCount());

            for(Sapaw sapaw : sapaws){
                sapawCard(players[i%3], sapaw);
            }
			players[i%3].calcHand();
            winner = checkWinner();
            if(winner != null){
                System.out.println(winner + "TONGITS!");
            } else{
                while(!throwCard(players[i%3],
                        players[i%3].getAgent().throwCard(
                                players[i%3].getHand(),
                                topCard,
                                houses,
                                deck.getCount(),
                                players[(i-1)%3].getCardCount(),
                                players[(i+1)%3].getCardCount()
                        )));
				players[i%3].calcHand();
                winner = checkWinner();
                if(winner != null){
                    System.out.println(winner + " WINS THE GAME!");
                }
                turn++;
                System.out.println("turn " + turn);
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public Player checkWinner(){
        if(players[0].getCardCount() == 0 || players[1].getCardCount() == 0 || players[2].getCardCount() == 0)
            tongits = true;
        if(players[0].getMinValue() == 0 || players[0].getCardCount() == 0)
            return players[0];
        if(players[1].getMinValue() == 0 || players[1].getCardCount() == 0)
            return players[1];
        if(players[2].getMinValue() == 0 || players[2].getCardCount() == 0) {
            return players[2];
        }
        Player winner = null;
        if(deck.getCount() == 0){
            int min = 200;
            if(players[0].getMinValue() <= min){
                min = players[0].getMinValue();
                winner = players[0];
            }
            if(players[1].getMinValue() <= min){
                min = players[1].getMinValue();
                winner = players[1];
            }
            if(players[2].getMinValue() <= min){
                min = players[2].getMinValue();
                winner = players[2];
            }
        }
        return winner;
    }

    private boolean isDisqualified(Player player){
        if(player.getErrorCtr() >= 3)
            return true;
        return false;
    }

    @Override
    public String toString(){
        String state = "";
        //Print the deck
        state += "DECK: " +
                "\r\nCount: " + deck.getCount() +
                "\r\nCards: ";
        Stack<Card> cards = deck.getCards();
        for(Card card : cards)
            state += "  " + card + ", ";
        //end
        //Print the houses
        System.out.println("\r\n\r\n");
        for(int i = 0; i < houses.size(); i++)
            state += "House #" + i + "  " + houses.get(i) + "\r\n";
        //end
        state += "\r\n\r\n";
        //Print the players
        for(int i = 0; i < 3; i++){
            state += players[i] + "Count: " + players[i].getCardCount() + "\r\n\r\n";
        }

        return state;
    }

    /**
     * This method distributes tha card to the Players
     */
    public void distributeCards(){
        int i;
        for(i = 0; i < 12; i++){
            players[0].addCard(deck.getCard());
            players[1].addCard(deck.getCard());
            players[2].addCard(deck.getCard());
        }
        players[0].addCard(deck.getCard());
    }
}
