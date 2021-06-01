/*
 * TCSS 143 –Spring 2021
 * Instructor: Tom Capaul
 *  Documentation of code
 */

/**
 * This program extends warrior from hero class.
 * @author  Corinne Watson
 * @version April 19th 2021
 *
 */



/**
 * class Monster inherits from class DungeonCharacter. It inherits
 * all necessary fields and methods from DungeonCharacter, gets and sets
 * the necessary fields specific to a Monster, and contains a heal method.
 * Healing is specific to Monsters. Monsters also have the chance to use a
 * special attack method called "double damage" that doubles the amount
 * of hit applied to their opponent.
 */
public abstract class Monster extends DungeonCharacter {
    /** field for Monsters's percentage of healing */
    private double myChanceToHeal;
    /** field for Monster's minimum heal points*/
    private int myMinHealPoints;
    /** field for Monster's max heal points */
    private int myMaxHealPoints;
    /** field for Monster's percent chance to use special attack*/
    private double mySpecialAttackChance;
    /** field for the minimum special damage*/
    private int myMinSpecialDamage;
    /** field for max special damage */
    private int myMaxSpecialDamage;


    /**
     * Constructor for Monster is passed in all of the necessary fields
     * that it inherits from DungeonCharacter. It initializes those fields and also
     * handles the new fields that are specific to a Monster.
     * @param theName current Monster's name
     * @param theHitPoints current Monster's hit points
     * @param theMinDamage current Monster's minimum damage
     * @param theMaxDamage current Monster's max damage
     * @param theChanceToHit current Monster's chance of hitting opponent
     * @param theAttackSpeed current Monster's attack speed
     * @param theChanceToHeal current Monster's percentage of healing
     * @param theMinHealPoints current Monster's min heal points
     * @param theMaxHealPoints current Monster's max heal points
     * @param theSpecialAttackChance current Monster's percent chance of special attack
     */
    protected Monster(final String theName, final int theHitPoints,
                      final int theMinDamage,final int theMaxDamage,
                      final double theChanceToHit, final int theAttackSpeed,
                      final double theChanceToHeal, final int theMinHealPoints,
                      final int theMaxHealPoints,
                      final double theSpecialAttackChance) {
        super(theName, theHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
        setMyChanceToHeal(theChanceToHeal);
        setMyMinHealPoints(theMinHealPoints);
        setMyMaxHealPoints(theMaxHealPoints);
        setSpecialAttackChance(theSpecialAttackChance);
        setMyMinSpecialDamage(25);
        setMyMaxSpecialDamage(70);
    }

    /**
     * method setSpecialAttackChance checks to see if the double passed
     * in is valid and then sets that as the percentage chance to
     * perform the special attack
     * @param theSpecialAttackChance is the percentage chance of applying attack
     */
    protected final void setSpecialAttackChance(final double theSpecialAttackChance) {
        if (theSpecialAttackChance <= 0) {
            throw new IllegalArgumentException("SpecialSkill > 0");
        }
        mySpecialAttackChance = theSpecialAttackChance;

    }

    /**
     * method canUseSpecialAttack checks to see if theAttackChance is
     * within the range of 0-100
     * @return true if attack chance is within range, false if out of bound
     */
    protected final boolean canUseSpecialAttack() {
        return MY_RANDOM.nextInt(101) <= mySpecialAttackChance;

    }

    /**
     * method specialAttack applies a special "double damage" attack
     * on the opponent. If the special attack chance is within range,
     * the special damage generated is doubled
     * @param theOpponent the current opposing hero is passed in
     */
    protected void specialAttack (final DungeonCharacter theOpponent) {
        if(canUseSpecialAttack()) {
            System.out.print(getName() + " DOUBLE DAMAGE!");
            int damage = generateSpecialDamage() * 2;
            System.out.println(" and hits for ** " + damage + " ** damage!! ");
            theOpponent.substractHitPoints(damage);
        }
        else {
            System.out.println(" Monster could not use special skill :-( ");
        }
    }

    /**
     * method setMyMinSpecialDamage checks to see if argument is valid
     * and sets that to the min special damage
     * @param theMinSpecialDamage minimum special damage that can be applied
     */
    private void setMyMinSpecialDamage(final int theMinSpecialDamage) {
        if (theMinSpecialDamage <= 0) {
            throw new IllegalArgumentException(" Special skill must be > 0 ");

        }
        myMinSpecialDamage = theMinSpecialDamage;
    }

    /**
     * method setMyMaxSpecialDamage checks to see if argument is valid
     * and sets that to the max special damage
     * @param theMaxSpecialDamage max special damage that can be applied
     */
    private void setMyMaxSpecialDamage(final int theMaxSpecialDamage) {
        if (theMaxSpecialDamage <= 0) {
            throw new IllegalArgumentException(" Special skill must be > 0 ");
        }
        myMaxSpecialDamage = theMaxSpecialDamage;
    }

    /**
     * method generateSpecialDamage calls generateRangedValue
     * and assigns a random value within the special damage range
     * @return an int that represents the special damage to be applied
     */
    private int generateSpecialDamage() {
        return DungeonCharacter.generateRangedValue(myMinSpecialDamage, myMaxSpecialDamage);
    }


    /**
     * method setMyChanceToHeal sets Monster's chance to heal with the
     * double that is passed in from constructor
     * @param theChanceToHeal Monster's percentage of healing
     */
    protected final void setMyChanceToHeal(final double theChanceToHeal) {
        myChanceToHeal = theChanceToHeal;
    }

    /**
     * method setMyMinHealPoints sets Monster's min heal points with the
     * value that is passed in from constructor
     * @param theMinHealPoints minimum heal applied to Monster
     */
    protected final void setMyMinHealPoints(final int theMinHealPoints){
        myMinHealPoints = theMinHealPoints;
    }
    /**
     * method setMyMaxHealPoints sets Monster's max heal points with the
     * value that is passed in from constructor
     * @param theMaxHealPoints max heal applied to Monster
     */
    protected final void setMyMaxHealPoints(final int theMaxHealPoints) {
        myMaxHealPoints = theMaxHealPoints;
    }

    /**
     * method subtractHitPoints overrides the method from DungeonCharacter
     * and updates the hit that the current Monster receives. If a hit
     * is applied AND Monster is still alive, heal
     * @param theAmount is the amount of hit/damage applied
     */
    @Override
    protected void substractHitPoints(final int theAmount ) {
        super.substractHitPoints(theAmount);
        if(alive()) {
            if(MY_RANDOM.nextDouble() <= myChanceToHeal) {
                heal();
            }

        }
    }

    /**
     * method getChanceToHeal gets the percentage a Monster has to heal
     * @return double that represents the percentage chance
     * a monster has to heal
     */
    public final double getChanceToHeal() {
        return myChanceToHeal;
    }

    /**
     * method getMyMinHealPoints gets the minimum heal points
     * @return current monster's min heal points
     */
    public final int getMyMinHealPoints() {
        return myMinHealPoints;
    }

    /**
     * method getMyMaxHealPoints gets the max heal points
     * @return current monster's max heal points
     */
    public final int getMyMaxHealPoints() {
        return myMaxHealPoints;
    }

    /**
     * method getMySpecialAttackChance gets the percentage
     * chance of Monster using the special attack
     * @return double representing the percent chance
     * of utilizing special attack
     */
    public final double getMySpecialAttackChance() {
        return mySpecialAttackChance;
    }

    /**
     * method heal allows a Monster to heal if theChanceToHeal is within range.
     * A random amount of hit points within the range are added on to a character's
     * current hit points.
     */
    public void heal() {
        int healPoints = DungeonCharacter.generateRangedValue(myMinHealPoints, myMaxHealPoints);
        setHitPoints(healPoints + getHitPoints());
    }

}



