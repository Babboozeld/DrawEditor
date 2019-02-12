package draweditor.frames;

import javax.swing.*;
import java.awt.*;

import draweditor.components.ColorPicker;
import draweditor.components.MenuBar;
	
public class MainDrawFrame extends JFrame {
    private static String TITLE = "DrawEditor";
    MenuBar makeMenu;

    public MainDrawFrame() {
        super();
        setTitle(TITLE);
        setSize(300,200); // default size is 0,0
        setLocation(10,200); // default is 0,0 (top left corner)

        JComponent newContentPane = new ColorPicker();
        newContentPane.setOpaque(true); //content panes must be opaque
        getContentPane().add(newContentPane, BorderLayout.EAST);
        getContentPane().setLayout(new BorderLayout());
        setContentPane(newContentPane);
        
        makeMenu = new MenuBar();
        setJMenuBar(makeMenu);
    }
}