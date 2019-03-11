package draweditor.handlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import draweditor.DrawEditor;

public class FocusHandler implements FocusListener {

    private DrawEditor d = DrawEditor.getInstance();

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        d.requestFocus();
    }

}