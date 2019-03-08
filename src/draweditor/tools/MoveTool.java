package draweditor.tools;

import draweditor.commands.ICommand;
import draweditor.commands.MoveCommand;
import draweditor.commands.TempMoveCommand;

public class MoveTool extends AbstractTool {
    public MoveTool() {
        this.toolIcon = createImageIcon("/images/move.png", "move");
    }

    @Override
    public ICommand getCommand(int x, int y, boolean temporary) {
        return temporary ? new TempMoveCommand(x - beginX, y - beginY) : new MoveCommand(x - beginX, y - beginY);
    }
}