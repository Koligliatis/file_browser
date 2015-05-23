import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupMenu extends JPopupMenu implements ActionListener {

    private static final long serialVersionUID = 42L;
    private JMenuItem menuItem;

    public void setMenuItem(String description) {
        menuItem = new JMenuItem(description);
        menuItem.addActionListener(this);
        add(menuItem);
    }
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        System.out.println(str);
    }
}




