package draweditor.visitors;

import draweditor.components.Group;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public interface IComponentVisitor {
    void visit(Group group);
    void visit(RectangleFigure rectangleFigure);
	void visit(EllipseFigure ellipseFigure);
}