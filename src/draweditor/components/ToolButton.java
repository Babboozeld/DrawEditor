package draweditor.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JToggleButton;

import draweditor.tools.ITool;

public class ToolButton extends JToggleButton{
    private ITool tool;

    public ToolButton(ITool tool) {
        super(tool.toolIcon);
        //tool.toonIcon.getImage();
        this.tool = tool;
        //this.setSize(new Dimension(100, 100));
        this.setPreferredSize(new Dimension(40, 40));
    }

    public ITool GetTool() {
        return this.tool;
    }
}