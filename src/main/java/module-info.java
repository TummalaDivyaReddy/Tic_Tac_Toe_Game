module com.example.tic_tac_toe_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tic_tac_toe_game to javafx.fxml;
    exports com.example.tic_tac_toe_game;
}