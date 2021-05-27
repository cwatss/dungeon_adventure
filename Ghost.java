
/**
 * Class Ghost inherits from Monster and is one of
 * the four Monster characters. Ghost is my bonus Monster character
 * as specified in the extra credit.
 * @author corinne watson
 * @version 4/21/21
 */
public class Ghost extends Monster{
    /**
     * The constructor calls the super method (DungeonCharacter) and
     * sets all the fields attributed to this character through the
     * parameters
     * @param theName user generated name for character
     */
    public Ghost(final String theName){
        super(theName, 200, 20, 50, .9,
                5, 0.8, 20, 70, 0.2);
    }
}
