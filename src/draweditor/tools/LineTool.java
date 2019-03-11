package draweditor.tools;

import java.awt.Color;

import draweditor.commands.ICommand;

public class LineTool extends AbstractTool {
    public LineTool() {
        this.toolIcon = createImageIcon("/images/line.png", "line");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary, Color color) {
        return null; // new EllipseFigure(beginX, beginY, x, y);
    }
}