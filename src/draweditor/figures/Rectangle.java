package draweditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

public class Rectangle implements IFigure {

    private int left;
    private int top;
    private int width;
    private int height;
    private Color color;

    public Rectangle(int left, int top, int width, int height, Color color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public List<String> Serialize() {
        return null; //Arrays.asList(left, top, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(left, top, width, height);
    }

    public void move(int dx, int dy) {
        left += dx;
        top += dy;
    }

}