package draweditor.commands;

import draweditor.DrawEditor;
import draweditor.components.IComponent;
import draweditor.decorators.AbstractDecorator;

public class DecoratorCommand implements ICommand, IReversibleCommand {

    private IComponent decorator;

    public DecoratorCommand(IComponent decorator) {
        this.decorator = decorator;
    }

    public void execute(DrawEditor draweditor) {
        draweditor.activeGroup.getFigures().set(draweditor.activePosision, decorator);
        draweditor.setActiveFigure(decorator);
        draweditor.redraw();
    }

    public void unexecute(DrawEditor draweditor) {
        draweditor.setActiveFigure(decorator);
        draweditor.activeGroup.getFigures().set(draweditor.activePosision, ((AbstractDecorator)decorator).nextComponent);
        draweditor.redraw();
    }
}