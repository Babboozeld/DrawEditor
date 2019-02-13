package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.figures.IFigure;

public class MoveCommand implements ICommand, IReversibleCommand {

    private IFigure figure;
    private int dx, dy;

    public MoveCommand(IFigure figure, int dx, int dy){
        this.figure = figure;
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        this.figure.move(this.dx, this.dy);
        draweditor.redraw();  
    }


    public void unexecute(DrawEditor draweditor) {
        this.figure.move(-this.dx, -this.dy);
        draweditor.redraw(); 
    }

}