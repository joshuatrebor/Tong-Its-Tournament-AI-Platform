import javax.swing.*;
import java.awt.*;

/**
 * Created by Castañeda on 9/5/2016.
 */
public class TongIts{

    private static Player playerA;
    private static Player playerB;
    private static Player playerC;
    private static Board board;
    private static TongItsUI game;

    public static void main(String args[]){
        int win1 = 0;
        int win2 = 0;
        int win3 = 0;

    	game = new TongItsUI(win1,win2,win3);
        while(game.isVisible())
		if(game.exitClicked){
            win1 = game.win1;
            win2 = game.win2;
            win3 = game.win3;
            game.dispose();
            game = new TongItsUI(win1,win2,win3);
        }        
    }
}
