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
    // Explore child's node and add it to tree
    public void setState() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
                  getLastSelectedPathComponent();
        String path = action.treePathToString(getSelectionPath());
        addFiles(path,selectedNode);
        scrollPathToVisible(getSelectionPath());
    }
    // Add nodes to tree from specific path
    public void addFiles(String path,DefaultMutableTreeNode node) {
        File directory = new File(path);
        File files [] = directory.listFiles();
        DefaultMutableTreeNode child;

        //node.removeAllChildren();

        if (files != null) {
            for (File curr : files) {
                if (curr.isDirectory()) {
                    child = new DefaultMutableTreeNode(curr.getName());
                    if (!hasChild(node,child)) {
                        node.add(child);
                    }
                }
            }
        }
    }
    // Takes a parent node and a child node and
    // returns true if child node is child of parent node
    // else returns false
    public boolean hasChild(DefaultMutableTreeNode parent,DefaultMutableTreeNode child) {
        DefaultMutableTreeNode node;
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> children = parent.children();
        while (children.hasMoreElements()) {
            node = children.nextElement();
            if (node.toString().equals(child.toString())) {
                return true;
            }
        }
        return false;
    }
    // Searchs tree's node and
    // finds tree path by regular string path
    public TreePath findTreePath(String s) {
        if (s.equals("/")) {
            return new TreePath(root.getPath());
        }
        String result [] = s.replaceFirst("/","").split("/");
        int count = 0;
        String path = "/";
        DefaultMutableTreeNode node;
        DefaultMutableTreeNode lastCorrectNode = root;
        // add to tree root childs
        addFiles(path,root);
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> children = root.children();
        while (true) {
            while (children.hasMoreElements()) {
                node = children.nextElement();
                if (node.toString().equalsIgnoreCase(result[count])) {
                    // add to tree node childs
                    path = path + result[count] + "/";
                    addFiles(path,node);
                    children = node.children();
                    lastCorrectNode = node;
                    break;
                }
                if (!children.hasMoreElements()){
                    return null;
                }
            }
            // path has find
            if (count == result.length - 1) {
                return new TreePath(lastCorrectNode.getPath());
            }
            count++;
        }
    }
}
// called when selected a tree node
class SelectionListener implements TreeSelectionListener {
    private ActionCenter action;
    public SelectionListener(ActionCenter action) {
        this.action = action;
    }
    public void valueChanged(TreeSelectionEvent se) {
       action.refreshFrame();
    }
}
