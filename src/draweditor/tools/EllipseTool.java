package draweditor.tools;

import draweditor.figures.IFigure;

import java.awt.Color;

import draweditor.commands.DrawCommand;
import draweditor.commands.ICommand;
import draweditor.commands.TempDrawCommand;
import draweditor.figures.EllipseFigure;

public class EllipseTool extends AbstractTool {
    public EllipseTool() {
        this.toolIcon = createImageIcon("/images/ellipse.png", "ellipse");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary, Color color) {
        IFigure figure;
        if (x < beginX) {
            if (y < beginY){
                figure = new EllipseFigure(x, y, beginX - x, beginY - y, color);
            } else {
                figure = new EllipseFigure(x, beginY, beginX - x, y - beginY, color);
            }
        }else if (y < beginY) {
            figure = new EllipseFigure(beginX, y, x- beginX, beginY - y, color);
        } else {
            figure = new EllipseFigure(beginX, beginY, x- beginX, y - beginY, color);
        }  
        return temporary ? new TempDrawCommand(figure) : new DrawCommand(figure);
    }
}