package draweditor.frame.components;

import draweditor.DrawEditor;
import draweditor.commands.DeleteCommand;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ShapeList extends JPanel implements ListSelectionListener {
    private static JList<String> list;
    static DefaultListModel<ListEntry> listModel = new DefaultListModel<ListEntry>();

    private static final String deleteString = "Delete";
    private static JButton deleteButton;
    public DrawEditor DE;
    int count = 0;

    public ShapeList() {
        super(new BorderLayout());

        JList<ListEntry> list1 = new JList<ListEntry>(listModel);
        list1.setCellRenderer(new ListEntryCellRenderer());
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setSelectedIndex(0);
        list1.addListSelectionListener(this);
        list1.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list1);

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
            DE.execute(new DeleteCommand(DE.activeFigure));
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

