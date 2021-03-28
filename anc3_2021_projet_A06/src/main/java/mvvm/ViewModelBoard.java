package mvvm;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Board;
import model.Column;
import model.Processor;
import model.board.AddColumnCommand;
import model.board.SetTitleBoardCommand;

public class ViewModelBoard {
    private Board board;

    private final SimpleListProperty<Column> columns = new SimpleListProperty<>();
    private SimpleBooleanProperty redoDisable = new SimpleBooleanProperty();
    private SimpleBooleanProperty undoDisable = new SimpleBooleanProperty();
    private SimpleStringProperty nameAction = new SimpleStringProperty(" ");
    private SimpleStringProperty nameActionRedo = new SimpleStringProperty(" ");
    private SimpleStringProperty boardName = new SimpleStringProperty();


    public ViewModelBoard(Board board) {
        this.board = board;
        configData();
    }

    public void configData(){
        columns.setValue(board.getColumns());
        refreshUndoRedoProperty();
        boardName.setValue(board.getName());


    }
    public void refreshUndoRedoProperty(){
        redoDisable.setValue(Processor.getInstance().getSizeUndoCommand());
        undoDisable.setValue(Processor.getInstance().getSizeCommand());
        if(Processor.getInstance().getLastCommand() != null){
            nameAction.setValue(Processor.getInstance().getLastCommand().getNameAction());
        }
        if(Processor.getInstance().getLastUndoCommand() != null){
            nameActionRedo.setValue(nameAction.getValue());

        }
        boardName.setValue(board.getName());


    }
    public void undo(){

        Processor.getInstance().undo();
        refreshUndoRedoProperty();
        if(Processor.getInstance().getSizeCommand()){
            nameAction.setValue("");
        }

    }
    public void redo(){

        Processor.getInstance().redo();
        refreshUndoRedoProperty();
        if(Processor.getInstance().getSizeUndoCommand()){
            nameActionRedo.setValue(nameAction.getValue());
        }

    }
    public Board getBoard() {
        return board;
    }
    public void addColumn(){
        Column column = new Column("column"+(board.getColumns().size() + 1),board.getColumns().size() +1,board);
        AddColumnCommand addColumnCommand = new AddColumnCommand(column);
        Processor.getInstance().execute(addColumnCommand);
        refreshUndoRedoProperty();
    }
    public void setTitle(String title){
        Processor.getInstance().execute(new SetTitleBoardCommand(board ,title ,board.getName()));
        refreshUndoRedoProperty();
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


}
