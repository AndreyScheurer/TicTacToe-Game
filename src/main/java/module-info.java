
module quadstrike {
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.github.andreyscheurer.tictactoe to javafx.fxml;

    exports ch.github.andreyscheurer.tictactoe;
}
