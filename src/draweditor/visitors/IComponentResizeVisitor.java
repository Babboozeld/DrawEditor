package draweditor.visitors;

import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.BasisFigure;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentResizeVisitor implements IComponentVisitor {

    private int dx, dy;

    public IComponentResizeVisitor(int dx, int dy) {
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
    public void visit(BasisFigure basisFigure) {
        basisFigure.width += dx;
        basisFigure.height += dy;
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        rectangleFigure.width += dx;
        rectangleFigure.height += dy;
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        ellipseFigure.width += dx;
        ellipseFigure.height += dy;
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