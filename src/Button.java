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

    public Button (File file, String path) {
        if (file.isDirectory()) {
            icon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-iconL.png" );
            setIcon(icon);
        }
        else {
            String extension = "";
            int i = file.getName().lastIndexOf('.');
            if (i > 0) {
                extension = file.getName().substring(i+1);
            }

            /*BufferedImage image;
            try {
                System.out.println(extension);
                image = ImageIO.read(new File("/icons/"+extension+".png"));
                icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/"+extension+".png");
            }
            catch (Exception e) {
                icon = new ImageIcon(System.getProperty("user.dir")
                                     +"/icons/unknown.png");
            }

            setIcon(icon);*/

            switch (extension) {
                case "doc":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/doc.png");
                    setIcon(icon);
                    break;
                case "docx":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/docx.png");
                    setIcon(icon);
                    break;
                case "ppt":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/ppt.png");
                    setIcon(icon);
                    break;
                case "pptx":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/pptx.png");
                    setIcon(icon);
                    break;
                case "xls":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/xls.png");
                    setIcon(icon);
                    break;
                case "xlsx":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/xlsx.png");
                    setIcon(icon);
                    break;
                case "txt":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/txt.png");
                    setIcon(icon);
                    break;
                case "pdf":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/pdf.png");
                    setIcon(icon);
                    break;
                case "html":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/html.png");
                    setIcon(icon);
                    break;
                case "htm":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/html.png");
                    setIcon(icon);
                    break;
                case "mkv":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/mkv.png");
                    setIcon(icon);
                    break;
                case "mp3":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/mp3.png");
                    setIcon(icon);
                    break;
                case "bin":
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/bin.png");
                    setIcon(icon);
                    break;
                default:
                    icon = new ImageIcon(System.getProperty("user.dir")
                                             +"/icons/unknown.png");
                    setIcon(icon);
                    break;
            }
        }

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
