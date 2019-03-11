package draweditor.commands;

import draweditor.DrawEditor;

public class MoveCommand implements ICommand, IReversibleCommand {
    
    private int dx, dy;

    public MoveCommand(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        draweditor.activeFigure.move(this.dx, this.dy);
        draweditor.redraw();  
    }


    public void unexecute(DrawEditor draweditor) {
        draweditor.activeFigure.move(-this.dx, -this.dy);
        draweditor.redraw(); 
    }
}