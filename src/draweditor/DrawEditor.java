package draweditor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import draweditor.frames.MainDrawFrame;
import draweditor.figures.IFigure;
import draweditor.tools.ITool;
import draweditor.commands.ICommand;
import draweditor.commands.IReversibleCommand;

public class DrawEditor {
    public IReversibleCommand lastExecutedCommand;
    public List<IReversibleCommand> commandsHistory = new ArrayList<IReversibleCommand>();
    public List<IFigure> figures = new ArrayList<IFigure>();
    public List<ITool> tools = new ArrayList<ITool>();
    public static void main(String[] args) throws Exception {
        System.out.println("Main started:");
        JFrame f = new MainDrawFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void execute(ICommand command){
        command.execute();
        if (command instanceof IReversibleCommand){ //add a cap to how large te list should be.
            if (lastExecutedCommand != null){
                int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
                if (lastExecutedCommandIndex != commandsHistory.size() - 1) {
                    commandsHistory.subList(commandsHistory.size() - lastExecutedCommandIndex, commandsHistory.size()).clear(); //index could be wrong.
                }
            }
            commandsHistory.add((IReversibleCommand)command);
            lastExecutedCommand = (IReversibleCommand)command;
        }
    }

    public void reverseExecute(){
        int lastExecutedCommandIndex = lastExecutedCommand == null ? 0 : commandsHistory.indexOf(lastExecutedCommand);
        if (lastExecutedCommandIndex < commandsHistory.size() - 1) {
            lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex + 1);
            lastExecutedCommand.execute();
        }
    }

    public void unexecute(){
        if (lastExecutedCommand != null){
            lastExecutedCommand.unexecute();
            int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
            if (lastExecutedCommandIndex != 0){
                lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex - 1);
            } else {
                lastExecutedCommand = null;
            }
        }
    } 

}






//https://stackoverflow.com/questions/147181/how-can-i-convert-my-java-program-to-an-exe-file
/*
Grammatica:  
file      = group 
group     = "group" count {figure} 
figure    = group | rectangle | ellipse | ornament 
rectangle = "rectangle" left top width height 
ellipse   = "ellipse" left top width height 
ornament  = "ornament" position string figure 
position  = "top" | "bottom" | "left" | "right" 
count, left, top, width, height = int 
 
*/