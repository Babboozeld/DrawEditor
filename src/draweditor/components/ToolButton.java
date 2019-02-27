package draweditor.components;

import java.awt.Dimension;

import javax.swing.JToggleButton;

import draweditor.tools.ITool;

public class ToolButton extends JToggleButton{
    private ITool tool;

    public ToolButton(ITool tool) {
        super(tool.toolIcon);
        //tool.toonIcon.getImage();
        this.tool = tool;
        this.setPreferredSize(new Dimension(40, 40));
    }

    public ITool GetTool() {
        return this.tool;
    }
}