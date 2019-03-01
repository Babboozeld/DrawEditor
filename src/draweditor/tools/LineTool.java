package draweditor.tools;

import draweditor.figures.IFigure;

public class LineTool extends AbstractTool {
    public LineTool() {
        this.toolIcon = createImageIcon("/images/line.png", "test");
    }

    @Override
    public IFigure getFigure(int x, int y) {
        return null; //new EllipseFigure(beginX, beginY, x, y);
    }
}