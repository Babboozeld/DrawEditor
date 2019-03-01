package draweditor.tools;

import draweditor.figures.IFigure;
//import draweditor.figures.EllipseFigure;

public class EllipseTool extends AbstractTool {
    public EllipseTool() {
        this.toolIcon = createImageIcon("/images/ellipse.png", "test");
    }

    @Override
    public IFigure getFigure(int x, int y) {
        return null; //new EllipseFigure(beginX, beginY, x, y);
    }
}