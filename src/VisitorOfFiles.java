import javax.swing.tree.*;
import java.io.*;
import java.util.Vector;

class VisitorOfFiles {
    public static void main(String[] args) {
        SystemRoots systemroots = new SystemRoots();
        Vector<String> dirname = new Vector();
        for (File f: systemroots.getSystemRoots()) {
            dirname.addElement(f.getAbsolutePath());
        }
        FileTreeNode root = new FileTreeNode("UltimateRoot");
        final DefaultTreeModel treeModel = new DefaultTreeModel(root);
        root.setAllowsChildren(true);
        for (String dir: dirname){
//
            FileTreeNode node = new FileTreeNode(dir);
            root.add(node);
            node.setAllowsChildren(true);
            FileTreeNode plug = new FileTreeNode("Empty Folder");
            node.add(plug);
            treeModel.nodeStructureChanged(node);
            }

        final GUI programGUI = new GUI(treeModel);
        LoadingExpansionListener loadingExpansionListener = new LoadingExpansionListener(programGUI);
        programGUI.getTree().addTreeExpansionListener(loadingExpansionListener);


    }
}