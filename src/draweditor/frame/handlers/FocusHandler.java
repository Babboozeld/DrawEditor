package draweditor.frame.handlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import draweditor.DrawEditor;

public class FocusHandler implements FocusListener {

    private DrawEditor draweditor = DrawEditor.getInstance();

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        draweditor.requestFocus();
    }
}