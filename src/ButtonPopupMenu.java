import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ButtonPopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;
    private ActionCenter action;
    private Button button;

    public ButtonPopupMenu(ActionCenter action,Button button) {
        this.action = action;
        this.button = button;
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
        File file = button.getButtonFile();
        if (command.equals("Rename")) {
            action.renameFile("RF",file,file.getName());
        }
        else if (command.equals("Delete")) {
            action.deleteFile(file);
        }
        button.setBorderPainted(false);


    }
}

