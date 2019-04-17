package draweditor.tools;

import draweditor.commands.DrawCommand;
import draweditor.commands.ICommand;
import draweditor.commands.TempDrawCommand;
import draweditor.figures.BasicFigure;

public class RectangleBasicTool extends AbstractTool {

    public RectangleBasicTool() {
        this.toolIcon = createImageIcon("/images/rectangle.png", "rectangle");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        BasicFigure figure = BasicFigure.GetInstanceRectangle();
        // todo: can be done in function
        if (x < beginX) {
            if (y < beginY){
                figure.SetAttributes(x, y, beginX - x, beginY - y, this.color);
            } else {
                figure.SetAttributes(x, beginY, beginX - x, y - beginY, this.color);
            }
        } else if (y < beginY) {
            figure.SetAttributes(beginX, y, x- beginX, beginY - y, this.color);
        } else {
            figure.SetAttributes(beginX, beginY, x- beginX, y - beginY, this.color);
        } 
        // 
        return temporary ? new TempDrawCommand(figure) : new DrawCommand(figure);
    }
}