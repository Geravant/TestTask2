import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.util.EventObject;

public class FileTreeCellEditor extends DefaultTreeCellEditor implements TreeCellEditor {
    private MyRenderer renderer;
    private DefaultTreeCellEditor.EditorContainer editorContainer;

    public  FileTreeCellEditor(JTree tree, MyRenderer renderer) {
        super(tree, renderer);
        this.editorContainer= new DefaultTreeCellEditor.EditorContainer();
        this.renderer = renderer;
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        System.out.println(value.toString());
        tree.repaint();
        return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return false;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {

    }
}
