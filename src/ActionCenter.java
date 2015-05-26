import javax.swing.tree.*;
import java.util.*;
import java.io.*;

// Contains the basic interaction of components
public class ActionCenter {
    public Tree tree;
    public Panel panel;
    public ToolBar toolbar;
    public MainFrame frame;
    private String currentPath;
    private PathHistory history;
    private boolean writeHistory;

    public ActionCenter(Tree tree, Panel panel, ToolBar toolbar, MainFrame frame) {
        this.tree = tree;
        this.panel = panel;
        this.toolbar = toolbar;
        this.frame = frame;
        history = new PathHistory();
        currentPath = "/";
        writeHistory = true;
    }
    // Refresh frame frome tree state
    // Get path of selected node and refresh panel
    public void refreshFrame() {
        tree.setState();
        String path = treePathToString(tree.getSelectionPath());
        panel.refresh(path);
        refreshSearchField();
        currentPath = panel.getCurrentPath();
        if (writeHistory) {
            history.addTail(currentPath + "\n");
        }
        writeHistory = true;
    }
    // Finds tree path from regular path
    // and fires tree action event with this tree path
    public void setSelectionPath(String path) {
        TreePath treepath = tree.findTreePath(path);
        tree.setSelectionPath(treepath);
    }
    public void backToPath() {
        writeHistory = false;
        setSelectionPath(history.getBackLine());
    }
    public void frontToPath() {
        writeHistory = false;
        setSelectionPath(history.getFrontLine());
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
    // and refresh it
    public void setPanelLayout(String layout) {
        if (layout.equals("FLOW")) {
            panel.buildFlowPanel();
        } else if (layout.equals("LIST")) {
            panel.buildListPanel();
        }
        String path = treePathToString(tree.getSelectionPath());
        panel.refresh(path);
    }
    public void close() {
        history.deleteFile();
    }
}

