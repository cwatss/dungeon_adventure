
/**
 * Class Skeleton inherits from Monster and is one of
 * the four Monster characters.
 * @author corinne watson
 * @version 4/21/21
 */
public class Skeleton extends Monster{
    /**
     * The constructor calls the super method (DungeonCharacter) and
     * sets all the fields attributed to this character through the
     * parameters
     * @param theName user generated name for character
     */
    public Skeleton(String theName) {
        super(theName, 100, 30,50,
                0.8, 3, 0.3,
                30, 50, 0.2);
    }
}
