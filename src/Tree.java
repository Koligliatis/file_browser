import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Tree extends JTree {

    private static final long serialVersionUID = 42L;

    public Tree () {
        super(new DefaultMutableTreeNode("root"));
        addTreeSelectionListener(new SelectionListener());
        // sets icons for tree's nodes
        DefaultTreeCellRenderer tRenderer =
        new DefaultTreeCellRenderer();
        ImageIcon folderIcon = new ImageIcon( System.getProperty("user.dir")+"/icons/Folder-plain-icon.png" );
        //ImageIcon folderIcon = new ImageIcon( System.getProperty("user.dir")+"/icons/Close-Folder-icon.png" );
        tRenderer.setLeafIcon( folderIcon );
        tRenderer.setClosedIcon( folderIcon );
        tRenderer.setOpenIcon( folderIcon );
        tRenderer.setTextSelectionColor(Color.RED);
        setCellRenderer(tRenderer);
    }
    // searchs path for directories and adds childs to selected node
    public void refreshTree(Tree tree) {
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
