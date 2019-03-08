package draweditor.commands;

import java.util.List;

import javax.swing.SwingUtilities;

import draweditor.DrawEditor;
import draweditor.figures.IFigure;

public class TempDrawCommand implements ICommand {

    public IFigure figure;

    public TempDrawCommand(IFigure figure){
        this.figure = figure;
    }

    public void execute(DrawEditor draweditor) {
        List<IFigure> figures = draweditor.activeGroup.getFigures();
        figures.add(draweditor.activePosision, this.figure);
        draweditor.redraw();  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                figures.remove(figure);  
            }
        });   
    }

}