package draweditor.frames;

import javax.swing.JFrame;

import draweditor.components.MenuBar;
	
public class MainDrawFrame extends JFrame {
    private static String TITLE = "DrawEditor";
    MenuBar makeMenu;

    public MainDrawFrame() {
        super();
        setTitle(TITLE);
        setSize(300,200); // default size is 0,0
        setLocation(10,200); // default is 0,0 (top left corner)

        makeMenu = new MenuBar();
        setJMenuBar(makeMenu);
    }
}