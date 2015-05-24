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
import javax.swing.tree.*;
import java.util.*;

public class Tree extends JTree {
    private static final long serialVersionUID = 42L;
    private ActionCenter action;
    public MainFrame frame;
    public DefaultMutableTreeNode root;

    public Tree (DefaultMutableTreeNode root,MainFrame frame) {
        super(root);
        this.root = root;
        this.frame = frame;
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
    public void setActionCenter(ActionCenter action) {
        this.action = action;
        addTreeSelectionListener(new SelectionListener(action));
    }
    // Set tree state from last selected node.
    // Explore child's node and add it to treepublic void setTreeState() {
    public void setState() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
                  getLastSelectedPathComponent();
        String path = action.treePathToString(getSelectionPath());
        File directory = new File(path);
        File files [] = directory.listFiles();

        if (files != null) {
            for (File curr : files) {
                if (curr.isDirectory()) {
                    selectedNode.add(new DefaultMutableTreeNode(curr.getName()));
                }
            }
        }
    }
    // find tree path by string
    public TreePath findTreePath(String s) {

        //Enumeration children = root.children();
        //while (true) {
        //    while (children.hasMoreElements()) {
        //        if
        //    }
        //}

        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                    return new TreePath(node.getPath());
            }
        }
        return null;
    }
}
// called when selected a tree node
class SelectionListener implements TreeSelectionListener {
    private ActionCenter action;
    public SelectionListener(ActionCenter action) {
        this.action = action;
    }
    public void valueChanged(TreeSelectionEvent se) {
       action.refreshFrameFromTree();
    }
}
