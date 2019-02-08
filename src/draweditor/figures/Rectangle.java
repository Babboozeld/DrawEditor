package draweditor.figures;

import java.awt.Graphics;
import java.util.List;

public class Rectangle implements IFigure, IDrawable {

    private int left;
    private int top;
    private int width;
    private int height;

    public Rectangle(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public List<String> Serialize() {
        return null;
    }

    public void draw(Graphics g) {
        g.drawRect(left, top, width, height);
    }

}