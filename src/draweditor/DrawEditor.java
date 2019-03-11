package draweditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import draweditor.commands.ICommand;
import draweditor.commands.IReversibleCommand;
import draweditor.frame.components.Canvas;
import draweditor.frame.components.ColorPicker;
import draweditor.frame.components.MenuBar;
import draweditor.frame.components.ShapeList;
import draweditor.frame.components.ToolButton;
import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.frame.handlers.FocusHandler;
import draweditor.frame.handlers.KeyHandler;
import draweditor.frame.handlers.MouseHandler;
import draweditor.frame.handlers.MouseMotionHandler;
import draweditor.tools.AbstractTool;
import draweditor.tools.EllipseTool;
import draweditor.tools.LineTool;
import draweditor.tools.MoveTool;
import draweditor.tools.RectangleTool;
import draweditor.tools.TriangleTool;

/*  note/to do:
    - make sure first group is not removed/ is there always
    - vgm moet je alle figures andersom in tree displayer (hoe dieper naar beneden hoe hoger op in het canvas het komt)
    - the implemented active system isn't great (esspesialy in how it is used in commands)
    - add a cap to how large te history list should be.
    - if group is selected and item draw edit it to the back of list in group
    to do:
    -
    could:
    - load error popup in filehandler
*/

public class DrawEditor extends JFrame {
    // public String path;
    // public bool filechange;
    private IReversibleCommand lastExecutedCommand;
    private List<IReversibleCommand> commandsHistory = new ArrayList<IReversibleCommand>();

    public AbstractTool activeTool;
    private Canvas drawCanvas = new Canvas();

    private Group figures = new Group();
    public IComponent activeFigure;
    public Group activeGroup;
    public int activePosision;
    
    public static void main(String[] args) throws Exception {
        System.out.println("Main started:");
        DrawEditor d = DrawEditor.getInstance();
        d.setVisible(true);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.addKeyListener(new KeyHandler());
        d.addFocusListener(new FocusHandler());
        d.drawCanvas.addMouseListener(new MouseHandler());
        d.drawCanvas.addMouseMotionListener(new MouseMotionHandler());
    }

    private static String TITLE = "DrawEditor";

    // https://docs.oracle.com/javase/tutorial/uiswing/painting/step3.html
    // https://stackoverflow.com/questions/17922443/drawing-canvas-on-jframe
    // https://docs.oracle.com/javase/tutorial/uiswing/layout/index.html

    private static DrawEditor instance = new DrawEditor();
    public static DrawEditor getInstance(){
        return instance;
    }

    private DrawEditor() {
        super();
        // main frame
        setTitle(TITLE);
        setSize(300, 200); // default size is 0,0
        setPreferredSize(new Dimension(1100, 600));
        setLocation(10, 10); // default is 0,0 (top left corner)
        getContentPane().setLayout(new BorderLayout());

        // menu
        MenuBar makeMenu = new MenuBar();
        setJMenuBar(makeMenu);

        // high level panels
        JPanel main = new JPanel(new BorderLayout());
        JPanel mainOptions = new JPanel(new BorderLayout());
        // mainOptions.setPreferredSize(new Dimension(100, 60));
        main.add(mainOptions, BorderLayout.PAGE_START);
        mainOptions.setBackground(new Color(235, 235, 235));
        mainOptions.add(this.getButtonGroup(), BorderLayout.LINE_START);
        main.add(drawCanvas);
        getContentPane().add(main, BorderLayout.CENTER);
        main.setBackground(Color.WHITE);

        JPanel leftBar = new JPanel(new BorderLayout());
        getContentPane().add(leftBar, BorderLayout.LINE_END);
        ColorPicker colorPickerComponent = new ColorPicker();
        colorPickerComponent.setOpaque(true); // content panes must be opaque
        colorPickerComponent.setSize(new Dimension(300, 200));
        // JPanel contentTree = new JPanel();
        JComponent newContentPane = new ShapeList(this);
        newContentPane.setOpaque(true);
        leftBar.add(colorPickerComponent, BorderLayout.PAGE_START);
        leftBar.add(newContentPane);
        leftBar.setBackground(Color.RED);

        // make result
        activeGroup = getFigures();
        activeFigure = (IComponent)activeGroup;
        activePosision = 0;
        pack();
        setFocusable(true);
    }

    private JComponent getButtonGroup() {
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        ActionListener listener = actionEvent -> { 
            activeTool = ((ToolButton)actionEvent.getSource()).GetTool();
            System.out.println(activeTool);
        }; 
        //add all tools for togglebuttons panel:
        List<AbstractTool> buttonTools = new ArrayList<AbstractTool>();
        buttonTools.add(new RectangleTool());
        buttonTools.add(new EllipseTool());
        buttonTools.add(new TriangleTool());
        buttonTools.add(new LineTool());
        buttonTools.add(new MoveTool());
        //create and add all togglebuttons to panel:
        for (AbstractTool tool : buttonTools) {
            JToggleButton b = new ToolButton(tool);
            b.addActionListener(listener);
            buttonGroup.add(b);
            buttonPanel.add(b);
        }
        ((JToggleButton)buttonPanel.getComponent(0)).doClick(); //hier voor zou mischien een check gedaan moeten worden
        return buttonPanel;
    }

    public Group getFigures() {
        return figures;
    }

    public void execute(ICommand command){
        command.execute(this);
        if (command instanceof IReversibleCommand) { //add a cap to how large te list should be.
            if (lastExecutedCommand != null){
                int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
                if (lastExecutedCommandIndex != commandsHistory.size() - 1) {
                    //commandsHistory.subList(commandsHistory.size() - lastExecutedCommandIndex, commandsHistory.size()).clear(); //index could be wrong.
                    commandsHistory = commandsHistory.subList(0, commandsHistory.size() - lastExecutedCommandIndex -1);
                }
            }
            commandsHistory.add((IReversibleCommand)command);
            lastExecutedCommand = (IReversibleCommand)command;
        }
    }
    //ctrl + z
    public void unexecute(){
        if (lastExecutedCommand != null){
            lastExecutedCommand.unexecute(this);
            int lastExecutedCommandIndex = commandsHistory.indexOf(lastExecutedCommand);
            if (lastExecutedCommandIndex > 0){
                lastExecutedCommand = commandsHistory.get(lastExecutedCommandIndex - 1);
            } else {
                lastExecutedCommand = null;
            }
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

    public void redraw() {
        drawCanvas.repaint();
    }

    public void setActiveFigure(IComponent figure) {
        activeFigure = figure;
        activePosision = activeGroup.getFigures().indexOf(figure);
        if (activePosision == -1) {
            activeGroup = getFigures().findGroup(figure);
            if (activeGroup != null) {
                activePosision = activeGroup.getFigures().indexOf(figure);
            } else if (figure == getFigures()) {
                activeGroup = (Group)figure;
                activePosision = 0;
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