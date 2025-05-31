import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Lead Author(s):
 * 
 * @author Lyle Steger <<add additional lead authors here, with a full first and
 *         last name>>
 * 
 *         Other contributors: <<add additional contributors (mentors, tutors,
 *         friends) here, with contact information>>
 * 
 *         References: Morelli, R., & Walde, R. (2016). Java, Java, Java:
 *         Object-Oriented Problem Solving. Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * 
 *         Version/date: 2.0
 * 
 *         Responsibilities of class: Logic of how the game works
 * 
 *
 */

public class BlackjackTwistGame {
	private int roundNum;// BlackjackTwist HAS-A number of rounds played
	private File gameStatFile;// BlackjackTwist HAS-A game file storing the how each round turned out
	private Deck deck;// BlackjackTwist HAS-A deck of cards
	private User user;// BlackjackTwist HAS-A user
	private Dealer dealer;// BlackjackTwist HAS-A dealer
	private boolean gameOver;// BlackjackTwist HAS-A indicator of when the game is over
	private boolean roundOver;// BlackjackTwist HAS-A indicator of when the round is over
	private String userLastMove;// BlackjackTwist HAS-A tracker of the user's last move
	private boolean userTurn;// BlackjackTwist HAS-A indicator of when it is the user's turn
	private String dealerLastMove;// BlackjackTwist HAS-A tracker for the dealer most recent move
	private int amountDamageTaken;// BlackjackTwist HAS-A tracker of how much damage the loser will take
	private boolean disabledHitOption;// BlackjackTwist HAS-A tracker for when the hit option has been disabled
	private boolean sendFutureMessage;// BlackjackTwist HAS-A tracker indicating when to send player an insightful
										// message
	private boolean extraInfo;// BlackjackTwist HAS-A tracker indicating when allow the user's to turn on
								// cheats

	/**
	 * Constructor method for the rank of the BlackjackCard
	 * 
	 * @param userName the user's name
	 */
	public BlackjackTwistGame(String userName) {
		gameStatFile = new File("GameStats.txt");
		clearGameStatsFile();
		roundNum = 0;
		deck = new Deck();
		user = new User(userName);
		dealer = new Dealer("Computer");
		gameOver = false;
		roundOver = false;
		userLastMove = "None";
		dealerLastMove = "None";
		amountDamageTaken = 5;
		disabledHitOption = false;
		sendFutureMessage = false;
		extraInfo = false;
	}

	/**
	 * getter method for the deck of cards
	 * 
	 * @return the desired rank of this card
	 */
	public Deck getGameDeck() {
		return this.deck;
	}

	/**
	 * getter method for the user in this game
	 * 
	 * @return the user of this game
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * getter method for the dealer in this game
	 * 
	 * @return the dealer of this game
	 */
	public Dealer getDealer() {
		return this.dealer;
	}

	/**
	 * getter method to check if the game is over
	 * 
	 * @return whether or not the game is over
	 */
	public boolean getGameOver() {
		return this.gameOver;
	}

	/**
	 * getter method to check if the round is over
	 * 
	 * @return whether or not the round is over
	 */
	public boolean getRoundOver() {
		return this.roundOver;
	}

	/**
	 * getter method for the last move of the user
	 * 
	 * @return the last move the user made
	 */
	public String getUserLastMove() {
		return this.userLastMove;
	}

	/**
	 * getter method for the last move of the dealer
	 * 
	 * @return the last move the dealer made
	 */
	public String getDealerLastMove() {
		return this.dealerLastMove;
	}

	/**
	 * getter method for the amount of damage the loser takes
	 * 
	 * @return the amount of damage the loser will take
	 */
	public int getAmountDamageTaken() {
		return this.amountDamageTaken;
	}

	/**
	 * getter method for whether to send a message from the future
	 * 
	 * @return the whether to send the message or not
	 */
	public boolean getSendFutureMessage() {
		return this.sendFutureMessage;
	}

	/**
	 * getter method to see if the hit option is disabled
	 * 
	 * @return the desired rank of this card
	 */
	public boolean getIsHitDisabled() {
		return disabledHitOption;
	}

	/**
	 * getter method to see if extra information should be provided to the user
	 * 
	 * @return the desired rank of this card
	 */
	public boolean getExtraInfo() {
		return this.extraInfo;
	}

	/**
	 * setter method for the last move of the user
	 * 
	 * @param the last move the dealer made
	 */
	public void setUserLastMove(String move) {
		this.userLastMove = move;
	}

	/**
	 * setter method for the last move of the dealer
	 * 
	 * @return the last move the dealer made
	 */
	public void setDealerLastMove(String move) {
		this.dealerLastMove = move;
	}

	/**
	 * setter method to decide whether to send the message or not
	 * 
	 * @param value whether to send the message or not
	 */
	public void setSendFutureMessage(boolean value) {
		this.sendFutureMessage = value;
	}

