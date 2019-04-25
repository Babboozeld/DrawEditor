package draweditor.frame.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import draweditor.DrawEditor;
import draweditor.commands.ICommand;

public class MouseHandler extends MouseAdapter {

    private DrawEditor draweditor = DrawEditor.getInstance();

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.draweditor.activeTool != null) {
            this.draweditor.activeTool.setBeginPoint(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.draweditor.activeTool != null && this.draweditor.activeTool.beginX != e.getX() && this.draweditor.activeTool.beginY != e.getY()) {
            ICommand command = this.draweditor.activeTool.getCommand(e.getX(), e.getY(), false);
            if (command != null) this.draweditor.execute(command);
        }
    }
}