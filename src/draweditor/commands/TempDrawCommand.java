package draweditor.commands;

import java.awt.Graphics;
import java.util.List;

import draweditor.DrawEditor;
import draweditor.figures.IFigure;

public class TempDrawCommand implements ICommand {

    public IFigure figure;

    public TempDrawCommand(IFigure figure){
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        //figure.draw(Graphics g);
        List<IFigure> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision, this.figure);
        draweditor.redraw();
        figures.remove(this.figure);        
    }

}