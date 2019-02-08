package draweditor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import draweditor.frames.MainDrawFrame;
import draweditor.figures.IFigure;
import draweditor.tools.ITool;
import draweditor.commands.ICommand;

public class DrawEditor {
    public List<ICommand> commandsHistory = new ArrayList<ICommand>();
    public List<IFigure> figures = new ArrayList<IFigure>();
    public List<ITool> tools = new ArrayList<ITool>();
    public static void main(String[] args) throws Exception {
        System.out.println("Main started:");
        JFrame f = new MainDrawFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
//https://stackoverflow.com/questions/147181/how-can-i-convert-my-java-program-to-an-exe-file
/*
Grammatica:  
file      = group 
group     = "group" count {figure} 
figure    = group | rectangle | ellipse | ornament 
rectangle = "rectangle" left top width height 
ellipse   = "ellipse" left top width height 
ornament  = "ornament" position string figure 
position  = "top" | "bottom" | "left" | "right" 
count, left, top, width, height = int 
 
*/