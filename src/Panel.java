import java.io.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.*;

public class Panel extends JPanel {

    private static final long serialVersionUID = 42L;
    private File [] files;
    private JButton file;

    public void setPanel () {
        setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT));
    }

    //add buttons/files to panel
    public void refreshPanel(String path) {
        File directory = new File(path);
        files = directory.listFiles();

        removeAll();
        for (File curr : files) {
            add(new Button(curr));
        }
        updateUI();
    }
}
