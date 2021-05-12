package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Board;
import model.BoardDao;
import model.Trello;
import mvvm.ViewModelBoard;
import view.TrelloView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Trello trelloTest = new Trello();
        trelloTest.test();
        //Board board = new Board(1,"Tableau");
        BoardDao boardDao = new BoardDao();
        Board board = boardDao.find();

        ViewModelBoard viewModelBoard = new ViewModelBoard(board);
        new TrelloView(viewModelBoard,primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
