public class Dealer extends Player{//The dealer IS-A player
    
	/**
	 * constructor for the dealer class
	 * @param name of the dealer
	 */
    public Dealer(String name) 
    {
    	super(name);
    }
 
    /**
	 * method to update and return the score of the dealer
	 * @return the current dealer's score
	 */
    public int updateScore() 
    {
    	setScore(0);
    	for(int i = 0; i < getHand().size(); i++)
    	{
    		setScore(getHand().get(i).getValue() + getScore());
    	}
    	return getScore();
    }
    
    /**
	 * mutator method that adds a card to dealer's hand
	 */
    public void addCardToHand(Card card) {
    	if(card!=null)
    	{
    		getHand().add(card);
    	}
    	else
    	{
    		System.out.println("Error: Null Card Added to User's hand");
    	}
    	updateScore();
    }
}





