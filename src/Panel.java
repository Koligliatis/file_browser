import java.io.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.*;
import javax.swing.*;
import java.text.*;

public class Panel extends JPanel {

    private static final long serialVersionUID = 42L;
    private File [] files;
    private JButton file;
    private String panelType;
    private JPanel filePanel;
    private SimpleDateFormat ft;

    public void buildFlowPanel() {
        panelType = "FLOW";
        setLayout(new ModifiedFlowLayout(ModifiedFlowLayout.LEFT));
    }

    public void buildListPanel() {
        panelType = "LIST";
        ft = new SimpleDateFormat("E',' dd MMMM yyyy hh:mm:ss 'GMT' ");
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void refresh(String path) {
        File directory = new File(path);
        files = directory.listFiles();

        removeAll();
        // for Flow panel
        if(panelType.equals("FLOW")) {
            for (File curr : files) {
                add(new Button(curr,path,panelType));
            }
        // for list panel
        }else{
            // create for each file a panel with 4 elements
            for (File curr : files) {
                filePanel = new JPanel();
                filePanel.setLayout(new GridLayout(1,4));
                filePanel.add(new Button(curr,path,panelType));
                filePanel.add(new JLabel((String.valueOf(curr.length()))));
                if(curr.isDirectory()) {
                    filePanel.add(new JLabel("Folder"));
                }else {
                    filePanel.add(new JLabel("File"));
                }
                filePanel.add(new JLabel(ft.format(curr.lastModified())));
                add(filePanel);
            }
        }
        updateUI();
    }

    public void addFile() {

    }
}
