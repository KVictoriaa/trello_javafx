package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvvm.ViewModelBoard;

public class TrelloView extends VBox {
    private ViewModelBoard viewModelBoard;
    private ViewBoard viewBoard;
    private EditableLabel editableLabel;
    private Stage stage;
    private HBox up = new HBox();
    private HBox down = new HBox();
    private Scene scene;


    public TrelloView(ViewModelBoard viewModelBoard, Stage stage) {
        this.viewBoard = new ViewBoard(viewModelBoard);
        this.viewModelBoard = viewModelBoard;
        this.stage = stage;
        scene = new Scene(this, 900,500);
        editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(),viewModelBoard,null,null);
        stage.setTitle("Trello");
        stage.setScene(scene);
        configTrello();


    }
    private void configTrello(){

        up.getChildren().addAll(editableLabel);
        down.getChildren().addAll(viewBoard);
        this.getChildren().addAll(up,down);


    }

}
