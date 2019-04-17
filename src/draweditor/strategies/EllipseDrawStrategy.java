package draweditor.strategies;

import java.awt.Graphics;

import draweditor.figures.BasicFigure;

public class EllipseDrawStrategy implements IDrawStrategy {

    @Override
    public void doOperation(BasicFigure basisFigure, Graphics g) {
        g.setColor(basisFigure.color);
        g.fillOval(basisFigure.left, basisFigure.top, basisFigure.width, basisFigure.height);
    }
}