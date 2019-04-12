package draweditor.visitors;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

import java.awt.Graphics;

public class IComponentDrawVisitor implements IComponentVisitor {

    private Graphics g;

    public IComponentDrawVisitor(Graphics g) {
        this.g = g;
    }

    @Override
    public void visit(Group group) {
        for (IComponent figure : group.getFigures()) {
            figure.accept(this);
        }
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        rectangleFigure.draw(g);
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        ellipseFigure.draw(g);
    }
}