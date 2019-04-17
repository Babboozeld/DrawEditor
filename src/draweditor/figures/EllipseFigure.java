package draweditor.figures;

import java.awt.Color;

import draweditor.figures.AbstractFigure;
import draweditor.visitors.IComponentVisitor;

public class EllipseFigure extends AbstractFigure {

    public EllipseFigure(int left, int top, int width, int height, Color color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }
}