import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class ToolBar extends JToolBar implements ActionListener {

    private static final long serialVersionUID = 42L;

    public ToolBar() {
        ImageIcon icon = new ImageIcon("icons/toolbar/Deep_Home.png");
        JButton home = new JButton(icon);
        home.addActionListener(this);
        add(home);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
