package draweditor.frame.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import draweditor.DrawEditor;
import draweditor.commands.DeleteCommand;

public class ShapeList extends JPanel implements ListSelectionListener {
    private JList<ListEntry> list;
    private DefaultListModel<ListEntry> listModel = new DefaultListModel<ListEntry>();

    private final String deleteString = "Delete";
    private JButton deleteButton;
    
    int count = 0;

    public ShapeList() {
        super(new BorderLayout());

        list = new JList<ListEntry>(listModel);
        list.setCellRenderer(new ListEntryCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        list.setDragEnabled(true);
        list.setDropMode(DropMode.INSERT);
        //list.setTransferHandler(new MyListDropHandler(null));
        //new MyDragListener(null);
        JScrollPane listScrollPane = new JScrollPane(list);

        deleteButton = new JButton(deleteString);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(new DeleteListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
   
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    private static ShapeList instance = new ShapeList();
    public static ShapeList getInstance(){
        return instance;
    }

    public void addItem(String figurestring) {
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
        }
        count++;

        listModel.addElement(new ListEntry(description + count, new ImageIcon(path)));
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
 
            int size = listModel.getSize();
 
            if (size == 0) { 
                deleteButton.setEnabled(false);
 
            } else {
                if (index == listModel.getSize()) {
                    index--;
                }
 
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            DrawEditor.getInstance().execute(new DeleteCommand(DrawEditor.getInstance().activeFigure));
        }
    }
   
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
 
            if (list.getSelectedIndex() == -1) {
                deleteButton.setEnabled(false);
 
            } else {
                deleteButton.setEnabled(true);
            }
        }
    }
}

// class MyDragListener implements DragSourceListener, DragGestureListener {
//     DragDropList list;
  
//     DragSource ds = new DragSource();
  
//     public MyDragListener(DragDropList list) {
//       this.list = list;
//       DragGestureRecognizer dgr = ds.createDefaultDragGestureRecognizer(list,
//           DnDConstants.ACTION_MOVE, this);
  
//     }
  
//     public void dragGestureRecognized(DragGestureEvent dge) {
//       StringSelection transferable = new StringSelection(Integer.toString(list.getSelectedIndex()));
//       ds.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);
//     }
  
//     public void dragEnter(DragSourceDragEvent dsde) {
//     }
  
//     public void dragExit(DragSourceEvent dse) {
//     }
  
//     public void dragOver(DragSourceDragEvent dsde) {
//     }
  
//     public void dragDropEnd(DragSourceDropEvent dsde) {
//       if (dsde.getDropSuccess()) {
//         System.out.println("Succeeded");
//       } else {
//         System.out.println("Failed");
//       }
//     }
  
//     public void dropActionChanged(DragSourceDragEvent dsde) {
//     }
//   }
  
//   class MyListDropHandler extends TransferHandler {
//     DragDropList list;
  
//     public MyListDropHandler(DragDropList list) {
//       this.list = list;
//     }
  
//     public boolean canImport(TransferHandler.TransferSupport support) {
//       if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//         return false;
//       }
//       JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
//       if (dl.getIndex() == -1) {
//         return false;
//       } else {
//         return true;
//       }
//     }
  
//     public boolean importData(TransferHandler.TransferSupport support) {
//       if (!canImport(support)) {
//         return false;
//       }
  
//       Transferable transferable = support.getTransferable();
//       String indexString;
//       try {
//         indexString = (String) transferable.getTransferData(DataFlavor.stringFlavor);
//       } catch (Exception e) {
//         return false;
//       }
  
//       int index = Integer.parseInt(indexString);
//       JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
//       int dropTargetIndex = dl.getIndex();
  
//       System.out.println(dropTargetIndex + " : ");
//       System.out.println("inserted");
//       return true;
//     }
//   }

class ListEntry
{
    private String value;
    private ImageIcon icon;
      
    public ListEntry(String value, ImageIcon icon) {
        this.value = value;
        this.icon = icon;
    }
      
    public String getValue() {
        return value;
    }
      
    public ImageIcon getIcon() {
        return icon;
    }
      
    public String toString() {
        return value;
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

