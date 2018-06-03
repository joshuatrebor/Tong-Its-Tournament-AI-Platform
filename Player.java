import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Castañeda on 9/4/2016.
 */
public class Player {
    private String playerName;
    private Agent agent;
    private CardSet hand;
    private ArrayList<CardSet> builds;
    private ArrayList<CardSet> semiBuilds;
    private int minValue;
    private int errorCtr = 0;
    private ArrayList<String> errors = new ArrayList<>();

    /**
     * Player Constructor
     * @param playerName
     * @param
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.agent = agent;
        hand = new CardSet();
        builds = new ArrayList<>();
        semiBuilds = new ArrayList<>();
        minValue = 1000;
        try{
            loadAgent();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to load " + playerName + " agent class\r\n" + e.getMessage(), "FAILED", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Cards/Failed.png"));
            e.printStackTrace();
        }
    }

    private void loadAgent() throws Exception{
        Class ClassAgent = this.getClass().getClassLoader().loadClass(playerName);
        agent = (Agent) ClassAgent.newInstance();
    }

    public void addError(String errorMessage){
        errorCtr++;
        errors.add(errorMessage);
    }

    public int getErrorCtr(){
        return errorCtr;
    }

    /**
     * This method returns the name of the Player
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * This method returns the value of the optimum meld of this cardset
     * @return maxValue
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * This method gets Card Set from player's hand
     * @return hand
     */
    public CardSet getHand() {
        return hand;
    }

    /**
     * This method returns count of cards at player's hand
     * @return
     */
    public int getCardCount(){
        return hand.getCardCount();
    }

    /**
     * This method adds card to player's hand
     * @param card
     */
    public void addCard(Card card){
        hand.addCard(card);
        int handValue = 0;
        for(Card card2 : hand.getCards())
            handValue += card2.getValue();
        minValue = handValue;
    }

    /**
     * This method calculates all possible melds, builds and semibuilds
     */
    public void calcHand(){
		builds.clear();
        semiBuilds.clear();

        calcBuilds();
		int handValue = 0;
		if(builds.isEmpty()){
			for(Card card : hand.getCards())
				handValue += card.getValue();
			minValue = handValue;
		}
		else{	
			for(int i = 0; i < builds.size(); i++){
				CardSet newHand = copyCardSet(hand);
				newHand.getCards().removeAll(builds.get(i).getCards());
				for(CardSet build : builds){
					if(build.equals(builds.get(i)))
						continue;
					if(!isCardUsed(build, newHand))
						newHand.getCards().removeAll(build.getCards());
				}
				handValue = 0;
				for(Card card : newHand.getCards())
					handValue += card.getValue();
				if(handValue < minValue)
					minValue = handValue;
			}
		}
		System.out.println("Hand Value: " + handValue);
    }

    private boolean isCardUsed(CardSet build, CardSet newHand){
        for(Card card : build.getCards()){
            if(!newHand.getCards().contains(card))
                return true;
        }
        return false;
    }

    private CardSet copyCardSet(CardSet set){
        CardSet newSet = new CardSet();
        for(Card card : set.getCards())
            newSet.addCard(card);
        return newSet;
    }

    /**
     * This method get all possible builds and semi-builds on hand
     */
    private void calcBuilds(){
        for(Card card : hand.getCards()){
            //Get similar cards
            CardSet similar = new CardSet();
            similar.addCard(card);
            for(Card card2 : hand.getCards()){
                if(card.equals(card2))
                    continue;
                if(card2.getValue() == card.getValue()) {
                    similar.addCard(card2);
                }
            }
            //Get sequence cards
            CardSet sequence = new CardSet();
            sequence.addCard(card);
            for(Card card2 : hand.getCards()){
                if(addToSequence(sequence, card2) && !sequence.getCards().contains(card2)){
                    sequence.addCard(card2);
                }
            }
            sequence.sortCards();

            if(similar.getCardCount() == 2 && !isOnSemiBuild(similar))
                semiBuilds.add(similar);
            if(similar.getCardCount() > 2 && !isOnBuild(similar))
                builds.add(similar);
            if(sequence.getCardCount() == 2 && !isOnSemiBuild(sequence))
                semiBuilds.add(sequence);
            if(sequence.getCardCount() > 2 && !isOnBuild(sequence))
                builds.add(sequence);
        }
    }

