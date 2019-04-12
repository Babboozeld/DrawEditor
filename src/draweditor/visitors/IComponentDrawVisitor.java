package draweditor.visitors;

import java.awt.Graphics;

import draweditor.components.Group;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;

public class IComponentDrawVisitor implements IComponentVisitor {

    private Graphics g;

    public IComponentDrawVisitor(Graphics g) {
        this.g = g;
    }

    @Override
    public void visit(Group group) {

    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        this.g.setColor(rectangleFigure.color);
        // Graphics2D g2 = (Graphics2D)g;
        // https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness
        // g2.setStroke(new BasicStroke(7));
        this.g.drawRect(rectangleFigure.left, rectangleFigure.top, rectangleFigure.width, rectangleFigure.height);
        // g.fillRect(left, top, width, height);
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        g.setColor(ellipseFigure.color);
        g.drawOval(ellipseFigure.left, ellipseFigure.top, ellipseFigure.width, ellipseFigure.height);
        // g.fillOval(left, top, width, height);
    }
}