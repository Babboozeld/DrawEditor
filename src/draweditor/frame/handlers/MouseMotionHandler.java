package draweditor.frame.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import draweditor.DrawEditor;

public class MouseMotionHandler extends MouseAdapter {

    private DrawEditor d = DrawEditor.getInstance();

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.d.activeTool != null && this.d.activeTool.beginX != e.getX() && this.d.activeTool.beginY != e.getY()) {
            this.d.execute(this.d.activeTool.getCommand(e.getX(), e.getY(), true));
        }
    }
}