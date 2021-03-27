package view;

import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Column;
import mvvm.ViewModelBoard;

import java.util.ArrayList;
import java.util.List;

public class ViewBoard extends ListView<Column> {
    private List<ViewColumn> columns = new ArrayList<>();
    private ViewModelBoard viewModelBoard;


    public ViewBoard(ViewModelBoard viewModelBoard) {
        this.viewModelBoard = viewModelBoard;
        this.setOrientation(Orientation.HORIZONTAL);
        this.setPrefWidth(1000);
        configData();
        customize();
        configAction();
    }
    private void configData(){

        this.itemsProperty().bind(viewModelBoard.columnsProperty());

    }
    private void customize(){
        this.setCellFactory(view-> new ListCell<>() {
            @Override
            protected void updateItem(Column column, boolean item){
                super.updateItem(column,item);
                ViewColumn viewColumn = null;
                if(column != null){
                    viewColumn = new ViewColumn(column,viewModelBoard);
                }
                setGraphic(viewColumn);
            }
        });
    }
    private void configAction(){
        this.setOnMouseClicked(e ->{
            if(e.getClickCount() == 3){
                viewModelBoard.addColumn();
            }
        });

    }


}
