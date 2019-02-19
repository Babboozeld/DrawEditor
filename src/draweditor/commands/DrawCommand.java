package draweditor.commands;

import java.util.List;

import draweditor.DrawEditor;
import draweditor.figures.IFigure;

public class DrawCommand implements ICommand, IReversibleCommand {

    public IFigure figure;
    //public IFigure pevFigure;

    public DrawCommand(IFigure figure){
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        List<IFigure> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision + 1, this.figure);
        draweditor.redraw();
        draweditor.setActiveFigure(this.figure);
    }

    public void unexecute(DrawEditor draweditor) {
        List<IFigure> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision + 1, this.figure);
        draweditor.redraw();
        draweditor.setActiveFigure(this.figure);
    }

}