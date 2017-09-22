import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class GUI {
    public void setTree(JTree tree) {
        this.tree = tree;
        this.tree.setEditable(true);
        this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.tree.setShowsRootHandles(false);
        this.tree.setRootVisible(false);
        this.tree.setCellRenderer(loadCellRenderer);
    }

    public void setFolder(JTree folder) {
        this.folder = folder;
        this.folder.setEditable(true);
        this.folder.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.folder.setShowsRootHandles(false);
        this.folder.setRootVisible(false);
        this.folder.setCellRenderer(loadCellRenderer);
    }

    public void setLoadCellRenderer(MyRenderer loadCellRenderer) {
        this.loadCellRenderer = loadCellRenderer;
    }

    public void setJspTree(JScrollPane jspTree) {
        this.jspTree = jspTree;
    }

    public void setJspFolder(JScrollPane jspFolder) {
        this.jspFolder = jspFolder;
    }

    public void setJfrm(JFrame jfrm) {
        this.jfrm = jfrm;
    }

    public void setContent(Container content) {
        this.content = content;
    }

    public void setjMenuLab(JLabel jMenuLab) {
        this.jMenuLab = jMenuLab;
    }

    public void setjLab(JLabel jLab) {
        this.jLab = jLab;
    }

    public void setJmb(MenuBar jmb) {
        this.jmb = jmb;
    }

    public void setJpu(PopupMenu jpu) {
        this.jpu = jpu;
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
    private DefaultTreeModel sub;
    private FileTreeNode plug;

    public JTree getTree() {
        return tree;
    }

    public JTree getFolder() {
        return folder;
    }

    public MyRenderer getLoadCellRenderer() {
        return loadCellRenderer;
    }

    public JScrollPane getJspTree() {
        return jspTree;
    }

    public JScrollPane getJspFolder() {
        return jspFolder;
    }

    public JFrame getJfrm() {
        return jfrm;
    }

    public Container getContent() {
        return content;
    }

    public JLabel getjMenuLab() {
        return jMenuLab;
    }

    public JLabel getjLab() {
        return jLab;
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

    public DefaultTreeModel getSub() {
        return sub;
    }

    public FileTreeNode getPlug() {
        return plug;
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

        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(false);
        tree.setRootVisible(false);
        tree.setCellRenderer(loadCellRenderer);
        loadCellRenderer.setPlug(plug.getUserObject().toString());

        folder.setEditable(true);
        folder.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        folder.setShowsRootHandles(false);
        folder.setRootVisible(false);
        folder.setCellRenderer(loadCellRenderer);


        jfrm.setSize(jspTree.getPreferredSize().width+200,jspTree.getPreferredSize().height);
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
        tree.addMouseListener(new ActionsMenu(jpu));

    }


}
