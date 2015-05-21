import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Button extends JButton {

    private static final long serialVersionUID = 42L;

    public Button (File file) {
        ImageIcon icon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-iconL.png" );
        setIcon(icon);
        setText(file.getName());
        setVerticalTextPosition(AbstractButton.BOTTOM);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

}
