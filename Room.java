
/*
 * Course:        TCSS143B - Fundamentals of Object-Oriented Programming
 * File Name:     Room.java
 * Assignment:    5
 * Due Date:      10 June 2021
 * Instructor:    Tom Capaul
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Allows the representation of a Room. A Room may or may not contain
 * various items of interest.
 *
 * @author Anthony Nguyen anguyenq@uw.edu
 * @version 28 May 2021
 */
public class Room {
	
	 /** A Random object for the class to use. */
	private static final Random MY_RANDOM = new Random();
	
	/** If an instance's contents have been revealed (true) or not. */
	private boolean myReveal;
	
	/** A list of items within the instance. */
	private List<String> myItems;
	/*
	 * X - Monster
	 * P - Pit
	 * I - Entrance (In)
	 * O - Exit (Out)
	 * V - Vision Potion
	 * H - Healing Potion
	 * E - Empty Room
	 * M – Multiple Items
	 * C - Crown
	 */

	/** Creates an instance of this class. */
	public Room() {
		myReveal = false;	// TRUE TO DEBUG
		myItems = setItems();
	}
	
	/**
	 * Creates a special instance of this class.
	 * 
	 * @param theUnique is a String for the unique item in the instance.
	 */
	public Room(final String theUnique) {
		myReveal = false;	// TRUE TO DEBUG
		myItems = new ArrayList<String>();
		switch (theUnique) {
			case "entrance":
				myReveal = true;
				myItems.add("I");
				break;
			case "exit":
				myItems.add("O");
				break;
			case "crown":
				myItems.add("C");
				break;
		}
	}
	
	/**
	 * Populates an instance with items.
	 * 
	 * @return a reference to an ArrayList of String items.
	 */
	private ArrayList<String> setItems() {
		ArrayList<String> returnArray = new ArrayList<String>();
		
		// Hero takes damage from pit first, fights monster second, and then picks up loot last
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("P");
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("X");
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("H");
		if (MY_RANDOM.nextDouble() <= 0.05)
			returnArray.add("V");
		if (returnArray.size() == 0)
			returnArray.add("E");
		return returnArray;
	}
	
	/**
	 * Activates the effects of the items within the instance upon the Hero
	 * when the Hero enters for the first time.
	 * 
	 * @param theHero is a DungeonCharacter instance for the player.
	 */
	public void roomActivate(final DungeonCharacter theHero) {
		myReveal = true;	// Hero retains knowledge of entered rooms
		if (myItems.get(0) != "I" && myItems.get(0) != "O" && myItems.get(0) != "E") {
			for (int i = 0; i < myItems.size(); i++) {
				switch (myItems.get(i)) {
					case "H":
						//PLACEHOLDER
						// HERO GETS HEALTH POT
						break;
					case "V":
						//PLACEHOLDER
						// HERO GETS VISION POT
						break;
					case "P":
						// Hero only takes damage from a pit upon entering a Room for the first time
						theHero.subtractHP(MY_RANDOM.nextInt(20) + 1);
						break;
					case "X":
						//PLACEHOLDER
						// GENERATEs MONSTER
						// ACTIVATEs COMBAT
						break;
					case "C":
						//PLACEHOLDER
						// HERO GETS A CROWN PIECE
						break;
					default:
						//PLACEHOLDER
						// WILL NEED MORE CASES
						break;
				}
			}
			myItems.clear();
			myItems.add("E");
		}
	}
	
	/**
	 * Returns a String representation of this instance.
	 * 
	 * @return a String representation of this instance.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (myReveal) {
			if (myItems.size() > 1)
				sb.append("M");
			else
				sb.append(myItems.get(0));
		} else
			sb.append("?");
		return sb.toString();
	}
}