	/**
	 * setter method for the amount of damage the loser takes
	 * 
	 * @param amount the amount of damage the loser will take
	 */
	public void setAmountDamageTaken(int amount) {
		this.amountDamageTaken = amount;
	}

	/**
	 * setter method to allow the users to get extra info about the game
	 * 
	 * @param bool whether or not to show the extra info
	 */
	public void setExtraInfo(Boolean bool) {
		this.extraInfo = bool;
	}

	/**
	 * setter method to allow the users to get extra info about the game
	 * 
	 * @param bool whether or not to disable the hit option
	 */
	public void disableHitOption(boolean bool) {
		disabledHitOption = bool;
	}

	/**
	 * mutator method that adds the additional damage the loser takes
	 * 
	 * @param amount the desired additional amount of damage the loser will take
	 */
	public void addAmountDamageTaken(int amount) {
		this.amountDamageTaken += amount;
	}

	/**
	 * sets the start of the game
	 */
	public void startRound() {
		roundNum++;
		// writes messages to the game stats file
		writeToGameStatsFile("Round: " + roundNum);
		writeToGameStatsFile("");
		Card userCard1 = deck.dealCard();
		Card userCard2 = deck.dealCard();
		// Adds 2 cards to the User's hand
		try {
			user.addCardToHand(userCard1);
			user.addCardToHand(userCard2);
		} catch (Exception e) {
			gameOver = true;
			System.out.println("Error: " + e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		// writes messages to the game stats file
		writeToGameStatsFile("User Health: " + user.getHealth());
		writeToGameStatsFile("Dealer Health: " + dealer.getHealth());
		writeToGameStatsFile("");
		writeToGameStatsFile("User's Starting cards: " + userCard1 + " | " + userCard2);
		Card dealerCard1 = deck.dealCard();
		Card dealerCard2 = deck.dealCard();
		// Adds 2 cards to the User's hand
		try {
			dealer.addCardToHand(dealerCard1);
			dealer.addCardToHand(dealerCard2);
		} catch (Exception e) {
			gameOver = true;
			System.out.println("Error: " + e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		// writes messages to the game stats file
		writeToGameStatsFile("Dealer's Starting cards: " + dealerCard1 + " | " + dealerCard2);
		writeToGameStatsFile("");
		writeToGameStatsFile("Dealer's score: " + dealer.getScore());
		writeToGameStatsFile("User's score: " + user.getScore());
		writeToGameStatsFile("");
		// resets field variables
		roundOver = false;
		userLastMove = "None";
		dealerLastMove = "None";
		userTurn = true;
	}

	/**
	 * updates the user's hand when they press the hit button
	 */
	public void userHit() {
		// user can hit when it is their turn and they are not at the max card limit
		if (!gameOver && userTurn && user.getMaxCard() != user.getHand().size() && !disabledHitOption) {
			// if deck is empty when drawing card create another deck
			if (deck.isDeckEmpty()) {
				deck.generateDeck();
			}
			// adds a card to the user hand
			Card card = deck.dealCard();
			try {
				user.addCardToHand(card);
			} catch (Exception e) {
				gameOver = true;
				System.out.println("Error: " + e.getMessage());
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			userLastMove = "Hit";
			userTurn = false;
			// writes messages to the game stats file
			writeToGameStatsFile("User's turn");
			writeToGameStatsFile("User's move: " + userLastMove);
			writeToGameStatsFile("User draws a : " + card);
			writeToGameStatsFile("User's hand: " + stringPlayerHand(user));
			writeToGameStatsFile("");
		}
	}

	/**
	 * user has selected to stand
	 */
	public void userStand() {
		if (!gameOver && userTurn) {
			// sets the user's last move to be "Stand"
			userLastMove = "Stand";
			userTurn = false;
			// writes messages to the game stats file
			writeToGameStatsFile("User's turn");
			writeToGameStatsFile("User's move: " + userLastMove);
			writeToGameStatsFile("User's hand: " + stringPlayerHand(user));
			writeToGameStatsFile("");
		}
	}

	/**
	 * Dealers logic deciding what move to make
	 */
	public void dealerDecision() {
		// Dealer can only make a decision is the game is not over and it is not the
		// userTurn
		if (!gameOver && !userTurn) {
			// Dealer draws a card if it has a score less than 17 and the hit option is not
			// disabled
			if (dealer.getScore() < 17 && !disabledHitOption) {
				// Adds card to dealer's hand
				Card card = deck.dealCard();
				try {
					dealer.addCardToHand(card);
				}catch(Exception e){
					gameOver = true;
					System.out.println("Error: " + e.getMessage());
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				dealerLastMove = "Hit";
				userTurn = true;
				// writes messages to the game stats file
				writeToGameStatsFile("Dealer's turn");
				writeToGameStatsFile("Dealer's move: " + dealerLastMove);
				writeToGameStatsFile("Dealer draws a : " + card);
				writeToGameStatsFile("Dealer's hand: " + stringPlayerHand(dealer));
				writeToGameStatsFile("");
				writeToGameStatsFile("User's score: " + user.getScore());
				writeToGameStatsFile("Dealer's score: " + dealer.getScore());
				writeToGameStatsFile("");

			} else {
				dealerLastMove = "Stand";
				userTurn = true;
				// writes messages to the game stats file
				writeToGameStatsFile("Dealer's turn");
				writeToGameStatsFile("Dealer's move: " + dealerLastMove);
				writeToGameStatsFile("Dealer's hand: " + stringPlayerHand(dealer));
				writeToGameStatsFile("Dealer's score: " + dealer.getScore());
				writeToGameStatsFile("");
				writeToGameStatsFile("User's score: " + user.getScore());
				writeToGameStatsFile("Dealer's score: " + dealer.getScore());
				writeToGameStatsFile("");
			}
		}
	}

	/**
	 * checks to see if round is over or not
	 */
	public void checkRoundOver() {
		if (dealerLastMove == "Stand" && userLastMove == "Stand") {
			this.roundOver = true;
		} else {
			this.roundOver = false;
		}
	}

	/**
	 * updates status if the round has ended
	 */
	public void updateGameStatus() {
		checkRoundOver();
		// If round is over change health and write about it to the file
		if (getRoundOver()) {
			int userScore = user.getScore();
			int dealerScore = dealer.getScore();
			if (userScore > 21 && dealerScore > 21) {
				if (userScore < dealerScore) {
					dealer.changeHealth(-amountDamageTaken); // Dealer wins, player loses health
					try {
						user.addCardToHand(new PowerCard());
					} catch (Exception e) {
						gameOver = true;
						System.out.println("Error: " + e.getMessage());
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					writeToGameStatsFile("User Wins!");
					writeToGameStatsFile("Power Card Added");
				} else // dealer wins ties
				{
					user.changeHealth(-amountDamageTaken); // Dealer wins, player loses health
					writeToGameStatsFile("Dealer Wins!");
				}
			} else if (dealerScore > 21) {
				dealer.changeHealth(-amountDamageTaken); // Player wins, dealer loses health
				try {
					user.addCardToHand(new PowerCard());
				} catch (Exception e) {
					gameOver = true;
					System.out.println("Error: " + e.getMessage());
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				writeToGameStatsFile("User Wins!");
				writeToGameStatsFile("Power Card Added");
			} else if (userScore > 21) {
				user.changeHealth(-amountDamageTaken); // Player wins, dealer loses health
				writeToGameStatsFile("Dealer Wins!");
			} else if (userScore > dealerScore) {
				dealer.changeHealth(-amountDamageTaken); // Dealer loses health
				try {
					user.addCardToHand(new PowerCard());
				} catch (Exception e) {
					gameOver = true;
					System.out.println("Error: " + e.getMessage());
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Game Error", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				writeToGameStatsFile("User Wins!");
				writeToGameStatsFile("Power Card Added");
			} else if (dealerScore > userScore) {
				user.changeHealth(-amountDamageTaken); // User loses health
				writeToGameStatsFile("Dealer Wins!");
			} else {
				user.changeHealth(-amountDamageTaken); // User loses health
				writeToGameStatsFile("Dealer Wins!");
			}
			// Check if the game should end based on health
			if (user.getHealth() <= 0 || dealer.getHealth() <= 0) {
				gameOver = true;
			}
			writeToGameStatsFile("");
			newRound();
		}
	}

	/**
	 * resets everything in preparation for the next round
	 */
	public void newRound() {
		// resets some field variables back to its default state
		amountDamageTaken = 5;
		disabledHitOption = false;
		sendFutureMessage = false;
		if (!gameOver) { // Only start a new round if the game is not over
			int size = user.getHand().size();
			dealer.getHand().clear();
			for (int i = 0; i < size; i++) {
				// make sure power cards are not deleted after a new round and remain
				// on the left hand side of the hand the user
				if (user.getHand().get(0).getValue() != 0) {
					user.getHand().remove(0);
				} else {
					user.getHand().add(user.getHand().get(0));
					user.getHand().remove(0);
				}

			}
			startRound();
		}
		// write to the file who the winner of the game is
		else {
			writeToGameStatsFile("Game Over!");
			if (user.getHealth() <= 0) {
				writeToGameStatsFile("Dealer Wins The Game!");
			} else {
				writeToGameStatsFile("User Wins The Game!");
			}
		}
	}

	/**
	 * this is responsible for the messages that will displayed on the terminal
	 * helping the user know what is going on
	 */
	public String getGameStatus() {
		String result = "";
		// Display who wins the game
		if (gameOver) {
			if (user.getHealth() <= 0) {
				return " Game Over! User has lost all health.";
			} else {
				return " Game Over! Dealer has lost all health.";
			}
		}
		// Display more information about what is happening in the game than normal
		else if (extraInfo) {
			return readGameStatsFile();
		}
		// Display who wins the round and there respective scores when the round is over
		else if (roundOver) {
			if (user.getScore() > dealer.getScore()) {
				if (user.getScore() <= 21) {
					result = " User wins!";
				} else {
					result = " Dealer wins!";
				}
			} else {
				if (user.getScore() == dealer.getScore()) {
					result = " Dealer wins!";
				} else if (dealer.getScore() <= 21) {
					result = " Dealer wins!";
				} else {
					result = " User wins!";
				}
			}
			return " Round Over!\n User Score: " + user.getScore() + "\n Dealer Score: " + dealer.getScore() + "\n"
					+ result;
		}
		// Display what is happening in the current round
		else {
			// Power card ability insight is use display more information to user
			if (sendFutureMessage) {
				if (user.getScore() > dealer.getScore()) {
					if (user.getScore() <= 21) {
						result = " User is winning";
					} else {
						result = " User is losing";
					}
				}
				// Give the user generic information about the game
				else {
					if (user.getScore() == dealer.getScore()) {
						result = " User is losing";
					} else if (dealer.getScore() <= 21) {
						result = " User is losing";
					} else {
						result = " User is winning";
					}
				}
			}
			return " User's Turn\n" + " User's Score: " + user.getScore() + "\n Dealer's Visible Card: "
					+ dealer.getHand().get(0) + "\n" + result;
		}
	}

	/**
	 * this is responsible for writing to a file about what happened each round
	 * 
	 * @param message what information we want to record in the file
	 */
	public void writeToGameStatsFile(String message) {
		PrintWriter outputfileWriter = null;
		try {
			// Create a PrintWriter object with a new file writer object in append mode so
			// it will not overwrite what is currently in there
			outputfileWriter = new PrintWriter(new FileWriter(gameStatFile, true));

			// Adds the desired content to the end of the file with different formating
			// making it more legible to the user
			if (message.equals("Round: " + roundNum) || message.equals("Game Over!")) {
				outputfileWriter.println(" " + message);
			} else {
				outputfileWriter.println("     " + message);
			}
		}
		// File can not be found so we can not write to it
		catch (Exception e) {
			System.out.println("Cannot write file. File will not be written.");
		}
		// We are done writing so we will close the file
		finally {
			if (outputfileWriter != null) {
				outputfileWriter.close();
			}
		}
	}

	/**
	 * this is responsible for clear the file so we do not have information from
	 * previous games in the txt file
	 */
	public void clearGameStatsFile() {
		PrintWriter outputfileWriter = null;
		try {
			// Create a PrintWriter object with a new file writer object without append mode
			// on so
			// it will overwrite what is currently in there
			outputfileWriter = new PrintWriter(new FileWriter(gameStatFile, false));
			// Clears the file and replaces it with an empty string
			outputfileWriter.print("");
		}
		// File can not be found so we can not write to it
		catch (Exception e) {
			System.out.println("Cannot Clear the File");
		}
		// We are done writing so we will close the file
		finally {
			if (outputfileWriter != null) {
				outputfileWriter.close();
			}
		}
	}

	/**
	 * this is responsible for returning a readable string of the cards in the
	 * player's hand
	 * 
	 * @param player the player we want to have a string of all their cards in their
	 *               hand
	 * @return a readable string of the cards in the player's hand
	 */
	public String stringPlayerHand(Player player) {
		String result = "";
		for (int i = 0; i < player.getHand().size(); i++) {
			result += player.getHand().get(i) + " | ";
		}
		return result;
	}

	/**
	 * this is responsible for converting the file into a string
	 * 
	 * @return a readable string of the game
	 */
	public String readGameStatsFile() {
		Scanner scan = null;
		String content = "";
		// attempts to read file line by line extracting its contents
		// and converting it into a string
		try {
			scan = new Scanner(gameStatFile);
			while (scan.hasNextLine()) {
				content += scan.nextLine() + "\n"; // Grab the content of the file through the Scanner with its next
			}
		}
		// If the file can not be found just return and empty string
		catch (FileNotFoundException e) {
			return "";
		}
		// Close the scanner to stop reading the file
		finally {
			if (scan != null) {
				scan.close();
			}
		}
		// returns the contents in file as a string and trims any leading whitespace
		return content;
	}

	/**
	 * @returns where the game has ended or not
	 */
	public boolean isGameOver() {
		return gameOver || user.getHealth() <= 0 || dealer.getHealth() <= 0;
	}

}