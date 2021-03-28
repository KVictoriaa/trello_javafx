package model;

import java.util.ArrayList;
import java.util.List;


public class Processor {
    private static Processor processor;
    private List<Command> commands = new ArrayList<>();
    private List<Command> undoCommands = new ArrayList<>();

    public static Processor getInstance(){
        if(processor == null){
            processor = new Processor();
        }
        return processor;
    }

    public Command getLastCommand(){
        if(!commands.isEmpty()){
            return commands.get(commands.size() -1);
        }
        return null;
    }
    public Command getLastUndoCommand(){
        if(!undoCommands.isEmpty()){
            return undoCommands.get(undoCommands.size()-1);
        }
        return null;
    }
    public void removeLastCommand(){
        if(!commands.isEmpty()){
            commands.remove(commands.size() -1);
        }
    }
    public boolean canBeUndone(){
        if(getLastCommand() == null){
            return false;
        }
        return getLastCommand().canBeUndone();
    }
    public void execute(Command command){
        command.execute();
        if(command.canBeUndone()){
            this.commands.add(command);
        }
        else {
            this.commands.clear();
        }
    }
    public void undo(){
        if(canBeUndone()){
            Command command = getLastCommand();
            command.undo();
            removeLastCommand();
            undoCommands.add(command);
        }
        else {
            throw new RuntimeException("Aucune commande à annuler.");
        }
    }
    public void redo(){
        if(!undoCommands.isEmpty()){
            Command command = undoCommands.get(undoCommands.size() - 1);
            undoCommands.remove(undoCommands.size() - 1);
            this.execute(command);
        }
        else{
            throw new RuntimeException("Aucune commande à refaire.");
        }
    }
    public boolean getSizeCommand(){
        return commands.isEmpty();

    }
    public boolean getSizeUndoCommand(){
        return undoCommands.isEmpty();
    }

}
