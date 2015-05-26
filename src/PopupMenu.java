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
    private String newDoc;

    public PopupMenu(ActionCenter action) {
        this.action = action;
        i = 1;
        newFolder = "";
        newDoc = "";
    }
    //method creates a new menuItem, add Action Listener to menuItem and add this to popup menu
    public void setMenuItem(String description) {
        ImageIcon icon;
        if (description.equals("Create New Folder")) {
            icon = new ImageIcon("icons/menu_bar/Deep_Folder_add.png");
            menuItem = new JMenuItem(description,icon);
        }
        else {
            icon = new ImageIcon("icons/menu_bar/Deep_File_add.png");
            menuItem = new JMenuItem(description,icon);
        }
        menuItem.addActionListener(this);
        add(menuItem);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //first menuItem of popupMenu (New Folder)
        if (command.equals("Create New Folder")) {
            File directory = new File(action.panel.currentPath);
            File files [] = directory.listFiles();
            i = 1;
            if (files != null) {
                //check how many Untitled folders exist in current directory
                for (File curr : files) {
                    if (curr.isDirectory()) {
                        if (curr.getName().contains("Untitled Folder")) {
                            i++;
                        }
                    }
                }
            }
            //if exist at least one Untitled folder, put a number at the end of newFolder's name
            if (i > 1) {
                newFolder = "Untitled Folder" + " " + Integer.toString(i);
            }
            else {
                newFolder = "Untitled Folder";
            }
            //create the directory in current path
            File dir = new File(action.panel.currentPath + newFolder);
            dir.mkdir();
        }
        //second menuItem of popupMenu (New Document)
        else if (command.equals("Create New Document")) {
            File directory = new File(action.panel.currentPath);
            File files [] = directory.listFiles();
            i = 1;
            if (files != null) {
                //check how many Untitled Documents exist in current directory
                for (File curr : files) {
                    if (!curr.isDirectory()) {
                        if (curr.getName().contains("Untitled Document")) {
                            i++;
                        }
                    }
                }
            }
            //if exist at least one Untitled Document, put a number at the end of newDoc's name
            if (i > 1) {
                newDoc = "Untitled Document" + " " + Integer.toString(i) + ".txt";
            }
            else {
                newDoc = "Untitled Document.txt";
            }
            //create the Document in current path
            File doc = new File(action.panel.currentPath + newDoc);
            try {
                doc.createNewFile();
            }catch (IOException ex) {}
        }
        action.refreshFrame();
    }
}
