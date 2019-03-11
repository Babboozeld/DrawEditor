package draweditor.frame.components;

import draweditor.DrawEditor;
import draweditor.commands.DeleteCommand;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
public class ShapeList extends JPanel implements ListSelectionListener {
    
    private static JList<String> list;
    private static DefaultListModel<String> listModel;

    private static final String deleteString = "Delete";
    private static JButton deleteButton;
    private static String NewItem;
    private static int a = 1;
    public DrawEditor DE;

    public ShapeList(DrawEditor DE) {
        super(new BorderLayout());
        this.DE = DE;
        listModel = new DefaultListModel<String>();
        
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
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
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    public static void addItem(String figurestring)
    {
        NewItem = figurestring + a;
        a++;
        listModel.addElement(NewItem);
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