import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

public class Button extends JButton {
    private static final long serialVersionUID = 42L;
    private File file;
    private String path;
    private ImageIcon icon;

    public Button (File file, String path, String layout) {
        String str = "";
        if (layout.equals("FLOW")) {
            str = new String("");
            setPreferredSize(new Dimension(100, 100));
        }else {
            str = new String("S");
            setPreferredSize(new Dimension(50,50 ));
        }

        if (file.isDirectory()) {
            icon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-icon"+str+".png" );
        }
        else {
            String extension = "";
            String iconPath = "icons/";
            int i = file.getName().lastIndexOf('.');
            if (i > 0) {
                extension = file.getName().substring(i+1);
            }

            iconPath = iconPath + extension + str + ".png";

            File imageFile = new File(iconPath);
            if (imageFile.exists()) {
                icon = new ImageIcon(System.getProperty("user.dir")
                                         +"/"+iconPath);
            }else {
                icon = new ImageIcon(System.getProperty("user.dir")
                                     +"/icons/unknown.png");
            }
        }

        String text = "";
        for (char ch: file.getName().toCharArray()) {
            if (text.length() % 10 == 0) {
                text = text + "\n" + ch;
            }
            else {
                text = text + ch;
            }
        }

        System.out.println(text);

        setIcon(icon);
        //setText(file.getName());
        setText(text);
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
