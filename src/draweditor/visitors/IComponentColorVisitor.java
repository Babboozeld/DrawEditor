package draweditor.visitors;

import java.awt.Color;
import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentColorVisitor implements IComponentVisitor {

    private Color color;

    public IComponentColorVisitor(Color color) {
        this.color = color;
    }

    @Override
    public void visit(Group group) {
        List<IComponent> figures = group.getFigures();
        for (IComponent figure : figures) {
            figure.accept(this);
        }
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        rectangleFigure.color = this.color;
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        ellipseFigure.color = this.color;
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