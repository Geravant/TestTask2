import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Vector;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private DefaultMutableTreeNode top;
    private Vector treePathes;

    MyFileVisitor(String dirname) {
        this.top = new DefaultMutableTreeNode(dirname);
        this.treePathes = new Vector();
        top.setAllowsChildren(true);

    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(path.getFileName());
        top.add(node);
        String treePath = node.getParent() + File.separator +node.toString();
        this.treePathes.addElement(node);
        return FileVisitResult.CONTINUE;
    }


    public Vector getPathes() {
        return treePathes;
    }

    public DefaultMutableTreeNode getTop() {
        return top;
    }
}