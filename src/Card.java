import java.util.ArrayList;
import java.util.Hashtable;

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
 * Responsibilities of class: How any card in a deck is formatted
 *
 */

public class Card {
	private static final String[] validNormalSuitDescription = {"hearts", "diamonds", "clubs", "spades"};//a Card HAS-Many different valid suits
	private static final String[] validPowerSuitDescription = {"Special","Risk","Insight","Control"};//a Card HAS-Many different power suits valid 
	private Hashtable<String, Integer> validNormalCardPairingDescriptions;//A card HAS-A legal rank and suit pairings that a normal card can have
	private Hashtable<String, Integer> validPowerCardPairingDescriptions;//A card HAS-A legal rank and suit pairings that a power card can have
	private String suit; // a Card HAS-A suit
	private String rank; // a Card HAS-A rank
	private int value; // a Card HAS-A value

	/**
	 * Constructor for the Card
	 * 
	 * @param suit  the desired suit for this card
	 * @param rank  the desired rank for this card
	 * @param value the desired value for this card
	 */
	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
		validNormalCardPairingDescriptions = new Hashtable<>();
		validPowerCardPairingDescriptions = new Hashtable<>();
		validNormalCardPairingDescriptions.put("2",2);
		validNormalCardPairingDescriptions.put("3",3);
		validNormalCardPairingDescriptions.put("4",4);
		validNormalCardPairingDescriptions.put("5",5);
		validNormalCardPairingDescriptions.put("6",6);
		validNormalCardPairingDescriptions.put("7",7);
		validNormalCardPairingDescriptions.put("8",8);
		validNormalCardPairingDescriptions.put("9",9);
		validNormalCardPairingDescriptions.put("10",10);
		validNormalCardPairingDescriptions.put("jack",10);
		validNormalCardPairingDescriptions.put("queen",10);
		validNormalCardPairingDescriptions.put("king",10);
		validNormalCardPairingDescriptions.put("ace",11);
		validPowerCardPairingDescriptions.put("None",0);
		validPowerCardPairingDescriptions.put("DoubleDmg",0);
		validPowerCardPairingDescriptions.put("WinOrLose",0);
		validPowerCardPairingDescriptions.put("StopDrawing",0);
	}

	/**
	 * Getter method for the suit of the Card
	 * 
	 * @return the suit of this card
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * Getter method for the rank of the Card
	 * 
	 * @return the rank of this card
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * Getter method for the value of the Card
	 * 
	 * @return the value of this card
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter method for the suit of the Card
	 * 
	 * @param the desired suit of this card
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/**
	 * Setter method for the rank of the Card
	 * 
	 * @param the desired rank of this card
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * Setter method for the value of the Card
	 * 
	 * @param the desired value of this card
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean validNormalCardSuit()
	{
		for(String suit:validNormalSuitDescription)
		{
			if(suit.equals(this.getSuit()))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean validPowerCardSuit()
	{
		if(this.getSuit().equals("Special"))
			if (this.getRank().equals("None")&&this.getValue() == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		for(String suit:validPowerSuitDescription)
		{
			if(suit.equals(this.getSuit()))
			{
				return true;
			}
		}
		return false;
	}
	
    public boolean validCard()
    {
    	if(validNormalCardSuit() 
    			&& validNormalCardPairingDescriptions.containsKey(this.getRank()) 
    			&& validNormalCardPairingDescriptions.get(this.getRank()) == this.getValue())
    	{
    		return true;
    	}
    	else if(validPowerCardSuit() 
    			&& validPowerCardPairingDescriptions.containsKey(this.getRank()) 
    			&& validPowerCardPairingDescriptions.get(this.getRank()) == this.getValue())
    	{
    		return true;
    	}
    	return false;
    }
	/**
	 * Print method for the rank of the Card
	 * 
	 * @return the desired format of the card when printed
	 */
	public String toString() {
		return "Suit: " + this.getSuit() + ", Rank: " + this.getRank() + ", Value: " + this.getValue();

	}

}
