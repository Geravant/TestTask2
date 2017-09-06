import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeNode extends DefaultMutableTreeNode {
    private boolean isLoading;
    private boolean isDir;

    FileTreeNode() {
        isLoading = false;
        isDir = false;
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

}
