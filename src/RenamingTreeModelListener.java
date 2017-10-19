import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import java.io.File;

public class RenamingTreeModelListener implements TreeModelListener{
    private String newValue;
    private String oldValue;

    public RenamingTreeModelListener(){
        super();
    }


    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        Object[] target = e.getChildren();
        this.newValue = target[0].toString();
        String oldValuePath = pathToString(e.getTreePath())  + File.separator +oldValue;
        String newValuePath = pathToString(e.getTreePath()) + File.separator + newValue;
        Renamer renamer = new Renamer(oldValuePath, newValuePath);
        renamer.renameDirectories();


    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }


    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setOldValue(TreePath oldvalue) {
        this.oldValue = pathToString(oldvalue);
    }

    public void setOldValue(Object object) {
        this.oldValue = object.toString();
    }

    private String pathToString (TreePath treePath){
        int dirDepth = treePath.getPathCount();
        String dirname = treePath.getPathComponent(1).toString();
        for(int i=2; i< dirDepth; i++) {
            dirname = dirname + File.separator+ treePath.getPathComponent(i);

        }
        return dirname;
    }

}
