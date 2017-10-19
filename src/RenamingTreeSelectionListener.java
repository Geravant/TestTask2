import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class RenamingTreeSelectionListener implements TreeSelectionListener {
    private TreePath currentFolder;
    private GUI programGUI;

    public  RenamingTreeSelectionListener(GUI programGUI) {
        super();
        this.programGUI = programGUI;

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        this.currentFolder = e.getPath();
        programGUI.setCurrentFolder(currentFolder);
        try {
            RenamingTreeModelListener renamingTreeModelListener = programGUI.getRenamingTreeModelListener();
            renamingTreeModelListener.setOldValue(currentFolder.getLastPathComponent());
        }
        catch (NullPointerException n) {
            System.out.println("currentFolder is not set");
        }

    }

    public void setProgramGUI(GUI programGUI) {
        this.programGUI = programGUI;
    }
}
