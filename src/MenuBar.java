import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuBar extends JMenuBar implements ActionListener{

    private static final long serialVersionUID = 42L;

    public MenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_C);

        // Menu items
        JMenuItem newTab = new JMenuItem("New Tab");
        JMenuItem newWindow = new JMenuItem("New Window");
        JMenuItem createNewFolder = new JMenuItem("Create New Folder");
        // set background to menu items
        newTab.setBackground(Color.WHITE);
        newWindow.setBackground(Color.WHITE);
        createNewFolder.setBackground(Color.WHITE);
        // setMnemonic to menu items
        newTab.setMnemonic(KeyEvent.VK_R);
        newWindow.setMnemonic(KeyEvent.VK_W);
        createNewFolder.setMnemonic(KeyEvent.VK_B);
        // add actionListener
        newTab.addActionListener(this);
        newWindow.addActionListener(this);
        createNewFolder.addActionListener(this);
        // add menu items to menu
        fileMenu.add(newTab);
        fileMenu.add(newWindow);
        fileMenu.add(createNewFolder);

        add(fileMenu);
    }

    public void actionPerformed(ActionEvent e) {
        String menuString = e.getActionCommand();

        if (menuString.equals("New Tab")) {
            System.out.println("New Tab");
        }else if (menuString.equals("New Window")) {
            System.out.println("New Window");
        }else if (menuString.equals("Create New Folder")) {
            System.out.println("Create new Folder");
        }
    }
}
