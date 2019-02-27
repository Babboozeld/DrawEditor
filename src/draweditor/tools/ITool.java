package draweditor.tools;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class ITool {
    public Icon toolIcon;

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
}

//https://icons8.com/icon/set/line/material