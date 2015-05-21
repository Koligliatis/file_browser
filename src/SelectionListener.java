import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class SelectionListener implements TreeSelectionListener {

    public void valueChanged(TreeSelectionEvent se) {

        Tree tree = (Tree) se.getSource();
        tree.refreshTree(tree);

    }
}
