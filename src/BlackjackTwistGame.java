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
 * Responsibilities of class: Logic of how the game works
 * 
 *
 */

public class BlackjackTwistGame {
    private Deck deck;//Blackjack HAS-A deck of cards
    private User player;//Blackjack HAS-A player
    private Dealer dealer;//Blackjack HAS-A dealer
    private boolean gameOver;//keeps track to see if the game is over
    private boolean roundOver;//keeps track to see if the round is over
    private String playerLastMove;//keeps track of players last move
    private boolean playerTurn;//keeps track to see if it is the player's turn or not
    private String dealerLastMove;//keeps track of dealers last move
    private int amountDamageTaken;//keeps track of how much damage done when either party loses
    private boolean disabledHitOption;//keeps track if player can choose to hit or not
    private boolean sendFutureMessage;//keeps track of if the insight power card has been used

	/**
	 * Constructor method for the rank of the BlackjackCard
	 * @param playerName the player's name
	 */
    public BlackjackTwistGame(String playerName) {
        deck = new Deck();
        player = new User(playerName);
        dealer = new Dealer("Computer");
        gameOver = false;
        roundOver = false;
        playerLastMove = "None";
        dealerLastMove = "None";
        amountDamageTaken = 5;
        disabledHitOption = false;
        sendFutureMessage = false;
    }
    
	/**
	 * getter method for the deck of cards
	 * @return the desired rank of this card
	 */
    public Deck getGameDeck()
    {
    	return this.deck;
    }
    
	/**
	 * getter method for the player in this game
	 * @return the player of this game
	 */
    public User getGamePlayer()
    {
    	return this.player;
    }
    
	/**
	 * getter method for the dealer in this game
	 * @return the dealer of this game
	 */
    public Dealer getGameDealer()
    {
    	return this.dealer;
    }
    
	/**
	 * getter method to check if the game is over
	 * @return whether or not the game is over
	 */
    public boolean getGameOver()
    {
    	return this.gameOver;
    }
    
	/**
	 * getter method to check if the round is over
	 * @return whether or not the round is over
	 */
    public boolean getRoundOver()
    {
    	return this.roundOver;
    }
   
	/**
	 * getter method for the last move of the player
	 * @return the last move the player made
	 */
    public String getPlayerLastMove()
    {
    	return this.playerLastMove;
    }
    
	/**
	 * getter method for the last move of the dealer
	 * @return the last move the dealer made
	 */
    public String getDealerLastMove()
    {
    	return this.dealerLastMove;
    }
    
	/**
	 * setter method for the last move of the player
	 * @param the last move the dealer made
	 */
    public void setPlayerLastMove(String move)
    {
    	this.playerLastMove = move;
    }
    
	/**
	 * setter method for the last move of the dealer
	 * @return the last move the dealer made
	 */
    public void setDealerLastMove(String move)
    {
    	this.dealerLastMove = move;
    }
    
	/**
	 * getter method for the amount of damage the loser takes 
	 * @return the amount of damage the loser will take
	 */
    public int getAmountDamageTaken()
    {
    	return this.amountDamageTaken;
    }
    
	/**
	 * getter method for whether to send a message from the future
	 * @return the whether to send the message or not
	 */
    public boolean getSendFutureMessage()
    {
    	return this.sendFutureMessage;
    }
    
	/**
	 * setter method to decide whether to send the message or not
	 * @param value whether to send the message or not
	 */
    public void setSendFutureMessage(boolean value)
    {
    	this.sendFutureMessage = value;
    }
    
	/**
	 * setter method for the amount of damage the loser takes 
	 * @param amount the amount of damage the loser will take
	 */
    public void setAmountDamageTaken(int amount)
    {
    	this.amountDamageTaken = amount;
    }
    
	/**
	 * mutator method that adds the additional damage the loser takes 
	 * @param amount the desired additional amount of damage the loser will take
	 */
    public void addAmountDamageTaken(int amount)
    {
    	this.amountDamageTaken += amount;
    }
    
