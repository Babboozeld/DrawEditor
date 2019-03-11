package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.figures.GroupFigure;
import draweditor.figures.IFigure;

public class DeleteCommand implements ICommand, IReversibleCommand {

    private IFigure figure;
    private GroupFigure group;
    private int position = 0;

    public DeleteCommand(IFigure figure) {
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        if (this.figure != draweditor.drawCanvas.getFigures()) {
            this.group = draweditor.activeGroup;
            this.position = draweditor.activePosision;
            draweditor.activeGroup.remove(this.figure);
            draweditor.redraw();
            if (draweditor.activeGroup.getSize() != 0 && draweditor.activePosision != 0) {
                draweditor.setActiveFigure(draweditor.activeGroup.getFigures().get(draweditor.activePosision - 1));
            } else {
                draweditor.setActiveFigure(draweditor.activeGroup);
            }
        }
    }

    public void unexecute(DrawEditor draweditor) {
        if (this.figure != draweditor.drawCanvas.getFigures()) {
            this.group.getFigures().add(this.position -1, this.figure);
            draweditor.redraw();
            draweditor.setActiveFigure(this.figure);
        }
    }
    
}