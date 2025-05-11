/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
package ch.github.andreyscheurer.tictactoe;

/**
 * This is the main class for running the QuadStrike game in the console. It can be launched by calling then
 * {@link ConsoleApp#main(String[])} method.
 */
public class ConsoleApp {

    /**
     * Launches the QuadStrike game in the console.
     *
     * @param args The list if (ignored) application arguments
     */
    public static void main(String[] args) {
        CLIController cliController = new CLIController();
        cliController.startGame(); // Play the game until it is finished
    }
}
