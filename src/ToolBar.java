import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;

public class ToolBar extends JToolBar implements ActionListener {

    private static final long serialVersionUID = 42L;

    private JButton back;
    private JButton front;
    private JButton refresh;
    private JButton home;
    private JButton computer;
    private JButton iconview;
    private JButton listview;

    private Tree tree;
    private Panel panel;

    public ToolBar(Tree tree, Panel panel) {
        this.tree = tree;
        this.panel = panel;
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

        back.addActionListener(this);
        front.addActionListener(this);
        refresh.addActionListener(this);
        computer.addActionListener(this);
        home.addActionListener(this);
        iconview.addActionListener(this);
        listview.addActionListener(this);

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
        add(iconview);
        addSeparator();
        add(listview);
    }

    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();

        if (pressedButton == back) {
            System.out.println("Back");
        } else if (pressedButton == front) {
            System.out.println("Front");
        } else if (pressedButton == refresh) {
            int [] sel = tree.getSelectionRows();
            tree.setSelectionRow(0);
            tree.setSelectionRows(sel);
        } else if (pressedButton == home) {
            tree.setTree("home");
        } else if (pressedButton == computer) {
            System.out.println("Computer");
        } else if (pressedButton == iconview) {
            panel.buildFlowPanel();
            String path = tree.refresh();
            tree.panel.refresh(path);
        } else if (pressedButton == listview) {
            panel.buildListPanel();
            String path = tree.refresh();
            tree.panel.refresh(path);
        }
    }
}
