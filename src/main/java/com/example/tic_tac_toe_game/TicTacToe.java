package com.example.tic_tac_toe_game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;

public class TicTacToe extends Application {
    private boolean playerXTurn = true;
    private Button[][] buttons = new Button[3][3];
    private Label turnLabel;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Prompt for player names
        String playerXName = getPlayerName("Player X");
        String playerOName = getPlayerName("Player O");

        turnLabel = new Label("Turn: " + playerXName);

        // Creating buttons for the Tic Tac Toe grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);

                // Event handler for button clicks
                button.setOnAction(event -> {
                    if (button.getText().isEmpty()) {
                        button.setText(playerXTurn ? "X" : "O");
                        // Checking for win or draw
                        if (checkWin()) {
                            showAlert(playerXTurn ? playerXName : playerOName);
                            primaryStage.close();
                        } else if (checkDraw()) {
                            showAlert("It's a draw!");
                            primaryStage.close();
                        } else {
                            // Switching turns
                            playerXTurn = !playerXTurn;
                            turnLabel.setText("Turn: " + (playerXTurn ? playerXName : playerOName));
                        }
                    }
                });
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        // Creating a horizontal box for the turn label
        HBox hbox = new HBox(turnLabel);
        hbox.setAlignment(Pos.CENTER);

        // Creating the scene
        Scene scene = new Scene(new javafx.scene.layout.VBox(hbox, gridPane), 320, 360);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to prompt for player names
    private String getPlayerName(String player) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText("Enter " + player + "'s name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("Player");
    }

    // Method to show an alert dialog
    private void showAlert(String winnerName) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winnerName + " Won the Game! ");
        alert.showAndWait();
    }

    // Method to check for a win
    private boolean checkWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].isEmpty()) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].isEmpty()) {
                return true;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].isEmpty()) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].isEmpty()) {
            return true;
        }

        return false;
    }

    // Method to check for a draw
    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

