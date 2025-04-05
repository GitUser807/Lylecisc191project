import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<BlackjackCard> deckOfCards;
	private static final String[] SUITS = {"hearts", "diamonds", "clubs", "spades"};
	private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
	private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

	public Deck() 
	{
		deckOfCards = new ArrayList<>();
		generateDeck();
	}

	public Card dealCard() 
	{
		Random random = new Random();
		if(!isDeckEmpty())
			return deckOfCards.remove(random.nextInt(deckOfCards.size()));
		return null;
		
	}
	
	public boolean isDeckEmpty()
	{
		if(deckSize()==0)
		{
			return true;
		}
		return false;
	}
	
	public int deckSize()
	{
		return deckOfCards.size();
	}
	
	public void generateDeck() 
	{
		for(int suit = 0; suit < SUITS.length; suit++)
		{
			for(int rank = 0; rank < RANKS.length; rank++)
			{
				deckOfCards.add(new BlackjackCard(SUITS[suit],RANKS[rank],VALUES[rank]));
			}
		}
	}
}
