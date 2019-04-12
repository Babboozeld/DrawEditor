package draweditor.frame.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import draweditor.DrawEditor;
import draweditor.visitors.IComponentDrawVisitor;

public class Canvas extends JPanel {

    public Canvas(){
        super();
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        DrawEditor.getInstance().getFigures().accept(new IComponentDrawVisitor(g)); 
    }
}