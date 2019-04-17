package draweditor.tools;

import draweditor.commands.DrawCommand;
import draweditor.commands.ICommand;
import draweditor.commands.TempDrawCommand;
import draweditor.figures.BasicFigure;

public class EllipseBasicTool extends AbstractTool {

    public EllipseBasicTool() {
        this.toolIcon = createImageIcon("/images/ellipse.png", "ellipse");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        BasicFigure figure = BasicFigure.GetInstanceEllipse();
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