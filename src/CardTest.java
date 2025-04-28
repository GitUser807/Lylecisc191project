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
 * Responsibilities of class: Tests all code related to cards
 * 
 *
 */

import java.util.Random;

public class CardTest {
	public static void main(String[] args) {
		// Test the Card class
		System.out.println("Testing the Card class:");
		Card regularCard = new Card("Hearts", "Ace", 11);
		System.out.println(regularCard);// Should print: Suit: Hearts, Rank: Ace, Value: 11
		assert (regularCard.toString().equals("Suit: Hearts, Rank: Ace, Value: 11"));
		System.out.println();

		// Modify the card using setters
		regularCard.setSuit("Diamonds");
		regularCard.setRank("King");
		regularCard.setValue(10);
		System.out.println(regularCard);// Should print: Suit: Diamonds, Rank: King, Value: 10
		assert (regularCard.toString().equals("Suit: Diamonds, Rank: King, Value: 10"));
		System.out.println();

		// Test the BlackjackCard class (extends Card)
		System.out.println("Testing the BlackjackCard class:");
		BlackjackCard blackjackCard = new BlackjackCard("Spades", "Jack", 10);
		System.out.println(blackjackCard);// Should print: Suit: Spades, Rank: Jack, Value: 10
		assert (blackjackCard.toString().equals("Suit: Spades, Rank: Jack, Value: 10"));
		System.out.println();

		// Test the PowerCard class (implements Special)
		System.out.println("Testing the PowerCard class:");
		PowerCard powerCard = new PowerCard();
		System.out.println("Before generating ability: " + powerCard); // Should print default Suit: Special, Rank:
																		// None, Value: 0
		assert (powerCard.toString().equals("Suit: Special, Rank: None, Value: 0"));
		System.out.println();

		// Generate an ability for the PowerCard
		powerCard.generateAbility("Risk");
		System.out.println("After generating ability: " + powerCard); // Should print Suit: Risk, Rank: DoubleDmg,
																		// Value: 0
		assert (powerCard.toString().equals("Suit: Risk, Rank: DoubleDmg, Value: 0"));
		System.out.println();

		// Test if the ability has been generated
		System.out.println("Ability generated: " + powerCard.getIsAbilityGenerated()); // Should print: true
		assert (powerCard.getIsAbilityGenerated() == true);
		System.out.println();

		// Test if generating the ability works only once
		powerCard.generateAbility("Insight");
		System.out.println("After attempting to generate a second ability: " + powerCard); // Should still show the
																							// first generated ability
		assert (powerCard.toString().equals("Suit: Risk, Rank: DoubleDmg, Value: 0"));
		System.out.println();

		// Testing card dealing from a deck
		System.out.println("Testing card dealing from a deck:");
		Deck deck = new Deck();

		// Deal a card and print it
		Card dealtCard = deck.dealCard();
		assert (dealtCard != null);
		if (dealtCard != null) {
			System.out.println("Dealt card: " + dealtCard);
		}

		// Print remaining deck size
		System.out.println("Card Testing Successfully completed");
		System.out.println();
	}
}