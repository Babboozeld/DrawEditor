package draweditor.tools;

import draweditor.commands.ICommand;

public class LineTool extends AbstractTool {

    public LineTool() {
        this.toolIcon = createImageIcon("/images/line.png", "line");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        return null; // new EllipseFigure(beginX, beginY, x, y);
    }
}