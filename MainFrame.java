import javax.swing.JFrame;
import javax.swing.JButton;

public class MainFrame extends JFrame{
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;

    public void setFrame() {
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("File Browser");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}

