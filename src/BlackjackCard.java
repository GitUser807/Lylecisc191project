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
 *         Responsibilities of class: Standard blackjack card
 * 
 *
 */

public class BlackjackCard extends Card//a blackjack card IS-A card
{
	/**
	 * Constructor method for the rank of the BlackjackCard
	 * 
	 * @param the desired rank of this card
	 */
	public BlackjackCard(String suit, String rank, int value) {
		super(suit, rank, value);
	}
}
