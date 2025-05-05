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
 *         Responsibilities of class: Tests the player's of the game work as
 *         intended
 * 
 *
 */

public class PlayerTest {
	public static void main(String[] args) {
		// First testing the User class
		System.out.println("Testing the User class:");

		// Sets the users name to Herald
		User user = new User("Herald");
		System.out.println("User created: " + user.getName());
		// Checks to see if the name was successfully assigned to the user
		assert (user.getName().equals("Herald"));
		// Checks to see if there is any card currently in the user's hand
		System.out.println("Initial hand size: " + user.getHand().size());
		assert (user.getHand().isEmpty());

		// Checks to see if a valid card was successfully added to the user's hand
		Card card1 = new Card("spades", "5", 5);
		try {
			user.addCardToHand(card1);
		} catch (InvalidCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAmountOfCardsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Added card: " + card1);
		assert (user.getHand().size() == 1);
		assert (user.getScore() == 5);

		// Adds more cards to the user's hand to reach the maxCard limit
		for (int i = 1; i < user.getMaxCard(); i++) {
			try {
				user.addCardToHand(new Card("clubs", "2", 2));
			} catch (InvalidCardException e) {
				e.printStackTrace();
			} catch (InvalidAmountOfCardsException e) {
				e.printStackTrace();
			}
		}
		assert (user.getHand().size() == user.getMaxCard());
		assert (user.getScore() == 27);

		// Try exceeding maxCard
		boolean exceptionThrown = false;
		try {
			user.addCardToHand(new Card("hearts", "3", 3));
		} catch (InvalidAmountOfCardsException | InvalidCardException e) {
			exceptionThrown = true;
			System.out.println("Caught expected exception: " + e.getMessage());
		}
		assert (exceptionThrown == true);
		assert (user.getScore() == 27);

		// Try to add a non-valid card to the user's hand
		user.emptyHand();
		assert (user.getScore() == 0);
		System.out.println("Emptying player hand");
		exceptionThrown = false;
		try {
			System.out.println("Adding non valid card to hand");
			try {
				user.addCardToHand(new Card("Dud", "3", 3));
			} catch (InvalidAmountOfCardsException e) {
				e.printStackTrace();
			}
		} catch (InvalidCardException e) {
			exceptionThrown = true;
			System.out.println("Caught expected exception: " + e.getMessage());
		}
		assert (user.getScore() == 0);
		assert (exceptionThrown == true);

		// Try to add a non-valid card to the user's hand
		exceptionThrown = false;
		try {
			System.out.println("Adding non valid card to hand");
			try {
				user.addCardToHand(new Card("spades", "dud", 3));
			} catch (InvalidAmountOfCardsException e) {
				e.printStackTrace();
			}
		} catch (InvalidCardException e) {
			exceptionThrown = true;
			System.out.println("Caught expected exception: " + e.getMessage());
		}
		assert (user.getScore() == 0);
		assert (exceptionThrown == true);
		System.out.println();

		// Testing the Dealer class
		System.out.println("Testing the Dealer class:");
		// Testing to see if Dealer can properly be assigned a name
		Dealer dealer = new Dealer("David");
		System.out.println("Dealer created: " + dealer.getName());
		assert (dealer.getName().equals("David"));
		assert (dealer.getHand().isEmpty());

		// Add a card to the dealer’s hand and verify score
		Card dealerCard = new Card("diamonds", "King", 10);
		dealer.addCardToHand(dealerCard);
		System.out.println("Added card to dealer: " + dealerCard);
		assert (dealer.getScore() == 10);
		assert (dealer.getHand().size() == 1);

		System.out.println("Player Testing Successfully completed");
		System.out.println();
	}
}
