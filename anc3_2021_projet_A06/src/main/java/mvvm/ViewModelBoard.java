package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Board;
import model.Column;
import mvvm.board.AddColumnCommand;
import mvvm.board.RemoveAllSelectedColumns;
import mvvm.board.SetTitleBoardCommand;

public class ViewModelBoard {
    private Board board;

    private final SimpleListProperty<Column> columns = new SimpleListProperty<>();
    private SimpleBooleanProperty redoDisable = new SimpleBooleanProperty();
    private SimpleBooleanProperty undoDisable = new SimpleBooleanProperty();
    private SimpleStringProperty nameAction = new SimpleStringProperty(" ");
    private SimpleStringProperty nameActionRedo = new SimpleStringProperty(" ");
    private SimpleStringProperty boardName = new SimpleStringProperty();
    private ViewModelColumn viewModelColumn;
    private final SimpleBooleanProperty disableButton = new SimpleBooleanProperty();
    private SimpleBooleanProperty deleteDisable = new SimpleBooleanProperty();
    private final SimpleBooleanProperty selected = new SimpleBooleanProperty();



    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }

    public void configData(){
        columns.setValue(board.getColumns());
        selected.setValue(board.getSelectedColumns().size() == 0);
        refreshUndoRedoProperty();
        boardName.setValue(board.getName());
        disableButton.set(board.isSelecte());

    }
    public void refreshUndoRedoProperty(){
        redoDisable.setValue(Processor.getInstance().getSizeUndoCommand());
        undoDisable.setValue(Processor.getInstance().getSizeCommand());
        if(Processor.getInstance().getLastCommand() != null){
            nameAction.setValue(Processor.getInstance().getLastCommand().getNameAction());
        }
        if(Processor.getInstance().getLastUndoCommand() != null){
            nameActionRedo.setValue(Processor.getInstance().getLastUndoCommand().getRedoNameAction());
        }
        boardName.setValue(board.getName());


    }
    public void undo(){

        Processor.getInstance().undo();
        configData();
        if(Processor.getInstance().getSizeCommand()){
            nameAction.setValue("");
        }
    }
    public void redo(){

        Processor.getInstance().redo();
        configData();
        if(Processor.getInstance().getSizeUndoCommand()){
            nameActionRedo.setValue(nameAction.getValue());
        }

    }
    public Board getBoard() {
        return board;
    }
    public void addColumn(){
        Column column = new Column((board.lastIdColumn() + 1), "column"+(board.getColumns().size() + 1),board.getColumns().size() +1,board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(column);
        Processor.getInstance().execute(addColumnCommand);
        refreshUndoRedoProperty();
    }
    public void setTitle(String title){
        Processor.getInstance().execute(new SetTitleBoardCommand(board ,title ,board.getName()));
        refreshUndoRedoProperty();
    }
    public void addColumn(Column column) {

        board.addColumn(column);
        selected.setValue(board.getSelectedColumns().size() == 0);



    }
    public void retireColumn(Column column) {

        board.removeColumn(column);
        selected.setValue(board.getSelectedColumns().size() == 0);


    }
    public void supprime() {

        board.getSelectedColumns().forEach(c->{
            //RemoveColumnCommand removeColumnCommand = new RemoveColumnCommand(c,c.getPosition());
            //Processor.getInstance().execute(removeColumnCommand);
            RemoveAllSelectedColumns removeAllSelectedColumns = new RemoveAllSelectedColumns(this.board,board.getSelectedColumns());
            Processor.getInstance().execute(removeAllSelectedColumns);
        });
        board.removeColumns();
        selected.setValue(board.getSelectedColumns().size() == 0);
        refreshUndoRedoProperty();
        disableButton.set(board.isSelecte());


    }

    public SimpleListProperty<Column> columnsProperty() {
        return columns;
    }
    public SimpleBooleanProperty undoDisableProperty() {
        return undoDisable;
    }
    public SimpleBooleanProperty redoDisableProperty() {
        return redoDisable;
    }
    public SimpleStringProperty nameActionProperty() {
        return nameAction;
    }
    public SimpleStringProperty nameActionRedoProperty() {
        return nameActionRedo;
    }
    public SimpleStringProperty boardNameProperty() {
        return boardName;
    }
    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }
    public SimpleBooleanProperty disableButtonProperty() {
        return disableButton;
    }

}
