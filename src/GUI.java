import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

public class GUI {

    public void setFolder(JTree folder) {
        this.folder = folder;
        this.folder.setEditable(true);
        this.folder.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.folder.setShowsRootHandles(false);
        this.folder.setRootVisible(false);
        this.folder.setCellRenderer(loadCellRenderer);
        jpu.setCurrentFolder("");
        this.actionsMenuFolder = new ActionsMenu(this.jpu);
        this.folder.addMouseListener(actionsMenuFolder);
        actionsMenuFolder.setTree(folder);

    }



    private JTree tree;
    private JTree folder;
    private MyRenderer loadCellRenderer;
    private JScrollPane jspTree;
    private JScrollPane jspFolder;
    private JFrame jfrm;
    private Container content;
    private JLabel jMenuLab;
    private JLabel jLab;
    private MenuBar jmb;
    private PopupMenu jpu;
    private DefaultTreeModel treeModel;
    private TreeModel sub;
    private FileTreeNode subNode;
    private FileTreeNode plug;
    private ActionsMenu actionsMenu;
    private ActionsMenu actionsMenuFolder;

    public JTree getTree() {
        return tree;
    }

    public JTree getFolder() {
        return folder;
    }

    public MyRenderer getLoadCellRenderer() {
        return loadCellRenderer;
    }



    public JScrollPane getJspFolder() {
        return jspFolder;
    }


    public Container getContent() {
        return content;
    }



    public MenuBar getJmb() {
        return jmb;
    }

    public PopupMenu getJpu() {
        return jpu;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public GUI(DefaultTreeModel treeModel) {
        tree = new JTree(treeModel);
        this.treeModel = treeModel;

        FileTreeNode[] folderRoot = {new FileTreeNode("UltimateFolderRoot")};
        folderRoot[0].setAllowsChildren(true);
        FileTreeNode plug = new FileTreeNode("Empty Folder");
        folderRoot[0].add(plug);
        DefaultTreeModel folderModel = new DefaultTreeModel(folderRoot[0]);

        folder = new JTree(folderModel);

        loadCellRenderer = new MyRenderer();
        jspTree = new JScrollPane(tree);
        jspFolder = new JScrollPane(folder);
        jfrm = new JFrame();
        content = jfrm.getContentPane();
        jMenuLab = new JLabel();
        jLab = new JLabel();
        jmb = new MenuBar();
        jpu = new PopupMenu();

//        JTextField renamingEditorTextField = new JTextField();
//        RenamingTreeCellEditor renamingTreeCellEditor = new RenamingTreeCellEditor();
//        DefaultTreeCellEditor treeCellEditor = new DefaultTreeCellEditor(tree, loadCellRenderer, renamingTreeCellEditor);
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(false);
        tree.setRootVisible(false);
        tree.setCellRenderer(loadCellRenderer);
        loadCellRenderer.setPlug(plug.getUserObject().toString());

        folder.setEditable(false);
        folder.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        folder.setShowsRootHandles(false);
        folder.setRootVisible(false);
        folder.setCellRenderer(loadCellRenderer);


        jfrm.setSize(jspTree.getPreferredSize().width+400,jspTree.getPreferredSize().height);
        content.setLayout(new BoxLayout(content,BoxLayout.X_AXIS));
        content.add(jspTree);
        content.add(jspFolder);
        jfrm.setVisible(true);
        tree.setShowsRootHandles(false);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        jfrm.setJMenuBar(jmb);



        jfrm.add(jLab, BorderLayout.SOUTH);
        jfrm.add(jMenuLab, BorderLayout.SOUTH);

        jpu.setCurrentFolder("");
        actionsMenu = new ActionsMenu(jpu);
        actionsMenu.setTree(tree);
        tree.addMouseListener(actionsMenu);


    }


    public TreeModel getSub() {
        return sub;
    }

    public void setSub(TreeModel sub) {
        this.sub = sub;
    }

    public FileTreeNode getSubNode() {
        return subNode;
    }

    public void setSubNode(FileTreeNode subNode) {
        this.subNode = subNode;
    }

    public void setActionsMenu(ActionsMenu actionsMenu) {
        this.actionsMenu = actionsMenu;
    }
}
