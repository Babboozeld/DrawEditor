package draweditor.commands;

import draweditor.DrawEditor;

public interface ICommand {
    public void execute(DrawEditor draweditor);
}