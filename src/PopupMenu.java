import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PopupMenu extends JPopupMenu {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;
    private ActionCenter action;

    public PopupMenu(ActionCenter action) {
        this.action = action;
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
        MenuActionListener listener = new MenuActionListener(action);
        menuItem.addActionListener(listener);
        add(menuItem);
    }
}
