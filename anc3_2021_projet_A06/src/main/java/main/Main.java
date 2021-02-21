package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import mvvm.ViewModel;
import view.View;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        ViewModel viewModel = new ViewModel(board);
        View view = new View(primaryStage, viewModel);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
