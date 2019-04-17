package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.components.Group;

public class LoadCommand implements ICommand, IReversibleCommand {

    private Group newGroup;
    private Group oldGroup;

    public LoadCommand(Group loadedGroup) {
        this.newGroup = loadedGroup;
        oldGroup = DrawEditor.getInstance().getFigures();
    }

    public void execute(DrawEditor draweditor) {
        draweditor.loadedFigures(newGroup);
        draweditor.redraw();  
    }

    public void unexecute(DrawEditor draweditor) {
        draweditor.loadedFigures(oldGroup);
        draweditor.redraw(); 
    }
}