import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class PowerCard extends Card implements Special { // a PowerCard is IS-A Card and IS special
	private Hashtable<String, ArrayList<String>> abilities;
	boolean isAbilityGenerated; // a power card knows when it has been activated

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
		abilities.put("Risk", new ArrayList<>());
		abilities.get("Risk").add("DoubleDmg");
		abilities.put("Insight", new ArrayList<>());
		abilities.get("Insight").add("WinOrLose");
		abilities.get("Insight").add("WinOrLose");
		abilities.put("Control", new ArrayList<>());
		abilities.get("Control").add("StopDrawing");
		
		//Might add these later if game is not too complicated
		//abilities.get("Risk").add("ForcedCardDraw");//testing this out
		//abilities.get("Insight").add("WhatNext");//testing this out
		// abilities.get("Control").add("RefreshHand");//testing this out
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
				Random random = new Random();
				int index;
				index = random.nextInt(abilities.get(key).size());
				setSuit(key);
				setRank(abilities.get(key).get(index));
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
