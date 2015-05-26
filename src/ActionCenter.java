import javax.swing.tree.*;
import java.util.*;
import java.io.*;

// Contains the basic interaction of components
public class ActionCenter {
    public Tree tree;
    public Panel panel;
    public ToolBar toolbar;
    public MainFrame frame;

    public ActionCenter(Tree tree, Panel panel, ToolBar toolbar, MainFrame frame) {
        this.tree = tree;
        this.panel = panel;
        this.toolbar = toolbar;
        this.frame = frame;
    }
    public void refreshFrame() {
        refreshFrameFromTree();
    }
    // Refresh frame frome tree state
    // Get path of selected node and refresh panel
    public void refreshFrameFromTree() {
        tree.setState();
        String path = treePathToString(tree.getSelectionPath());
        panel.refresh(path);
        refreshSearchField();
    }
    // Finds tree path from regular path
    // and fires tree action event with this tree path
    public void setSelectionPath(String path) {
        TreePath treepath = tree.findTreePath(path);
        tree.setSelectionPath(treepath);
        refreshFrameFromTree();
    }
    // Set search field text base on current path
    public void refreshSearchField() {
        toolbar.setSearchField(panel.currentPath);
    }
    // Return current path in string
    public String getCurrentPath() {
        return panel.currentPath;
    }
    // Return current tree path
    public TreePath getCurrentTreePath() {
        return tree.getSelectionPath();
    }
    // Convert TreePath to string
    public String treePathToString(TreePath treepath) {
        String newPath = "";
        String path = treepath.toString();
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
    // set layout of action's center panel
    public void setPanelLayout(String layout) {
        if (layout.equals("FLOW")) {
            this.panel.buildFlowPanel();
        } else if (layout.equals("LIST")) {
            this.panel.buildListPanel();
        }
    }
}

