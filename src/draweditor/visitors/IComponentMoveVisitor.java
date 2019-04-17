package draweditor.visitors;

import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.BasicFigure;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentMoveVisitor implements IComponentVisitor {

    private int dx, dy;

    public IComponentMoveVisitor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void visit(Group group) {
        List<IComponent> figures = group.getFigures();
        for (IComponent figure : figures) {
            figure.accept(this);
        }
    }

    @Override
    public void visit(BasicFigure basisFigure) {
        basisFigure.left += dx;
        basisFigure.top += dy;
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

    @Override
    public void visit(BottomTextDecorator bottomTextDecorator) {

    }

    @Override
    public void visit(LeftTextDecorator leftTextDecorator) {

    }

    @Override
    public void visit(RightTextDecorator rightTextDecorator) {

    }

    @Override
    public void visit(TopTextDecorator topTextDecorator) {

    }
}