package draweditor.tools;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import draweditor.figures.IFigure;

public abstract class AbstractTool {
    public Icon toolIcon;
    public int beginX;
    public int beginY;

    public Icon createImageIcon(String path, String description) 
    {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) 
        {
            return new ImageIcon(imgURL, description);
        } 
        else 
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	public abstract IFigure getFigure(int x, int y);

	public void setBeginPoint(int x, int y) {
        beginX = x;
        beginY = y;
	}  
}

//https://icons8.com/icon/set/line/material