
package ch.github.andreyscheurer.tictactoe;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * This is the main class for running the QuadStrike game as a JavaFX
 * application. It can be launched by calling then
 * {@link JavaFXApp#main(String[])} method.
 */
public class JavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXApp.class.getResource("/game.fxml"));
        System.out.println("Loading FXML...");
        Parent root = fxmlLoader.load(); // Loading FXML
        System.out.println("FXML loaded successfully.");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("QuadStrike");
        primaryStage.show();
    }

    /**
     * Launches the QuadStrike game as a JavaFX application.
     *
     * @param args The list of (ignored) application arguments
     */
    public static void main(String[] args) {
        launch();
    }

}
