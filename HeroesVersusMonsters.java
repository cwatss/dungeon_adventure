
import java.util.Scanner;
import java.util.Random;

/**
 * class HeroesVersusMonsters picks a hero and a monster to fight.
 * It creates game play based on the characteristics of
 * the chosen characters.
 * ********CHEAT! IF USER ENTERS CHARACTERS NAME WITH AN EXCLAMATION POINT
 * (ex: CORINNE!) THAT CHARACTER WILL BE IN "GOD MODE", POLYTHEISM
 * IS NOT ALOUD ;-) , BOTH CHARACTERS CAN NOT BE IN GOD MODE ****************
 * @author Corinne Watson
 * @version 4/21/21
 */
public class HeroesVersusMonsters {

    /** this is for the sound player object*/
    private static SoundPlayer soundPlayer = new SoundPlayer();
    /** this variable is the loss sound filepath*/
    private static String deathSoundPath ="loss.wav";
    /** this variable is the win sound filepath*/
    private static String victorySoundPath ="victory.wav";


    /**
     * Method createRandomMonster generates a random monster character using
     * switch statement logic (more expressive than if-else statement)
     * @return random Monster character
     * @throws Exception if user enters an invalid number
     */
    public static Monster createRandomMonster() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to HEROES VS MONSTERS!!!");
        System.out.println("Enter Monster's name: ");
        String name = input.nextLine();

        Random rand = new Random();
        int r = rand.nextInt(4); // Gets floored to integer.

        switch (r) {
            case 0:
                return new Ogre(name);
            case 1:
                return new Skeleton(name);
            case 2:
                return new Gremlin(name);
            case 3:
                return new Ghost(name);
            default:
                throw new Exception("Random number out of range!");
        }
    }

    /**
     * method createHero creates a new hero object based on user's
     * selection.
     * @return New hero character
     * @throws Exception if a user enters a number out of range
     */
    public static Hero createHero() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose a hero: ");
        System.out.println("Enter 1 for Warrior");
        System.out.println("Enter 2 for Thief");
        System.out.println("Enter 3 for Sorceress");
        System.out.println("Enter 4 for Ninja Turtle");
        System.out.println(" ");
        String selection = input.next();
        System.out.println("Enter a name for your character: ");
        String name = input.next();
        switch (selection) {
            case "1":
                return new Warrior(name);
            case "2":
                return new Thief(name);
            case "3":
                return new Sorceress(name);
            case "4":
                return new NinjaTurtle(name);
            default:
                throw new Exception("You did not enter a valid hero selection, silly goose");

        }
    }

    /**
     * Method fight makes characters battle, and utilizes the cheat "god mode"
     * @param hero Passes in current hero object
     * @param monster passes in current monster object
     */
    public static void fight(Hero hero, Monster monster) {
        while (hero.alive() && monster.alive() && !hero.runAway()) {
            System.out.println(hero.getName() + " hit points: " + hero.getHitPoints());
            System.out.println(monster.getName() + " hit points: " + monster.getHitPoints());

            // Deal with god mode here.
            // If monster is in god mode, hero's attack doesn't work.
            if(!monster.getIsInGodMode()) {
                hero.attack(monster);
            }

            if (!hero.runAway()) {
                // Deal with god mode here.
                // If hero is in god mode, monster's attack doesn't work.
                if(!hero.getIsInGodMode()) {
                    monster.attack(hero);
                }
            }else {
                System.out.println(hero.getName()+ " RUNAWAY ");
            }

            // Print god mode message.
            if(hero.getIsInGodMode() && monster.getIsInGodMode()){
                System.out.println("GOD MODE STALEMATE!");
                break;
            }else if(hero.getIsInGodMode()) {
                System.out.println(monster.getName()+"'s attack useless, "+hero.getName()+"is in god mode");
            } else if(monster.getIsInGodMode()){
                System.out.println(hero.getName()+"'s attack useless, "+monster.getName()+"is in god mode");
            }

            if(monster.canUseSpecialAttack()){
                System.out.print(monster.getName() + "SPECIAL ATTACK ACTIVATED");
                monster.specialAttack(hero);
            }

            // Control while loop.
            System.out.println(" Press for next round ");
            DungeonCharacter.input.nextLine();
        }

        if (monster.alive() && hero.alive()) {
            System.out.println("Game over..");
            return;
        }

        if(monster.alive()) {
            System.out.println(monster.getName() + " WON!!");
            soundPlayer.play(victorySoundPath);

            System.out.println(hero.getName() + " LOST!!");
            soundPlayer.play(deathSoundPath);

        }else if (hero.alive()){
            System.out.println(hero.getName() + " WON!!");
            soundPlayer.play(victorySoundPath);

            System.out.println(monster.getName() + " LOST!!");
            soundPlayer.play(deathSoundPath);
        }
    }


    /**
     * method play creates the hero and monster objects, uses reflection
     * to capture the monster/hero objects at runtime to inform the user
     * what character they have chosen and what it's name is.
     * Play uses this information to call fight();
     * @throws Exception if there is no current hero/monster object
     */

    public static void Play() throws Exception {
        // This method will work as follows:
        // 1. Create hero
        // 2. Create monster (and display type using reflection)
        // 3. Fight them
        // 4. Recurse if user wants

        // Creates a new monster object based on the random selection from above
        Monster monster = createRandomMonster();
        // Use reflection to inspect at runtime the monster's sub-class.
        System.out.println("Monster is a "+ monster.getClass().getSimpleName()+ " named: "+monster.getName());
        System.out.println("");

        //creates a new hero based on user input from above
        Hero hero = createHero();
        // Use reflection to inspect at runtime the hero's sub-class.
        System.out.println("");
        System.out.println("Hero is a " + hero.getClass().getSimpleName() + " named: " + hero.getName());
        System.out.println("");


        //Call the fight method with the current hero and monster objects passed in
        fight(hero, monster);

        //recursively calls Play() again to play another game
        // (in this case, just choose another character since were just testing :-) )
        Play();
    }

}
