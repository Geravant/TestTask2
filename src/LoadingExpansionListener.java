import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingExpansionListener implements TreeExpansionListener{
    private GUI programGUI;

    public LoadingExpansionListener(GUI programGUI){
        this.programGUI = programGUI;
    }

    @Override
    public synchronized void treeExpanded(TreeExpansionEvent e) {

        final Thread currentThread = Thread.currentThread();


        final LazyLoad lazyload = new LazyLoad(e);
        final TreeExpansionEvent event = e;

        programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());
        programGUI.getLoadCellRenderer().setCurrentLoadingNode(lazyload.getNode());
//        programGUI.getLoadCellRenderer().setCurrentLoadingNodePath(e.getPath());
        FileTreeNode plugNode =(FileTreeNode) lazyload.getNode().getChildAt(0);
        programGUI.getLoadCellRenderer().setPlug(plugNode.getUserObject().toString());
        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
//        final Thread lazyloadrun = new Thread(lazyload);

//        SwingUtilities.invokeLater(lazyload);
//        lazyloadrun.run();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

                programGUI.getLoadCellRenderer().setCurrentLoadingNodePath(event.getPath());
                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
                LazyLoad localLazyLoad = lazyload;
                Thread lazyloadrun = new Thread(localLazyLoad);
                lazyloadrun.run();
                programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
                DefaultTreeModel sub = new DefaultTreeModel(lazyload.getNode());
                final TreePath path = event.getPath();
                programGUI.setSubNode(lazyload.getNode());
                programGUI.setSub(sub);
                programGUI.setFolder(new JTree(sub));
                programGUI.getFolder().addMouseListener(new ActionsMenu(programGUI.getJpu()));
                programGUI.getJpu().setCurrentFolder(lazyload.getDirname());
                programGUI.getJmb().setCurrentFolder(lazyload.getDirname());
                programGUI.getJpu().setCurrentFolder(lazyload.getDirname());
                programGUI.getFolder().setRootVisible(false);
                programGUI.getFolder().setEditable(true);
                programGUI.getFolder().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                programGUI.getFolder().setShowsRootHandles(false);
                programGUI.getFolder().setRootVisible(false);
                programGUI.getFolder().setCellRenderer(programGUI.getLoadCellRenderer());
                programGUI.getFolder().addTreeSelectionListener(programGUI.getRenamingTreeSelectionListener());
                programGUI.getSub().addTreeModelListener(programGUI.getRenamingTreeModelListener());
                JScrollPane jspSubFolder = new JScrollPane(programGUI.getFolder());
                programGUI.getContent().add(jspSubFolder);
                int componentToRemove;
                if (programGUI.getContent().getComponentZOrder(programGUI.getJspFolder()) > 0) {
                    componentToRemove = programGUI.getContent().getComponentZOrder(programGUI.getJspFolder());
                }
                else componentToRemove = programGUI.getContent().getComponentZOrder(jspSubFolder)-1;
                programGUI.getContent().remove(componentToRemove);
                programGUI.getContent().validate();
                programGUI.getLoadCellRenderer().setCurrentLoading(null);
                programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
                programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
                programGUI.getFolder().addTreeExpansionListener(new TreeExpansionListener() {
                    @Override
                    public void treeExpanded(TreeExpansionEvent e) {
                        programGUI.getTree().expandPath(path.pathByAddingChild(e.getPath().getLastPathComponent()));
                    }

                    @Override
                    public void treeCollapsed(TreeExpansionEvent e) {

                    }
                });
//                try {
//                    Thread.sleep(2000);
//
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
                programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
                currentThread.run();

            }
        };
        Timer timer = new Timer(2000, listener);
        timer.start();


//            @Override
//            public void run() {
//                System.out.println("Entering lazyload");
//                programGUI.getLoadCellRenderer().setCurrentLoading(null);
//                programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
//                programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
//                try {
//                    Thread.sleep(2000);
//
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                currentThread.run();
//                programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
//
//            }
//        });
        lazyload.getNode().setLoading(true);

        programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());

        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                TreeModel sub = new DefaultTreeModel(lazyload.getNode());
//                programGUI.setFolder(new JTree(sub));
//                programGUI.getFolder().addMouseListener(new ActionsMenu(programGUI.getJpu()));
//                programGUI.getJpu().setCurrentFolder(lazyload.getDirname());
//                programGUI.getJmb().setCurrentFolder(lazyload.getDirname());
//                programGUI.getFolder().setRootVisible(false);
//                programGUI.getFolder().setEditable(true);
//                programGUI.getFolder().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//                programGUI.getFolder().setShowsRootHandles(false);
//                programGUI.getFolder().setRootVisible(false);
//                programGUI.getFolder().setCellRenderer(programGUI.getLoadCellRenderer());
//                JScrollPane jspSubFolder = new JScrollPane(programGUI.getFolder());
//                programGUI.getContent().add(jspSubFolder);
//                int componentToRemove;
//                if (programGUI.getContent().getComponentZOrder(programGUI.getJspFolder()) > 0) {
//                    componentToRemove = programGUI.getContent().getComponentZOrder(programGUI.getJspFolder());
//                }
//                else componentToRemove = programGUI.getContent().getComponentZOrder(jspSubFolder)-1;
//                programGUI.getContent().remove(componentToRemove);
//                programGUI.getContent().validate();
//            }
//        });

//        programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
//        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());

    }
    @Override
    public void treeCollapsed(TreeExpansionEvent e) {
        LazyLoad lazyload = new LazyLoad(e);
        lazyload.getNode().removeAllChildren();
        lazyload.getNode().setDir(true);
        FileTreeNode plug = new FileTreeNode("Empty folder");

        lazyload.getNode().add(plug);
//        if (programGUI.getSub().getRoot() == e.getPath().getLastPathComponent()){
//            System.out.println("indeed");
//            TreePath path = programGUI.getFolder().getPathForRow(0).getParentPath();
//            System.out.println(path.toString());
//            programGUI.getFolder().collapsePath(path);
//        }


    }

    public void setProgramGUI(GUI programGUI) {
        this.programGUI = programGUI;
    }
}
