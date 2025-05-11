package ch.bfh.bti3001.quadstrike;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * The GUIController class handles the interaction between the user interface
 * and
 * the game logic in the GUI. It manages the game state, updates the UI
 * components, and
 * processes user inputs.
 */
public class GUIController {

    @FXML
    private TextField player1Name; // Input field for Player 1's name

    @FXML
    private TextField player2Name; // Input field for Player 2's name

    @FXML
    private Label nextPlayer; // Label indicating which player's turn it is

    @FXML
    private Label statusLabel; // Label showing the game status or winner

    @FXML
    private TextArea movesList; // Text area logging all moves

    @FXML
    private Pane gameBoard; // Pane where the game board is drawn

    @FXML
    private Label totalMoves; // Label shows how many moves

    private Game game; // The game logic instance
    private static final double BUTTON_SIZE = 30.0; // Size of each game board button
    private static final double COORD_OFFSET_RIGHT = 10.0; // Horizontal offset for coordinates
    private static final double COORD_OFFSET_BOTTOM = 20.0; // Vertical offset for coordinates

    /**
     * Starts a new game, resets the UI, and initializes the game board.
     */
    @FXML
    public void startNewGame() {
        // Retrieve names from input fields or use defaults if empty
        String player1 = player1Name.getText().isEmpty() ? "Player 1" : player1Name.getText();
        String player2 = player2Name.getText().isEmpty() ? "Player 2" : player2Name.getText();

        // Initialize the game logic
        game = new Game(player1, player2);

        // Reset UI elements
        nextPlayer.setText("Next player: " + game.getCurrentPlayer());
        movesList.clear();
        statusLabel.setVisible(false);

        // Clear the game board and draw a fresh one
        gameBoard.getChildren().clear();
        initializeGameBoard();
    }

    /**
     * Sets up the game board by placing buttons and drawing coordinates.
     */
    private void initializeGameBoard() {
        Board board = game.getBoard();
        int minX = board.getMinX();
        int maxX = board.getMaxX();
        int minY = board.getMinY();
        int maxY = board.getMaxY();

        // Clear any existing elements from the board
        gameBoard.getChildren().clear();

        // Calculate the board's center alignment
        double boardWidth = (maxX - minX + 1) * BUTTON_SIZE;
        double boardHeight = (maxY - minY + 1) * BUTTON_SIZE;
        double centerX = (gameBoard.getWidth() - boardWidth) / 2;
        double centerY = (gameBoard.getHeight() - boardHeight) / 2;

        // Create buttons for each cell on the board
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Button button = new Button();

                // Set the button's text based on the cell state
                String cellState = getCellState(x, y);
                if (!cellState.isEmpty()) {
                    button.setText(cellState);
                }
                // Assign a unique ID based on the cell's coordinates
                button.setId("cell-" + x + "-" + y);

                // Configure button size and position
                button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setLayoutX(centerX + (x - minX) * BUTTON_SIZE);
                button.setLayoutY(centerY + (y - minY) * BUTTON_SIZE);

                // Add a click event handler for the button
                int finalX = x;
                int finalY = y;
                button.setOnAction(event -> handleMove(button, finalX, finalY));

                // Add the button to the game board
                gameBoard.getChildren().add(button);
            }
        }

        // Draw the X and Y coordinates around the board
        drawCoordinates(minX, maxX, minY, maxY, centerX, centerY);
    }

    /**
     * Draws the X (horizontal) and Y (vertical) coordinates around the game board.
     */
    private void drawCoordinates(int minX, int maxX, int minY, int maxY, double centerX, double centerY) {
        // Draw X-coordinates below the board
        for (int x = minX; x <= maxX; x++) {
            Text xCoord = new Text(String.valueOf(x));
            xCoord.setLayoutX(centerX + (x - minX) * BUTTON_SIZE + BUTTON_SIZE / 2 - 4);
            xCoord.setLayoutY(centerY + (maxY - minY + 1) * BUTTON_SIZE + COORD_OFFSET_BOTTOM);
            gameBoard.getChildren().add(xCoord);
        }

        // Draw Y-coordinates to the right of the board
        for (int y = minY; y <= maxY; y++) {
            Text yCoord = new Text(String.valueOf(y));
            yCoord.setLayoutX(centerX + (maxX - minX + 1) * BUTTON_SIZE + COORD_OFFSET_RIGHT);
            yCoord.setLayoutY(centerY + (y - minY) * BUTTON_SIZE + BUTTON_SIZE / 2);
            gameBoard.getChildren().add(yCoord);
        }
    }

    /**
     * Retrieves the state of a specific cell as a string ("X", "O", or empty).
     */
    private String getCellState(int x, int y) {
        Board board = game.getBoard();
        if (board.isCrossed(x, y)) {
            return "X";
        } else if (board.isCircled(x, y)) {
            return "O";
        }
        return "";
    }

    /**
     * Handles a player's move when a button is clicked.
     */
    private void handleMove(Button cell, int x, int y) {
        // Ignore the move if the cell is already marked or the game is over
        if (!cell.getText().isEmpty()) {
            return;
        }

        try {
            String currentPlayer = game.getCurrentPlayer();

            // Mark the cell based on the current player
            if (currentPlayer.equals(game.getPlayerName1())) {
                game.play(new Move(x, y));
                cell.setText("X");
            } else {
                game.play(new Move(x, y));
                cell.setText("O");
            }

            // Log the move in the moves list
            movesList.appendText(currentPlayer + " -> [" + x + ", " + y + "]\n");

            // Update the total moves count and display it in the totalMoves label
            int totalMovesCount = game.getNumberOfMoves(); // Assuming getNumberOfMoves() exists
            totalMoves.setText("" + totalMovesCount + " moves played");

            // Check if the game is over
            if (game.getBoard().gameOver()) {
                statusLabel.setText(currentPlayer + " WINS!");
                statusLabel.setVisible(true);

                // Highlight the winning cells
                highlightWinningCells();

                // Disable the board to prevent further moves
                disableBoard();
            } else {
                // Update the next player and refresh the board
                nextPlayer.setText("Next player: " + game.getCurrentPlayer());
                initializeGameBoard();
                statusLabel.setVisible(false);
            }
        } catch (RuntimeException e) {
            // Display an error message if the move is invalid
            statusLabel.setText(e.getMessage());
            statusLabel.setVisible(true);
        }
    }

    /**
     * Disables all buttons on the board, preventing further interactions.
     */
    private void disableBoard() {
        gameBoard.getChildren().forEach(node -> {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        });
    }

    /**
     * Highlights the winning cells by changing their background color to pink.
     */
    private void highlightWinningCells() {
        Board board = game.getBoard();

        // Iterate through all winning cells
        for (int x = board.getMinX(); x <= board.getMaxX(); x++) {
            for (int y = board.getMinY(); y <= board.getMaxY(); y++) {
                if (board.getCell(x, y) == Cell.WINNING) {
                    // Lookup the button by its ID
                    String buttonId = "cell-" + x + "-" + y;
                    Button winningButton = (Button) gameBoard.lookup("#" + buttonId);

                    // Highlight the button if it exists
                    if (winningButton != null) {
                        winningButton.setStyle("-fx-background-color: pink;");
                    }
                }
            }
        }
    }
}
