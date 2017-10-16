
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionsMenu extends MouseAdapter {
    private PopupMenu jpu;
    private JTree tree;

    public ActionsMenu(PopupMenu popupMenu){
        jpu = popupMenu;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()) {
            jpu.show(e.getComponent(), e.getX(), e.getY());
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger()) {
            jpu.show(e.getComponent(), e.getX(), e.getY());
        }
    }


    public void setTree(JTree tree) {
        this.tree = tree;
    }
}
