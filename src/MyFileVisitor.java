import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Vector;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private JTree directoryTree;
    private DefaultMutableTreeNode top;
    private Vector treePathes;
    private DefaultTreeModel treeModel;

    MyFileVisitor(String dirname) {
        this.top = new DefaultMutableTreeNode(dirname);
        this.treePathes = new Vector();
        top.setAllowsChildren(true);

    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
        System.out.println(path);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(path.getFileName());
        top.add(node);
        System.out.println(node.getParent()+"\\"+node.toString());
        String treePath = node.getParent() + File.separator +node.toString();
        this.treePathes.addElement(node);
        return FileVisitResult.CONTINUE;
    }

    public JTree getTree() {
        this.directoryTree  = new JTree(top);
        return this.directoryTree;
    }

    public Vector getPathes() {
        return treePathes;
    }

    /*public void setPathes(DefaultMutableTreeNode node) {
        for (int fnum = 0; fnum < this.treePathes.size(); fnum++){
            this.top.add(new DefaultMutableTreeNode(treePathes.elementAt(fnum)));
        }
    }*/
    /*public  void updateModel(DefaultTreeModel treeModel, DefaultMutableTreeNode node) {
        for (int fnum = 0; fnum < this.treePathes.size(); fnum++) {
            node.add(new DefaultMutableTreeNode(treePathes.elementAt(fnum)));
            treeModel.insertNodeInto((DefaultMutableTreeNode) this.treePathes.elementAt(fnum), node, node.getChildCount()-1);
            treeModel.nodeStructureChanged(node);
        }
    }*/

    public boolean isDir() {
        return top.getAllowsChildren();
    }

    public DefaultMutableTreeNode getTop() {
        return top;
    }
}