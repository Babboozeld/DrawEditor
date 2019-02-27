package draweditor;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import draweditor.figures.Group;
import draweditor.figures.IFigure;
import draweditor.tools.ITool;
import draweditor.tools.ToolEllipse;
import draweditor.tools.ToolLine;
import draweditor.tools.ToolRectangle;
import draweditor.tools.ToolTriangle;
import draweditor.commands.ICommand;
import draweditor.commands.IReversibleCommand;
import draweditor.components.ColorPicker;
import draweditor.components.MenuBar;
import draweditor.components.ToolButton;

/*  note/to do:
    - make sure first group is not removed/ is there always
    - vgm moet je alle figures andersom in tree displayer (hoe dieper naar beneden hoe hoger op in het canvas het komt)
    - redraw() is temp placeholder
    - the implemented active system isn't great (esspesialy in how it is used in commands)
    - add a cap to how large te history list should be.
    - if group is selected and item draw edit it to the back of list in group
    to do:
    -
    could:
    - load error in filehandler
*/

public class DrawEditor extends JFrame {
    // public String path;
    // public bool filechange;
    private IReversibleCommand lastExecutedCommand;
    private List<IReversibleCommand> commandsHistory = new ArrayList<IReversibleCommand>();

    private Group figures;
    public ITool activeTool;

    public IFigure activeFigure;
    public Group activeGroup;
    public int activePosision;

    public static void main(String[] args) throws Exception {
        System.out.println("Main started:");
        JFrame f = new DrawEditor();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static String TITLE = "DrawEditor";

    //https://stackoverflow.com/questions/17922443/drawing-canvas-on-jframe
    //https://docs.oracle.com/javase/tutorial/uiswing/layout/index.html

    private DrawEditor() {
        super();
        //main frame
        setTitle(TITLE);
        setSize(300, 200); // default size is 0,0
        setPreferredSize(new Dimension(1100, 600));
        setLocation(10, 10); // default is 0,0 (top left corner)
        getContentPane().setLayout(new BorderLayout());

        //menu
        MenuBar makeMenu = new MenuBar();
        setJMenuBar(makeMenu);

        //high level pannels
        JPanel main = new JPanel(new BorderLayout());
        JPanel mainOptions = new JPanel();
        // mainOptions.setPreferredSize(new Dimension(100, 60));
        main.add(mainOptions, BorderLayout.PAGE_START);
        mainOptions.setBackground(Color.YELLOW);
        mainOptions.add(this.getButtonGroup(), BorderLayout.LINE_START);
        Canvas drawCanvas = new Canvas();
        main.add(drawCanvas);
        getContentPane().add(main, BorderLayout.CENTER);
        main.setBackground(Color.BLUE);

        JPanel leftBar = new JPanel(new BorderLayout());
        getContentPane().add(leftBar, BorderLayout.LINE_END);
        JComponent colorPickerComponent = new ColorPicker();
        colorPickerComponent.setOpaque(true); // content panes must be opaque
        colorPickerComponent.setSize(new Dimension(300, 200));
        JPanel contentTree = new JPanel();
        JScrollPane contentPlane = new JScrollPane(contentTree);
        leftBar.add(colorPickerComponent, BorderLayout.PAGE_START);
        leftBar.add(contentPlane);
        leftBar.setBackground(Color.RED);
        
        //make result
        pack();
    }
    
    public JComponent getButtonGroup() {   
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel buttonPanel = new JPanel();
        ActionListener listener = actionEvent -> { 
            activeTool = ((ToolButton)actionEvent.getSource()).GetTool();
            System.out.println(activeTool);
        }; 
        //add all tools for togglebuttons panel:
        List<ITool> buttonTools = new ArrayList<ITool>();
        buttonTools.add(new ToolRectangle());
        buttonTools.add(new ToolEllipse());
        buttonTools.add(new ToolTriangle());
        buttonTools.add(new ToolLine());
        //create and add all togglebuttons to panel:
        for (ITool tool : buttonTools) {
            JToggleButton b = new ToolButton(tool);
            b.addActionListener(listener);
            buttonGroup.add(b);
            buttonPanel.add(b);
        }
        return buttonPanel;
    }
    
    public void execute(ICommand command){
        command.execute(this);
        if (command instanceof IReversibleCommand){ //add a cap to how large te list should be.
            if (lastExecutedCommand != null){
                int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
                if (lastExecutedCommandIndex != commandsHistory.size() - 1) {
                    commandsHistory.subList(commandsHistory.size() - lastExecutedCommandIndex, commandsHistory.size()).clear(); //index could be wrong.
                }
            }
            commandsHistory.add((IReversibleCommand)command);
            lastExecutedCommand = (IReversibleCommand)command;
        }
    }
    //ctrl + shift + z
    public void reverseExecute(){
        int lastExecutedCommandIndex = lastExecutedCommand == null ? 0 : commandsHistory.indexOf(lastExecutedCommand);
        if (lastExecutedCommandIndex < commandsHistory.size() - 1) {
            lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex + 1);
            lastExecutedCommand.execute(this);
        }
    }
    //ctrl + z
    public void unexecute(){
        if (lastExecutedCommand != null){
            lastExecutedCommand.unexecute(this);
            int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
            if (lastExecutedCommandIndex != 0){
                lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex - 1);
            } else {
                lastExecutedCommand = null;
            }
        }
    }

    public void redraw() {
        
    }

    public void setActiveFigure(IFigure figure) {
        activeFigure = figure;
        activePosision = activeGroup.getFigures().indexOf(figure);
        if (activePosision == -1) {
            activeGroup = figures.findGroup(figure);
            if (activeGroup != null){
                activePosision = activeGroup.getFigures().indexOf(figure);
            } else {
                throw new Error("No group found. (source: DrawEditor setActiveFigure())");
            }
        }
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