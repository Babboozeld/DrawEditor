package draweditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import draweditor.figures.AbstractFigure;
import draweditor.visitors.IComponentVisitor;

public class EllipseFigure extends AbstractFigure {

    private int left;
    private int top;
    private int width;
    private int height;
    private Color color;

    public EllipseFigure(int left, int top, int width, int height, Color color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public List<String> Serialize() {
        List<String> serialized = new ArrayList<String>();
        serialized.add(Integer.toString(this.left));
        serialized.add(Integer.toString(this.top));
        serialized.add(Integer.toString(this.width));
        serialized.add(Integer.toString(this.height));
        serialized.add(this.color.toString());
        return serialized;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(left, top, width, height);
        // g.fillOval(left, top, width, height);
    }

    public void move(int dx, int dy) {
        left += dx;
        top += dy;
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }
}