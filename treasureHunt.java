import java.util.*;

public class treasureHunt {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        char reply;
        do {
            gameSetup game = new gameSetup();
            game.playGame();
            System.out.print("\nPlay another game (y/n)? ");
            reply = in.next().charAt(0);
            System.out.println();
        } 
        while (reply == 'y' || reply == 'Y');
    }
}
