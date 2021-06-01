/*
 * TCSS 143 –Spring 2021
 * Instructor: Tom Capaul
 *  Documentation of code
 */



/**
 * Class Thief that extends from Hero class.
 * The class generates special skills for a thief.
 * @author Emmiliia Fedina emmafe@uw.edu
 * @version 25APR2021
 */


public class Thief extends Hero{
    //field that is specific for Thief
    private int mySpecialSkillChance;

    /**
     * Constructor that passes values parameters from Hero class
     * Initializes new Special Skill for a Thief
     * @param theName passes name of a Thief
     */
    public Thief (final String theName) {
        super(theName, 75, 20, 40, 0.8, 6, 40, 40);
        setSpecialSkillChance(40);

    }


    /**
     * checks if special skill chance is bigger than 0
     * If value is bigger than it initializes Special skill
     * @param theSpecialSkillChance passes special skill
     */
    private void setSpecialSkillChance(int theSpecialSkillChance) {
        if (theSpecialSkillChance <= 0) {
            throw new IllegalArgumentException("Special "
                    + "skill must be >0");
        }
        mySpecialSkillChance = theSpecialSkillChance;
    }
    /**
     * The method is overrides attack from Hero class
     * @param theOpponent is a monster class
     */
    @Override
    protected void specialAttack (final DungeonCharacter theOpponent) {
        int randomValue = MY_RANDOM.nextInt(100);
        if (randomValue <= mySpecialSkillChance) {
            //code for sneak attack
            System.out.print(getName() + "sneaky attack");
            theOpponent.substractHitPoints(randomValue);

        }

        else if (randomValue > 80) {
            System.out.println("thief get caught");
        }
        else {
            //normal attack
            theOpponent.substractHitPoints(randomValue);

        }
    }


}



