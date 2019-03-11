package draweditor.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.*;

import draweditor.DrawEditor;

/* ColorChooserDemo.java requires no other files. */
public class ColorPicker extends JPanel implements ChangeListener 
{
    public JColorChooser tcc;
    public Color color;
    public DrawEditor DE;

    public ColorPicker(DrawEditor DE) {
        super(new BorderLayout());

        //Set up color chooser for setting text color
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

        AbstractColorChooserPanel[] newPanels = new AbstractColorChooserPanel[1];
        newPanels[0] = findPanel(tcc, "javax.swing.colorchooser.DefaultSwatchChooserPanel");
        tcc.setChooserPanels(newPanels);
             
        tcc.setPreviewPanel(new JPanel());
        add(tcc, BorderLayout.PAGE_START);
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

    public Color GetColor()
    {
        color = tcc.getColor();
        System.out.println(color);
        return color;
    }

    public void stateChanged(ChangeEvent e) {
        // int i = 0;
        // color = GetColor(color);
        // DE.activeFigure;
    }
}