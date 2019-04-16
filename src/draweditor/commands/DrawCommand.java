package draweditor.commands;

import java.util.List;

import draweditor.DrawEditor;
import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.frame.components.ShapeList;

public class DrawCommand implements ICommand, IReversibleCommand {

    private IComponent figure;
    private Group group;
    private int position = 0;

    public DrawCommand(IComponent figure) {
        this.figure = figure;
        DrawEditor draweditor = DrawEditor.getInstance();
        this.group = draweditor.activeGroup;
        this.position = draweditor.activePosision;
    }

    public void execute(DrawEditor draweditor) {
        List<IComponent> figures = this.group.getFigures();
        figures.add(this.position, this.figure);
        ShapeList.getInstance().addItem(figure.getClass().getSimpleName());
        draweditor.redraw();
        draweditor.setActiveFigure(this.figure);
    }

    public void unexecute(DrawEditor draweditor) {
        draweditor.setActiveFigure(this.figure);
        draweditor.activeGroup.remove(this.figure);
        //ShapeList.getInstance().deleteItem();
        draweditor.redraw();
        if (draweditor.activeGroup.getSize() != 0 && draweditor.activePosision != 0) {
            draweditor.setActiveFigure(draweditor.activeGroup.getFigures().get(draweditor.activePosision - 1));
        } else {
            draweditor.setActiveFigure(draweditor.activeGroup);
        }
    }
}

