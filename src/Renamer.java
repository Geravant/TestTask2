import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;

public class Renamer {
    private TreePath treePath;
    private String stringTreePath;

    public Renamer(TreePath treePath){
        this.treePath = treePath;
        this.stringTreePath = treePathToString(treePath);
    }

    private String treePathToString(TreePath treePath) {

        String wannaBePath = new String();
        for(int i = 1; i < treePath.getPathCount() ; i++) {
            String wannaBeDirPart = treePath.getPathComponent(i).toString();
            wannaBePath = wannaBePath + wannaBeDirPart + File.separator;
        }
        return wannaBePath;
    }

    public String getStringTreePath() {
        return stringTreePath;
    }
}
