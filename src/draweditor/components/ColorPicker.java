package draweditor.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.*;

/* ColorChooserDemo.java requires no other files. */
public class ColorPicker extends JPanel implements ChangeListener 
{
    protected JColorChooser tcc;
    protected JLabel banner;

    public ColorPicker() {
        super(new BorderLayout());

        //Set up color chooser for setting text color
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

        AbstractColorChooserPanel[] newPanels = new AbstractColorChooserPanel[1];
        newPanels[0] = findPanel(tcc, "javax.swing.colorchooser.DefaultSwatchChooserPanel");
        tcc.setChooserPanels(newPanels);

        add(tcc, BorderLayout.PAGE_END);
    }

    public static AbstractColorChooserPanel findPanel(JColorChooser tcc, String name) 
    {
        AbstractColorChooserPanel[] panels = tcc.getChooserPanels();
        for (int i = 0; i < panels.length; i++) 
        {
            String clsName = panels[i].getClass().getName();
            if (clsName.equals(name)) 
            {
                return panels[i];
            }
        }
        return null;
    }

    public void stateChanged(ChangeEvent e) {}
}