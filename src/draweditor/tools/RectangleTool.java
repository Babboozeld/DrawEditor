package draweditor.tools;

import java.awt.Color;

import draweditor.commands.DrawCommand;
import draweditor.commands.ICommand;
import draweditor.commands.TempDrawCommand;
import draweditor.figures.IFigure;
import draweditor.figures.RectangleFigure;

public class RectangleTool extends AbstractTool {
    public RectangleTool() {
        this.toolIcon = createImageIcon("/images/rectangle.png", "rectangle");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        IFigure figure;
        if (x < beginX) {
            if (y < beginY){
                figure = new RectangleFigure(x, y, beginX - x, beginY - y, Color.RED);
            } else {
                figure = new RectangleFigure(x, beginY, beginX - x, y - beginY, Color.RED);
            }
        }else if (y < beginY) {
            figure = new RectangleFigure(beginX, y, x- beginX, beginY - y, Color.RED);
        } else {
            figure = new RectangleFigure(beginX, beginY, x- beginX, y - beginY, Color.RED);
        }  
        return temporary ? new TempDrawCommand(figure) : new DrawCommand(figure);
    }
}