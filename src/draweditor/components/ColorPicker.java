package draweditor.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.*;

import draweditor.DrawEditor;

public class ColorPicker extends JPanel implements ChangeListener 
{
    public JColorChooser tcc;
    public Color color;
    public DrawEditor DE;

    public ColorPicker(DrawEditor DE) {
        super(new BorderLayout());
        //Set up color chooser for setting text color
        tcc = new JColorChooser(Color.BLACK);
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

        AbstractColorChooserPanel[] newPanels = new AbstractColorChooserPanel[1];
        newPanels[0] = findPanel(tcc, "javax.swing.colorchooser.DefaultSwatchChooserPanel");
        tcc.setChooserPanels(newPanels);
             
        tcc.setPreviewPanel(new JPanel());
        add(tcc, BorderLayout.PAGE_START);

        //https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html
        this.DE = DE;
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

    @Override
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        if (newColor != null) {
            this.DE.activeTool.setColor(newColor);
            System.out.println(newColor);
        }
    }
}