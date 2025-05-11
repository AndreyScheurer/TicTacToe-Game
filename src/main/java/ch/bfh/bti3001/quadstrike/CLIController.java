package ch.bfh.bti3001.quadstrike;

import java.util.List;
import java.util.Scanner;

/**
 * The CLIController class handles the interaction between the user interface and
 * the game logic in the CLI. It processes user input and outputs that into the console.
 */
public class CLIController {
    private final Scanner scanner = new Scanner(System.in);
    private Game game;

    /**
     * Initialize the game
     */
    public void startGame() {
        System.out.println("WELCOME TO THE QUADSTRIKE APP");
        System.out.println("=============================");
        System.out.println();

        // Get Player Names
        System.out.print("Enter name of Player 1: ");
        String player1 = scanner.nextLine();
        System.out.print("Enter name of Player 2: ");
        String player2 = scanner.nextLine();

        // Initialize the Game
        game = new Game(player1, player2);
        System.out.println();
        System.out.println(game.getBoard().toString()); // Show initial board
        System.out.println();

        // Start game loop
        playGame();
    }
    
    // Handle input and output while the game is playing
    private void playGame() {
        while (!game.gameOver()) {
            System.out.print(game.getCurrentPlayer() + " > ");
            String input = scanner.nextLine(); // Get input
            
            /*
             * Validate the input.
             * Split the input string by '/' into two parts if there is only one '/' present.
             */
            String[] parts = input.split("/");
            // Check if there's exactly one '/'
            if (parts.length != 2) {
                System.out.println("Invalid input, try again");
                continue; // Bad input skip to the next iteration in the while loop 
            }
            // Each part of the input will now be validated
            int part1;
            int part2;
            try {
                /*
                 * Try to parse both parts as integers.
                 * If both parts are integers, it's valid
                 */
                part1 = Integer.parseInt(parts[0].trim());
                part2 = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                // If any part is not a valid integer, continue to the next iteration.
                System.out.println("Invalid input, try again");
                continue;
            }
            // Input has been validated, now make sure the input is a valid move
            Move currentMove = new Move(part1, part2);
            // Check move validity
            if (!game.checkMove(currentMove)) {
                System.out.println("Invalid move, try again");
            } else {
                game.play(currentMove);
                System.out.println(game.getBoard().toString()); // Display updated board
                System.out.println();
            }
        }
        
        // Game is now over
        String winner = game.getCurrentPlayer();
        int moveAmount = game.getNumberOfMoves();
        List<Move> moves = game.getMoves();
        StringBuilder formattedMoveList = new StringBuilder();
        // Remove square brackets from the move list using a for loop
        for (int i = 0; i < moves.size(); i++) {
            formattedMoveList.append(moves.get(i));
            if (i < moves.size() - 1) {
                formattedMoveList.append(", ");
            }
        }
        System.out.println("GAME OVER: " + winner + " wins after " + moveAmount + " moves: " + formattedMoveList);
        System.out.println();
        
        // End program or restart the game
        while (true) {
            System.out.print("Start new game [y/n]? ");
            String gameOverInput = scanner.nextLine();
            if (gameOverInput.equalsIgnoreCase("y") || gameOverInput.equalsIgnoreCase("yes")) {
                System.out.println();
                game.restart();
                startGame();
            } else if (gameOverInput.equalsIgnoreCase("n") || gameOverInput.equalsIgnoreCase("no")) {
                System.out.print("Bye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input, try again");
            }
        }
    }
}
