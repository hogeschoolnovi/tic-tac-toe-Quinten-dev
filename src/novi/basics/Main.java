package novi.basics;

import java.util.Scanner;

public class Main {

    public static Scanner PLAYERINPUT = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Player 1, what is your name?");
        String player1Name = PLAYERINPUT.next();
        Player player1 = new Player(player1Name, 'x');

        System.out.println("Player 2, what is your name?");
        String player2Name = PLAYERINPUT.next();
        Player player2 = new Player(player2Name, 'o');

        Game game = new Game(player1, player2);
        game.play();
    }
}