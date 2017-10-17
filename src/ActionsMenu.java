import javax.swing.*;
import javax.swing.tree.*;
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

        if (e.getClickCount() > 2 ) {
            try {
                TreeModel model = tree.getModel();
                TreeCellEditor editor = tree.getCellEditor();
                Object oldValue = editor.getCellEditorValue();
                RenamingTreeModelListener renamingTreeModelListener = new RenamingTreeModelListener();
                renamingTreeModelListener.setOldValue(oldValue.toString());
                model.addTreeModelListener(renamingTreeModelListener);
                renamingTreeModelListener.setCurrentFolder(jpu.getCurrentFolder());
                renamingTreeModelListener.setModel(model);
            }
            catch (NullPointerException notInTree) {

            }


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
