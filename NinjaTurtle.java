

/**
 * Class NinjaTurtle extends from Hero class.
 * The class generates special attack for ninja.
 * @author Emmiliia Fedina emmafe@uw.edu
 * @version 25APR2021
 */
public class NinjaTurtle  extends Hero{
    /**
     * Constructs NinjaTurtle
     * @param theName-passes the name for it
     */
    public NinjaTurtle (final String theName) {
        super(theName, 129, 35, 60, .8, 4, 20, 0.4, "ninjaturtle.wav");//expects all the value from hero constructor
    }
    /**
     * Overrides special attack from Hero class.
     * the attack will take all the points from theOponnent
     */
    @Override
    protected void specialAttack (final DungeonCharacter theOpponent) {
        int BestValue = theOpponent.getHitPoints();
        // PLAY SOUND
        soundPlayer.play(getCharacterSpecificAttackSoundPath());
        //normal attack
        theOpponent.substractHitPoints(BestValue);
    }
}
