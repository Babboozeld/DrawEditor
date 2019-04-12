package draweditor.frame.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import draweditor.DrawEditor;
import draweditor.commands.ICommand;

public class MouseHandler extends MouseAdapter {

    private DrawEditor d = DrawEditor.getInstance();

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.d.activeTool != null) {
            this.d.activeTool.setBeginPoint(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.d.activeTool != null && this.d.activeTool.beginX != e.getX() && this.d.activeTool.beginY != e.getY()) {
            ICommand command = this.d.activeTool.getCommand(e.getX(), e.getY(), false);
            if (command != null) this.d.execute(command);
        }
    }
}