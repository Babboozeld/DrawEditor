package draweditor.components;

import java.awt.Dimension;

import javax.swing.JToggleButton;

import draweditor.tools.AbstractTool;

public class ToolButton extends JToggleButton{
    private AbstractTool tool;

    public ToolButton(AbstractTool tool) {
        super(tool.toolIcon);
        //tool.toonIcon.getImage();
        this.tool = tool;
        this.setPreferredSize(new Dimension(40, 40));
    }

    public AbstractTool GetTool() {
        return this.tool;
    }
}