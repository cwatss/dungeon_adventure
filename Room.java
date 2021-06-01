
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
	
	private static final Random MY_RANDOM = new Random();
	
	/** If an instance's contents have been revealed (true) or not
	 * (false).
	 */
	private boolean myReveal;
	
	/** A list of items within the instance. */
	private List<String> myItems;
	/*
	 * X - Monster
	 * P - Pit
	 * I - Entrance (In) (SPECIAL)
	 * O - Exit (Out) (SPECIAL)
	 * V - Vision Potion
	 * H - Healing Potion
	 * E - Empty Room
	 * M � Multiple Items
	 * C - Crown
	 */

	/** Creates an instance of this class. */
	public Room() {
		myReveal = true;	// TRUE TO DEBUG
		myItems = setItems();
	}
	
	/**
	 * Creates a special instance of this class.
	 * 
	 * @param theUnique is a String description of the unique item in the
	 * instance.
	 */
	public Room(final String theUnique) {
		myReveal = true;	// TRUE TO DEBUG
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
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("H");
		if (MY_RANDOM.nextDouble() <= 0.05)
			returnArray.add("V");
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("P");
		if (MY_RANDOM.nextDouble() <= 0.1)
			returnArray.add("X");
		if (returnArray.size() == 0)
			returnArray.add("E");
		return returnArray;
	}
	
	public void affectHero(final DungeonCharacter theHero) {
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
					theHero.subtractHP(MY_RANDOM.nextInt(20) + 1);
					break;
				case "X":
					//PLACEHOLDER
					// GENERATE MONSTER
					// ACTIVATE COMBAT
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
		
		// Neutralizes all active items within the room
		if (myItems.get(0) != "I" && myItems.get(0) != "O" && myItems.get(0) != "E") {
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