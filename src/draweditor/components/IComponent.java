package draweditor.components;

import java.util.List;

import draweditor.visitors.IComponentVisitor;

public interface IComponent {
    //should include constructor arguments in the same order
    public List<String> Serialize();
    //public void draw(Graphics g);
    //public void move(int dx, int dy);
    public void accept(IComponentVisitor iComponentVisitor);
}