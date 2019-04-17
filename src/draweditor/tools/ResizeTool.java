package draweditor.tools;

import draweditor.commands.ICommand;
import draweditor.commands.ResizeCommand;
import draweditor.commands.TempResizeCommand;

public class ResizeTool extends AbstractTool {

    public ResizeTool() {
        this.toolIcon = createImageIcon("/images/ellipse.png", "ellipse");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        return temporary ? new TempResizeCommand(x - beginX, y - beginY) : new ResizeCommand(x - beginX, y - beginY);
    }
}