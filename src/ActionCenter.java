import javax.swing.tree.*;
import java.util.*;
import java.io.*;

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
    // Set search field text base on current path
    public void refreshSearchField() {
        toolbar.setSearchField(panel.currentPath);
    }
    // Return current path in string
    public String getCurrentPath() {
        return panel.currentPath;
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

    // refresh action's center tree
    public String refreshTree() {
        return tree.refresh();
    }
    public void setTree(String path) {
        TreePath treepath = tree.find(path);
        tree.setSelectionPath(treepath);
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

