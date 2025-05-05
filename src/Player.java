
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
 * 
 *  
 * Version/date: 2.0 
 * 
 * Responsibilities of class: Defines what a player of this game is and has
 * 
 *
 */

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Player {
	private ArrayList<Card> hand;// a player HAS-A hand full of cards
	private String name;// a player HAS-A name
	private int health;// a player HAS-A certain amount of health
	private int score;// a player HAS-A a score based on their hand

	/**
	 * Constructor for the player class
	 * 
	 * @param name the desired name of the player
	 */
	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.name = name;
		this.health = 20;
		this.score = 0;
	}

	/**
	 * getter method for the name of the player
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter method for the hand of the player
	 * 
	 * @return hand of the player
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}

	/**
	 * getter method for the health of the player
	 * 
	 * @return health of the player
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * getter method for the score of the player
	 * 
	 * @return score of the player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * setter method for the score of the player
	 * 
	 * @param desired health of the player
	 */
	public void setScore(int total) {
		this.score = total;
	}

	/**
	 * mutator method for the score of the player
	 * 
	 * @param desired health added to the player's current health
	 */
	public void changeHealth(int amount) {
		this.health += amount;
	}

	/**
	 * empty and update the players hand
	 */
	public void emptyHand() {
		this.hand.clear();
		this.score = 0;
	}

	/**
	 * mutator method for the hands of the player
	 * 
	 * @param the card added to the hand of the player
	 * @throws InvalidAmountOfCardsException
	 * @throws InvalidCardException
	 */
	public abstract void addCardToHand(Card card) throws InvalidCardException, InvalidAmountOfCardsException;

}
