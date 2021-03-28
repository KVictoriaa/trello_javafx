package model.board;

import model.Board;
import model.Command;

public abstract class BoardCommand implements Command {

    private final Board board;

    public BoardCommand(Board board) {
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }
}
