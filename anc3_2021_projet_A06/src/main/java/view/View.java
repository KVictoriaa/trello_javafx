package view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class View {

    public View(Stage primaryStage) {


        Scene scene = new Scene(new VBox());
        primaryStage.setTitle("Project Trello");
        primaryStage.setScene(scene);
    }


}
