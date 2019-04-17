package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.components.IComponent;
import draweditor.visitors.IComponentResizeVisitor;

public class ResizeCommand implements ICommand, IReversibleCommand {

    private IComponent figure;
    private int dx, dy;

    public ResizeCommand(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        figure = DrawEditor.getInstance().activeFigure;
    }

    public void execute(DrawEditor draweditor) {
        figure.accept(new IComponentResizeVisitor(this.dx, this.dy));
        draweditor.redraw();  
    }

    public void unexecute(DrawEditor draweditor) {
        figure.accept(new IComponentResizeVisitor(-this.dx, -this.dy));
        draweditor.redraw(); 
    }
}