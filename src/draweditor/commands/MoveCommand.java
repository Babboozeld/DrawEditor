package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.components.IComponent;
import draweditor.visitors.IComponentMoveVisitor;

public class MoveCommand implements ICommand, IReversibleCommand {
    
    private IComponent figure;
    private int dx, dy;

    public MoveCommand(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        figure = DrawEditor.getInstance().activeFigure;
    }

    public void execute(DrawEditor draweditor) {
        figure.accept(new IComponentMoveVisitor(this.dx, this.dy));
        draweditor.redraw();  
    }

    public void unexecute(DrawEditor draweditor) {
        figure.accept(new IComponentMoveVisitor(-this.dx, -this.dy));
        draweditor.redraw(); 
    }
}