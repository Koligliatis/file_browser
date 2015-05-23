import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame extends JFrame{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private static final long serialVersionUID = 42L;
    private JSplitPane splitPane;
    private Tree tree;
    private Panel panel;
    private JScrollPane treeView;
    private JScrollPane panelView;
    private DefaultMutableTreeNode root;

    private ActionCenter action;

    public void setFrame() {

        panel = new Panel();
        panel.buildFlowPanel();
        panelView = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                           JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        root = new DefaultMutableTreeNode("root");
        tree = new Tree(root,panel,this);
        treeView = new JScrollPane(tree);

        action = new ActionCenter(tree,panel);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView,panelView);
        splitPane.setDividerLocation(250 + splitPane.getInsets().left);

        setJMenuBar(new MenuBar());

        ToolBar toolbar = new ToolBar(tree,panel);
        add(toolbar, BorderLayout.NORTH);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/Folder-plain-iconSm.png")));
        setSize(WIDTH,HEIGHT);
        setTitle("File Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
}

