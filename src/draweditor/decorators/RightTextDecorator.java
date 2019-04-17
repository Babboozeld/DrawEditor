package draweditor.decorators;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.visitors.IComponentVisitor;

public class RightTextDecorator extends AbstractDecorator {

    public String text = "Right";

    public RightTextDecorator(IComponent figure) {
        nextComponent = figure;
        if (figure instanceof AbstractDecorator) {
            component = ((AbstractDecorator)figure).component;
        } else {
            component = figure;
        }
    }

    public boolean correctComponent() {
        return !(component instanceof Group);
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        nextComponent.accept(iComponentVisitor);
        iComponentVisitor.visit(this);
    }
} 