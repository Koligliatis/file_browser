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

    public void refreshFrame() {
        toolbar.searchField.setText(panel.currentPath);
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

