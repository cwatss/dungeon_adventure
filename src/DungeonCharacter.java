
/*
 * TCSS 143 –Spring 2021
 * Instructor: Tom Capaul
 *  Documentation of code
 */

/**
 * This program runs a game called Heros VS Monsters
 * @author Corinne Watson and Emma Fedina
 * @version April 19th 2021
 *
 */
import java.util.Random;
import java.util.Scanner;

/**
 * This program runs a game called Heros VS Monsters
 * @author Corinne Watson and Emma Fedina
 * @version April 19th 2021
 *
 */

/**
 * Class DungeonCharacter is a public abstract class which
 * serves as the super class for the program. It is the super class
 * for class Hero, class Monster, and their subclasses.
 */

public abstract class DungeonCharacter {
    protected final static Random MY_RANDOM = new Random();
    public final static Scanner input = new Scanner(System.in);

    /** field for character's name */
    private String myName;
    /** field for how much hit a character takes */
    private int myHitPoints;
    /** field for minimum damage a hit will cause on a character */
    private int myMinDamage;
    /** field for maximum damage a hit will cause on a character */
    private int myMaxDamage;
    /** field for the percent chance that a character has of hitting opponent */
    private double myChanceToHit;
    /** field for how fast a character attacks */
    private int myAttackSpeed;

    /** field for whether god mode is activated */
    private boolean isInGodMode;

    /**
     * Method generateRangedValue serves as away for the entire program
     * to grab a random number when needed. Child classes can use this
     * method through polymorphism, thus reducing the need for repeat
     * code within subclasses.
     *
     * @param theLow the low value of given range; ie: myMinDamage
     * @param theHigh the high value of given range; ie: myMaxDamage
     * @return a random int that is within the specified range
     */
    protected static int generateRangedValue(final int theLow, final int theHigh) {
        return theLow + MY_RANDOM.nextInt(theHigh-theLow + 1);
    }

    /**
     * DungeonCharacter constructor initializes the instance fields,
     * setters utilize the parameters passed in
     *
     * @param theName is the character's name
     * @param theHitPoints is the hit a character takes
     * @param theMinDamage is minimum damage a hit causes
     * @param theMaxDamage is the max damage a hit causes
     * @param theChanceToHit the percent chance that a character has of hitting opponent
     * @param theAttackSpeed how fast a character attacks
     */
    protected DungeonCharacter(final String theName, final int theHitPoints,
                               final int theMinDamage,final int theMaxDamage,
                               final double theChanceToHit, final int theAttackSpeed) {
        setName(theName);
        setHitPoints(theHitPoints);
        setMinDamage(theMinDamage);
        setMaxDamage(theMaxDamage);
        setChanceToHit(theChanceToHit);
        setAttackSpeed(theAttackSpeed);
    }

    /**
     * method setName checks to see if user input for name
     * is valid, if it is valid it sets the character's name
     * @param theName
     * @return void
     */
    protected final void setName(final String theName) {
        if (theName == null || theName.length()==0) {
            throw new IllegalArgumentException("Dungeon character name was null");
        }

        if(theName.contains("!")){
            isInGodMode = true;
            System.out.println(">>>>>GOD MODE ACTIVE<<<<<<");
        }

        myName = theName;
    }

    /**
     * method setHitPoints checks to see if a hit is valid (a positive int),
     * if valid, it sets the hit points to theHitPoints that were applied
     * @param theHitPoints
     * @return null
     */
    protected final void setHitPoints(final int theHitPoints) {
        if (theHitPoints < 0)
            throw new IllegalArgumentException("The hit point is negative");
        myHitPoints = theHitPoints;
    }

    /**
     * method setMinDamage checks to see if min damage is valid,
     * if valid it also calls checkMinVersusMax so ensure that the
     * min is within range and less than max
     * @param theMinDamage
     * @return null
     */
    protected final void setMinDamage(final int theMinDamage) {
        if (theMinDamage <= 0)
            throw new IllegalArgumentException("The mindamage is negative or 0");
        myMinDamage = theMinDamage;
        if (myMaxDamage>0)
            checkMinVersusMax();
    }

