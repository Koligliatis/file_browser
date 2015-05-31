import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.applet.*;

public class SearchBar extends JToolBar implements ActionListener,KeyListener {

    private static final long serialVersionUID = 42L;
    private ActionCenter action;
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton cancel;

    public SearchBar() {
        ImageIcon icon;

        icon = new ImageIcon("icons/toolbar/Deep_Delete.png");
        cancel = new JButton(icon);
        cancel.setToolTipText("Cancel");
        cancel.setBorderPainted(false);
        cancel.setRequestFocusEnabled(false);
        cancel.setOpaque(false);
        cancel.addActionListener(this);

        setFloatable( false );
        searchPanel = new JPanel();
        searchPanel.setMaximumSize(new Dimension(500,100));
        searchField = new JTextField(90);
        searchField.addKeyListener(this);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.setOpaque(false);

        add(new JSeparator(SwingConstants.VERTICAL));
        add(searchPanel);
        addSeparator();
        addSeparator();
        add(cancel);
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
        addSeparator();
    }

    public void setActionCenter(ActionCenter action) {
        this.action = action;
    }

    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        if (pressedButton == cancel) {
            action.changeToolbar();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            action.setSelectionPath(searchField.getText());
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
