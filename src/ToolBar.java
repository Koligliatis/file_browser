import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class ToolBar extends JToolBar implements ActionListener {

    private static final long serialVersionUID = 42L;

    public ToolBar() {
        setFloatable( false);
        ImageIcon icon;

        icon = new ImageIcon("icons/toolbar/Back.png");
        JButton back = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Front.png");
        JButton front = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/refresh.png");
        JButton refresh = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Deep_Home.png");
        JButton home = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/Deep_Computer.png");
        JButton computer = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/icon_view.png");
        JButton iconview = new JButton(icon);
        icon = new ImageIcon("icons/toolbar/list_view.png");
        JButton listview = new JButton(icon);

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

    }
}
