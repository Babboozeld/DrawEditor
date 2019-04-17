package draweditor.strategies;

import java.awt.Graphics;

import draweditor.figures.BasisFigure;

public interface IDrawStrategy {
    public void doOperation(BasisFigure basisFigure, Graphics g);
}