import javax.swing.*;
import javax.swing.event.TreeModelListener;
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

//        if (e.getClickCount() > 2 ) {
//            try {
//                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//                TreeCellEditor editor = tree.getCellEditor();
//                Object oldValue = editor.getCellEditorValue();
//                RenamingTreeModelListener renamingTreeModelListener = new RenamingTreeModelListener();
//                renamingTreeModelListener.setOldValue(oldValue.toString());
////                for (TreeModelListener i : model.getTreeModelListeners()) {
////                    model.removeTreeModelListener(i);
////                }
//                if (model.getTreeModelListeners().length > 1){
//                    model.removeTreeModelListener(model.getTreeModelListeners()[model.getTreeModelListeners().length-1]);
//                }
//                model.addTreeModelListener(renamingTreeModelListener);
//                renamingTreeModelListener.setCurrentFolder(jpu.getCurrentFolder());
//                renamingTreeModelListener.setModel(model);
//            }
//            catch (NullPointerException notInTree) {
//
//            }
//
//
//        }
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
