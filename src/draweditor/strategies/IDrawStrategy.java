package draweditor.strategies;

import java.awt.Graphics;

import draweditor.figures.BasicFigure;

public interface IDrawStrategy {
    public void doOperation(BasicFigure basisFigure, Graphics g);
}