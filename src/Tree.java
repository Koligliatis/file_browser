import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Tree extends JTree {
    private static final long serialVersionUID = 42L;
    public Panel panel;
    public MainFrame frame;

    public Tree (Panel panel,MainFrame frame) {
        super(new DefaultMutableTreeNode("root"));
        this.panel = panel;
        this.frame = frame;
        addTreeSelectionListener(new SelectionListener());
        // sets icons for tree's nodes
        DefaultTreeCellRenderer tRenderer =
        new DefaultTreeCellRenderer();
        ImageIcon folderIcon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-iconS.png" );
        //ImageIcon folderIcon = new ImageIcon( System.getProperty("user.dir")+"/icons/Close-Folder-icon.png" );
        tRenderer.setLeafIcon( folderIcon );
        tRenderer.setClosedIcon( folderIcon );
        tRenderer.setOpenIcon( folderIcon );
        tRenderer.setTextSelectionColor(Color.RED);
        setCellRenderer(tRenderer);
    }
    // searchs path for directories and adds childs to selected node
    public String refreshTree(Tree tree) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                  .getLastSelectedPathComponent();
        String path = tree.getSelectionPath().toString();
        path = makePath(path);
        File directory = new File(path);
        File files [] = directory.listFiles();

        if (files != null) {
            for (File curr : files) {
                if (curr.isDirectory()) {
                    selectedNode.add(new DefaultMutableTreeNode(curr.getName()));
                }
            }
        }

        return path;
    }
    // makes path from treePath
    private String makePath(String path) {
        String newPath = "";
        path = path.replaceAll("\\]","");
        path = path.replaceAll("\\[","");
        String tokens [] = path.split("\\, ");

        if (tokens[0].equals("root")) {
            tokens[0] = "";
        }

        for (String token : tokens) {
            newPath = newPath + token + "/";
        }
        return newPath;
    }
}
// called when selected a tree node
class SelectionListener implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent se) {
        Tree tree = (Tree) se.getSource();
        String path = tree.refreshTree(tree);
        tree.panel.refresh(path);
    }
}
