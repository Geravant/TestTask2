import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingExpansionListener implements TreeExpansionListener{
    private GUI programGUI;
    private LazyLoad lazyload;
    private Q

    public LoadingExpansionListener(GUI programGUI){
        this.programGUI = programGUI;
    }


    @Override
    public synchronized void treeExpanded(TreeExpansionEvent e) {

        final Thread currentThread = Thread.currentThread();


        lazyload = new LazyLoad(e);
        final TreeExpansionEvent event = e;

        programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());
        programGUI.getLoadCellRenderer().setCurrentLoadingNode(lazyload.getNode());
        FileTreeNode plugNode =(FileTreeNode) lazyload.getNode().getChildAt(0);
        programGUI.getLoadCellRenderer().setPlug(plugNode.getUserObject().toString());
        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
        programGUI.getLoadCellRenderer().setCurrentLoadingNodePath(event.getPath());
        programGUI.getLoadCellRenderer().setCurrentLoadingRow(programGUI.getTree().getRowForPath(e.getPath()));

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {



                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
                LazyLoad localLazyLoad = lazyload;
                Thread lazyloadrun = new Thread(localLazyLoad);
                lazyloadrun.run();
                programGUI.getTreeModel().nodeStructureChanged(programGUI.getLoadCellRenderer().getCurrentLoadingNode());
                DefaultTreeModel sub = new DefaultTreeModel(lazyload.getNode());
                final TreePath path = event.getPath();
                lazyload.setSubNode(lazyload.getNode());
                programGUI.setSubNode(lazyload.getSubNode());
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
                programGUI.getLoadCellRenderer().getCurrentLoadingRow().remove(0);
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
                programGUI.getLoadCellRenderer().removeCurrentLoading(lazyload.getCurrentLoading().toString());
                programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());
                programGUI.getLoadCellRenderer().getCurrentLoadingNodePath().remove(0);
                currentThread.run();

            }
        };
        Timer timer = new Timer(2000, listener);
        timer.start();


        lazyload.getNode().setLoading(true);

        programGUI.getLoadCellRenderer().setCurrentLoading(lazyload.getCurrentLoading().toString());

        programGUI.getTreeModel().nodeStructureChanged(lazyload.getNode());


    }
    @Override
    public void treeCollapsed(TreeExpansionEvent e) {

        FileTreeNode collapsedDirectory = (FileTreeNode)e.getPath().getLastPathComponent();
        collapsedDirectory.removeAllChildren();
        collapsedDirectory.setDir(true);
        FileTreeNode plug = new FileTreeNode("Empty folder");
        collapsedDirectory.add(plug);



    }

    public void setProgramGUI(GUI programGUI) {
        this.programGUI = programGUI;
    }
}
