package draweditor.commands;

import javax.swing.SwingUtilities;

import draweditor.DrawEditor;
import draweditor.visitors.IComponentResizeVisitor;

public class TempResizeCommand implements ICommand {

    private int dx, dy;

    public TempResizeCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void execute(DrawEditor draweditor) {
        draweditor.activeFigure.accept(new IComponentResizeVisitor(this.dx, this.dy));
        draweditor.redraw();  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                draweditor.activeFigure.accept(new IComponentResizeVisitor(-dx, -dy));
            }
        });  
    }
}