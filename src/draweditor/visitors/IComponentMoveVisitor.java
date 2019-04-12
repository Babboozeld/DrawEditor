package draweditor.visitors;

import draweditor.components.Group;
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

    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        rectangleFigure.left += dx;
        rectangleFigure.top += dy;
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        ellipseFigure.left += dx;
        ellipseFigure.top += dy;
    }
}