import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameLauncher {
    private int numberOfPlayers;
    private Game game;

    public GameLauncher() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            System.out.println("Hello! Welcome to cluedo.\nPlease enter the number of players:");
            s = br.readLine();
            int n = validateInt(s);
            while(true) {
                if (n < 3 || n > 6) {
                    System.out.println("Invalid number of players, Please enter a number between 3 and 6:");
                    s = br.readLine();
                    n=validateInt(s);
                } else {
                    numberOfPlayers = n;
                    break;
                }
            }
            game = new Game(numberOfPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  parses strings to intergers while safely catching
     *  exceptions if it does not pass successfully
     * @param s string from input
     */
    static Integer validateInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public static void main(String[] args) {
        GameLauncher launcher = new GameLauncher();
    }

}
