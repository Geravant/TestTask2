import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

class VisitorOfFiles {
    public static void main(String[] args) {
        SystemRoots systemroots = new SystemRoots();
        Vector<String> dirname = new Vector();
        for (File f: systemroots.getSystemRoots()) {
            System.out.println(f.getAbsolutePath());
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
            System.out.println(dir+"pluged");
//
                treeModel.nodeStructureChanged(node);
            }

        final JTree tree = new JTree(treeModel);
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(false);
        tree.setRootVisible(false);
        final MyRenderer loadCellRenderer = new MyRenderer();
        tree.setCellRenderer(loadCellRenderer);

        final JScrollPane jsp = new JScrollPane(tree);
        final JFrame jfrm = new JFrame();
        jfrm.setSize(1000,500);
        jfrm.add(jsp);
        jfrm.setVisible(true);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLab = new JLabel();
        jfrm.add(jLab, BorderLayout.SOUTH);

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public synchronized void treeExpanded(TreeExpansionEvent e) {


                LazyLoad lazyload = new LazyLoad(e);
                Thread lazyloadrun = new Thread(lazyload);
                lazyloadrun.start();
                lazyload.getNode().setLoading(true);
                loadCellRenderer.setCurrentLoading(lazyload.getNode().getUserObject());
                treeModel.nodeChanged(lazyload.getNode());
                System.out.println(lazyload.getNode().getUserObject()+"5");
                System.out.println(lazyload.getNode().getUserObject() == loadCellRenderer.getCurrentLoading());
                //treeModel.reload(lazyload.getNode());
                jsp.revalidate();
                loadCellRenderer.revalidate();
                tree.revalidate();
                jfrm.revalidate();
                jsp.repaint();
                tree.repaint();
                loadCellRenderer.repaint();
                jfrm.repaint();

                try {
                    lazyloadrun.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                treeModel.nodeStructureChanged(lazyload.getNode());
                lazyloadrun.interrupt();
                lazyload.getNode().setLoading(false);

            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
                System.out.println("Closed");
            }
        });
    }
}