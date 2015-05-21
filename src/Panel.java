import java.io.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Dimension;

public class Panel extends JPanel {

    private static final long serialVersionUID = 42L;
    private File [] files;
    private JButton file;

    public void setPanel () {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    //add buttons/files to panel
    public void addButtons(String path) {
        File directory = new File(path);
        files = directory.listFiles();

        for (File curr : files) {
            file = new JButton(curr.getName());
            add(file);
            //add(Box.createHorizontalGlue());
            add(Box.createRigidArea(new Dimension(50,0)));
        }
    }
}