    /**
     * This method checks if build already exist
     * @param set
     * @return
     */
    private boolean isOnBuild(CardSet set){
        for(CardSet build : builds){
            if(build.toString().equals(set.toString()))
                return true;
        }
        return false;
    }

    /**
     * This Method checks if semi build already exist
     * @param set
     * @return
     */
    private boolean isOnSemiBuild(CardSet set){
        for(CardSet semiBuild : semiBuilds){
            if(semiBuild.toString().equals(set.toString()))
                return true;
        }
        return false;
    }

    /**
     * This method checks if card is part of a sequence build/semi-build
     * @param sequence
     * @param card2
     * @return
     */
    private boolean addToSequence(CardSet sequence, Card card2){
        for(Card card : sequence.getCards()){
            if(card2.getSuit() == card.getSuit()){
                if(card2.getValue() == card.getValue()+1 || card2.getValue() == card.getValue()-1)
                    return true;
            }
        }
        return false;
    }

    /**
     * This method gets all builds and semi-builds where parameter card can be inserted
     * @param card
     * @return
     */
    private ArrayList<CardSet> getPossibleBuilds(Card card){
        ArrayList<CardSet> possibleBuilds = new ArrayList<>();
        //For semi-builds
        for(CardSet semiBuilds : this.semiBuilds){
            //Get similars
            if(card.getValue() == semiBuilds.getCards().get(0).getValue())
                possibleBuilds.add(semiBuilds);
            //Get sequence
            for(Card card2 : semiBuilds.getCards()){
                if((card.getValue() == card2.getValue()+1 && card.getSuit() == card2.getSuit()) || (card.getValue() == card2.getValue()-1 && card.getSuit() == card2.getSuit())){
                    possibleBuilds.add(semiBuilds);
                    break;
                }
            }
        }
        //For builds
        for(CardSet builds : this.builds){
            //Get similars
            if(card.getValue() == builds.getCards().get(0).getValue())
                possibleBuilds.add(builds);
            //Get sequence
            for(Card card2 : builds.getCards()){
                if(card.getValue() == card2.getValue()+1 || card.getValue() == card2.getValue()-1){
                    possibleBuilds.add(builds);
                    break;
                }
            }
        }
        return possibleBuilds;
    }

    /**
     * This method gets all builds and semi-builds where parameter card can be inserted given the houses as parameter
     * @param card
     * @return
     */
    public ArrayList<CardSet> getPossibleBuilds(Card card, ArrayList<CardSet> houses){
        ArrayList<CardSet> possibleBuilds = new ArrayList<>();
        //For semi-builds
        for(CardSet house : houses){
            //Get similars
            if(card.getValue() == house.getCards().get(0).getValue())
                possibleBuilds.add(house);
            //Get sequence
            for(Card card2 : house.getCards()){
                if((card.getValue() == card2.getValue()+1 && card.getSuit() == card2.getSuit())|| (card.getValue() == card2.getValue()-1 && card.getSuit() == card2.getSuit())){
                    possibleBuilds.add(house);
                    break;
                }
            }
        }
        return possibleBuilds;
    }

    public ArrayList<CardSet> getSemiBuilds() {
        return semiBuilds;
    }

    public ArrayList<CardSet> getBuilds() {
        return builds;
    }

    public Agent getAgent() {
        return agent;
    }

    @Override
    public String toString(){
        calcHand();
        String details = playerName + ": Hand Value = " + minValue + "\r\nHand:\t\t" + hand + "\r\nBuilds:\t\t";
        for(CardSet set : builds)
            details += "(" + set + "), ";
        details += "\r\nSemi-builds:\t\t";
        for(CardSet set : semiBuilds)
            details += "(" + set + "), ";
        details += "\r\n";
        return details;
    }
}