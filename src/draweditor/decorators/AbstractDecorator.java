package draweditor.decorators;

import draweditor.components.IComponent;

public abstract class AbstractDecorator implements IComponent {

    public IComponent nextComponent;
    public IComponent component;

    public abstract boolean correctComponent();

    @Override
    public IComponent findSelected(int x, int y) {
        return nextComponent.findSelected(x, y) != null ? this : null;
    }
} 