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
        return new RectangleFigure(beginX, beginY, x, y, Color.RED);
    }
}