

/**
 * Class Warrior inherits from class hero.
 * this class generates special skills for a Warrior.
 * @author Corinne Watson
 * @version 4/21/21
 */
public class Warrior extends Hero {
    /** field for min special damage value */
    private int myMinSpecialDamage;
    /** field for max special damage value */
    private int myMaxSpecialDamage;
    /**
     * Create constructor for warrior
     * @param theName
     */
    public Warrior(final String theName) {
        super(theName, 125, 35, 60,
                .8, 4, 20,
                0.4, "Warrior.wav");//expects all the value from hero constructor
        setMinSpecialDamage(75);
        setMaxSpecialDamage(175);

    }
    /**
     * Checks if maximum special damage is less than 0.
     * If it is bigger than 0, than initialize myMaxSpecialDamage
     * @param theMaxSpecialDamage is maximum special damage
     */
    private void setMaxSpecialDamage(final int theMaxSpecialDamage) {
        if (theMaxSpecialDamage <= 0) {
            throw new IllegalArgumentException(" Special skill must be >0 ");

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
            throw new IllegalArgumentException(" Special skill must be >0 ");
        }
        myMinSpecialDamage = theMinSpecialDamage;
    }

    /**
     * The method overrides special attack for Warrior
     *@param theOpponent is a monster class
     */
    @Override
    protected void specialAttack (final DungeonCharacter theOpponent) {
        System.out.print(getName() + " crushing blow");
        if(canUseSpecialSkill()) {
            // PLAY SOUND
            soundPlayer.play(getCharacterSpecificAttackSoundPath());

            int damage = generateSpecialDamage();
            System.out.println(" and hits for ** " + damage + " ** damage!! ");
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
        return DungeonCharacter.generateRangedValue(myMinSpecialDamage, myMaxSpecialDamage);
    }
}
