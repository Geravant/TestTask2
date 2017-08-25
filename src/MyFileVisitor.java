import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private JTree directoryTree;
    private DefaultMutableTreeNode top;
    private TreePath[] treePathes;
    private int count;

    MyFileVisitor(String dirname) {
        this.top = new DefaultMutableTreeNode(dirname);
        this.count = 0;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
        System.out.println(path);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(path.getFileName());
        top.add(node);
        System.out.println(node.getParent()+"\\"+node.toString());
        this.treePathes[this.count] = new TreePath(node);
        this.count++;
        return FileVisitResult.CONTINUE;
    }

    public JTree getTree() {
        this.directoryTree  = new JTree(top);
        return this.directoryTree;
    }

    public TreePath[] getPathes() {
        return treePathes;
    }
}