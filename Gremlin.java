
/**
 * Class Gremlin inherits from Monster and is one of
 * the four Monster characters. Gremlins are cool. 
 * @author corinne watson
 * @version 4/21/21
 */
public class Gremlin extends Monster {
    /**
     * The constructor calls the super method (DungeonCharacter) and
     * sets all the fields attributed to this character through the
     * parameters
     * @param theName user generated name for character
     */
    public Gremlin(final String theName) {
        super(theName, 70, 15, 30,
                0.8,5,0.4,
                20, 40, 0.2);
    }
}
