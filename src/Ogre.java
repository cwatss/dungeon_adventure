/**
 * Class Ogre inherits from Monster and is one of
 * the four Monster characters.
 */
public class Ogre extends Monster {
    /**
     * The constructor calls the super method (DungeonCharacter) and
     * sets all the fields attributed to this character through the
     * parameters
     * @param theName user generated name for character
     */
    public Ogre(final String theName) {
        super(theName, 200, 30, 60,
                0.6, 2, 0.1,
                30,60, 0.2);
    }

}
