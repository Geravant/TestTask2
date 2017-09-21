import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        //jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));
        jfrm.setSize(jsp.getPreferredSize().width+200,jsp.getPreferredSize().height);
        jfrm.add(jsp);
        jfrm.setVisible(true);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jMenuLab = new JLabel();
        MenuBar jmb = new MenuBar();

        jfrm.setJMenuBar(jmb);



        JLabel jLab = new JLabel();
        jfrm.add(jLab, BorderLayout.SOUTH);
        jfrm.add(jMenuLab, BorderLayout.SOUTH);

        PopupMenu jpu = new PopupMenu();
        tree.addMouseListener(new ActionsMenu(jpu));

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public synchronized void treeExpanded(TreeExpansionEvent e) {

                final Thread currentThread = Thread.currentThread();

                LazyLoad lazyload = new LazyLoad(e);
                loadCellRenderer.setCurrentLoading(lazyload.getCurrentLoading().toString());
                loadCellRenderer.setCurrentLoadingNode(lazyload.getNode());
                loadCellRenderer.setCurrentLoadingNodePath(e.getPath());
                treeModel.nodeStructureChanged(lazyload.getNode());
                Thread lazyloadrun = new Thread(lazyload);
                SwingUtilities.invokeLater(lazyload);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        loadCellRenderer.setCurrentLoading(null);
                        //treeModel.nodeChanged(loadCellRenderer.getCurrentLoadingNode());
                        treeModel.nodeStructureChanged(loadCellRenderer.getCurrentLoadingNode());
                        try {
                            Thread.sleep(2000);
                            System.out.println("waited");

                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        currentThread.run();
                        treeModel.nodeStructureChanged(loadCellRenderer.getCurrentLoadingNode());

                    }
                });
                lazyload.getNode().setLoading(true);
//                loadCellRenderer.setCurrentLoading(lazyload.getCurrentLoading().toString());
//
//                treeModel.nodeStructureChanged(lazyload.getNode());
                System.out.println(System.nanoTime()+"C1");
                System.out.println(lazyload.getNode().getUserObject()+"5");
                System.out.println(lazyload.getNode().getUserObject() == loadCellRenderer.getCurrentLoading());

                System.out.println(lazyloadrun.isAlive()+"prelife");

                loadCellRenderer.setCurrentLoading(lazyload.getCurrentLoading().toString());

                treeModel.nodeStructureChanged(lazyload.getNode());
//                try {
//                    wait(2000);
//                    System.out.println("waited");
//
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                while (lazyloadrun.isAlive()) {
//                    System.out.println(loadCellRenderer.getCurrentLoading());
//                    System.out.println(lazyload.getNode());
//                    treeModel.nodeChanged(lazyload.getNode());
//                    treeModel.nodeStructureChanged(lazyload.getNode());
//
//                    try {
//                        wait(100);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                }
                //loadCellRenderer.setCurrentLoading(null);
                System.out.println(loadCellRenderer.getCurrentLoading());

                treeModel.nodeStructureChanged(lazyload.getNode());

                System.out.println(lazyloadrun.isAlive()+"postpostlife");
                treeModel.nodeStructureChanged(lazyload.getNode());
                System.out.println(System.nanoTime()+"C2");

                //treeModel.nodeChanged(lazyload.getNode());

                //lazyloadrun.interrupt();
//                if (!lazyloadrun.isInterrupted()) {
//                    loadCellRenderer.setCurrentLoading(lazyload.getCurrentLoading().toString());
//                    treeModel.nodeStructureChanged(lazyload.getNode());
//                    System.out.println(System.nanoTime()+"C3");
//                }
//
//                else {
//                    loadCellRenderer.setCurrentLoading(null);
//                    treeModel.nodeStructureChanged(lazyload.getNode());
//                    System.out.println(System.nanoTime()+"C4");
//                }
                System.out.println(Thread.currentThread()+"VoF");
                //loadCellRenderer.setCurrentLoading(null);
                //treeModel.nodeChanged(lazyload.getNode());
                //lazyload.getNode().setLoading(false);

            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
                System.out.println("Closed");
            }
        });

//        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
//            @Override
//            public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
//
//                LazyLoad lazyload = new LazyLoad(e);
//                loadCellRenderer.setCurrentLoading(lazyload.getCurrentLoading().toString());
//
//                treeModel.nodeChanged(lazyload.getNode());
//                treeModel.nodeStructureChanged(lazyload.getNode());
//
//            }
//
//            @Override
//            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
//
//            }
//        });
    }
}