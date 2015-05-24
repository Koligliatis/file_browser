import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;
    private ActionCenter action;
    private int i;
    private String currPath;
    private String newFolder;

    public PopupMenu(ActionCenter action) {
        this.action = action;
        i = 1;
        currPath = "";
        newFolder = "";
    }

    public void setMenuItem(String description) {
        menuItem = new JMenuItem(description);
        menuItem.addActionListener(this);
        add(menuItem);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Create New Folder")) {

            File directory = new File(action.panel.currentPath);
            File files [] = directory.listFiles();

            i = 1;
            if (files != null) {
                for (File curr : files) {
                    if (curr.isDirectory()) {
                        if (curr.getName().contains("Untitled Folder")) {
                            i++;
                        }
                    }
                }
            }

            if (i > 1) {
                newFolder = "Untitled Folder" + " " + Integer.toString(i);
            }
            else {
                newFolder = "Untitled Folder";
            }

            File dir = new File(action.panel.currentPath + newFolder);
            dir.mkdir();
        }
        else if (command.equals("Create New Document")) {

        }
    }
}




