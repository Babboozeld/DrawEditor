package draweditor.tools;

import java.awt.Color;

import draweditor.DrawEditor;
import draweditor.commands.ColorCommand;
import draweditor.commands.ICommand;
import draweditor.components.IComponent;

public class BucketTool extends AbstractTool {

    private IComponent figure = null;

    public BucketTool() {
        this.toolIcon = createImageIcon("/images/bucket.png", "bucket");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        return null;
    }

    @Override
    public void setBeginPoint(int x, int y) {
        DrawEditor draweditor = DrawEditor.getInstance();
        figure = draweditor.getFigures().findSelected(x, y);
        if (figure != null) draweditor.execute(new ColorCommand(figure, color));
        
	}

    @Override
	public void setColor(Color color) {
        this.color = color;
    }
}