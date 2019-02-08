package draweditor.commands;

public interface IReversibleCommand extends ICommand {
    public void unexecute();
}