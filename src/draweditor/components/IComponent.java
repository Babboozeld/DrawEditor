package draweditor.components;

import java.awt.Graphics;
import java.util.List;

public interface IComponent {
    //should include constructor arguments in the same order
    public List<String> Serialize();
    public void draw(Graphics g);
    public void move(int dx, int dy);
}