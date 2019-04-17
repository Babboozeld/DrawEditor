package draweditor.components;

import draweditor.visitors.IComponentVisitor;

public interface IComponent {
    //should include constructor arguments in the same order
    public IComponent findSelected(int x, int y);
    public void accept(IComponentVisitor iComponentVisitor);
}