import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    private static final long serialVersionUID = 42L;
    private JSplitPane splitPane;
    private Tree tree;
    private Panel panel;
    private JScrollPane treeView;
    private JScrollPane panelView;

    public void setFrame() {

        panel = new Panel();
        panel.buildFlowPanel();
        panelView = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                           JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tree = new Tree(panel,this);
        treeView = new JScrollPane(tree);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView,panelView);
        splitPane.setDividerLocation(250 + splitPane.getInsets().left);

        setJMenuBar(new MenuBar());

        ToolBar toolbar = new ToolBar();
        add(toolbar, BorderLayout.NORTH);

        setSize(WIDTH,HEIGHT);
        setTitle("File Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
}

