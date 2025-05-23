
/**
 * Lead Author(s):
 * @author Lyle Steger
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * https://www.youtube.com/watch?v=QEF62Fm81h4
 *  
 * Cards Citations:
 * One Schoolhouse. (2023). Card images provided in AP Computer Science A course materials.
 * https://www.freepik.com/free-photos-vectors/playing-card-design(Back of card picture)
 *  
 * Version/date: 2.0 
 * 
 * Responsibilities of class: Tests to seem if the game works as intended
 * 
 * 
 *
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

//currently editing this
public class BlackjackTwistGameGui {

	private JFrame frame;// BlackjackTwistGameGui HAS-A frame where the game will be
	private JPanel panel;// BlackjackTwistGameGui HAS-A Panel where widgets will be added
	private JLabel background;// BlackjackTwistGameGui HAS-A background to make the game more appeasing
	private JTextArea textArea;// BlackjackTwistGameGui HAS-A textArea where an in game terminal will display
								// information about the game
	private JButton hitButton;// BlackjackTwistGameGui HAS-A hit button where the user can draw a card
	private JButton standButton;// BlackjackTwistGameGui HAS-A stand button where the user can end their turn
	private BlackjackTwistGame game;// BlackjackTwistGameGui HAS-A hit game logic that it follows
	private ArrayList<JLabel> userHandPics;// BlackjackTwistGameGui HAS-MANY different GUI cards in the user's hand
	private ArrayList<JLabel> dealerHandPics;// BlackjackTwistGameGui HAS-MANY different GUI cards in the dealer's hand
	private Hashtable<JButton, JLabel> powerButtonGuiCardPairs;// BlackjackTwistGameGui HAS-MANY power buttons
																// associated with specific power cards
	private Hashtable<JLabel, Card> powerGuiCardCardPairs;// BlackjackTwistGameGui HAS-MANY power buttons
															// associated with specific power cards
	private JLabel userHealthLabel;// BlackjackTwistGameGui HAS-A label where it displays the user's health
	private JLabel dealerHealthLabel;// BlackjackTwistGameGui HAS-A label where it displays the dealer's health
	private JLabel dealerName;// BlackjackTwistGameGui HAS-A label where it displays the dealer's name
	private boolean revealedDealerCards;// BlackjackTwistGameGui knows when to show the all the cards in the dealer's
										// hand
	private JButton riskButton;// BlackjackTwistGameGui HAS-A button where the user can activate the risk power
						// card
	private JButton insightButton;// BlackjackTwistGameGui HAS-A button where the user can activate the insight
							// power card
	private JButton controlButton;// BlackjackTwistGameGui HAS-A button where the user can activate the control
							// power card
	private JButton cheatsButton;// BlackjackTwistGameGui HAS-A button where the user can activate cheats giving
							// them information they
							// shouldn't know

	public BlackjackTwistGameGui() {
		revealedDealerCards = false;
		riskButton = new JButton("Risk");
		insightButton = new JButton("Insight");
		controlButton = new JButton("Control");
		userHandPics = new ArrayList<>();
		dealerHandPics = new ArrayList<>();
		powerButtonGuiCardPairs = new Hashtable<>();
		powerGuiCardCardPairs = new Hashtable<>();

		// Creates the frame for the BlackjackTwistgame
		frame = new JFrame("Blackjack Game");
		frame.setSize(1500, 900); // Set a large enough frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Creates the panel which widgets will be add to
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1100, 800));
		frame.add(panel);

		// Creates the background of the video game
		String picUrl = "/Background/GameBackground.jpeg";
		ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource(picUrl));
		Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		backgroundImage = backgroundImage.getScaledInstance(1550, 900, Image.SCALE_SMOOTH);
		ImageIcon scaledBackgroundImage = new ImageIcon(backgroundImage);
		background = new JLabel(scaledBackgroundImage);
		background.setBounds(-270, -50, 2000, 1000);
		panel.add(background);

		// Creates the text area where the terminal will display info about the game
		textArea = new JTextArea(10, 50);
		textArea.setBackground(new Color(29, 46, 53));
		textArea.setForeground(new Color(130, 230, 255));
		textArea.setEditable(false);
		// Allows the user to scroll on the terminal to accommodate the potential large
		// amounts of information
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.getVerticalScrollBar().setBackground(new Color(29, 46, 53));
		scrollPane.getHorizontalScrollBar().setBackground(new Color(29, 46, 53));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.DARK_GRAY;
			}
		});
		scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.DARK_GRAY;
			}
		});
		scrollPane.setBounds(10, 90, 400, 120);
		panel.add(scrollPane);

		// Labels to display the Dealer's name
		dealerName = new JLabel("Dealer");
		dealerName.setForeground(new Color(130, 230, 255));
		dealerName.setBounds(730, 370, 300, 25);
		panel.add(dealerName);

		// Labels to display the User's current health
		userHealthLabel = new JLabel("User's Health: 20");
		userHealthLabel.setForeground(new Color(130, 230, 255));
		userHealthLabel.setBounds(1200, 155, 300, 25);
		panel.add(userHealthLabel);

		// Labels to display the Dealer's current health
		dealerHealthLabel = new JLabel("Dealer's Health: 20");
		dealerHealthLabel.setForeground(new Color(130, 230, 255));
		dealerHealthLabel.setBounds(1200, 125, 300, 25);
		panel.add(dealerHealthLabel);

		// Button enabling the user to switch between the cheat terminal and the normal
		// terminal
		cheatsButton = new JButton("Help");
		cheatsButton.setBounds(135, 250, 120, 40);
		cheatsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getExtraInfo() == false) {
					cheatsButton.setText("Exit");
					game.setExtraInfo(true);
				} else {
					cheatsButton.setText("Help");
					game.setExtraInfo(false);
				}
				updateGameStatus();
			}
		});
		panel.add(cheatsButton);

		// Button enabling the user to draw a card
		hitButton = new JButton("Hit");
		hitButton.setBounds(1200, 810, 120, 40);
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.userHit();
				game.dealerDecision();
				game.checkRoundOver();
				updateGameStatus();
			}
		});
		panel.add(hitButton);

		// Button enabling the user to end their turn
		standButton = new JButton("Stand");
		standButton.setBounds(1330, 810, 120, 40);
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.userStand();
				game.dealerDecision();
				game.checkRoundOver();
				updateGameStatus();
			}
		});
		panel.add(standButton);
		updateBackground();
		panel.revalidate();
	}

	/**
	 * this is responsible for adding the gui card version of a card to the user's
	 * hand
	 * 
	 * card image citation:
	 * One Schoolhouse. (2023). Card images provided in AP Computer Science A course materials.
	 * 
	 * @param message what information we want to record in the file
	 */
	private void userAddCardGui(Card currCard) {
		String cardRank = currCard.getRank();
		String cardSuit = currCard.getSuit();
		int cardValue = currCard.getValue();
		String picUrl;
		ImageIcon cardImage;
		// if the card's value is not 0 it has is a normal card
		if (cardValue != 0) {
			// Adds the corresponding normal card image based on the passed card's rank suit
			// and value
			picUrl = "/cards/" + cardRank + cardSuit + ".GIF";
			cardImage = new ImageIcon(getClass().getResource(picUrl));
			JLabel cardLabel = new JLabel(cardImage);
			userHandPics.add(cardLabel);
			panel.add(cardLabel);
		}
		// The card value is 0 therefore it is a power card
		else {
			// Adds the power card image with it's own power button to activate it
			picUrl = "/cards/JO.GIF";
			cardImage = new ImageIcon(getClass().getResource(picUrl));
			JButton powerButton = new JButton("Use");
			JLabel cardLabel = new JLabel(cardImage);
			userHandPics.add(cardLabel);
			powerButtonGuiCardPairs.put(powerButton, cardLabel);
			powerGuiCardCardPairs.put(cardLabel, currCard);
			panel.add(powerButton);
			panel.add(cardLabel);
			// power buttons that allows the user to choose an ability
			powerButton.addActionListener(new ActionListener() {
				// power button holds onto the label of the power card
				JLabel correspondingGuiCard = cardLabel;

				// When clicked power card activates or deactivates
				public void actionPerformed(ActionEvent e) {
					// Activates power card
					if (powerButton.getText().equals("Use")) {
						// Disable the power button of all power cards except the activated one
						for (JButton pButton : powerButtonGuiCardPairs.keySet()) {
							if (powerButton != pButton) {
								pButton.setEnabled(false);
							}
						}
						powerButton.setText("Cancel");
						// Image of activated power card
						correspondingGuiCard.setIcon(new ImageIcon(getClass().getResource("/cards/JOS.GIF")));
						hitButton.setEnabled(false);
						standButton.setEnabled(false);
						// Links up the power button with the risk, control, and insight buttons
						linkPowerCardOptions(powerButton);
					}
					// Deactivates power card
					else {
						// Enables the power button of all power cards except the activated one
						for (JButton pButton : powerButtonGuiCardPairs.keySet()) {
							if (powerButton != pButton) {
								pButton.setEnabled(true);
							}
						}
						powerButton.setText("Use");
						// Image of deactivated power card
						correspondingGuiCard.setIcon(new ImageIcon(getClass().getResource("/cards/JO.GIF")));
						hitButton.setEnabled(true);
						standButton.setEnabled(true);
						// Removes the buttons to select power cards ability from the panel
						panel.remove(riskButton);
						panel.remove(insightButton);
						panel.remove(controlButton);

					}
					// update the background to make sure it is in the back
					updateBackground();
				}
			});
			// align power buttons so it is underneath the respective power card
			alignPowerButton();
		}
		// align the players cards so it is centered on the screen
		alignUserCards();
		// align the power buttons to be underneath the respective power card
		alignPowerButton();
	}

	/**
	 * this is responsible aligning the power card underneath the respective power
	 * card
	 */
	private void alignPowerButton() {
		// positions power button under their respective power cards
		for (JButton pButton : powerButtonGuiCardPairs.keySet()) {
			pButton.setBounds(powerButtonGuiCardPairs.get(pButton).getX() + 20, 770, 80, 20);
		}
		// update the background to make sure it is in the back
		updateBackground();

	}

	/**
	 * this is responsible aligning the power card so it is centered on the screen
	 */
	private void alignUserCards() {
		int playerGuiCardSize = userHandPics.size();
		int xFirstCardCoord;
		// depending on whether the user has an odd or even amount of cards
		// the first card's x coordinate is different
		if (playerGuiCardSize % 2 == 1) {
			xFirstCardCoord = 687 - playerGuiCardSize / 2 * 120;
		} else {
			xFirstCardCoord = 687 - playerGuiCardSize / 2 * 120 + 60;
		}
		// based on the xFirstCardCoord place the rest of the cards down
		for (int i = 0; i < playerGuiCardSize; i++) {
			userHandPics.get(i).setBounds(xFirstCardCoord + (i) * 120, 680, 120, 100);
		}
		// update the background to make sure it is in the back
		updateBackground();
	}

	/**
	 * this is responsible for linking the risk,control,and insight button to a card
	 * and power button\
	 * 
	 * @param powerCardButton the power button we are linking to the 3 buttons
	 */
	private void linkPowerCardOptions(JButton powerCardButton) {
		riskButton.setBounds(490, 400, 120, 40);
		insightButton.setBounds(690, 400, 120, 40);
		controlButton.setBounds(890, 400, 120, 40);
		panel.add(riskButton);
		panel.add(insightButton);
		panel.add(controlButton);
		// Removes any listener's previously attached to the risk, insight, and control
		// buttons
		ActionListener[] riskListeners = riskButton.getActionListeners();
		for (ActionListener listener : riskListeners) {
			riskButton.removeActionListener(listener);
		}
		ActionListener[] insightListeners = insightButton.getActionListeners();
		for (ActionListener listener : insightListeners) {
			insightButton.removeActionListener(listener);
		}
		ActionListener[] controlListeners = controlButton.getActionListeners();
		for (ActionListener listener : controlListeners) {
			controlButton.removeActionListener(listener);
		}
		panel.revalidate();
		panel.repaint();
		// Adds a action listener to all 3 buttons
		riskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Loser takes double damage
				game.setAmountDamageTaken(game.getAmountDamageTaken() * 2);
				game.getUser().getHand()
						.remove(powerGuiCardCardPairs.get(powerButtonGuiCardPairs.get(powerCardButton)));
				// removes the power card, power card label, and power button
				panel.remove(powerCardButton);
				panel.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerGuiCardCardPairs.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerButtonGuiCardPairs.remove(powerCardButton);
				panel.remove(riskButton);
				panel.remove(insightButton);
				panel.remove(controlButton);
				// removes the power card, power card label, and power button
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				// writes about the players using the power card into the game text file
				game.writeToGameStatsFile("Player Activates Risk PowerCard!");
				game.writeToGameStatsFile("");
				// Updates the status of the game
				updateGameStatus();
			}
		});
		insightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// User gains insight about the current round
				game.setSendFutureMessage(true);
				game.getUser().getHand()
						.remove(powerGuiCardCardPairs.get(powerButtonGuiCardPairs.get(powerCardButton)));
				// removes the power card, power card label, and power button
				panel.remove(powerCardButton);
				panel.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerGuiCardCardPairs.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerButtonGuiCardPairs.remove(powerCardButton);
				panel.remove(riskButton);
				panel.remove(insightButton);
				panel.remove(controlButton);
				// removes the power card, power card label, and power button
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				// writes about the players using the power card into the game text file
				game.writeToGameStatsFile("Player Activates Insight PowerCard!");
				game.writeToGameStatsFile("");
				// Updates the status of the game
				updateGameStatus();
			}
		});
		controlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Both players can not draw cards this round
				game.disableHitOption(true);
				game.getUser().getHand()
						.remove(powerGuiCardCardPairs.get(powerButtonGuiCardPairs.get(powerCardButton)));
				// removes the power card, power card label, and power button
				panel.remove(powerCardButton);
				panel.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerGuiCardCardPairs.remove(powerButtonGuiCardPairs.get(powerCardButton));
				powerButtonGuiCardPairs.remove(powerCardButton);
				panel.remove(riskButton);
				panel.remove(insightButton);
				panel.remove(controlButton);
				// removes the power card, power card label, and power button
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				// writes about the players using the power card into the game text file
				game.writeToGameStatsFile("Player Activates Control PowerCard!");
				game.writeToGameStatsFile("");
				// Updates the status of the game
				updateGameStatus();
			}
		});
		panel.revalidate();
		panel.repaint();
	}

	// Used for debugging
	private void printCurrentButtons() {
		Component[] components = panel.getComponents();
		for (Component component : components) {
			System.out.println(component);
		}
	}

	/**
	 * this is responsible for updating the gui component of the dealer's hand
	 */
	private void dealerCardsGui() {
		String cardRank;
		String cardSuit;
		String picUrl;
		ImageIcon cardImage;
		int dealerHandSize = game.getDealer().getHand().size();
		// loops through the dealer's hand and add the corresponding imaged to the
		// dealer's gui hand
		for (int i = 0; i < dealerHandSize; i++) {
			// Only the first card is revealed other wise you are just looking at the back
			// of the card
			if (i == 0 || revealedDealerCards) {
				cardRank = game.getDealer().getHand().get(i).getRank();
				cardSuit = game.getDealer().getHand().get(i).getSuit();
				picUrl = "/cards/" + cardRank + cardSuit + ".GIF";
			} else {
				picUrl = "/cards/back1.GIF";
			}
			cardImage = new ImageIcon(getClass().getResource(picUrl));
			JLabel cardLabel = new JLabel(cardImage);
			dealerHandPics.add(cardLabel);
			panel.add(cardLabel);
			// Aligns the dealer's card so it is centered
			alignDealerCards();
		}
	}

	/**
	 * this is responsible for aligning the gui of the dealers cards
	 */
	private void alignDealerCards() {
		int dealerGuiCardSize = dealerHandPics.size();
		int xFirstCardCoord;
		// Different alignments based on whether there is an even or odd amount of cards
		if (dealerGuiCardSize % 2 == 1) {
			xFirstCardCoord = 687 - dealerGuiCardSize / 2 * 120;
		} else {
			xFirstCardCoord = 687 - dealerGuiCardSize / 2 * 120 + 60;
		}
		// Position the cards based on the calculated first card's x coordinate
		for (int i = 0; i < dealerGuiCardSize; i++) {
			dealerHandPics.get(i).setBounds(xFirstCardCoord + (i) * 120, 520, 120, 100);
		}
		// update the background so it remains in the back
		updateBackground();
	}

	/**
	 * clears the gui of the dealer's hand to update it
	 */
	private void dealerUpdateCardsGui() {
		int dealerHandSize = dealerHandPics.size();
		for (int i = 0; i < dealerHandSize; i++) {
			panel.remove(dealerHandPics.get(i));
		}
		dealerHandPics.clear();
		dealerCardsGui();
	}

	/**
	 * temporarily reveals the dealer's hand to the user after the round is over
	 * Citation: https://www.youtube.com/watch?v=QEF62Fm81h4 This video helped me
	 * write this method
	 */
	public void revealDealerCards() {
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		revealedDealerCards = true;
		dealerUpdateCardsGui();
		// creates a timer to count how long to show the cards
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int timeRevealed = 6;

			@Override
			public void run() {
				timeRevealed--;
				if (timeRevealed <= 0) {
					// stops revealing cards after timer reaches 0 and proceeds to next round
					timer.cancel();
					revealedDealerCards = false;
					hitButton.setEnabled(true);
					standButton.setEnabled(true);
					updateGameStatus();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);

	}

	/**
	 * clears the gui for the user's hand to update it
	 */
	private void updateUserCardsGui() {
		// Removes all card labels from user's hand
		for (JLabel cardLabel : userHandPics) {
			panel.remove(cardLabel);
		}
		// Removes all card labels from user's hand
		for (JButton powerButton : powerButtonGuiCardPairs.keySet()) {
			panel.remove(powerButton);
		}
		userHandPics.clear();
		powerGuiCardCardPairs.clear();
		powerButtonGuiCardPairs.clear();
		// Readds the update gui cards
		for (int i = 0; i < game.getUser().getHand().size(); i++) {
			userAddCardGui(game.getUser().getHand().get(i));
		}
	}

	/**
	 * updates the gui game status
	 */
	private void updateGameStatus() {
		String newStatus = game.getGameStatus();
		// If the game is over pop up a window to show who won the game
		if (game.isGameOver()) {
			userHealthLabel.setText("User's Health: " + game.getUser().getHealth());
			dealerHealthLabel.setText("Dealer's Health: " + game.getDealer().getHealth());
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			cheatsButton.setEnabled(false);
			JOptionPane.showMessageDialog(new JFrame(), newStatus, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			// allows user to close the game
			System.exit(0);
		}
		// Updates the gui for the round being over
		else if (game.getRoundOver()) {
			// prevents the user from pressing buttons when revealing dealer's card
			if (game.getIsHitDisabled() == true) {
				game.disableHitOption(false);
			}
			hitButton.setEnabled(false);
			standButton.setEnabled(false);
			cheatsButton.setEnabled(false);
			for (JButton pButton : powerButtonGuiCardPairs.keySet()) {
				pButton.setEnabled(false);
			}
			textArea.setText(newStatus);
			revealDealerCards();
			game.updateGameStatus();
		}
		// Updates the gui for the ongoing round
		else {
			hitButton.setEnabled(true);
			standButton.setEnabled(true);
			cheatsButton.setEnabled(true);
			for (JButton pButton : powerButtonGuiCardPairs.keySet()) {
				pButton.setEnabled(true);
			}
			game.updateGameStatus();
			if (!textArea.getText().equals(newStatus)) {
				textArea.setText(newStatus);
			}

			// Update player information (health, score, cards)
			userHealthLabel.setText("User's Health: " + game.getUser().getHealth());
			dealerHealthLabel.setText("Dealer's Health: " + game.getDealer().getHealth());

			// Update dealer information (only show one card initially)
			updateUserCardsGui();
			dealerUpdateCardsGui();
		}
		updateBackground();

	}

	/**
	 * makes sure the background label stays in the back
	 */
	public void updateBackground() {
		Component[] components = panel.getComponents();
		panel.setComponentZOrder(background, components.length - 1);
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * starts the game
	 */
	public void startGame(String playerName) {
		game = new BlackjackTwistGame(playerName);
		game.startRound();
		userAddCardGui(game.getUser().getHand().get(0));
		userAddCardGui(game.getUser().getHand().get(1));
		updateGameStatus();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BlackjackTwistGameGui gui = new BlackjackTwistGameGui();
				gui.startGame("Player1"); // Start game with player name
			}
		});
	}
}
