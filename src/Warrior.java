/*
 * TCSS 143 –Spring 2021
 * Instructor: Tom Capaul
 *  Documentation of code
 */

/**
 * This program extends warrior from hero class.
 * @author Emmiliia Fedina emmafe@uw.edu
 * @version April 19th 2021
 *
 */
public class Warrior extends Hero {
    /** field for Warrior minimum special damage */
    private int myMinSpecialDamage;
    /** field for Warrior maximum special damage */
    private int myMaxSpecialDamage;

    /**
     * Create constructor for warrior
     * @param theName
     */

    public Warrior(final String theName) {
        super(theName, 125, 35, 60, .8, 4, 20, 0.4);//expects all the value
        //from hero constructor
        setMinSpecialDamage(75);//value for min special value
        setMaxSpecialDamage(175);//value for max special value

    }
    /**
     * Checks if maximum special damage is less than 0.
     * If it is bigger than 0, than initialize myMaxSpecialDamage
     * @param theMaxSpecialDamageis is maximum special damage
     */
    private void setMaxSpecialDamage(final int theMaxSpecialDamage) {
        if (theMaxSpecialDamage <= 0) {
            throw new IllegalArgumentException(" Special "
                    + "skill must be >0 ");

        }
        myMaxSpecialDamage = theMaxSpecialDamage;
    }
    /**
     * Checks if minimum special damage is less than 0.
     * If it is bigger than 0, than initialize myMinSpecialDamage
     * @param theMinSpecialDamage is minimum special damage
     */

    private void setMinSpecialDamage(final int theMinSpecialDamage) {
        if (theMinSpecialDamage <= 0) {
            throw new IllegalArgumentException(" Special"
                    + " skill must be >0 ");

        }
        myMinSpecialDamage = theMinSpecialDamage;
    }
    /**
     * The method overrides special attack for Warrior
     *@param theOpponent is a monster class
     */
    @Override
    protected void specialAttack (final DungeonCharacter theOpponent) {
        System.out.print(getName() + "crushing blow");
        if(canUseSpecialSkill()) {
            int damage = generateSpecialDamage();
            System.out.println(" and hits for **"
                    + damage + "**damage!! ");
            theOpponent.substractHitPoints(damage);
        }
        else {
            System.out.println(" FAILS ");
        }
    }
    /**
     * generates value for special damage
     * @return ranged value
     */
    private int generateSpecialDamage() {
        return DungeonCharacter.generateRangedValue(myMinSpecialDamage,
                myMaxSpecialDamage);
    }

}





