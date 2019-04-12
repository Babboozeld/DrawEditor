package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.visitors.IComponentMoveVisitor;

public class MoveCommand implements ICommand, IReversibleCommand {
    
    private int dx, dy;

    public MoveCommand(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        //draweditor.activeFigure.move(this.dx, this.dy);
        draweditor.activeFigure.accept(new IComponentMoveVisitor(this.dx, this.dy));
        draweditor.redraw();  
    }


    public void unexecute(DrawEditor draweditor) {
        //draweditor.activeFigure.move(-this.dx, -this.dy);
        draweditor.activeFigure.accept(new IComponentMoveVisitor(-this.dx, -this.dy));
        draweditor.redraw(); 
    }
}