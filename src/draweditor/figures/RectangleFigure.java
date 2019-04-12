package draweditor.figures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import draweditor.visitors.IComponentVisitor;

public class RectangleFigure extends AbstractFigure {

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

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }
}