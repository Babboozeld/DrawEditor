package draweditor.visitors;

import draweditor.components.Group;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.BasisFigure;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public interface IComponentVisitor {
    void visit(Group group);
    void visit(BasisFigure basisFigure);
    void visit(RectangleFigure rectangleFigure);
	void visit(EllipseFigure ellipseFigure);
	void visit(BottomTextDecorator bottomTextDecorator);
    void visit(LeftTextDecorator leftTextDecorator);
    void visit(RightTextDecorator rightTextDecorator);
	void visit(TopTextDecorator topTextDecorator);
}