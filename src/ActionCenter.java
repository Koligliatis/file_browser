import javax.swing.tree.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.io.File;
import java.awt.BorderLayout;

// Contains the basic interaction of components
public class ActionCenter {
    public Tree tree;
    public Panel panel;
    public ToolBar toolbar;
    private SearchBar searchBar;
    public MainFrame frame;
    private String currentPath;
    private PathHistory history;
    private Configuration config;
    private boolean writeHistory;

    public ActionCenter(Tree tree, Panel panel, ToolBar toolbar, SearchBar searchBar, MainFrame frame) {
        this.tree = tree;
        this.panel = panel;
        this.toolbar = toolbar;
        this.searchBar = searchBar;
        this.frame = frame;
        history = new PathHistory();
        config = new Configuration();
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
        if (treepath != null) {
            tree.setSelectionPath(treepath);
        }else {
            toolbar.errorPath();
        }
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
    public void repaintFrame() {
        frame.revalidate();
        frame.repaint();
    }
    public void changeToolbar() {
        if (searchBar.getParent() == null) {
            frame.remove(toolbar);
            frame.add(searchBar,BorderLayout.NORTH);
        }else {
            frame.remove(searchBar);
            frame.add(toolbar,BorderLayout.NORTH);
        }
        repaintFrame();
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
    // Find program with extension of file in dictionary
    // and run that file with this program
    public void runProgram(String name,String extension) {
        try {
            String path;
            Process p;
            String program = config.getProgram(extension);
            if (program == null) {
                JOptionPane.showMessageDialog(frame,"Not found program for this type of file",
                                    "Program not found",JOptionPane.PLAIN_MESSAGE);
            } else {
                path = program + " " + name;
                p = Runtime.getRuntime().exec(path);
                p.waitFor();
                p.destroy();
            }
        } catch (IOException e) {
        } catch(InterruptedException e) {
           Thread.currentThread().interrupt();
        }
    }
    public void renameFile(String message,File file,String name) {
        String dialogMessage;
        String textMessage;
        boolean returnValue = false;
        if (message.equals("ND")) {
            dialogMessage = new String("Give a name for the new folder");
            textMessage = new String("New Folder");
        }else if (message.equals("NF")) {
            dialogMessage = new String("Give a name for the new file");
            textMessage = new String("New File");
        }else if (message.equals("RF")) {
            dialogMessage = new String("New name");
            textMessage = new String ("Rename File");
        }else {
            dialogMessage = new String("");
            textMessage = new String("");
        }
        String s = (String)JOptionPane.showInputDialog(
                frame,dialogMessage,
                textMessage,JOptionPane.PLAIN_MESSAGE,null,
                null,name);
        if ((s != null) && (s.length() > 0)) {
            File newFile = new File(currentPath + s);
            file.renameTo(newFile);
        }else if (message.equals("ND")){
            file.delete();
        }else if (message.equals("NF")) {
            file.delete();
        }
        refreshFrame();
    }

    public void deleteFile(File file) {
        Object [] options = {"Delete","Cansel"};
        int n = JOptionPane.showOptionDialog(frame,"Are you sure you want to " +
                "permanently delete \""+file.getName()+"\"?","Attenstion",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                null,options,options[0]);
        if (n == 0) {
            file.delete();
        }
        refreshFrame();

    }
    public void close() {
        history.deleteFile();
    }
}

