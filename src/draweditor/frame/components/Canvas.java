package draweditor.frame.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import draweditor.components.Group;

public class Canvas extends JPanel {

    private static Canvas instance = new Canvas();

    private Canvas(){
        super();
    }
 
    public static Canvas getInstance() {
        return instance;
    }

    private Group figures = new Group();
    public Group getFigures() {
        return figures;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        figures.draw(g); 
    }

   // private IReversibleCommand lastExecutedCommand;
   // private List<IReversibleCommand> commandsHistory = new ArrayList<IReversibleCommand>();

   // private GroupFigure figures;
   // public AbstractTool activeTool;

   // public IFigure activeFigure;
   // public GroupFigure activeGroup;
   // public int activePosision;

   // public Graphics canvasGraphics;
   // public Canvas drawCanvas;
}