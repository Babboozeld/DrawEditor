package draweditor.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class RectangleFigure extends AbstractFigure {

    private int left;
    private int top;
    private int width;
    private int height;
    private Color color;

    public RectangleFigure(int left, int top, int width, int height, Color color) {
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
        // Graphics2D g2 = (Graphics2D)g; https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness
        // g2.setStroke(new BasicStroke(7));
        g.drawRect(left, top, width, height);
        //g.fillRect(left, top, width, height);
    }

    public void move(int dx, int dy) {
        left += dx;
        top += dy;
    }  
}