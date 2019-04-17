package draweditor.figures;

import java.awt.Color;

import draweditor.figures.AbstractFigure;
import draweditor.strategies.*;
import draweditor.visitors.IComponentVisitor;

public class BasisFigure extends AbstractFigure {

    public IToStringStrategy toStringStrat;
    public IDrawStrategy drawStrat;

    public BasisFigure(IToStringStrategy toStringStrat, IDrawStrategy drawStrat) {
        this.toStringStrat = toStringStrat;
        this.drawStrat = drawStrat;
    }

    public void SetAttributes(int left, int top, int width, int height, Color color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public BasisFigure GetInstanceRectangle() {
        return new BasisFigure(new RectangleToStringStrategy(), new RectangleDrawStrategy());
    }

    public BasisFigure GetInstanceEllipse() {
        return new BasisFigure(new EllipseToStringStrategy(), new EllipseDrawStrategy());
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }
}