package draweditor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import draweditor.frames.MainDrawFrame;
import draweditor.figures.Group;
import draweditor.figures.IFigure;
import draweditor.tools.ITool;
import draweditor.commands.ICommand;
import draweditor.commands.IReversibleCommand;

/*  note:
    - make sure first group is not removed/ is there always
    - vgm moet je alle figures andersom in tree displayer (hoe dieper naar beneden hoe hoger op in het canvas het komt)
    - redraw() is temp placeholder
    - rewrite load fuction of filehandler
    - figer serialize of IFigure out (give values ad making of list)
    - the implemented active system isn't great (esspesialy in how it is used in commands)
    - add a cap to how large te history list should be.
    - if group is selected and item draw edit it to the back of list in group
*/

public class DrawEditor {
    //public String path;
    //public bool filechange;
    private IReversibleCommand lastExecutedCommand;
    private List<IReversibleCommand> commandsHistory = new ArrayList<IReversibleCommand>();
    //public List<IFigure> figures = new ArrayList<IFigure>();
    public Group figures;  
    public List<ITool> tools = new ArrayList<ITool>();

    public IFigure activeFigure;
    public Group activeGroup;
    public int activePosision;

    public static void main(String[] args) throws Exception {
        System.out.println("Main started:");
        JFrame f = new MainDrawFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void execute(ICommand command){
        command.execute(this);
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
            lastExecutedCommand.execute(this);
        }
    }

    public void unexecute(){
        if (lastExecutedCommand != null){
            lastExecutedCommand.unexecute(this);
            int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
            if (lastExecutedCommandIndex != 0){
                lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex - 1);
            } else {
                lastExecutedCommand = null;
            }
        }
    }

    public void redraw(){
        
    }

    public void setActiveFigure(IFigure figure) {
        activeFigure = figure;
        activePosision = activeGroup.getFigures().indexOf(figure);
        if (activePosision == -1) {
            activeGroup = figures.findGroup(figure);
            if (activeGroup != null){
                activePosision = activeGroup.getFigures().indexOf(figure);
            } else {
                throw new Error("No group found. (source: DrawEditor setActiveFigure())");
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