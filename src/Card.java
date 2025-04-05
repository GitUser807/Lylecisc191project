

public class Card {
    private String suit; //a Card HAS-A suit
    private String rank; //a Card HAS-A rank
    private int value; //a Card HAS-A value

	/**
	 * Constructor for the Card
	 * @param suit the desired suit for this card
	 * @param rank the desired rank for this card
	 * @param value the desired value for this card
	 */
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

	/**
	 * Getter method for the suit of the Card
	 * @return the suit of this card
	 */
    public String getSuit() {
        return suit;
    }
    
	/**
	 * Getter method for the rank of the Card
	 * @return the rank of this card
	 */
    public String getRank() {
    	return rank;
    }
    
	/**
	 * Getter method for the value of the Card
	 * @return the value of this card
	 */
    public int getValue() {
        return value;
    }
    
	/**
	 * Setter method for the suit of the Card
	 * @param the desired suit of this card
	 */
    public void setSuit(String suit) {
        this.suit = suit;
    }
    
	/**
	 * Setter method for the rank of the Card
	 * @param the desired rank of this card
	 */
    public void setRank(String rank) {
        this.rank = rank;
    }
    
	/**
	 * Setter method for the value of the Card
	 * @param the desired value of this card
	 */
    public void setValue(int value) {
        this.value = value;
    }
    
	/**
	 * Print method for the rank of the Card
	 * @return the desired format of the card when printed
	 */
    public String toString() {
        return "Suit: " + this.getSuit() + ", Rank: " + this.getRank() + ", Value: " + this.getValue();
    
    }

}
