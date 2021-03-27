package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModelBoard;
import view.TrelloView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board("Tableau");
        ViewModelBoard viewModelBoard = new ViewModelBoard(board);
        new TrelloView(viewModelBoard,primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
