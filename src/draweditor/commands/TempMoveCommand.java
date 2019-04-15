package draweditor.commands;

import javax.swing.SwingUtilities;

import draweditor.DrawEditor;
import draweditor.visitors.IComponentMoveVisitor;

public class TempMoveCommand implements ICommand {

    private int dx, dy;

    public TempMoveCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        draweditor.activeFigure.accept(new IComponentMoveVisitor(this.dx, this.dy));
        draweditor.redraw();  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                draweditor.activeFigure.accept(new IComponentMoveVisitor(-dx, -dy));
            }
        });  
    }
}