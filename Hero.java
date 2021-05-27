
    /**
     * Class Hero that extends from DungeonCharacter class.
     * The class generates special skills for hero.
     * @author Emmiliia Fedina emmafe@uw.edu
     * @version 25APR2021
     */
public abstract class Hero extends DungeonCharacter {
    /** field for chance to block value*/
    private int myChanceToBlock;
    /** field for if a character runs away*/
    private boolean myRunAway;
    /** field for special skill percentage */
    private double mySpecialSkillChance;
    /** field for hero's specific sound */
    private String characterSpecificAttackSoundPath;
    /** field for hero's block sound*/
    private static String blockSoundPath ="block.wav";

    /**
     *  Construct all the variables from DungeonCharacter class.
     *  Initializes parameters specific for Hero class
     * @param theName is the String with a hero name.
     * @param theHitPoints is the integer with hit points.
     * @param theMinDamage is the integer with minimum points.
     * @param theMaxDamage is the integer with maximum points.
     * @param theChanceToHit is the double with a chance to hit.
     * @param theAttackSpeed is the integer with attack speed.
     * @param theChanceToBlock is the integer with a chance to block.
     * @param theSpecialSkillChance is a double with a special
     *  skill chance.
     */
    protected Hero(final String theName, final int theHitPoints,
                   final int theMinDamage,final int theMaxDamage,
                   final double theChanceToHit, final int theAttackSpeed,
                   final int theChanceToBlock, final double theSpecialSkillChance ) {
        super(theName, theHitPoints,theMinDamage,theMaxDamage,theChanceToHit,theAttackSpeed);
        setChanceToBlock(theChanceToBlock);
        setRunAway(false);
        setSpecialSkillChance(theSpecialSkillChance);
    }

    /**
     *  Construct all the variables from DungeonCharacter class.
     *  Initializes parameters specific for Hero class
     * @param theName is the String with a hero name.
     * @param theHitPoints is the integer with hit points.
     * @param theMinDamage is the integer with minimum points.
     * @param theMaxDamage is the integer with maximum points.
     * @param theChanceToHit is the double with a chance to hit.
     * @param theAttackSpeed is the integer with attack speed.
     * @param theChanceToBlock is the integer with a chance to block.
     * @param theSpecialSkillChance is a double with a special
     *  skill chance.
     */
    protected Hero(final String theName, final int theHitPoints,
                   final int theMinDamage,final int theMaxDamage,
                   final double theChanceToHit, final int theAttackSpeed,
                   final int theChanceToBlock, final double theSpecialSkillChance, final String characterSpecificAttackSoundPath) {
        super(theName, theHitPoints,theMinDamage,theMaxDamage,theChanceToHit,theAttackSpeed);
        setChanceToBlock(theChanceToBlock);
        setRunAway(false);
        setSpecialSkillChance(theSpecialSkillChance);
        setCharacterSpecificAttackSoundPath(characterSpecificAttackSoundPath);
    }

    /**
     * Initialize special skill chance.
     * Checks if special skills is bigger than 0
     * @param theSpecialSkillChance is for special skill chance
     */
    private void setSpecialSkillChance(double theSpecialSkillChance) {
        if (theSpecialSkillChance <= 0) {
            throw new IllegalArgumentException("SpecialSkill > 0");
        }
        mySpecialSkillChance = theSpecialSkillChance;
    }
    /**
     * Checks if random number is less than special skill chance
     * @return true if random number is less than special skill chance
     * return false if random number is bigger than special skill chance
     */
    protected final boolean canUseSpecialSkill() {
        return MY_RANDOM.nextInt(101) <= mySpecialSkillChance;
    }
    /**
     * Initialize chance to block.
     * Check if set chance to block in range of 100
     * @param theChanceToBlock is for chance to block
     */
    private final void setChanceToBlock(final int theChanceToBlock) {
        if (theChanceToBlock < 0 || theChanceToBlock > 100) {
            throw new IllegalArgumentException("theChanceToBlock < 0 || theChanceToBlock > 100");
        }
        myChanceToBlock = theChanceToBlock;
    }
    /**
     * Initialize run away.
     * @param theRunAway
     */
    private void setRunAway(final boolean theRunAway) {
        myRunAway = theRunAway;
    }

    protected void setCharacterSpecificAttackSoundPath(String path){
        // SHOULD check to make sure this file path exists.
        // Either prevent compilation, or log error...??

        characterSpecificAttackSoundPath = path;
    }

    /**
     * checks run away
     * @return true or false for run away.
     */
    public final boolean runAway() {
        return myRunAway;
    }

    /**
     * The overrides attack method in the game.
     * This method overrides method from DungeonCharacter class
     * @param theOpponent is a monster in the game
     */
    @Override
    public void attack(final DungeonCharacter theOpponent) {
        int attackChoice;
        int numberOfAttacks = getAttackTurns(theOpponent);


        while (numberOfAttacks > 0 && theOpponent.alive() && !runAway()) {

            attackChoice = getChoice();
            if (attackChoice == 1) {
                // plays generic sound in super class.
                super.attack(theOpponent);
            }
            else if (attackChoice == 2) {
                specialAttack(theOpponent);
            }
            else {
                setRunAway(true);
            }
            numberOfAttacks--;

        }

    }
    /**
     * The method check how many attack a hero will get.
     * @param theOpponent is a monster in the game
     * @return number of turns based on the attack speed
     */
    private final int getAttackTurns(final DungeonCharacter  theOpponent) {
        int numberOfTurns = getAttackSpeed() / theOpponent.getAttackSpeed();
        if (numberOfTurns == 0) {
            numberOfTurns = 1;
        }
        return numberOfTurns;

    }

    public String getCharacterSpecificAttackSoundPath(){
        return characterSpecificAttackSoundPath;
    }

    /**
     * This method is an abstract and it will be used in child's
     * classes.
     * @param theOpponent passes a monster
     */
    protected abstract void specialAttack(final DungeonCharacter theOpponent);

    /**
     * This method overrides from DungeonCharacter class
     * Method checks if random number is less than chance to block
     * If a random number is bigger that my chance to block than it will
     * override DungeonCharacter class.
     * @param theAmount is the amount of point that will be subtracted.
     */
    @Override
    protected void substractHitPoints(final int theAmount) {
        if (MY_RANDOM.nextInt(101) <= myChanceToBlock) {
            System.out.println(getName()+ " blocked the attack! ");

            // PLAY SOUND
            soundPlayer.play(blockSoundPath);
        }
        else {
            super.substractHitPoints(theAmount);
        }
    }

    /**
     * The method asks uset to choose what attack he wants to do.
     * @return attack choice
     */
    private final int getChoice() {
        System.out.println("Type attack choice");
        System.out.println("1 - regular attack");
        System.out.println("2 - special attack");
        System.out.println("3 - run ");
        int attackChoice = input.nextInt();
        return attackChoice;
    }


}







