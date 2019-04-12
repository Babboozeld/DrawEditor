package draweditor.tools;

import java.awt.Color;

import draweditor.DrawEditor;
import draweditor.commands.ICommand;
import draweditor.components.IComponent;

public class SelectTool extends AbstractTool {

    private IComponent figure = null;

    public SelectTool() {
        this.toolIcon = createImageIcon("/images/move.png", "move");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        // if (!temporary && figure != null){
        //     figure = null;
        // } 
        return null;
    }

    @Override
    public void setBeginPoint(int x, int y) {
        DrawEditor draweditor = DrawEditor.getInstance();
        figure = draweditor.getFigures().findSelected(x, y);
        if (figure != null) draweditor.setActiveFigure(figure);
	}

    @Override
	public void setColor(Color color) {
        //if (figure != null) 
        this.color = color;
    }
}