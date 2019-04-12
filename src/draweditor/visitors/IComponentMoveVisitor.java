package draweditor.visitors;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentMoveVisitor implements IComponentVisitor {

    int dx;
    int dy;

    public IComponentMoveVisitor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void visit(Group group) {
        for (IComponent figure : group.getFigures()) {
            figure.accept(this);
        }
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        rectangleFigure.move(dx, dy);
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        ellipseFigure.move(dx, dy);
    }
}