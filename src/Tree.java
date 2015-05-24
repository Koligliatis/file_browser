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
    public Panel panel;
    public MainFrame frame;
    public DefaultMutableTreeNode root;

    public Tree (DefaultMutableTreeNode root,Panel panel,MainFrame frame) {
        super(root);
        this.root = root;
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
    public void setActionCenter(ActionCenter action) {
        this.action = action;
    }
    // searchs path for directories and adds childs to selected node
    public String refresh() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this
                  .getLastSelectedPathComponent();
        String path = this.getSelectionPath().toString();
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
    public void setTree(String path) {
        TreePath treepath = find(path);
        setSelectionPath(treepath);
    }
    // find tree path by string
    public TreePath find(String s) {
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
        String path = tree.refresh();
        tree.panel.refresh(path);
    }
}
