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
    MenuBar main_menu = new MenuBar();
    Menu fichier = new Menu("Fichier");
    Menu edition = new Menu("Edition");
    MenuItem colonne = new MenuItem("Nouvelle colonne");
    MenuItem quitter = new MenuItem("Quitter");
    private MenuItem annuler = new MenuItem("Annuler");
    private MenuItem refaire= new MenuItem("Refaire");
    private final KeyCombination ctrlZ = new KeyCodeCombination(KeyCode.Z,KeyCombination.SHORTCUT_DOWN);
    private final KeyCombination ctrlY = new KeyCodeCombination(KeyCode.Y,KeyCombination.SHORTCUT_DOWN);


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
    private void configAction(){

        colonne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewModelBoard.addColumn();
            }
        });
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                viewModelBoard.undo();
                editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(), viewModelBoard,null,null);
                up.getChildren().clear();
                up.getChildren().addAll(editableLabel);

            }
        });
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(ctrlZ.match(keyEvent)){
                    viewModelBoard.undo();
                }
            }
        });
        this.setOnKeyReleased(e->{
            if(e.getCode() == KeyCode.Z && e.isShortcutDown()){
                viewModelBoard.undo();
            }
        });
        refaire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                viewModelBoard.redo();
                editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(), viewModelBoard,null,null);
                up.getChildren().clear();
                up.getChildren().addAll(editableLabel);
            }
        });
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(ctrlY.match(keyEvent)){
                    viewModelBoard.redo();
                }
            }
        });
    }

}
