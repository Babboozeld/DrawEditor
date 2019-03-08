package draweditor.commands;

import javax.swing.SwingUtilities;

import draweditor.DrawEditor;
import draweditor.figures.IFigure;

public class TempMoveCommand implements ICommand {

    private int dx, dy;

    public TempMoveCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        IFigure avectedFigure = draweditor.activeFigure;
        avectedFigure.move(this.dx, this.dy);
        draweditor.redraw();  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                avectedFigure.move(-dx, -dy);  
            }
        });  
    }

}