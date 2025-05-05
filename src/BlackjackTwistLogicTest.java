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
 *         Responsibilities of class: Tests to seem if the game works as
 *         intended
 * 
 *
 */

public class BlackjackTwistLogicTest {
	public static void main(String[] args) {
		System.out.println("Testing the BlackjackTwist class:");

		// Create an instance of the game
		BlackjackTwistGame game = new BlackjackTwistGame("player");
		assert (game != null);
		System.out.println("Game has been created");

		// Check players are properly created when starting the game
		assert (game.getUser() != null);
		assert (game.getDealer() != null);
		System.out.println(
				"Players are initialized: User-" + game.getUser().getName() + ", Dealer-" + game.getDealer().getName());

		// Checks to see if the round is set up correctly
		game.startRound();
		assert (game.getUserLastMove().equals("None"));
		assert (game.getUserLastMove().equals("None"));
		System.out.println("Players haven't made a move yet");
		assert (game.getUser().getHand().size() == 2);
		assert (game.getDealer().getHand().size() == 2);
		System.out.println("Bother Players have been dealt 2 cards");

		// Testing Dealer logic during a round
		// Dealer needs to keep drawing cards until their score is at least 17
		game.getUser().emptyHand();
		game.getDealer().emptyHand();
		Card dealerCard1 = new Card("spades", "9", 9);
		Card dealerCard2 = new Card("diamonds", "8", 6);
		game.getDealer().addCardToHand(dealerCard1);
		game.getDealer().addCardToHand(dealerCard2);
		// Dealer has a score of 17 therefore his next move should be to stand
		assert (game.getDealer().getScore() == 17);
		assert (game.getDealer().getHand().size() == 2);
		game.dealerDecision();
		assert (game.getDealer().getHand().size() == 2);
		assert (game.getDealerLastMove().equals("Stand"));
		System.out.println("Dealer stands at 17");

		// Dealer needs to keep drawing cards until their score is at least 17
		game.getUser().emptyHand();
		game.getDealer().emptyHand();
		dealerCard1 = new Card("spades", "9", 9);
		dealerCard2 = new Card("diamonds", "6", 6);
		game.getDealer().addCardToHand(dealerCard1);
		game.getDealer().addCardToHand(dealerCard2);
		// Dealer has a score of 15 therefore his next move should be to hit
		assert (game.getDealer().getScore() == 15);
		assert (game.getDealer().getHand().size() == 2);
		game.dealerDecision();
		assert (game.getDealer().getHand().size() == 3);
		assert (game.getDealerLastMove().equals("Hit"));
		System.out.println("Dealer hits at 15");

		// Testing a potential round of this game
		// Dealer should win ties
		game.getUser().emptyHand();
		game.getDealer().emptyHand();
		dealerCard1 = new Card("spades", "9", 9);
		dealerCard2 = new Card("diamonds", "8", 8);
		Card userCard1 = new Card("hearts", "9", 9);
		Card userCard2 = new Card("clubs", "8", 8);
		game.getDealer().addCardToHand(dealerCard1);
		game.getDealer().addCardToHand(dealerCard2);
		try {
			game.getUser().addCardToHand(userCard1);
		} catch (InvalidCardException e1) {
			e1.printStackTrace();
		} catch (InvalidAmountOfCardsException e1) {
			e1.printStackTrace();
		}
		try {
			game.getUser().addCardToHand(userCard2);
		} catch (InvalidCardException e1) {
			e1.printStackTrace();
		} catch (InvalidAmountOfCardsException e1) {
			e1.printStackTrace();
		}
		// Dealer has 17 so they will stand
		game.dealerDecision();
		// Usder stands because they like their score
		game.userStand();
		// checks to see if both players have not updated
		assert (game.getDealer().getScore() == 17);
		assert (game.getDealer().getHand().size() == 2);
		assert (game.getUser().getScore() == 17);
		assert (game.getUser().getHand().size() == 2);
		// checks to see if both players have chosen to stand
		assert (game.getDealerLastMove().equals("Stand"));
		assert (game.getUserLastMove().equals("Stand"));
		// user loses ties therefore they lose health
		assert (game.getUser().getHealth() == 15);
		assert (game.getDealer().getHealth() == 20);
		System.out.println("Dealer has won the round");

		// Testing a potential round of this game where the user wins
		game.getUser().emptyHand();
		game.getDealer().emptyHand();
		dealerCard1 = new Card("spades", "9", 9);
		dealerCard2 = new Card("diamonds", "8", 8);
		userCard1 = new Card("hearts", "9", 9);
		userCard2 = new Card("clubs", "10", 10);
		game.getDealer().addCardToHand(dealerCard1);
		game.getDealer().addCardToHand(dealerCard2);
		try {
			game.getUser().addCardToHand(userCard1);
		} catch (InvalidCardException e) {
			e.printStackTrace();
		} catch (InvalidAmountOfCardsException e) {
			e.printStackTrace();
		}
		try {
			game.getUser().addCardToHand(userCard2);
		} catch (InvalidCardException e) {
			e.printStackTrace();
		} catch (InvalidAmountOfCardsException e) {
			e.printStackTrace();
		}
		// Dealer has 17 so they will stand
		game.dealerDecision();
		// User stands because they like their score
		game.userStand();
		// checks to see if both players have not updated
		assert (game.getDealer().getScore() == 17);
		assert (game.getDealer().getHand().size() == 2);
		assert (game.getUser().getScore() == 19);
		assert (game.getUser().getHand().size() == 2);
		// checks to see if both players have chosen to stand
		assert (game.getDealerLastMove().equals("Stand"));
		assert (game.getUserLastMove().equals("Stand"));
		assert (game.getUser().getHealth() == 15);
		assert (game.getDealer().getHealth() == 15);
		// User should get a power card for winning
		assert (game.getUser().getHand().size() == 3);
		System.out.println("Player has won the round and gotten a power card");

		// Tests to see if the game is over when health reaches 0
		// Tests to see if game is over when dealer health is 0
		game.getUser().emptyHand();
		game.getDealer().emptyHand();
		game.getDealer().changeHealth(-15);
		game.updateGameStatus();
		assert (game.getGameOver() == true);
		System.out.println("Game over, player has won game");
		// Tests to see if game is over when user health is 0
		game.getDealer().changeHealth(15);
		game.updateGameStatus();
		game.getUser().changeHealth(-15);
		game.updateGameStatus();
		assert (game.getGameOver() == true);
		System.out.println("Game over, dealer has won game");

		System.out.println("BlackjackTwistGame Testing Successfully completed");
		System.out.println();
	}
}