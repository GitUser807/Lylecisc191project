import java.util.Random;

public class CardTest {
    public static void main(String[] args) {
        // Test the Card class
        System.out.println("Testing the Card class:");
        Card regularCard = new Card("Hearts", "Ace", 11);
        System.out.println(regularCard);  // Should print: Suit: Hearts, Rank: Ace, Value: 11

        // Modify the card using setters
        regularCard.setSuit("Diamonds");
        regularCard.setRank("King");
        regularCard.setValue(10);
        System.out.println(regularCard);  // Should print: Suit: Diamonds, Rank: King, Value: 10
        System.out.println();

        // Test the BlackjackCard class (extends Card)
        System.out.println("Testing the BlackjackCard class:");
        BlackjackCard blackjackCard = new BlackjackCard("Spades", "Jack", 10);
        System.out.println(blackjackCard);  // Should print: Suit: Spades, Rank: Jack, Value: 10
        System.out.println();

        // Test the PowerCard class (implements Special)
        System.out.println("Testing the PowerCard class:");
        PowerCard powerCard = new PowerCard("Control", "None", 0);
        System.out.println("Before generating ability: " + powerCard);  // Should print default suit: Control, rank: None, value: 0
        
        // Generate an ability for the PowerCard
        powerCard.generateAbility("Risk");
        System.out.println("After generating ability: " + powerCard);  // Should print the suit as Risk and a random rank (e.g., Double Risk)

        // Test if the ability has been generated
        System.out.println("Ability generated: " + powerCard.getIsAbilityGenerated());  // Should print: true
        System.out.println();

        // Test if generating the ability works only once
        powerCard.generateAbility("Insight");
        System.out.println("After attempting to generate a second ability: " + powerCard);  // Should still show the first generated ability
        System.out.println();

        // Testing card dealing from a deck
        System.out.println("Testing card dealing from a deck:");
        Deck deck = new Deck();
        System.out.println("Deck size before dealing: " + deck.deckSize());
        
        // Deal a card and print it
        Card dealtCard = deck.dealCard();
        if (dealtCard != null) {
            System.out.println("Dealt card: " + dealtCard);
        }
        
        // Print remaining deck size
        System.out.println("Deck size after dealing: " + deck.deckSize());
    }
}