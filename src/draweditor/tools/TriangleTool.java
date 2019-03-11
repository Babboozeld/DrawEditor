package draweditor.tools;

import java.awt.Color;

import draweditor.commands.ICommand;

public class TriangleTool extends AbstractTool {
    public TriangleTool() {
        this.toolIcon = createImageIcon("/images/triangle.png", "triangle");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary, Color color) {
        return null; // new RectangleFigure(beginX, beginY, x, y, Color.RED);
    }
}