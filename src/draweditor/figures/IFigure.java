package draweditor.figures;

import java.awt.Graphics;
import java.util.List;

public interface IFigure {
    public List<String> Serialize();
    public void draw(Graphics g);
    public void move(int dx, int dy);
}