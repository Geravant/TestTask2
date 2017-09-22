import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;

public class FileTreeNode extends DefaultMutableTreeNode {
    private boolean isLoading;
    private boolean isDir;
    private boolean isPlug;

    FileTreeNode() {
        isLoading = false;
        isDir = false;
        isPlug = true;
    }

    FileTreeNode(String string) {
        super(string);
        isLoading = false;
    }

    FileTreeNode(Object object) {
        super(object);
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    public boolean isPlug() {
        return isPlug;
    }

    public void setPlug(boolean plug) {
        isPlug = plug;
    }


}


