package draweditor.frame.handlers;

import draweditor.components.Group;
import draweditor.components.IComponent;

public class PendingGroup {
    private Group group;
    private int empty;

    public PendingGroup(Group group, int emptySpaces) {
        this.group = group;
        this.empty = emptySpaces;
    }

    public Group getGroup() {
        return group;
    }

    public int getValue() {
        return empty;
    }

    public void fillGroup(IComponent component) {
        group.add(component);
        empty--;
    }
}