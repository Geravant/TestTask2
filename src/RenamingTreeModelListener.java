import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import java.io.File;

public class RenamingTreeModelListener implements TreeModelListener{
    private String newValue;
    private String oldValue;
    private String currentFolder;
    private TreeModel model;
    private Renamer renamer;

    public RenamingTreeModelListener(){
        super();
    }


    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        Object[] target = e.getChildren();
        this.newValue = target[0].toString();
        System.out.println("New value: " + target[0].toString());
        System.out.println("Going to create: "+ currentFolder + File.separator + newValue+" instead of "+currentFolder  + File.separator +oldValue);
        System.out.println("CurrentFolder "+currentFolder);
        String oldValuePath = currentFolder  + File.separator +oldValue;
        String newValuePath = currentFolder + File.separator + newValue;
        Renamer renamer = new Renamer(oldValuePath, newValuePath);
        renamer.renameDirectories();
        model.removeTreeModelListener(this);

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

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    public void setModel(TreeModel model) {
        this.model = model;
    }
}
