package draweditor.components;
 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
/* ListDemo.java requires no other files. */
public class ShapeList extends JPanel
                      implements ListSelectionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;

    private JButton deleteButton;
    private String NewItem;
    private int a = 1;

    public void addItem(String figure)
    {
        NewItem = figure + a;
        a++;
        listModel.addElement(NewItem);
    }
 
    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
 
            int size = listModel.getSize();
 
            if (size == 0) { //Nobody's left, disable firing.
                deleteButton.setEnabled(false);
 
            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }
 
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }
   
 
    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
 
            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                deleteButton.setEnabled(false);
 
            } else {
            //Selection, enable the fire button.
                deleteButton.setEnabled(true);
            }
        }
    }
}