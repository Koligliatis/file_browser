import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ButtonPopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;
    private ActionCenter action;

    public ButtonPopupMenu(ActionCenter action) {
        this.action = action;
    }
    //method creates a new menuItem, add Action Listener to menuItem and add this to popup menu
    public void setMenuItem(String description) {
        if (description.equals("Rename")) {
            menuItem = new JMenuItem(description);
        }
        else if (description.equals("Delete")) {
            menuItem = new JMenuItem(description);
        }
        menuItem.addActionListener(this);
        add(menuItem);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Rename")) {
        }
        else if (command.equals("Delete")) {
        }
    }
}

