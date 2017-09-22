
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
//            System.out.println(f.getAbsolutePath());
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
//            System.out.println(dir+"pluged");
//
                treeModel.nodeStructureChanged(node);
            }

        final GUI programGUI = new GUI(treeModel);


//        final FileTreeNode[] folderRoot = {new FileTreeNode("UltimateFolderRoot")};
//        folderRoot[0].setAllowsChildren(true);
//
//        FileTreeNode plug = new FileTreeNode("Empty Folder");
//        folderRoot[0].add(plug);
//        final DefaultTreeModel folderModel = new DefaultTreeModel(folderRoot[0]);
//
//        final JTree tree = new JTree(treeModel);
//        tree.setEditable(true);
//        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        tree.setShowsRootHandles(false);
//        tree.setRootVisible(false);
//        final MyRenderer loadCellRenderer = new MyRenderer();
//        tree.setCellRenderer(loadCellRenderer);
//        loadCellRenderer.setPlug(plug.getUserObject().toString());
//
//        final JTree folder = new JTree(folderModel);
//        folder.setEditable(true);
//        folder.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        folder.setShowsRootHandles(false);
//        folder.setRootVisible(false);
//        folder.setCellRenderer(loadCellRenderer);

//
//        final JScrollPane jspTree = new JScrollPane(tree);
//
//        final JScrollPane jspFolder = new JScrollPane(folder);
//        final JFrame jfrm = new JFrame();
//        jfrm.setSize(jspTree.getPreferredSize().width+200,jspTree.getPreferredSize().height);
//        final Container content = jfrm.getContentPane();
//        content.setLayout(new BoxLayout(content,BoxLayout.X_AXIS));
//        content.add(jspTree);
//        content.add(jspFolder);
//        jfrm.setVisible(true);
//        tree.setShowsRootHandles(false);
//        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JLabel jMenuLab = new JLabel();
//        final MenuBar jmb = new MenuBar();
//
//        jfrm.setJMenuBar(jmb);



//        JLabel jLab = new JLabel();
//        jfrm.add(jLab, BorderLayout.SOUTH);
//        jfrm.add(jMenuLab, BorderLayout.SOUTH);
//
//        final PopupMenu jpu = new PopupMenu();
//        jpu.setCurrentFolder("");
//        tree.addMouseListener(new ActionsMenu(jpu));

        programGUI.getTree().addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public synchronized void treeExpanded(TreeExpansionEvent e) {

                final Thread currentThread = Thread.currentThread();

                final LazyLoad lazyload = new LazyLoad(e);

                programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());
                programGUI.getLoadCellRenderer().setCurrentLoadingNode(lazyload.getNode());
                programGUI.getLoadCellRenderer().setCurrentLoadingNodePath(e.getPath());
                FileTreeNode plugNode =(FileTreeNode) lazyload.getNode().getChildAt(0);
                programGUI.getLoadCellRenderer().setPlug(plugNode.getUserObject().toString());
                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
                Thread lazyloadrun = new Thread(lazyload);
                SwingUtilities.invokeLater(lazyload);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        programGUI.getLoadCellRenderer().setCurrentLoading(null);
                        programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
                        try {
                            Thread.sleep(2000);

                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        currentThread.run();
                        programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());

                    }
                });
                lazyload.getNode().setLoading(true);

                programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());

                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TreeModel sub = new DefaultTreeModel(lazyload.getNode());
                        programGUI.setFolder(new JTree(sub));
                        programGUI.getFolder().addMouseListener(new ActionsMenu(programGUI.getJpu()));
                        programGUI.getJpu().setCurrentFolder(lazyload.getDirname());
                        programGUI.getJmb().setCurrentFolder(lazyload.getDirname());
                        programGUI.getFolder().setRootVisible(false);
                        programGUI.getFolder().setEditable(true);
                        programGUI.getFolder().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                        programGUI.getFolder().setShowsRootHandles(false);
                        programGUI.getFolder().setRootVisible(false);
                        programGUI.getFolder().setCellRenderer(programGUI.getLoadCellRenderer());
                        JScrollPane jspSubFolder = new JScrollPane(programGUI.getFolder());
                        programGUI.getContent().add(jspSubFolder);
                        int componentToRemove;
                        if (programGUI.getContent().getComponentZOrder(programGUI.getJspFolder()) > 0) {
                            componentToRemove = programGUI.getContent().getComponentZOrder(programGUI.getJspFolder());
                        }
                        else componentToRemove = programGUI.getContent().getComponentZOrder(jspSubFolder)-1;
                        programGUI.getContent().remove(componentToRemove);
                        programGUI.getContent().validate();
                    }
                });


                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());

            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
                LazyLoad lazyload = new LazyLoad(e);
                lazyload.getNode().setDir(true);
            }
        });

    }
}