package draweditor.commands;

import draweditor.DrawEditor;

public interface IReversibleCommand extends ICommand {
    public void unexecute(DrawEditor draweditor);
}