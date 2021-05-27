
/**
 * Class Sorceress that extends from Hero class.
 * The class generates special skills for a sorceress.
 * @author Emmiliia Fedina emmafe@uw.edu
 * @version 25APR2021
 */

public class Sorceress extends Hero {
    /** field for min heal value*/
    private int myMinSpecialHeal;
    /** field for max heal value*/
    private int  myMaxSpecialHeal;
    /**
     * Constructor that passes values parameters from Hero class
     * Initializes new Special Skill for a Sorceress
     * @param theName passes name of a sorceress
     * Passes value to setMinSpecialHeal and setMaxSpecialHeal.
     */
    public Sorceress(final String theName) {
        super(theName, 75, 25, 45, 0.7,
                5, 3, 1.0,
                "sorceress.wav");
        setMinSpecialHeal(30);
        setMaxSpecialHeal(50);

    }

    /**
     * Overrides special attack from Hero class.
     * the attack will take all the points from theOponnent
     */
    @Override
    protected void specialAttack(final DungeonCharacter theOpponent) {
        int healPoints = generateHeal();
        setHitPoints(healPoints + getHitPoints());
        System.out.println(getName() + " just healed for " + healPoints);

        // PLAY SOUND
        soundPlayer.play(getCharacterSpecificAttackSoundPath());
    }
    /**
     * The method is generates heal.
     * @return value for heal
     */
    private int generateHeal() {
        return DungeonCharacter.generateRangedValue(myMinSpecialHeal, myMaxSpecialHeal);
    }

    /**
     * checks if max special skill is less than 0.
     * If parameter is bigger than 0 it initializes maximum special heal.
     * @param theMaxSpecialHeal max heal value
     */
    private void setMaxSpecialHeal(final int theMaxSpecialHeal) {
        if (theMaxSpecialHeal <= 0) {
            throw new IllegalArgumentException("Special heal must be >0");
        }
        myMaxSpecialHeal = theMaxSpecialHeal;
    }
    /**
     * checks if minimum special skill is less than 0.
     * If parameter is bigger than 0 it initializes minimum
     *  special heal.
     * @param theMinSpecialHeal min heal value
     */
    private void setMinSpecialHeal(final int theMinSpecialHeal) {
        if (theMinSpecialHeal <= 0) {
            throw new IllegalArgumentException("Special heal must be >0");
        }
        myMinSpecialHeal = theMinSpecialHeal;
    }



}
