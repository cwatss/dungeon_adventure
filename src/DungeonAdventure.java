/*
 * Course:        TCSS143B - Fundamentals of Object-Oriented Programming
 * File Name:     DungeonAdventure.java
 * Assignment:    5
 * Due Date:      10 June 2021
 * Instructor:    Tom Capaul
 */


import java.util.Scanner;

/**
 * Contains the instruction of the game and
 * creates a Dungeon Object and a Hero object.
 * Contains main method
 *
 * @author emmafedina
 * @version 31may2021
 */
public class DungeonAdventure {
    public static Hero createHero() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the game!!!");
        System.out.println();
        System.out.println("This is an adventure game where a" +"\n" +
                            "hero is randomly placed within a dungeon that is" +"\n" +
                            "a 5 x 5 2D array of Rooms." +"\n" +
                            "The hero needs to find the two pieces"+ "\n" +
                            "of the Crown of Coding," +"\n"+
                            "and take them to the exit to win the game. "+"\n" +
                            "Some features of the dungeon will prove a hindrance to the hero's task " +"\n"+
                            "(monsters!), while some will prove helpful (healing potions).");
        System.out.println();
        System.out.println("You need to pick your hero");
        System.out.println("1-Ninja Turtle \n"+
                            "2-Warrior \n"+
                            "3-Thief \n"+
                            "4-Sorceress \n");
        System.out.println("Type your number: ");

        /** initialize with number user pick. */
        int number = input.nextInt();

        System.out.println(number);

        System.out.println("Type the name for hero: ");

        /** initialize name for monster*/
        String name = input.next();

        System.out.println("The name of your hero is " + name);

        /** switch case to pick a hero*/
        switch (number){
            case 1:
                return new NinjaTurtle(name);
            case 2:
                return new Warrior(name);
            case 3:
                return new Thief(name);
            case 4:
                return new Sorceress(name);
            default:
                throw new Exception("The number should be 1 - 4" +
                        "You typed " +  number);
        }

    }

    /**
     * Main method that creates a hero
     * and a dungeon object.
     * @param theArgs
     * @throws Exception
     */
    public static void main(String[] theArgs) throws Exception {
        Hero newHero = createHero();
        System.out.println("You successfully create hero " + newHero.getClass().getSimpleName() +
                            " with name " + newHero.getName());
        //Dungeon newDungeon = createDungeon();

       // fight(newHero);
      //  createDungeon();
    }

}
