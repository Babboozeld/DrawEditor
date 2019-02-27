package draweditor.components;

import javax.swing.JToggleButton;

import draweditor.tools.ITool;

public class ToolButton extends JToggleButton{
    private ITool tool;

    public ToolButton(ITool tool) {
        super(tool.toolName, false);
        this.tool = tool;
    }

    public ITool GetTool() {
        return this.tool;
    }
}