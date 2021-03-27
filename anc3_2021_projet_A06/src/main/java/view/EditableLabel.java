package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import mvvm.ViewModelBoard;
import mvvm.ViewModelCard;
import mvvm.ViewModelColumn;

public class EditableLabel extends Label {
    private TextField textField = new TextField();
    private ViewModelBoard viewModelBoard;
    private ViewModelCard viewModelCard;
    private ViewModelColumn viewModelColumn;
    private String backup = "";

    public EditableLabel(
        SimpleStringProperty name,
        ViewModelBoard viewModelBoard,
        ViewModelColumn viewModelColumn,
        ViewModelCard viewModelCard
    ) {
       super(name.get());
       this.viewModelBoard = viewModelBoard;
       this.viewModelColumn = viewModelColumn;
       this.viewModelCard = viewModelCard;
       configAction();

    }

   public void configAction() {
        this.setOnMouseClicked(e-> {
            if(e.getClickCount() == 2) {
                textField.setText(backup = this.getText());
                this.setGraphic(textField);
                this.setText("");
                textField.requestFocus();
            }
        });

   }
}
