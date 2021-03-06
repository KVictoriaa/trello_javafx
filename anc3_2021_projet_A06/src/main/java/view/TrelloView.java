package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvvm.ViewModelBoard;
import mvvm.ViewModelColumn;

public class TrelloView extends VBox {
    private final KeyCombination ctrlZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
    private final KeyCombination ctrlY = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);

    MenuBar main_menu = new MenuBar();
    Menu fichier = new Menu("Fichier");
    Menu edition = new Menu("Edition");
    MenuItem colonne = new MenuItem("Nouvelle colonne");
    MenuItem quitter = new MenuItem("Quitter");
    private ViewModelColumn viewModelColumn;
    private ViewModelBoard viewModelBoard;
    private ViewBoard viewBoard;
    private EditableLabel editableLabel;
    private Stage stage;
    private HBox menu = new HBox();
    private HBox up = new HBox();
    private HBox down = new HBox();
    private Scene scene;
    private Button button = new Button("Delete selected");
    private MenuItem annuler = new MenuItem("Annuler");
    private MenuItem refaire = new MenuItem("Refaire");


    public TrelloView(ViewModelBoard viewModelBoard, Stage stage) {
        this.viewBoard = new ViewBoard(viewModelBoard);
        this.viewModelBoard = viewModelBoard;
        this.stage = stage;
        scene = new Scene(this, 900, 500);
        this.setPrefWidth(50);
        configAction();
        editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(), viewModelBoard, null, null);
        stage.setTitle("Trello");
        stage.setScene(scene);
        configTrello();
        configData();

    }

    private void configTrello() {
        fichier.getItems().addAll(colonne, quitter);
        edition.getItems().addAll(annuler, refaire);

        main_menu.getMenus().addAll(fichier, edition);
        menu.getChildren().add(main_menu);
        up.getChildren().addAll(editableLabel);
        down.getChildren().addAll(viewBoard,button);
        this.getChildren().addAll(menu, up, down);


    }

    private void configAction() {

        colonne.setOnAction(actionEvent -> viewModelBoard.addColumn());
        quitter.setOnAction(actionEvent -> stage.close());
        annuler.setOnAction(actionEvent -> {

            viewModelBoard.undo();
            editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(), viewModelBoard, null, null);
            up.getChildren().clear();
            up.getChildren().addAll(editableLabel);

        });
        this.setOnKeyPressed(keyEvent -> {
            if (ctrlZ.match(keyEvent)) {
                viewModelBoard.undo();
            }
        });
        this.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.Z && e.isShortcutDown()) {
                viewModelBoard.undo();
            }
        });
        refaire.setOnAction(actionEvent -> {

            viewModelBoard.redo();
            editableLabel = new EditableLabel(viewModelBoard.boardNameProperty(), viewModelBoard, null, null);
            up.getChildren().clear();
            up.getChildren().addAll(editableLabel);
        });
        this.setOnKeyPressed(keyEvent -> {
            if (ctrlY.match(keyEvent)) {
                viewModelBoard.redo();
            }
        });
        button.setOnAction(e-> {
            viewModelBoard.supprime();
            //viewModelBoard.getBoard().getSelectColumns().clear();
        });
    }

    public void configData() {
        annuler.disableProperty().bind(viewModelBoard.undoDisableProperty());
        refaire.disableProperty().bind(viewModelBoard.redoDisableProperty());
        annuler.textProperty().bind(Bindings.concat(new SimpleStringProperty("Annuler"+"   "), viewModelBoard.nameActionProperty(), new SimpleStringProperty("      CTRL+Z")));
        refaire.textProperty().bind(Bindings.concat(new SimpleStringProperty("Refaire"+"   "), viewModelBoard.nameActionRedoProperty(), new SimpleStringProperty("      CTRL+Y")));
        button.disableProperty().bindBidirectional(viewModelBoard.selectedProperty());
    }
/*je
vais afficher le button,
faire une methode remove dans le model board avec comme param??tre une colonne
dans le modele board cr??er une nouvelle liste,
dans la m??thode remove lorsqu'une colonne est cheched on l'ajoute a la liste et je fais un remove columnlist,
dans le viewmodelboard creer une nouvelle liste de column;
je cr??er une m??thode pour recup??rer la column selectionn?? , et appeler le remove cr??er;
dans la vue je met sur le button comme action d'appeler la nouvelle m??thode cr??er dans le viewmodelboard.
en recup??rant la ou les columns s??lection
je cr??er une nouvelle classe dans le mvvm ou j'appel le remove cr??er en methode execute et une autre m??thode add sur le undo. */
}
