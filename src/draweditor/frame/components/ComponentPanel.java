package draweditor.frame.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import draweditor.DrawEditor;
import draweditor.commands.DeleteCommand;
import draweditor.commands.DrawCommand;
import draweditor.components.Group;

public class ComponentPanel extends JPanel {

    private JButton deleteButton;
    private final String deleteString = "delete";
    private JButton addGroupButton;
    private final String addGroupString = "add group";
    
    public ComponentPanel() {
        super(new BorderLayout());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS)); 
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        deleteButton = new JButton(deleteString);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(new DeleteListener());

        addGroupButton = new JButton(addGroupString);
        addGroupButton.setActionCommand(addGroupString);
        addGroupButton.addActionListener(new AddGroupListener());

        buttonPane.add(deleteButton);
        buttonPane.add(addGroupButton);

        add(ComponentList.getInstance(), BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }
}

class DeleteListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // int index = list.getSelectedIndex();
        // listModel.remove(index);

        // int size = listModel.getSize();

        // if (size == 0) { 
        //     deleteButton.setEnabled(false);

        // } else {
        //     if (index == listModel.getSize()) {
        //         index--;
        //     }

        //     list.setSelectedIndex(index);
        //     list.ensureIndexIsVisible(index);
        // }
        DrawEditor.getInstance().execute(new DeleteCommand(DrawEditor.getInstance().activeFigure));
    }
}

class AddGroupListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        DrawEditor.getInstance().execute(new DrawCommand(new Group()));
    }
}