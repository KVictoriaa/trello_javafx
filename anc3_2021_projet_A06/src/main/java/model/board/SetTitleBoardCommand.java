package model.board;

import model.Board;

public class SetTitleBoardCommand extends BoardCommand {
    private String name;
    private String content;

    public SetTitleBoardCommand(Board board, String name, String content) {
        super(board);
        this.name = name;
       this.content = content;
    }

    @Override
    public void execute() {
        this.getBoard().setName(name);
      }

    @Override
    public void undo() {
        this.getBoard().setName(content);

    }

    @Override
    public boolean canBeUndone() {
        return true;
    }

    @Override
    public String getNameAction() {
        return "la modification du titre";
    }


}
