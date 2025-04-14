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
 * Responsibilities of class: Creates the User for the blackjack game
 * 
 *
 */

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





