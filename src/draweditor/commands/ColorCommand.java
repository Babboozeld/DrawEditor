package draweditor.commands;

import java.awt.Color;

import draweditor.DrawEditor;
import draweditor.components.IComponent;
import draweditor.figures.AbstractFigure;
import draweditor.visitors.IComponentColorVisitor;

public class ColorCommand implements ICommand, IReversibleCommand {

    private IComponent figure;
    private Color newcolor;
    private Color oldcolor;

    public ColorCommand(IComponent figure, Color color) {
        this.figure = figure;
        this.newcolor = color;
        if (figure instanceof AbstractFigure) {
            oldcolor = ((AbstractFigure)figure).color;
        } else {
            // its a group. fix for now is default of black.
            oldcolor = Color.BLACK;
        }
    }

    public void execute(DrawEditor draweditor) {
        figure.accept(new IComponentColorVisitor(newcolor));
        draweditor.redraw();
    }

    public void unexecute(DrawEditor draweditor) {
        figure.accept(new IComponentColorVisitor(oldcolor));
        draweditor.redraw();
    }
}