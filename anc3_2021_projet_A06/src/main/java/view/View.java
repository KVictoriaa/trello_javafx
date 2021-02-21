package view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvvm.ViewModel;


public class View {

    private final MyTrelloView trello;

    private final ViewModel viewModel;

    public View(Stage primaryStage, ViewModel viewModel) {

        trello = new MyTrelloView("Trello", viewModel.getColumns(), viewModel);

        this.viewModel = viewModel;

        Scene scene = new Scene(trello);
        primaryStage.setTitle("Trello");
        primaryStage.setScene(scene);
    }
}