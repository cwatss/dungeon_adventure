


/**
 * Class MAIN is the actual driver class for the entire program.
 * I (Corinne) chose to put Main into its own class for portability and modularity.
 */
public class Main {
    /**
     * This method uses a try/catch block to call the Play method in class
     * HeroesVSMonsters. If an error occurs an exception is thrown and
     * the game exits.
     * @param theArgs takes an command line argument.
     */
    public static void main(String[] theArgs) {
        try {
            HeroesVersusMonsters.Play();
        } catch(Exception e){
            System.out.println("Game encountered a problem: "+e.getMessage());
        }
    }
}