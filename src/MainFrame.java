import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private static final long serialVersionUID = 42L;
    private JSplitPane splitPane;
    private JTree tree;
    private JScrollPane treeView;

    public void setFrame() {

        tree = new JTree();
        treeView = new JScrollPane(tree);

        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setSize(WIDTH,HEIGHT);
        setTitle("File Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
}

