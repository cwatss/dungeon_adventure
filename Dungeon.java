/*
 * Course:        TCSS143B - Fundamentals of Object-Oriented Programming
 * File Name:     Dungeon.java
 * Assignment:    5
 * Due Date:      10 June 2021
 * Instructor:    Tom Capaul
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Allows the representation of a Dungeon as a 2D array of Room instances.
 * Annotates all locales of interest within the array and keeps track of
 * the Hero.
 *
 * @author Anthony Nguyen anguyenq@uw.edu
 * @version 27 May 2021
 */
public class Dungeon {
	
	/*
	 * DEBUG MAIN
	 * Utilized with a placeholder Room class that just returns "?" as its
	 * toString()
	 */
	public static void main(String[] theArgs) {
		Dungeon test = new Dungeon();
		System.out.println(test);		
	}
	
	/** 2D array of Rooms within an instance of this class. */
	private Room[][] myMaze;
	
	/** 
	 * 2D integer array holding "coordinates" of locales of interest within
	 * an instance of this class.
	 */
	private int[][] myLocales;
	
	/** Creates an instance of this class. */
	public Dungeon() {
		/* 
		 * Corresponding rows of myLocales
		 * 0 - Entrance
		 * 1 - Exit
		 * 2 - Crown1
		 * 3 - Crown2
		 * 4 - Hero (Starts out at the entrance)
		 */
		myLocales = setLocales();
		myMaze = setMaze();
	}
	
	/**
	 * Creates and returns a 5x2 2D integer array. Each row represents
	 * coordinates and is mutually exclusive.
	 * 
	 * @return a reference to a 5x2 2D integer array.
	 */
	private int[][] setLocales() {
		Random rand = new Random();
		
		int[][] returnArray = new int[5][2];
		
		// Keeps track of what values have been used
		Map<Integer, Set<Integer>> temp = new HashMap<Integer,
													  Set<Integer>>();		
		int holder = 0;
		for (int row = 0; row < returnArray.length - 1; row++) {
			for (int col = 0; col < returnArray[row].length; col++) {
				int hold = rand.nextInt(5);
				if (col == 0)
					holder = hold;
				else {
					if (temp.containsKey(holder)) {
						while(temp.get(holder).contains(hold))
							hold = rand.nextInt(5);
					} else
						temp.put(holder, new HashSet<Integer>());
					temp.get(holder).add(hold);				
				}
				returnArray[row][col] = hold;			
			}
		}
		
		// Starts hero at entrance
		returnArray[4][0] = returnArray[0][0];
        returnArray[4][1] = returnArray[0][1];
        
		return returnArray;	
	}
	
	/**
	 * Creates and returns a 5x5 2D array of Rooms.
	 * 
	 * @return a reference to a 5x5 2D array of Rooms.
	 */
	private Room[][] setMaze() {
		Room[][] returnArray = new Room[5][5];
		
		// Creates non-unique Rooms
		for (int row = 0; row < returnArray.length; row++) {
			for (int col = 0; col < returnArray[row].length; col++)
					returnArray[row][col] = new Room();
		}
		
		// Creates unique Rooms
		for (int i = 0; i < myLocales.length - 1; i++) {
			int row = myLocales[i][0];
			int col = myLocales[i][1];
			switch (i) {
				case 0:
					returnArray[row][col] = new Room("entrance");
					break;
				case 1:
					returnArray[row][col] = new Room("exit");
					break;
				default:
					returnArray[row][col] = new Room("crown");
					break;
			}
		}

		return returnArray;
	}
	
	/**
	 * Returns a String representation of this instance.
	 * 
	 * @return a String representation of this instance.
	 */
	@Override
	public String toString() {
		// * * * * * * * * * * *
		// * E | ? | ? | ? | ? *
		// * - * - * - * - * - *
		// * ? | ? | ? | ? | ? *
		// * - * - * - * - * - *
		// * ? | ? | ? | ? | ? *
		// * - * - * - * - * - *
		// * ? | ? | ? | ? | ? *
		// * - * - * - * - * - *
		// * ? | ? | ? | ? | ? *
		// * * * * * * * * * * *
		StringBuilder sb = new StringBuilder();
		sb.append(lineMaker("*", myMaze[0].length));
		for (int row = 0; row < myMaze.length; row++) {
			sb.append(lineMaker(row));
			if (row != myMaze.length - 1)
				sb.append(lineMaker("-", myMaze[0].length));
		}
		sb.append(lineMaker("*", myMaze[0].length));
		return sb.toString();
	}
	
	/**
     * Creates and return a wall whose length and design is dependent on
     * passed parameters.
     *
     * @param theSegment is the connecting link between *.
     * @param theLength is the length of the wall.
     *
     * @return a String representation of the wall.
     */
	private String lineMaker(final String theSegment, final int theLength) {
		StringBuilder sb = new StringBuilder();
		sb.append("    *");
		for (int i = 0; i < theLength; i++) {
			sb.append(" ");
			sb.append(theSegment);
			sb.append(" ");
			sb.append("*");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	/**
     * Creates and return a row of this instance's myMaze.
     *
     * @param theRow is a row number of this instance's myMaze.
     *
     * @return a String representation of the row.
     */
	private String lineMaker(final int theRow) {
		StringBuilder sb = new StringBuilder();
		sb.append("    *");
		for (int col = 0; col < myMaze[theRow].length; col++) {
			sb.append(" ");
			if (theRow == myLocales[4][0] && col == myLocales[4][1])
				sb.append("@");	// @ for Hero's location
			else
				sb.append(myMaze[theRow][col]);
			sb.append(" ");
			if (col == myMaze[theRow].length - 1)
				sb.append("*");
			else
				sb.append("|");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * Updates the Hero's location within myLocales.
	 * 
	 * @param theDirection is the direction the Hero has chosen to move.
	 */
	public void moveHero(final DungeonCharacter theHero, final String theDirection) {
		boolean properMove = false;
		int x = myLocales[4][0], y = myLocales[4][1];
		switch(theDirection.toLowerCase()) { // toLowerCase() may not be necessary if data is fixed up beforehand
			case "w": 
				if (x - 1 < 0)
					System.out.println("Cannot move up any further.");
				else {
					x -= 1;
					properMove = true;
				}
				break;
			case "s":
				if (x + 1 > myMaze.length - 1)
					System.out.println("Cannot move down any further.");
				else {
					x += 1;
					properMove = true;
				}
				break;
			case "a":
				if (y - 1 < 0)
					System.out.println("Cannot move left any further.");
				else {
					y -= 1;
					properMove = true;
				}
				break;
			case "d":
				if (y + 1 > myMaze[0].length - 1)
					System.out.println("Cannot move right any further.");
				else {
					y += 1;
					properMove = true;
				}
				break;
			default:
				System.out.println("That is not a proper movement.");
				break;
		}
		if (properMove) {
			myLocales[4][0] = x;
			myLocales[4][1] = y;
			myMaze[x][y].roomActivate(theHero);
		}
	}
}
