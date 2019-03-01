package draweditor.tools;

import draweditor.figures.IFigure;

public class TriangleTool extends AbstractTool {
    public TriangleTool() {
        this.toolIcon = createImageIcon("/images/triangle.png", "test");
    }

    @Override
    public IFigure getFigure(int x, int y) {
        return null; //new RectangleFigure(beginX, beginY, x, y, Color.RED);
    }
}