	/**
	 * sets the start of the game
	 */
    public void startRound() {
        // Initial cards at start of round
        player.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());

        dealer.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());

        //gameOver = false;
        roundOver = false;
        playerLastMove = "None";
        dealerLastMove = "None";
        playerTurn = true;
    }

	/**
	 * updates the players hand when they press the hit button
	 */
    public void playerHit() {
    	//player can hit when it is there turn and the have are not at the max card limit
        if (!gameOver && playerTurn && player.getMaxCard()!=player.getHand().size() && !disabledHitOption) {
        	//if deck is empty when drawing card create another deck
        	if(deck.isDeckEmpty())
        	{
        		deck.generateDeck();
        	}
	        Card card = deck.dealCard();
	        if (card != null) {
	        	player.addCardToHand(card); 
	            //System.out.println("Player hits! New card: " + card);
	            //System.out.println("Current Player Score: " + player.getScore());
	
	            if (player.getScore() > 21) {
	                //System.out.println("Player busts!");
	                //System.out.println("Current Player Score: " + player.getScore());
	                //player.changeHealth(-5); // Player loses health when busting
	            }
	            playerLastMove = "Hit";
	            playerTurn = false;
	        }
        }
    }

	/**
	 * player has selected to stay
	 */
    public void playerStand() {
        if (!gameOver && playerTurn) {
            playerLastMove = "Stand";
            playerTurn = false;
            //updateGameStatus();
        }
    }

	/**
	 * Dealers logic deciding what move to make
	 */
    public void dealerDecision() {
        if (!gameOver && !playerTurn) {
        	if(dealer.getScore()<17 && !disabledHitOption)
        	{
        		Card card = deck.dealCard();
        		dealer.addCardToHand(card);
        		dealerLastMove = "Hit";
        		playerTurn = true;
        		
        	}
        	else
        	{
        		dealerLastMove = "Stand";
        		playerTurn = true;
        		System.out.println("Deaker Stands");
        	}
        }
    }
    
	/**
	 * checks to see if round is over or not
	 */
    public void checkRoundOver() {
        if (dealerLastMove=="Stand" && playerLastMove=="Stand") {
        	System.out.println("round is over");
        	this.roundOver = true;
        }
        else
        {
	        this.roundOver = false;
	        System.out.println("round is not over");
        }
    }

	/**
	 * updates status if the round has ended
	 */
    public void updateGameStatus() {
    	//System.out.println("made it into updateGameStatus");
        if (dealerLastMove=="Stand" && playerLastMove=="Stand") {
            int playerScore = player.getScore();
            int dealerScore = dealer.getScore();
            roundOver = true;///testing
            if (playerScore > 21 && dealerScore > 21) {
            	if(playerScore<dealerScore)
            	{
	                //System.out.println("Player and Dealer Busts! Player wins the round.");
	                dealer.changeHealth(-amountDamageTaken); // Dealer wins, player loses health
	                player.addCardToHand(new PowerCard());
            	}
	            else //dealer wins ties
	            {
	                //System.out.println("Player and Dealer Busts! Dealer wins the round.");
	                player.changeHealth(-amountDamageTaken); // Dealer wins, player loses health
	            }
            }
            else if (dealerScore > 21) {
            	//System.out.println("Dealer Busts! Player wins the round.");
            	dealer.changeHealth(-amountDamageTaken); // Player wins, dealer loses health
            	player.addCardToHand(new PowerCard());
            }
            
            else if (playerScore > 21) {
            	//System.out.println("Player Busts! Dealer wins the round.");
            	player.changeHealth(-amountDamageTaken); // Player wins, dealer loses health
            }
            
            else if (playerScore > dealerScore) {
                //System.out.println("Player wins the round.");
                dealer.changeHealth(-amountDamageTaken); // Dealer loses health
                player.addCardToHand(new PowerCard());
            }
            else if (dealerScore > playerScore) {
                //System.out.println("Dealer wins the round.");
                player.changeHealth(-amountDamageTaken); // Player loses health
            }
            else {
            	//System.out.println("It's a tie!"); // dealer wins ties
                //System.out.println("Dealer wins the round.");
                player.changeHealth(-amountDamageTaken); // Player loses health
            }
            //System.out.println("Dealer Score: " + dealerScore);
            //System.out.println("Player Score: " + playerScore);
            // Check if the game should end based on health
            if (player.getHealth() <= 0 || dealer.getHealth() <= 0) {
                gameOver = true;
                if(player.getHealth()<=0)
                {
                	//System.out.println("Player has lost all health. Game Over!");
                }
                else
                {
                	//System.out.println("Dealer has lost all health. Game Over!");
                }
            }
            //System.out.println("new round starting");
            newRound(); 
        }
    }

	/**
	 * resets everything in preperation for the next round
	 */
    public void newRound() {
    	amountDamageTaken = 5;
    	disabledHitOption = false;
    	sendFutureMessage = false;
        if (!gameOver) {  // Only start a new round if the game is not over
        	int size = player.getHand().size();
        	dealer.getHand().clear();
        	for(int i = 0; i < size;i++)
        	{
        		//make sure power cards are not deleted after a new round and remain 
        		//on the left hand side of the hand the player
        		if(player.getHand().get(0).getValue()!=0)
        		{
            		player.getHand().remove(0);	
        		}
        		else
        		{
        			player.getHand().add(player.getHand().get(0));
        			player.getHand().remove(0);
        		}
        		
        	}
            startRound();
        }
    }

	/**
	 * this is the messages that will displayed on the terminal helping the player know what is going on
	 */
    public String getGameStatus() {
        if (gameOver) {
            if (player.getHealth() <= 0) {
                return " Game Over! Player has lost all health.";
            } else{
                return " Game Over! Dealer has lost all health.";
            } 
        }
        if(roundOver) {
        	String result;
    		if(player.getScore()>dealer.getScore())
    		{
    			if(player.getScore()<=21)
    			{
    				result = " Player wins!";
    			}
    			else
    			{
    				result = " Dealer wins!";
    			}
    		}
    		else
    		{
    			if(player.getScore()==dealer.getScore())
    			{
    				result = " Dealer wins!";
    			}
    			else if(dealer.getScore()<=21)
    			{
    				result = " Dealer wins!";
    			}
    			else
    			{
    				result = " Player wins!";
    			}
    		}
            return " Round Over!\n Player Score: " + player.getScore() + "\n Dealer Score: " + dealer.getScore() + "\n" + result;
        }
        else {
        	if(sendFutureMessage)
        	{
        		if(player.getScore()>dealer.getScore())
        		{
        			if(player.getScore()<=21)
        			{
        				return " Player is winning";
        			}
        			else
        			{
        				return " Player is losing";
        			}
        		}
        		else
        		{
        			if(player.getScore()==dealer.getScore())
        			{
        				return " Player is losing";
        			}
        			else if(dealer.getScore()<=21)
        			{
        				return " Player is losing";
        			}
        			else
        			{
        				return " Player is winning";
        			}
        		}
        	}
            return " Player's Turn\n" + " Player's Score: " + player.getScore() + "\n Dealer's Visible Card: " + dealer.getHand().get(0);
        }
    }

	/**
	 * @returns where the game has ended or not
	 */
    public boolean isGameOver() {
        return gameOver || player.getHealth() <= 0 || dealer.getHealth() <= 0;
    }

	/**
	 * @returns the player in game 
	 */
    public User getPlayer() {
        return player;
    }

	/**
	 * @returns the dealer in game 
	 */
    public Dealer getDealer() {
        return dealer;
    }
}