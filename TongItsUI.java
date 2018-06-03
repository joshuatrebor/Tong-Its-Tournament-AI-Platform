import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class TongItsUI extends JFrame implements ActionListener{

	public TongItsUI(int win1, int win2, int win3){
		this.win1 = win1;
		this.win2 = win2;
		this.win3 = win3;
		initComponents();
	}

	public void initComponents(){
		this.setSize(900, 700);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("AI Tongits Tournament 2016");

		panel_StartWindow.setSize(900, 700);
		panel_StartWindow.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Cards/Icon.png"));
		this.add(panel_StartWindow);

		label_bg.setBounds(0, 0, 900, 700);
		label_bg2.setBounds(910, 0, 900, 700);

		for(int loop = 0; loop < 3; loop++){
			panel_AgentName[loop] = new JPanel();
			text_AgentName[loop] = new JTextField();
			label_AgentName[loop] = new JLabel(new ImageIcon("Cards/AgentName2.png"));

			panel_AgentName[loop].setBounds(125 + (loop * 220), 300, 210, 150);
			panel_AgentName[loop].setLayout(null);
			panel_AgentName[loop].setBorder(null);
			panel_AgentName[loop].setBackground(new Color(0,0,0,25));
			panel_StartWindow.add(panel_AgentName[loop]);

			text_AgentName[loop].setBounds(15, 70, 180, 40);
			//text_AgentName[loop].setText(" MyAgent");
			text_AgentName[loop].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			text_AgentName[loop].setBackground(SystemColor.control);
			text_AgentName[loop].setFont(new Font("Segoe UI Light", Font.BOLD, 18));
			text_AgentName[loop].setToolTipText("Agent must be COMPILED First");
			panel_AgentName[loop].add(text_AgentName[loop]);
    
			label_AgentName[loop].setBounds(0, 0, 210, 50);
			label_AgentName[loop].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			//label_AgentName[loop].setText("ENTER AGENT'S NAME");
			//label_AgentName[loop].setHorizontalAlignment(SwingConstants.CENTER);
			panel_AgentName[loop].add(label_AgentName[loop]);
		}

		text_AgentName[0].setText("Berta");
		text_AgentName[1].setText("KaTonying");
		text_AgentName[2].setText("KaRobert");
		
		ImageIcon image = new ImageIcon(new ImageIcon("sponsor.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
		
		label_Sponsor.setBounds(675,475,620,300);
		label_Sponsor.setFont(new Font("Segoe UI", 620, 20));
		label_Sponsor.setForeground(Color.WHITE);
		label_Sponsor.setHorizontalTextPosition(JLabel.LEFT);
		label_Sponsor.setIcon(image);
		panel_StartWindow.add(label_Sponsor);

		label_Banner.setBounds(0, 0, 900, 300);
		label_Banner.setBorder(null);
		panel_StartWindow.add(label_Banner);
		
		button_Start.setBounds(0, 450, 900, 100);
		//button_Start.setFont(new Font("Segoe UI Light", 1, 72));
		button_Start.addActionListener(this);
		button_Start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_Start.setRolloverIcon(new ImageIcon("Cards/Start2.png"));
		button_Start.setBorder(null);
		button_Start.setFocusPainted(false);
		panel_StartWindow.add(button_Start);
		panel_StartWindow.add(label_bg);
		this.setVisible(true);
	}

	public void loadGameBoard(){

		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);

		board = new Board(player[0],player[1],player[2]);
		panel_Board = new JPanel();
		panel_Houses = new JPanel();

		board.distributeCards();

		panel_PausePanel.setBounds(0,0,900,700);
		panel_PausePanel.setVisible(false);
		panel_PausePanel.setLayout(null);
		panel_PausePanel.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		panel_Board.setBounds(0,0,900,700);
		panel_Board.setLayout(null);
		this.add(panel_PausePanel);
		this.add(panel_Board);
		//this.add(panel_Board);

		label_Pause.setBounds(350,150,470,320);
		label_Pause.setFont(new Font("Courier", 1, 40));
		label_Pause.setForeground(Color.WHITE);
		panel_PausePanel.add(label_Pause);
		
		label_ThrownCard.setBounds(445,30,120,150);
		label_ThrownCardLabel.setBounds(445,-5,200,40);
		label_ThrownCardLabel.setFont(new Font("Courier New",1,20));
		label_ThrownCardLabel.setForeground(Color.decode("#FFFFFF"));

		label_HousesLabel.setBounds(400, 180,200,40);
		label_HousesLabel.setFont(new Font("Courier New",1,20));
		label_HousesLabel.setForeground(Color.decode("#FFFFFF"));

		label_Deck.setBounds(320, 30, 120, 150);
		label_DeckCount.setBounds(330, -5, 200, 40);
		label_DeckCount.setFont(new Font("Courier New",1,20));
		label_DeckCount.setForeground(Color.decode("#FFFFFF"));

		panel_Houses.setLayout(null);
		panel_Houses.setPreferredSize(new Dimension(400, 200));
		panel_Houses.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));

		label_AgentLabel[0] = new JLabel();
		label_AgentLabel[0].setText(player[0].getPlayerName());
		label_AgentLabel[0].setBounds(10,-5,200,40);
		label_AgentLabel[0].setFont(new Font("Courier New", 1, 20));
		label_AgentLabel[0].setForeground(Color.decode("#FFFFFF"));

		label_AgentLabel[1] = new JLabel();
		label_AgentLabel[1].setText(player[1].getPlayerName());
		label_AgentLabel[1].setBounds(170,470,200,40);
		label_AgentLabel[1].setFont(new Font("Courier New", 1, 20));
		label_AgentLabel[1].setForeground(Color.decode("#FFFFFF"));

		label_AgentLabel[2] = new JLabel();
		label_AgentLabel[2].setText(player[2].getPlayerName());
		label_AgentLabel[2].setBounds(750,-5,200,40);
		label_AgentLabel[2].setFont(new Font("Courier New", 1, 20));
		label_AgentLabel[2].setForeground(Color.decode("#FFFFFF"));

		scroller = new JScrollPane(panel_Houses);
		scroller.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                panel_Houses.repaint();
                scroller.repaint();
     			panel_Board.repaint();
     			repaint();
            }
        });
		scroller.setBounds(160, 210, 600, 240);
		scroller.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		scroller.setBorder(BorderFactory.createLineBorder(new Color(1.0f,1.0f,1.0f,0.0f)));
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	

        panel_Board.add(label_AgentLabel[0]);
		panel_Board.add(label_AgentLabel[1]);
		panel_Board.add(label_AgentLabel[2]);
		panel_Board.add(label_ThrownCard);
		//panel_Board.add(label_HousesLabel);
		panel_Board.add(label_ThrownCardLabel);
		panel_Board.add(label_Deck);
		panel_Board.add(label_DeckCount);
		panel_Board.add(scroller);

		loadStats();
		startGameLoop();
	}

	public void startGameLoop(){
		hand[0] = player[0].getHand();
		hand[1] = player[1].getHand();
		hand[2] = player[2].getHand();
		prev[0] = player[0].getHand();
		prev[1] = player[1].getHand();
		prev[2] = player[2].getHand();

		for(int playerIndex = 0; playerIndex < 3; playerIndex++){
			int x1 = 10;
			int y1 = 30;
			int x2 = 170;
			int y2 = 510;
			int x3 = 760;
			int y3 = 30;
			for(int i = 0; i < hand[playerIndex].getCards().size(); i++){	
				label_Hands[playerIndex][i] = new JLabel();
				currentCard = hand[playerIndex].getCards().get(i);
				label_Hands[playerIndex][i].setIcon(new StretchIcon(getCardValue()));

				if(playerIndex == 0){
					label_Hands[0][i].setBounds(x1,y1,120,150);
					y1 += 40;
				} else if(playerIndex == 1){
					label_Hands[1][i].setBounds(x2,y2,120,150);
					x2 += 40;
				} else if(playerIndex == 2){
					label_Hands[2][i].setBounds(x3,y3,120,150);
					y3 += 40;
				}
				panel_Board.add(label_Hands[playerIndex][i], 0, 0);
			}
		}
		currentCard = null;
		loadDeckCount();
		loadStats();
		refreshStats();
		panel_Board.add(label_bg);
		repaint();
	}

	public void refresh(){
		int limit;
		if(turn%3==0){
			limit = 13;
		} else{
			limit = 12;
		}
		for(int loop2 = 0; loop2 < limit; loop2++){
			if(label_Hands[turn%3][loop2] != null){
				label_Hands[turn%3][loop2].setIcon(null);
				label_Hands[turn%3][loop2] = null;
			}
		}
		hand[0] = player[0].getHand();
		hand[1] = player[1].getHand();
		hand[2] = player[2].getHand();

		int x1 = 10;
		int y1 = 30;
		int x2 = 170;
		int y2 = 510;
		int x3 = 760;
		int y3 = 30;

		for(int i = 0; i < hand[turn%3].getCards().size(); i++){
			label_Hands[turn%3][i] = new JLabel();
			currentCard = hand[turn%3].getCards().get(i);
			label_Hands[turn%3][i].setIcon(new StretchIcon(getCardValue()));
			if(turn%3 == 0){
				label_Hands[0][i].setBounds(x1,y1,120,150);
				y1 += 40;
			} else if(turn%3 == 1){
				label_Hands[1][i].setBounds(x2,y2,120,150);
				x2 += 40;
			} else if(turn%3 == 2){
				label_Hands[2][i].setBounds(x3,y3,120,150);
				y3 += 40;
			}
			panel_Board.add(label_Hands[turn%3][i], 0, 0);
		}
		prev[0] = hand[0];
		prev[1] = hand[1];
		prev[2] = hand[2];
		currentCard = null;
		panel_Board.add(label_bg);
		panel_Board.repaint();
		repaint();
	}

	public void loadHouses(){
		if(label_Houses != null){
			for(int house = 0; house < prevHouseCount; house++){
				for(int card = 0; card  < 13; card++){
					if(label_Houses[house][card] != null){
						label_Houses[house][card].setIcon(null);
						label_Houses[house][card] = null;
					}
				}
			}
		}
		label_Houses = new JLabel[board.getHouses().size()][13];
		prevHouseCount = board.getHouses().size();
		for(int house = 0; house < board.getHouses().size(); house++){
			for(int card = 0; card  < board.getHouses().get(house).getCards().size(); card++){
				currentCard = board.getHouses().get(house).getCards().get(card);
				label_Houses[house][card] = new JLabel();
				if(currentCard != null)
					label_Houses[house][card].setIcon(new StretchIcon(getCardValue()));
				else
					label_Houses[house][card].setIcon(null);
				label_Houses[house][card].setBounds(x,y,50,70);
				panel_Houses.add(label_Houses[house][card],0,0);
				x += 20;
			}
			x += 30;
			if(largestX < x)
				largestX = x;
			if((house+1)%5 == 0){
				y += 75;
				x = 0;
			}
		}
		panel_Houses.setPreferredSize(new Dimension(largestX+30, y));
		panel_Board.add(label_bg);
		currentCard = null;
		x = 0;
		y = 0;
	}

	public void loadThrown(){
		currentCard = board.getTopCard();
		label_ThrownCard.setIcon(new StretchIcon(getCardValue()));
	}

	public void loadDeckCount(){
		deckCount = board.getDeckCount();
		label_DeckCount.setText("Deck : " + deckCount);
	}

	public String getCardValue(){
		int value = currentCard.getValue();
		int suit = currentCard.getSuit();
		String cardString = "Cards/";

		switch(value){
			case 1: {
                cardString = cardString + "ace_";
                break;
            }
            case 11: {
                cardString = cardString + "jack_";
                break;
            }
            case 12: {
                cardString = cardString + "queen_";
                break;
            }
            case 13: {
                cardString = cardString + "king_";
                break;
            }
            default: {
                cardString = cardString + value + "_";
                break;
            }
		}
		switch(suit){
            case 1: {
                cardString = cardString + "of_spades";
                break;
            }
            case 2: {
                cardString = cardString + "of_hearts";
                break;
            }
            case 3: {
                cardString = cardString + "of_diamonds";
                break;
            }
            case 4: {
                cardString = cardString + "of_clubs";
                break;
            }
		}
		cardString = cardString + ".png";
		System.out.println(cardString);
		return cardString;
	}

	public void loadStats(){
		label_bg2.setBounds(0,0,200,60);
		label_bg2.setBorder(null);
		panel_Stats.setBounds(899,0,204,695);
		panel_Stats.setLayout(null);
		panel_Stats.setBorder(null);
		//panel_Stats.setBorder(null);
		panel_Stats.setBackground(Color.decode("#118d3a"));
		this.add(panel_Stats);
		panel_Stats.add(label_bg2);
		panel_Stats.repaint();
		repaint();

		int x = 10;
		int y = 70;

		for(int loop = 0; loop < 3; loop++){
			if(turn == 3){
				label_StatsAgentName[loop] = new JLabel();
				label_StatsErrorsLabel[loop] = new JLabel(new ImageIcon("Cards/err.png"));
				label_StatsErrorsLabel[loop].setToolTipText("Number of Errors");
				label_StatsErrorsCount[loop] = new JLabel();
				label_StatsHandsLabel[loop] = new JLabel(new ImageIcon("Cards/card.png"));
				label_StatsHandsLabel[loop].setToolTipText("Number of Cards");
				label_StatsHandsCount[loop] = new JLabel();
				label_StatsValueLabel[loop] = new JLabel(new ImageIcon("Cards/hand.png"));
				label_StatsValueLabel[loop].setToolTipText("Current Card Value");
				label_StatsValueCount[loop] = new JLabel();
				label_StatsWinsLabel[loop] = new JLabel(new ImageIcon("Cards/win.png"));
				label_StatsWinsLabel[loop].setToolTipText("Number of Wins");
				label_StatsWinsCount[loop] = new JLabel();
			}

			label_StatsAgentName[loop].setBounds(x,y,170,40);
			label_StatsAgentName[loop].setFont(new Font("Courier New", 1, 18));
			label_StatsAgentName[loop].setForeground(Color.WHITE);

			label_StatsErrorsLabel[loop].setBounds(x,y+32,20,20);
			label_StatsErrorsLabel[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsErrorsCount[loop].setBounds(x+120,y+25,170,40);
			label_StatsErrorsCount[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsErrorsCount[loop].setForeground(Color.WHITE);

			label_StatsHandsLabel[loop].setBounds(x,y+52,20,20);
			label_StatsHandsLabel[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsHandsCount[loop].setBounds(x+120,y+45,170,40);
			label_StatsHandsCount[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsHandsCount[loop].setForeground(Color.WHITE);

			label_StatsValueLabel[loop].setBounds(x,y+72,20,20);
			label_StatsValueLabel[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsValueCount[loop].setBounds(x+120,y+65,170,40);
			label_StatsValueCount[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsValueCount[loop].setForeground(Color.WHITE);

			label_StatsWinsLabel[loop].setBounds(x,y+92,20,20);
			label_StatsWinsLabel[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsWinsCount[loop].setBounds(x+120,y+85,170,40);
			label_StatsWinsCount[loop].setFont(new Font("Courier New", 1, 14));
			label_StatsWinsCount[loop].setForeground(Color.WHITE);

			switch(loop){
				case 0: label_StatsWinsCount[loop].setText(Integer.toString(win1)); break;
				case 1: label_StatsWinsCount[loop].setText(Integer.toString(win2)); break;
				case 2: label_StatsWinsCount[loop].setText(Integer.toString(win3)); break;
			}

			panel_Stats.add(label_StatsAgentName[loop]);
			panel_Stats.add(label_StatsErrorsLabel[loop]);
			panel_Stats.add(label_StatsErrorsCount[loop]);
			panel_Stats.add(label_StatsHandsLabel[loop]);
			panel_Stats.add(label_StatsHandsCount[loop]);
			panel_Stats.add(label_StatsValueLabel[loop]);
			panel_Stats.add(label_StatsValueCount[loop]);
			panel_Stats.add(label_StatsWinsLabel[loop]);
			panel_Stats.add(label_StatsWinsCount[loop]);

			x = 10;
			y += 175;
		}

		button_StartGame.setBounds(32,565,155,50);
		button_StartGame.setFocusPainted(false);
		button_StartGame.setContentAreaFilled(false);
		button_StartGame.setBorder(null);
		button_StartGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_StartGame.setRolloverIcon(new ImageIcon("Cards/play2.png"));
		button_StartGame.addActionListener(this);
		panel_Stats.add(button_StartGame);

		button_PauseGame.setBounds(32,565,155,50);
		button_PauseGame.setFocusPainted(false);
		button_PauseGame.setContentAreaFilled(false);
		button_PauseGame.setEnabled(false);
		button_PauseGame.setVisible(false);
		button_PauseGame.setBorder(null);
		button_PauseGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_PauseGame.setRolloverIcon(new ImageIcon("Cards/pause2.png"));
		button_PauseGame.addActionListener(this);
		panel_Stats.add(button_PauseGame);

		button_Exit.setBounds(32,605,155, 50);
		button_Exit.setFocusPainted(false);
		button_Exit.setContentAreaFilled(false);
		button_Exit.setBorder(null);
		button_Exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_Exit.setRolloverIcon(new ImageIcon("Cards/exit2.png"));
		button_Exit.addActionListener(this);
		panel_Stats.add(button_Exit);
	}

	public void refreshStats(){
		for(int loop = 0; loop < 3; loop++){
			label_StatsAgentName[loop].setText(player[loop].getPlayerName());
			label_StatsErrorsCount[loop].setText(Integer.toString(player[loop].getErrorCtr()));
			label_StatsHandsCount[loop].setText(Integer.toString(player[loop].getCardCount()));
			label_StatsValueCount[loop].setText(Integer.toString(player[loop].getMinValue()));
			//label_StatsWinsCount[loop].setText(player[loop].);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button_Start){
			player[0] = new Player(text_AgentName[0].getText());
			player[1] = new Player(text_AgentName[1].getText());
			player[2] = new Player(text_AgentName[2].getText());

			if( player[0] != null && player[1] != null && player[2] != null){
				panel_StartWindow.setVisible(false);
				loadGameBoard();
			}

		} else if(e.getSource() == button_Exit){
			exitClicked = true;
		} else if(e.getSource() == button_StartGame){
			button_StartGame.setVisible(false);
			button_PauseGame.setVisible(true);
			if(!gameLoop.isRunning()){
				button_StartGame.setEnabled(false);
				button_PauseGame.setEnabled(true);
				panel_PausePanel.setVisible(false);
				gameLoop.start();
			}
		} else if(e.getSource() == button_PauseGame){
			button_StartGame.setVisible(true);
			button_PauseGame.setVisible(false);
			if(gameLoop.isRunning()){
				button_StartGame.setEnabled(true);
				button_PauseGame.setEnabled(false);
				panel_PausePanel.setVisible(true);
				gameLoop.stop();
			}
		}

	}

	class GameLoop implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(!end){
				board.updatePlayers();
				if(turn%3==0){
					refreshStats();
					currentCount = player[0].getCardCount();
					board.move();
					refreshStats();
				} else if(turn%3==1){
					refreshStats();
					currentCount = player[1].getCardCount();
					board.move();
					refreshStats();
				} else if(turn%3==2){
					refreshStats();
					currentCount = player[2].getCardCount();
					board.move();
					refreshStats();
				}
				refresh();
				loadDeckCount();
				loadThrown();
				loadHouses();
				refreshStats();
				panel_Board.repaint();
				repaint();					
				if(board.winner != null){
					if(board.tongits){
						JOptionPane.showMessageDialog(null, board.winner + "TONGITS!", "WINNER", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Cards/Win2.png"));
					} else {
						JOptionPane.showMessageDialog(null, board.winner + "WINS THE GAME!", "WINNER", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Cards/Win2.png"));
					}
					if(board.winner == player[0]){
						win1++;
					} else if(board.winner == player[1]){
						win2++;
					} else if(board.winner == player[2]){
						win3++;
					}
					end = true;
				} else{
					turn++;
				}
			}
			if(end){
				int response = JOptionPane.showConfirmDialog(null, "START A NEW GAME?", "TONGITS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("Cards/Ask.png") );
				if(response == JOptionPane.YES_OPTION){
					exitClicked = true;
				}
				gameLoop.stop();
			}
		}
	}

	public Board board;
	public Thread game;
	public Player[] player = new Player[3];
	public JPanel panel_Board = new JPanel();
	private JLabel label_Banner = new JLabel(new ImageIcon("Cards/Banner.png"));
	public JPanel panel_Houses = new JPanel();
	public JPanel panel_StartWindow = new JPanel();
	public JPanel panel_Stats = new JPanel();
	public JPanel panel_PausePanel = new JPanel();
	public JPanel[] panel_AgentName = new JPanel[3];
	private JButton button_Start = new JButton(new ImageIcon("Cards/Start1.png"));
	public JScrollPane scroller = new JScrollPane();
	public JTextField[] text_AgentName = new JTextField[3];
	public JLabel[] label_AgentName = new JLabel[3];
	public JLabel[] label_AgentLabel = new JLabel[3];
	public JLabel[][] label_Hands = new JLabel[3][13];
	public JLabel[][] label_Houses;
	public JLabel label_Deck = new JLabel(new StretchIcon("Cards/back.png"));
	public JLabel label_DeckCount = new JLabel();
	private JLabel label_bg = new JLabel(new ImageIcon("Cards/Background2.jpg"));
	private JLabel label_bg2 = new JLabel(new ImageIcon("Cards/stats.png"));
	public JLabel label_ThrownCard = new JLabel();
	public JLabel label_HousesLabel = new JLabel("Houses");
	public JLabel label_ThrownCardLabel = new JLabel("Thrown Card");
	public JLabel[] label_StatsAgentName = new JLabel[3];
	public JLabel[] label_StatsErrorsLabel = new JLabel[3];
	public JLabel[] label_StatsErrorsCount = new JLabel[3];
	public JLabel[] label_StatsHandsLabel = new JLabel[3];
	public JLabel[] label_StatsHandsCount = new JLabel[3];
	public JLabel[] label_StatsValueLabel = new JLabel[3];
	public JLabel[] label_StatsValueCount = new JLabel[3];
	public JLabel[] label_StatsWinsLabel = new JLabel[3];
	public JLabel[] label_StatsWinsCount = new JLabel[3];
	private JButton button_Exit = new JButton(new ImageIcon("Cards/exit.png"));
	private JButton button_StartGame = new JButton(new ImageIcon("Cards/Play1.png"));
	public JButton button_PauseGame = new JButton(new ImageIcon("Cards/pause1.png")); 
	public JButton button_NewGame = new JButton("NEW GAME");
	public JLabel label_Pause = new JLabel("PAUSED");

	public CardSet[] hand = new CardSet[3];
	public CardSet[] prev = new CardSet[3];
	public Card currentCard;
	public Card thrownCard;
	public int currentCount;
	public int turn = 3;
	public int deckCount;
	public int prevHouseCount;
	public int x = 0;
	public int y = 0;
	public int largestX = 0;
	public boolean end = false;
	public boolean exitClicked = false;
	public boolean pauseClicked = false;
	public int win1;
	public int win2;
	public int win3;
	
	private JLabel label_Sponsor = new JLabel("Sponsored by: ");
	public Timer gameLoop = new Timer(20, new GameLoop());
}