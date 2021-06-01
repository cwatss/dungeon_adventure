/*
 * TCSS 143 –Spring 2021
 * Instructor: Tom Capaul
 *  Documentation of code
 */

/**
 * Class Sorceress that extends from Hero class.
 * The class generates special skills for a sorceress.
 * @author Emmiliia Fedina emmafe@uw.edu
 * @version 25APR2021
 */


public class Sorceress extends Hero {
    /** field for Sorceress minimum special healing */
    private int myMinSpecialHeal;
    /** field for Sorceress maximum special healing */
    private int  myMaxSpecialHeal;
    /**
     * Constructor that passes values parameters from Hero class
     * Initializes new Special Skill for a Sorceress
     * @param theName passes name of a sorceress
     * Passes value to setMinSpecialHeal and setMaxSpecialHeal.
     */
    public Sorceress(final String theName) {
        super(theName, 75, 25, 45, 0.7,  5, 3, 1.0);
        setMinSpecialHeal(30);
        setMaxSpecialHeal(50);

    }

    /**
     * The method is overrides attack from Hero class
     * @param theOpponent is a monster class
     */


    @Override
    protected void specialAttack(final DungeonCharacter theOpponent) {
        int healPoints = generateHeal();
        setHitPoints(healPoints + getHitPoints());
        System.out.println(getName() + " just healed for "
                + healPoints);


    }




    /**
     * The method is generates heal.
     * @return value for heal
     */
    private int generateHeal() {
        return DungeonCharacter.generateRangedValue(myMinSpecialHeal,
                myMaxSpecialHeal);
    }

    /**
     * checks if max special skill is less than 0.
     * If parameter is bigger than 0 it initializes maximum special heal.
     * @param theMaxSpecialHeal
     */
    private void setMaxSpecialHeal(final int theMaxSpecialHeal) {
        if (theMaxSpecialHeal <= 0) {
            throw new IllegalArgumentException("Special heal "
                    + " must be >0");
        }
        myMaxSpecialHeal = theMaxSpecialHeal;
    }

    /**
     * checks if minimum special skill is less than 0.
     * If parameter is bigger than 0 it initializes minimum
     *  special heal.
     * @param theMinSpecialHeal
     */
    private void setMinSpecialHeal(final int theMinSpecialHeal) {
        if (theMinSpecialHeal <= 0) {
            throw new IllegalArgumentException("Special"
                    + " heal must be >0");
        }
        myMinSpecialHeal = theMinSpecialHeal;
    }
}
