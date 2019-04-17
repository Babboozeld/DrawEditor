package draweditor.frame.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import draweditor.DrawEditor;
import draweditor.commands.DecoratorCommand;
import draweditor.decorators.AbstractDecorator;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.frame.handlers.FileHandler;

public class MenuBar extends JMenuBar implements ActionListener {

    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    public MenuBar(){
        CreateMenu();
    }

    public void CreateMenu()
    {      
        //Create the menu bar.
        //menuBar = new JMenuBar();

        // //Build the first menu.
        // menu = new JMenu("A Menu");
        // menu.setMnemonic(KeyEvent.VK_A);
        // menu.getAccessibleContext().setAccessibleDescription(
        //         "The only menu in this program that has menu items");
        // add(menu);

        // //a group of JMenuItems
        // menuItem = new JMenuItem("A text-only menu item",
        //                         KeyEvent.VK_T);
        // menuItem.setAccelerator(KeyStroke.getKeyStroke(
        //         KeyEvent.VK_1, ActionEvent.ALT_MASK));
        // menuItem.getAccessibleContext().setAccessibleDescription(
        //         "This doesn't really do anything");
        // menu.add(menuItem);

        // menuItem = new JMenuItem("Both text and icon",
        //                         new ImageIcon("images/middle.gif"));
        // menuItem.setMnemonic(KeyEvent.VK_B);
        // menu.add(menuItem);

        // menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
        // menuItem.setMnemonic(KeyEvent.VK_D);
        // menu.add(menuItem);

        // //a group of radio button menu items
        // menu.addSeparator();
        // ButtonGroup group = new ButtonGroup();
        // rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        // rbMenuItem.setSelected(true);
        // rbMenuItem.setMnemonic(KeyEvent.VK_R);
        // group.add(rbMenuItem);
        // menu.add(rbMenuItem);

        // rbMenuItem = new JRadioButtonMenuItem("Another one");
        // rbMenuItem.setMnemonic(KeyEvent.VK_O);
        // group.add(rbMenuItem);
        // menu.add(rbMenuItem);

        // //a group of check box menu items
        // menu.addSeparator();
        // cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        // cbMenuItem.setMnemonic(KeyEvent.VK_C);
        // menu.add(cbMenuItem);

        // cbMenuItem = new JCheckBoxMenuItem("Another one");
        // cbMenuItem.setMnemonic(KeyEvent.VK_H);
        // menu.add(cbMenuItem);

        // //a submenu
        // menu.addSeparator();
        // submenu = new JMenu("A submenu");
        // submenu.setMnemonic(KeyEvent.VK_S);

        // menuItem = new JMenuItem("An item in the submenu");
        // menuItem.setAccelerator(KeyStroke.getKeyStroke(
        //         KeyEvent.VK_2, ActionEvent.ALT_MASK));
        // submenu.add(menuItem);

        // menuItem = new JMenuItem("Another item");
        // submenu.add(menuItem);
        // menu.add(submenu);

        // //Build second menu in the menu bar.
        // menu = new JMenu("Another Menu");
        // menu.setMnemonic(KeyEvent.VK_N);
        // menu.getAccessibleContext().setAccessibleDescription(
        //         "This menu does nothing");
        // add(menu);

        /* menu for decorators */
        JMenu menu = new JMenu("Decorators");
        menu.setMnemonic(KeyEvent.VK_C);
        menu.getAccessibleContext().setAccessibleDescription(
        "This menu is for adding decorators");
        add(menu);

        JMenuItem menuItem = new JMenuItem("add BottomTextDecorator");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("add LeftTextDecorator");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("add RightTextDecorator");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("add TopTextDecorator");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        /* menu for saving */
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_C);
        menu.getAccessibleContext().setAccessibleDescription(
        "This menu is for saving");
        
        add(menu);

        menuItem = new JMenuItem("Save");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menuItem.addActionListener((ActionEvent e) -> FileHandler.SaveFile(DrawEditor.getInstance().getFigures(), "../save.txt"));
        menu.add(menuItem);

        menuItem = new JMenuItem("Load");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menuItem.addActionListener((ActionEvent e) -> DrawEditor.getInstance().loadedFigures(FileHandler.ReadFile("../save.txt")));
        menu.add(menuItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractDecorator decorator = null;
        switch (((JMenuItem)(e.getSource())).getText()) {
            case "add BottomTextDecorator":
                decorator = new BottomTextDecorator(DrawEditor.getInstance().activeFigure);
                break;
            case "add LeftTextDecorator":
                decorator = new LeftTextDecorator(DrawEditor.getInstance().activeFigure);
                break;
            case "add RightTextDecorator":
                decorator = new RightTextDecorator(DrawEditor.getInstance().activeFigure);
                break;
            case "add TopTextDecorator":
                decorator = new TopTextDecorator(DrawEditor.getInstance().activeFigure);
                break;
        }
        if (decorator != null) {
            if (decorator.correctComponent()) DrawEditor.getInstance().execute(new DecoratorCommand(decorator));
        }
    }
}