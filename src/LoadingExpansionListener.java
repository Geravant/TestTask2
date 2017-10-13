import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

public class LoadingExpansionListener implements TreeExpansionListener{
    private GUI programGUI;

    public LoadingExpansionListener(GUI programGUI){
        this.programGUI = programGUI;
    }

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
                programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
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

        programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());

    }
    @Override
    public void treeCollapsed(TreeExpansionEvent e) {
        LazyLoad lazyload = new LazyLoad(e);
        lazyload.getNode().setDir(true);
    }

    public void setProgramGUI(GUI programGUI) {
        this.programGUI = programGUI;
    }
}
