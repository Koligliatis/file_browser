import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class Button extends JButton {
    private static final long serialVersionUID = 42L;
    private File file;
    private String path;

    public Button (File file, String path, String layout) {
        ImageIcon icon;
        if (layout.equals("FLOW") ) {
            icon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-iconL.png" );
        }else {
            icon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-icon.png" );
        }
        setIcon(icon);
        setText(file.getName());
        setVerticalTextPosition(AbstractButton.BOTTOM);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(new ButtonActionListener());
    }

    public File getButtonFile() {
        return file;
    }

    public String getButtonPath() {
        return path;
    }
}

class ButtonActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    }
}
