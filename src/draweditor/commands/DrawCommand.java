package draweditor.commands;

import java.util.List;

import draweditor.DrawEditor;
import draweditor.components.IComponent;

public class DrawCommand implements ICommand, IReversibleCommand {

    public IComponent figure;

    public DrawCommand(IComponent figure) {
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        List<IComponent> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision, this.figure);
        //ShapeList.addItem(figure.getClass().getSimpleName());
        draweditor.redraw();
        draweditor.setActiveFigure(this.figure);
    }

    public void unexecute(DrawEditor draweditor) {
        draweditor.setActiveFigure(this.figure);
        draweditor.activeGroup.remove(this.figure);
        //ShapeList.deleteItem();
        draweditor.redraw();
        if (draweditor.activeGroup.getSize() != 0 && draweditor.activePosision != 0) {
            draweditor.setActiveFigure(draweditor.activeGroup.getFigures().get(draweditor.activePosision - 1));
        } else {
            draweditor.setActiveFigure(draweditor.activeGroup);
        }
    }
}