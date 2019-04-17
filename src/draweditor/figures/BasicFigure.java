package draweditor.figures;

import java.awt.Color;

import draweditor.components.IComponent;
import draweditor.figures.AbstractFigure;
import draweditor.strategies.*;
import draweditor.visitors.IComponentVisitor;

public class BasicFigure extends AbstractFigure {

    public IToStringStrategy toStringStrat;
    public IDrawStrategy drawStrat;

    public IComponent SetAttributes(int left, int top, int width, int height, Color color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
        return this;
    }

    private BasicFigure(IToStringStrategy toStringStrat, IDrawStrategy drawStrat) {
        this.toStringStrat = toStringStrat;
        this.drawStrat = drawStrat;
    }
    
    public static BasicFigure GetInstanceRectangle() {
        return new BasicFigure(new RectangleToStringStrategy(), new RectangleDrawStrategy());
    }

    public static BasicFigure GetInstanceEllipse() {
        return new BasicFigure(new EllipseToStringStrategy(), new EllipseDrawStrategy());
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }
}