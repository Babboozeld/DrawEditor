package draweditor.strategies;

import java.awt.Graphics;

import draweditor.figures.BasicFigure;

public class RectangleDrawStrategy implements IDrawStrategy {

    @Override
    public void doOperation(BasicFigure basisFigure, Graphics g) {
        g.setColor(basisFigure.color);
        g.fillRect(basisFigure.left, basisFigure.top, basisFigure.width, basisFigure.height);
    }
}



