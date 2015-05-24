import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;

    public void setMenuItem(String description) {
        menuItem = new JMenuItem(description);
        menuItem.addActionListener(this);
        add(menuItem);
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Create New Folder")) {
            File dir = new File("Untitled Folder");
            if (!dir.exists()) {
                dir.mkdir();
            }
        }
    }
}




