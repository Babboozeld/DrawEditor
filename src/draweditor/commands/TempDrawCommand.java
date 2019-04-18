package draweditor.commands;

import java.util.List;

import javax.swing.SwingUtilities;

import draweditor.DrawEditor;
import draweditor.components.IComponent;

public class TempDrawCommand implements ICommand {

    private IComponent figure;

    public TempDrawCommand(IComponent figure){
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        List<IComponent> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision, this.figure);
        draweditor.redraw();  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                figures.remove(figure);  
            }
        });   
    }
}