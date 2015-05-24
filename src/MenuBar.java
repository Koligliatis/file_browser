import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MenuBar extends JMenuBar implements ActionListener{

    private static final long serialVersionUID = 42L;

    private JMenu fileMenu;
    private JMenuItem createNewFolder;
    private JMenuItem createNewFile;
    private JMenuItem exit;
    private int i;
    private String newFolder;
    private String newDoc;
    private ActionCenter action;

    public MenuBar(ActionCenter action) {
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_C);

        // Menu items
        //JMenuItem newTab = new JMenuItem("New Tab");
        //JMenuItem newWindow = new JMenuItem("New Window");

        createMenuItems();
        setMnemonic();
        addActionListeners();

        fileMenu.add(createNewFolder);
        fileMenu.add(createNewFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        add(fileMenu);

        this.action = action;
        i = 1;
        newFolder = "";
        newDoc = "";
    }

    private void createMenuItems() {
        ImageIcon icon;

        icon = new ImageIcon("icons/menu_bar/Deep_Folder_add.png");
        createNewFolder = new JMenuItem("Create New Folder",icon);

        icon = new ImageIcon("icons/menu_bar/Deep_File_add.png");
        createNewFile = new JMenuItem("Create New Document",icon);

        icon = new ImageIcon("icons/menu_bar/Deep_Prohibit.png");
        exit = new JMenuItem(" Exit",icon);
    }

    private void setMnemonic() {
        createNewFolder.setMnemonic(KeyEvent.VK_B);
        createNewFile.setMnemonic(KeyEvent.VK_E);
    }

    private void addActionListeners() {
        createNewFolder.addActionListener(this);
        createNewFile.addActionListener(this);
        exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        //first menuItem of MenuBar (New Folder)
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
        //second menuItem of MenuBar (New Document)
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
        else if (command.equals(" Exit")) {
            System.exit(0);
        }
    }
}
