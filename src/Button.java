import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.tree.*;

public class Button extends JButton {
    private static final long serialVersionUID = 42L;
    private File file;
    private String path;
    private ImageIcon icon;
    private ActionCenter action;
    private ButtonPopupMenu buttonPopup;
    private String extension;

    public Button (ActionCenter action, File file, String path, String layout) {
        this.action = action;
        this.file = file;
        this.path = path;
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
            extension = "";
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
                                     +"/icons/unknown"+str+".png");
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

        setIcon(icon);
        //setText(file.getName());
        setText(text);
        if (layout.equals("FLOW")) {
            setVerticalTextPosition(AbstractButton.BOTTOM);
            setHorizontalTextPosition(AbstractButton.CENTER);
        }
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        showPopupMenu();
    }

    public String getExtension() {
        return extension;
    }

    public File getButtonFile() {
        return file;
    }
    public String getButtonPath() {
        return path;
    }

    public void showPopupMenu() {
        buttonPopup = new ButtonPopupMenu(action);
        buttonPopup.setMenuItem("Rename");
        buttonPopup.setMenuItem("Delete");
        MouseListener ButtonPopupListener = new ButtonPopupListener(buttonPopup, action, this);
        addMouseListener(ButtonPopupListener);
    }
}

class ButtonPopupListener extends MouseAdapter {
    JPopupMenu buttonPopup;
    private ActionCenter action;
    private Button button;
    ButtonPopupListener(JPopupMenu popupMenu, ActionCenter action, Button button) {
        buttonPopup = popupMenu;
        this.action = action;
        this.button = button;
    }
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (button.getButtonFile().isDirectory()) {
                action.setSelectionPath(button.getButtonPath());
            }else {
                String name = button.getButtonPath();
                String extension = button.getExtension();
                action.runProgram(name,extension);
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            maybeShowPopup(e);
        }
    }
    public void mouseReleased(MouseEvent e) {
         maybeShowPopup(e);
    }
    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
             buttonPopup.show(e.getComponent(),
             e.getX(), e.getY());
        }
    }
}
