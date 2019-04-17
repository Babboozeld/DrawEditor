package draweditor.visitors;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.AbstractFigure;
import draweditor.figures.BasisFigure;
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
        for (int i = figures.size() - 1; i >= 0; i--) {
            figures.get(i).accept(this);
        }
    }

    @Override
    public void visit(BasisFigure basisFigure) {
        basisFigure.drawStrat.doOperation(basisFigure, this.g);
    }

    @Override
    public void visit(RectangleFigure rectangleFigure) {
        // https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness
        // Graphics2D g2 = (Graphics2D)g;
        // g2.setStroke(new BasicStroke(7));

        // this.g.drawRect(rectangleFigure.left, rectangleFigure.top,
        // rectangleFigure.width, rectangleFigure.height);
        this.g.setColor(rectangleFigure.color);
        this.g.fillRect(rectangleFigure.left, rectangleFigure.top, rectangleFigure.width, rectangleFigure.height);
    }

    @Override
    public void visit(EllipseFigure ellipseFigure) {
        g.setColor(ellipseFigure.color);
        g.fillOval(ellipseFigure.left, ellipseFigure.top, ellipseFigure.width, ellipseFigure.height);
    }

    @Override
    public void visit(BottomTextDecorator bottomTextDecorator) {
        if (g instanceof Graphics2D && bottomTextDecorator.component instanceof AbstractFigure) {
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            Graphics2D g2 = (Graphics2D) g;
            AbstractFigure figure = (AbstractFigure) (bottomTextDecorator.component);
            g.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString(bottomTextDecorator.text,
                    figure.left + (figure.width - metrics.stringWidth(bottomTextDecorator.text)) / 2,
                    figure.top + figure.height + metrics.getHeight());
        }
    }

    @Override
    public void visit(LeftTextDecorator leftTextDecorator) {
        if (g instanceof Graphics2D && leftTextDecorator.component instanceof AbstractFigure) {
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            Graphics2D g2 = (Graphics2D) g;
            AbstractFigure figure = (AbstractFigure) (leftTextDecorator.component);
            g.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString(leftTextDecorator.text, figure.left - metrics.stringWidth(leftTextDecorator.text) - 2,
                    figure.top + (figure.height + metrics.getHeight()) / 2);
        }
    }

    @Override
    public void visit(RightTextDecorator rightTextDecorator) {
        if (g instanceof Graphics2D && rightTextDecorator.component instanceof AbstractFigure) {
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            Graphics2D g2 = (Graphics2D) g;
            AbstractFigure figure = (AbstractFigure) (rightTextDecorator.component);
            g.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString(rightTextDecorator.text, figure.left + figure.width + 2,
                    figure.top + (figure.height + metrics.getHeight()) / 2);
        }
    }

    @Override
    public void visit(TopTextDecorator topTextDecorator) {
        if (g instanceof Graphics2D && topTextDecorator.component instanceof AbstractFigure) {
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            Graphics2D g2 = (Graphics2D) g;
            AbstractFigure figure = (AbstractFigure) (topTextDecorator.component);
            g.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString(topTextDecorator.text,
                    figure.left + (figure.width - metrics.stringWidth(topTextDecorator.text)) / 2, figure.top - 2);
        }
    }
}