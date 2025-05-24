
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
 * Responsibilities of class: How a power card works
 * 
 *
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class PowerCard extends Card implements Special { // a PowerCard is IS-A Card and IS special
	private Hashtable<String, String> abilities; // a PowerCard HAS-Many different abilities
	boolean isAbilityGenerated; // a power cards HAS-A indicator of when it's ability has been generated

	/**
	 * Constructor method for the rank of the BlackjackCard
	 * 
	 * @param the desired rank of this card
	 */
	public PowerCard() {
		// calls super constructor
		super("Special", "None", 0);
		// Responsible for storing the different powercard abilities
		abilities = new Hashtable<>();// A power card HAS-MANY different abilities
		// Power card has just been created so it's ability hasn't been generated
		isAbilityGenerated = false;
		// Adds the keys(Ability Category) and the value(what the ability does)
		abilities.put("Risk", "DoubleDmg");
		abilities.put("Insight", "WinOrLose");
		abilities.put("Control", "StopDrawing");
	}

	/**
	 * Generates an ability based on the given key
	 * 
	 * @param key the given type of ability desired
	 */
	public void generateAbility(String key) {
		if (!getIsAbilityGenerated()) {
			if (abilities.containsKey(key)) {
				// If the ability is listed the the abilities hashtable
				// pick random ability in that category for the power card
				setSuit(key);
				setRank(abilities.get(key));
				setValue(0);
				this.isAbilityGenerated = true;
			}
		}
	}

	/**
	 * Getter method to see if the power card's ability has been generated
	 * 
	 * @return whether the power card's ability has been generated
	 */
	public boolean getIsAbilityGenerated() {
		return this.isAbilityGenerated;
	}

}
