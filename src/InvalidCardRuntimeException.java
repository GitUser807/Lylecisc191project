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
 * <<add more references here>>
 *  
 * Version/date: 
 * 
 * Responsibilities of class: Handle and creates a new error when the amount is not correct
 * 
 */
/**
 */

public class InvalidCardRuntimeException extends RuntimeException {
	
	public InvalidCardRuntimeException(Card card)
	{
		super(card != null
				? "This is not a valid Card: " + 
				  "Suit-" + card.getSuit() +
				  ", Rank-" + card.getRank() +
				  ", Value-" + card.getValue() +
				  " is not allowed"
				  : "Error: Null Card Added to User's hand");
	}
}

