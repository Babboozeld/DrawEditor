package draweditor.components;

import java.util.List;

import draweditor.visitors.IComponentVisitor;

public interface IComponent {
    //should include constructor arguments in the same order
    public List<String> Serialize();
    public IComponent findSelected(int x, int y);
    public void accept(IComponentVisitor iComponentVisitor);
}