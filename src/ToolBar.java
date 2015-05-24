import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.*;

public class ToolBar extends JToolBar implements ActionListener {

    private static final long serialVersionUID = 42L;

    private JButton back;
    private JButton front;
    private JButton refresh;
    private JButton home;
    private JButton computer;
    private JButton iconview;
    private JButton listview;
    private JButton search;
    private JPanel searchPanel;
    private JTextField searchField;

    private ActionCenter action;

    public ToolBar() {
        setFloatable( false);
        ImageIcon icon;

        icon = new ImageIcon("icons/toolbar/Back.png");
        back = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Front.png");
        front = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/refresh.png");
        refresh = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Deep_Home.png");
        home = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Deep_Computer.png");
        computer = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/icon_view.png");
        iconview = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/list_view.png");
        listview = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Search.png");
        search = new JButton(icon);

        searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(400,100));
        searchField = new JTextField(90);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.setOpaque(false);

        iconview.setToolTipText("Icon View");
        iconview.setBorderPainted(false);
        iconview.setRequestFocusEnabled(false);
        iconview.setOpaque(false);

        listview.setToolTipText("List View");
        listview.setBorderPainted(false);
        listview.setRequestFocusEnabled(false);
        listview.setOpaque(false);

        back.setToolTipText("Go to the previous visited location");
        back.setBorderPainted(false);
        back.setRequestFocusEnabled(false);
        back.setOpaque(false);

        front.setToolTipText("Go to the next visited location");
        front.setBorderPainted(false);
        front.setRequestFocusEnabled(false);
        front.setOpaque(false);

        refresh.setToolTipText("Reload the current location");
        refresh.setBorderPainted(false);
        refresh.setRequestFocusEnabled(false);
        refresh.setOpaque(false);

        home.setToolTipText("Go to home directory");
        home.setBorderPainted(false);
        home.setRequestFocusEnabled(false);
        home.setOpaque(false);

        computer.setToolTipText("Go to Computer");
        computer.setBorderPainted(false);
        computer.setRequestFocusEnabled(false);
        computer.setOpaque(false);

        search.setToolTipText("Go to Computer");
        search.setBorderPainted(false);
        search.setRequestFocusEnabled(false);
        search.setOpaque(false);

        back.addActionListener(this);
        front.addActionListener(this);
        refresh.addActionListener(this);
        computer.addActionListener(this);
        home.addActionListener(this);
        iconview.addActionListener(this);
        listview.addActionListener(this);
        search.addActionListener(this);

        addSeparator();
        add(back);
        addSeparator();
        add(front);
        addSeparator();
        add(refresh);
        addSeparator();
        add(home);
        addSeparator();
        add(computer);
        add(new JSeparator(SwingConstants.VERTICAL));
        addSeparator();
        add(searchPanel);
        addSeparator();
        add(search);
        addSeparator();
        addSeparator();
        add(iconview);
        addSeparator();
        add(listview);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(String path) {
        searchField.setText(path);
    }

    public void setActionCenter(ActionCenter action) {
        this.action = action;
    }

    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        //BACK BUTTON
        if (pressedButton == back) {
            System.out.println("Back");
        //FRONT BUTTON
        } else if (pressedButton == front) {
            System.out.println("Front");
        //REFRESH BUTTON
        } else if (pressedButton == refresh) {
            int [] sel = action.tree.getSelectionRows();
            action.tree.setSelectionRow(0);
            action.tree.setSelectionRows(sel);
        //HOME BUTTON
        } else if (pressedButton == home) {
            action.setSelectedPath("home");
        //COMPUTER BUTTON
        } else if (pressedButton == computer) {
            System.out.println("Computer");
        //SEARCH BUTTON
        } else if (pressedButton == search) {
            String searchPath = searchField.getText();
        //ICONVIEW BUTTON
        } else if (pressedButton == iconview) {
            action.panel.buildFlowPanel();
            action.refreshFrameFromTree();
        //LISTVIEW BUTTON
        } else if (pressedButton == listview) {
            action.panel.buildListPanel();
            action.refreshFrameFromTree();
        }
    }
}
