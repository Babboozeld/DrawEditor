package draweditor.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.*;

import draweditor.DrawEditor;

public class ColorPicker extends JPanel implements ChangeListener 
{
    private JColorChooser tcc;

    public ColorPicker() {
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

    //https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html
    @Override
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        if (newColor != null) {
            DrawEditor.getInstance().activeTool.setColor(newColor);
            System.out.println(newColor);
        }
    }
}