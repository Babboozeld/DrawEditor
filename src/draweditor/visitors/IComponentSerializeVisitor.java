package draweditor.visitors;

import java.util.List;
import java.util.ArrayList;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.BasicFigure;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentSerializeVisitor implements IComponentVisitor {

    private List<String> serialized = new ArrayList<String>();

    public IComponentSerializeVisitor(IComponent figures) {
        figures.accept(this);
    }

    public List<String> getSerialized() {
        return serialized;
    }

    @Override
    public void visit(Group group) {
        serialized.add("group " + group.getSize());
        for (IComponent figure : group.getFigures()) {
            figure.accept(this);
        }
    }

    @Override
    public void visit(BasicFigure basisFigure) {
        serialized.add("shape basicfigure " + basisFigure.toStringStrat.doOperation() + " " + basisFigure.left + " " + basisFigure.top + " " + basisFigure.width
        + " " + basisFigure.height + " " + String.valueOf(basisFigure.color.getRGB()));
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        serialized.add("shape rectangle " + rectangleFigure.left + " " + rectangleFigure.top + " " + rectangleFigure.width
                        + " " + rectangleFigure.height + " " + String.valueOf(rectangleFigure.color.getRGB()));
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        serialized.add("shape ellipse " + ellipseFigure.left + " " + ellipseFigure.top + " " + ellipseFigure.width + " "
                + ellipseFigure.height + " " + String.valueOf(ellipseFigure.color.getRGB()));
    }

    @Override
    public void visit(BottomTextDecorator bottomTextDecorator) {
        serialized.add("ornament bottom " + bottomTextDecorator.text);
    }

    @Override
    public void visit(LeftTextDecorator leftTextDecorator) {
        serialized.add("ornament left " + leftTextDecorator.text);
    }

    @Override
    public void visit(RightTextDecorator rightTextDecorator) {
        serialized.add("ornament right " + rightTextDecorator.text);
    }

    @Override
    public void visit(TopTextDecorator topTextDecorator) {
        serialized.add("ornament top " + topTextDecorator.text);
    }
}