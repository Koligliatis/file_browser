import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private static final long serialVersionUID = 42L;
    private JSplitPane splitPane;
    private Tree tree;
    private JPanel panel;
    private JScrollPane treeView;
    private JScrollPane panelView;

    public void setFrame() {

        tree = new Tree();
        treeView = new JScrollPane(tree);

        panel = new Panel();
        panelView = new JScrollPane(panel);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView,panelView);
        setSize(WIDTH,HEIGHT);
        setTitle("File Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
}

