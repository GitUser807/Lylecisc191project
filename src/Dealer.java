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
 *         Responsibilities of class: Creates the Dealer of the game
 * 
 *
 */

public class Dealer extends Player {// The dealer IS-A player

	/**
	 * constructor for the dealer class
	 * 
	 * @param name of the dealer
	 */
	public Dealer(String name) {
		super(name);
	}

	/**
	 * method to update and return the score of the dealer
	 * 
	 * @return the current dealer's score
	 */
	public int updateScore() {
		setScore(0);
		for (int i = 0; i < getHand().size(); i++) {
			setScore(getHand().get(i).getValue() + getScore());
		}
		return getScore();
	}

	/**
	 * mutator method that adds a card to dealer's hand
	 */
	public void addCardToHand(Card card) {
		if (card != null) {
			getHand().add(card);
		} else {
			System.out.println("Error: Null Card Added to User's hand");
		}
		updateScore();
	}
}