    /**
     * method setMaxDamage checks to see if max damage is valid,
     * if valid it also calls checkMinVersusMax so ensure that the
     * max is within range and greater than min
     * @param theMaxDamage passes in int representing max hit damage
     */
    protected final void setMaxDamage(final int theMaxDamage){
        if (theMaxDamage <= 0) {
            throw new IllegalArgumentException("The mindamage is negative or 0");
        }
        myMaxDamage = theMaxDamage;
        checkMinVersusMax();
    }

    /**
     * method checkMinVersusMax checks to see if min is in fact less
     * than the given max value
     */
    private void checkMinVersusMax() {
        if (myMinDamage > myMaxDamage)
            throw new IllegalArgumentException("The min damage cant be more than max damage");

    }

    /**
     * method setChanceToHit checks to make sure that the chance to hit is valid
     * @param theChanceToHit passes in the double that represents
     * the percentage a character has to hit
     * @return void
     */
    protected final void setChanceToHit(final double theChanceToHit) {
        if (theChanceToHit <= 0 || theChanceToHit >1)
            throw new IllegalArgumentException("Chance to hit smaller 0 or bigger 1");
        myChanceToHit = theChanceToHit;
    }

    /**
     * method setAttackSpeed checks to see if the passed value is
     * valid, if so it sets that value as the attack speed for given character
     * @param theAttackSpeed passes in the given attack speed for given character
     * @return void
     */
    protected final void setAttackSpeed(final int theAttackSpeed) {
        if (theAttackSpeed < 0)
            throw new IllegalArgumentException("attack speed <0");
        myAttackSpeed = theAttackSpeed;
    }

    /**
     * method subtractHitPoints checks to see if theAmount of hit
     * passed in is valid, if so then it updates the current hit points
     * by subtracting the hit that was applied
     * @param theAmount is the amount of hit/damage applied
     * @return void
     */
    protected void substractHitPoints(final int theAmount ) {
        if (theAmount< 0)
            throw new IllegalArgumentException("amount <0");
        System.out.println(getName()+ " receives << " + theAmount + " >> points in damage from the attack");
        myHitPoints = 	myHitPoints - theAmount;
        if (myHitPoints <= 0) {
            myHitPoints = 0;
            System.out.println(getName()+" has fainted!");
        }
        System.out.println(getName()+ " hit points now at << "+ myHitPoints + " >>");

    }

    /**
     * method getName gets the current characters name
     * @return current character's name
     */

    public final String getName() {
        return myName;
    }

    /**
     * method getHitPoints gets the current characters hit points
     * @return the current characters hit points
     */
    public final int getHitPoints() {
        return myHitPoints;
    }

    public final int getAttackSpeed(){
        return myAttackSpeed;
    }

    /**
     * method alive checks if the hit points are greater than 0
     * to determine if current character is alive or not
     * @return true if character is alive or false if hitPoints < 0
     */
    public final boolean alive() {
        return myHitPoints > 0;
    }

    /**
     * Indicates whether a character is in god mode.
     * @return boolean indicating if character is in god mode
     */
    protected final boolean getIsInGodMode() {
        return isInGodMode;
    }

    /**
     * Method attack checks to see if opponent is valid,
     * it then checks to see if opponent is alive,
     * it then generates a random double, if that value falls
     * within "myChanceToHit" then a hit within the characters
     * damage range is applied. If chance to hit was not within range,
     * it informs the user that the hit was missed
     * @param theOpponent passes in the current opponent character
     */
    public void attack (final DungeonCharacter theOpponent) {
        if (theOpponent == null) {
            throw new IllegalArgumentException("no opponents to attack");
        }
        if (alive()) {
            double randDouble = MY_RANDOM.nextDouble();
            if (randDouble <= myChanceToHit) {
                int damage = generateRangedValue(myMinDamage,myMaxDamage);
                System.out.println(getName()+ " attacked " + theOpponent.getName());
                theOpponent.substractHitPoints(damage);
            } else {
                System.out.println(getName()+ " attack on " + theOpponent.getName()+ " missed!");

            }

        }
    }
}