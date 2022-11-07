

import java.util.Scanner;

public class Main {

    public static String[] tiles;
    public static int[] fine;
    public static int[] fall;
    public static boolean loop = true;
    private static final String PLAYER = "player";
    private static final String SQUARE = "square";
    private static final String STATUS = "status";
    private static final String DICE = "dice";
    private static final String EXIT = "exit";


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String players = input.next();

        int squares = input.nextInt();
        tiles = new String[squares + 1];

        int numFines = input.nextInt();
        fine = new int[numFines];
        for (int i = 0; i < numFines; i ++) {
            fine[i] = input.nextInt();
        }

        int numFalls = input.nextInt();
        fall = new int[numFalls];
        for (int x = 0; x < numFalls; x ++) {
            fall[x] = input.nextInt();
        }

        MainSystem Central = new MainSystem(squares, players, tiles, fine, fall);
        Central.setUpGame();

        while (loop) {
            runCommand(input, Central);
        }

    }


    //compare the user input with built commands and run the corresponding with MainSystem.java methods;
    private static void runCommand(Scanner input, MainSystem Central) {
        String prompt = input.next();

        switch (prompt) {
            case PLAYER: runPlayer(Central); break;
            case SQUARE: runSquare(Central, input); break;
            case STATUS: runStatus(Central, input); break;
            case DICE: runDice(Central, input); break;
            case EXIT: runExit(Central); break;
            default:
                input.nextLine();
                System.out.println("Invalid command"); break;
        }

    }

    //prints the next Player to play if the game is not yet won;
    private static void runPlayer(MainSystem Central) {
        if (Central.isGameOn()) {
            System.out.println("Next to play: " + (Central.getRunnerUp()).getName());
        }
        else {
            System.out.println("The game is over");
        }

    }

    //recives the letter of the requested Player and prints the requested Player's position;
    private static void runSquare(MainSystem Central, Scanner input) {
        String target = input.nextLine().trim();
        Player selected = Central.selectPlayer(target);

        if (selected != null) {
            System.out.println(selected.getName() + " is on square " + selected.getPosition());
        }
        else {
            System.out.println("Nonexistent player");
        }

    }

    //recives the letter of the requested Player and prints if the requested Player doesnt have fines to roll the dice;
    private static void runStatus(MainSystem Central, Scanner input) {
        String target = input.nextLine().trim();
        Player selected = Central.selectPlayer(target);

        if (selected != null) {
            if (Central.isGameOn()) {
                if (Central.getStatus(selected)) {
                    System.out.println(selected.getName() + " can roll the dice");
                }
                else {
                    System.out.println(selected.getName() + " cannot roll the dice");
                }
            }
            else {
                System.out.println("The game is over");
            }
        }
        else {
            System.out.println("Nonexistent player");
        }

    }

    //recives both dices' numbers, validates them and adds them to the Player's position if the game is not yet won;
    private static void runDice(MainSystem Central, Scanner input) {
        int dice1 = input.nextInt();
        int dice2 = input.nextInt();




        if ((Math.max(dice1, dice2) <= 6) && (Math.min(dice1, dice2) >= 1)) {
            if (Central.isGameOn()) {
                Central.playRound(dice1, dice2, tiles);
            } else {
                System.out.println("The game is over");
            }
        }
        else {
            System.out.println("Invalid dice");
        }


    }

    //ends the game and prints if any Player reached the last square;
    public static void runExit(MainSystem Central) {
        if (Central.isGameOn()) {
            System.out.println("The game was not over yet...");
        }
        else {
            System.out.println(Central.getWinner().getName() + " won the game!");
        }
        loop = false;

    }

}