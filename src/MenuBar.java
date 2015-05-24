import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuBar extends JMenuBar implements ActionListener{

    private static final long serialVersionUID = 42L;

    private JMenu fileMenu;
    private JMenuItem createNewFolder;
    private JMenuItem createNewFile;
    private JMenuItem exit;

    public MenuBar() {
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
    }

    private void createMenuItems() {
        ImageIcon icon;

        icon = new ImageIcon("icons/menu_bar/Deep_Folder_add.png");
        createNewFolder = new JMenuItem(" Create New Folder",icon);

        icon = new ImageIcon("icons/menu_bar/Deep_File_add.png");
        createNewFile = new JMenuItem(" Create New Document",icon);

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
        String menuString = e.getActionCommand();

        if (menuString.equals(" Create New Folder")) {
            System.out.println("Create new Folder");
        } else if (menuString.equals(" Create New File")) {
            System.out.println("Create new File");
        } else if (menuString.equals(" Exit")) {
            System.exit(0);
        }
    }
}
