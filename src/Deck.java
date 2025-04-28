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
 * Responsibilities of class: Deck of cards holds 52 cards used in the game
 * 
 *
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Deck {
	private ArrayList<BlackjackCard> deckOfPossibleCards;
	Queue<BlackjackCard> deckOfCards;
	private static final String[] SUITS = { "hearts", "diamonds", "clubs", "spades" };
	private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",
			"ace" };
	private static final int[] VALUES = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

	public Deck() {
		deckOfCards = new LinkedList<>();
		deckOfPossibleCards = new ArrayList<>();
		generateDeck();
	}

	public Card dealCard() {
		if (isDeckEmpty())
			return deckOfCards.poll();
		return null;

	}

	public boolean isDeckEmpty() {
		if (deckOfCards.peek()!=null) {
			return true;
		}
		return false;
	}

	/*
	public int deckSize() {
		return deckOfCards.size();
	}
	*/

	public void generateDeck() {
		generateAllPossibleCards();
		Random random = new Random();
		int randomNumber;
		while(deckOfPossibleCards.size()>0)
		{
			randomNumber = random.nextInt(deckOfPossibleCards.size());
			deckOfCards.add(deckOfPossibleCards.remove(randomNumber));
		}
	}
	
	public void generateAllPossibleCards()
	{
		for (int suit = 0; suit < SUITS.length; suit++) {
			for (int rank = 0; rank < RANKS.length; rank++) {
				deckOfPossibleCards.add(new BlackjackCard(SUITS[suit], RANKS[rank], VALUES[rank]));
			}
		}
	}
}
