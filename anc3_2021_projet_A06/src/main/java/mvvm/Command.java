package model;

public interface Command {
    void execute();
    void undo();
    boolean canBeUndone();
    String getNameAction();
}

