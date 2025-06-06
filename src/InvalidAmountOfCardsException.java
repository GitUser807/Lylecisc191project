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

public class InvalidAmountOfCardsException extends Exception { //InvalidAmountOfCardsException IS-A exception

	public InvalidAmountOfCardsException(Player player) {
		super(player.getName() + " has an Exceeded the max amount of cards. They have " + player.getHand().size()
				+ " cards in their hand");
	}
}
