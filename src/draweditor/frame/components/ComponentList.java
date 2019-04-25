package draweditor.frame.components; // http://www.java2s.com/Tutorial/Java/0240__Swing/Usedraganddroptoreorderalist.htm

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import draweditor.DrawEditor;
import draweditor.components.IComponent;

public class ComponentList extends JScrollPane implements ListSelectionListener {

  private DefaultListModel<ListEntry> listmodel;
  // private JList<ListEntry> list;
  public int count = 0;

  public ComponentList(JList<ListEntry> list) {
    super(list);
    listmodel = (DefaultListModel<ListEntry>) (list.getModel());
    // this.list = list;
    list.setDragEnabled(true);
    list.setDropMode(DropMode.INSERT);
    list.setCellRenderer(new ListEntryCellRenderer());
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    // list.setSelectedIndex(0);
    list.addListSelectionListener(this);
    list.setTransferHandler(new MyListDropHandler(this));
    new MyDragListener(list);
  }

  private static ComponentList instance = new ComponentList(new JList<ListEntry>(new DefaultListModel<ListEntry>()));

  public static ComponentList getInstance() {
    return instance;
  }

  public void addItem(String figurestring, IComponent figure) {
    String path = null;
    String description = null;
    switch (figurestring) {
    case "RectangleFigure":
      path = ("src/images/rectangle.png");
      description = ("rectangle");
      break;
    case "EllipseFigure":
      path = ("src/images/ellipse.png");
      description = ("ellipse");
      break;
    case "BasicFigure":
      path = ("src/images/line.png");
      description = ("basic");
      break;
    case "Group":
      path = ("src/images/group.png");
      description = ("group");
      break;
    }
    count++;

    listmodel.addElement(new ListEntry(description + count, new ImageIcon(path), figure));
  }

  public void deleteItem(IComponent figure) {
    for (int i = 0; i < listmodel.size(); i++) {
      if (listmodel.get(i).getFigure() == figure) {
        listmodel.removeElement(listmodel.get(i));
        break;
      }
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    DrawEditor.getInstance().setActiveFigure(((ListEntry)((JList<ListEntry>)e.getSource()).getSelectedValue()).getFigure());
  }

}

class ListEntryCellRenderer extends JLabel implements ListCellRenderer<ListEntry>
{
    @Override
    public Component getListCellRendererComponent(JList<? extends ListEntry> list, ListEntry value, int index, boolean isSelected, boolean cellHasFocus) {
      
        setText(value.toString());
        setIcon(value.getIcon());
       
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
      
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
      
        return this;
    }
}

class MyDragListener implements DragSourceListener, DragGestureListener {
  
  private JList<ListEntry> list;
  private DragSource dragSource;

  public MyDragListener(JList<ListEntry> list) {
    this.list = list;
    //DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_MOVE, this);
  }

  public void dragGestureRecognized(DragGestureEvent dge) {
    StringSelection transferable = new StringSelection(Integer.toString(list.getSelectedIndex()));
    dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);
  }

  public void dragEnter(DragSourceDragEvent dsde) {
  }

  public void dragExit(DragSourceEvent dse) {
  }

  public void dragOver(DragSourceDragEvent dsde) {
  }

  public void dragDropEnd(DragSourceDropEvent dsde) {
    if (dsde.getDropSuccess()) {
      System.out.println("Succeeded");
    } else {
      System.out.println("Failed");
    }
  }

  public void dropActionChanged(DragSourceDragEvent dsde) {
  }
}

class MyListDropHandler extends TransferHandler {
  ComponentList list;

  public MyListDropHandler(ComponentList list) {
    this.list = list;
  }

  public boolean canImport(TransferHandler.TransferSupport support) {
    if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
      return false;
    }
    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
    if (dl.getIndex() == -1) {
      return false;
    } else {
      return true;
    }
  }

  public boolean importData(TransferHandler.TransferSupport support) {
    if (!canImport(support)) {
      return false;
    }

    Transferable transferable = support.getTransferable();
    //String indexString;
    try {
      /*indexString = (String)*/transferable.getTransferData(DataFlavor.stringFlavor);
    } catch (Exception e) {
      return false;
    }

    //int index = Integer.parseInt(indexString);
    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
    int dropTargetIndex = dl.getIndex();

    System.out.println(dropTargetIndex + " : ");
    System.out.println("inserted");
    return true;
  }
}

class ListEntry
{
    private String value;
    private ImageIcon icon;
    private IComponent figure;
      
    public ListEntry(String value, ImageIcon icon, IComponent figure) {
        this.value = value;
        this.icon = icon;
        this.figure = figure;
    }
      
    public String getValue() {
        return value;
    }
      
    public ImageIcon getIcon() {
        return icon;
    }

    public IComponent getFigure() {
      return figure;
    }
      
    public String toString() {
        return value;
    }
}