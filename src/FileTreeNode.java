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

    public void createDirHere() {
        String dirname = "";
        TreeNode[] pathParts =this.getPath();
        for (int i = 0; i< pathParts.length;  i++){
            dirname = dirname+File.separator+(FileTreeNode)pathParts[i];
        }

        new File("C:\\Directory1").mkdir();
    }

    public boolean isPlug() {
        return isPlug;
    }

    public void setPlug(boolean plug) {
        isPlug = plug;
    }


}


