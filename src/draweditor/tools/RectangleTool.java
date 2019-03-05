package draweditor.tools;

import java.awt.Color;

import draweditor.figures.IFigure;
import draweditor.figures.RectangleFigure;

public class RectangleTool extends AbstractTool {
    public RectangleTool() {
        this.toolIcon = createImageIcon("/images/rectangle.png", "test");
    }

    @Override
    public IFigure getFigure(int x, int y) {
        if (x < beginX) {
            if (y < beginY){
                return new RectangleFigure(x, y, beginX - x, beginY - y, Color.RED);
            } else {
                return new RectangleFigure(x, beginY, beginX - x, y - beginY, Color.RED);
            }
        }else if (y < beginY) {
            return new RectangleFigure(beginX, y, x- beginX, beginY - y, Color.RED);
        } else {
            return new RectangleFigure(beginX, beginY, x- beginX, y - beginY, Color.RED);
        }       
    }
}