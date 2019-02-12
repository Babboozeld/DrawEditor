package draweditor.commands;

import java.awt.Graphics;

import draweditor.figures.IDrawable;

public class DrawCommand implements ICommand {

    public IDrawable figure;

    public DrawCommand(IDrawable figure){
        this.figure = figure;
    }

    public void execute() {
        //figure.draw(Graphics g);
    }

    public void unexecute() {
        //this.delete();
    }

}