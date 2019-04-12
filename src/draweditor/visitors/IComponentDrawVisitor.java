package draweditor.visitors;

import java.awt.Graphics;
import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentDrawVisitor implements IComponentVisitor {

    private Graphics g;

    public IComponentDrawVisitor(Graphics g) {
        this.g = g;
    }

    @Override
    public void visit(Group group) {
        List<IComponent> figures = group.getFigures();
        for (int i = figures.size() - 1; i >= 0 ; i--) {
            figures.get(i).accept(this);
        }
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        // https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness
        // Graphics2D g2 = (Graphics2D)g;
        // g2.setStroke(new BasicStroke(7));

        //this.g.drawRect(rectangleFigure.left, rectangleFigure.top, rectangleFigure.width, rectangleFigure.height);
        this.g.setColor(rectangleFigure.color);
        this.g.fillRect(rectangleFigure.left, rectangleFigure.top, rectangleFigure.width, rectangleFigure.height);
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        g.setColor(ellipseFigure.color);
        g.drawOval(ellipseFigure.left, ellipseFigure.top, ellipseFigure.width, ellipseFigure.height);
        // g.fillOval(left, top, width, height);
    }
}