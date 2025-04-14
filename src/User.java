public class User extends Player{//The user IS-A player
    int maxCard;//The user has a max amount of cards
    
    /**
	 * constructor for the player class
	 * @param name of the user
	 */
    public User(String name) 
    {
    	super(name);
    	maxCard = 12;
    }
 
    /**
	 * getter method for the user's max amount of cards
	 * @return the max amount of card the user is allowed to have
	 */
    public int getMaxCard()
    {
    	return this.maxCard;
    }
    
    /**
	 * method to update and return the score of the user
	 * @return the current player's score
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
	 * mutator method that adds a card to user's hand
	 */
    public void addCardToHand(Card card) {
    	if(card!=null)
    	{
    		if(getHand().size()<maxCard)
    		{
    			getHand().add(card);
    		}
    		else
    		{
    			System.out.println("Max Card Count Reached");
    		}
    	}
    	else
    	{
    		System.out.println("Error: Null Card Added to User's hand");
    	}
    	updateScore();
    }
}





