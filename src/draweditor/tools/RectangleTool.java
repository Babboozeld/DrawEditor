package draweditor.tools;

import draweditor.commands.DrawCommand;
import draweditor.commands.ICommand;
import draweditor.commands.TempDrawCommand;
import draweditor.components.IComponent;
import draweditor.figures.RectangleFigure;

public class RectangleTool extends AbstractTool {

    public RectangleTool() {
        this.toolIcon = createImageIcon("/images/rectangle.png", "rectangle");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) 
    {
        IComponent figure;
        if (x < beginX) {
            if (y < beginY){
                figure = new RectangleFigure(x, y, beginX - x, beginY - y, color);
            } else {
                figure = new RectangleFigure(x, beginY, beginX - x, y - beginY, color);
            }
        }else if (y < beginY) {
            figure = new RectangleFigure(beginX, y, x- beginX, beginY - y, color);
        } else {
            figure = new RectangleFigure(beginX, beginY, x- beginX, y - beginY, color);
        }
        return temporary ? new TempDrawCommand(figure) : new DrawCommand(figure);
    }
}
