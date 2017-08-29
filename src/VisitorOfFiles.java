import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.EnumSet;
import java.util.Vector;

class VisitorOfFiles {
    public static void main(String[] args) {
        String dirname = "C:\\";

        System.out.println(dirname+"\n");

        MyFileVisitor myFileVisitor = new MyFileVisitor(dirname);
        try {
            Files.walkFileTree(Paths.get(dirname), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, myFileVisitor);
        } catch (IOException exc) {
            System.out.println("IO Error");
        }

        //System.out.println(myFileVisitor.getTree());
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(dirname);
        final DefaultTreeModel treeModel = new DefaultTreeModel(root);
        root.setAllowsChildren(true);
        DefaultMutableTreeNode plug = new DefaultMutableTreeNode("Empty Folder");
        root.add(plug);
        final JTree tree = new JTree(treeModel);
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(false);

        JScrollPane jsp = new JScrollPane(tree);
        JFrame jfrm = new JFrame();
        jfrm.setSize(500,500);
        jfrm.add(jsp);
        jfrm.setVisible(true);

        JLabel jLab = new JLabel();
        jfrm.add(jLab, BorderLayout.SOUTH);

        tree.addTreeExpansionListener(new TreeExpansionListener(){
            @Override
            public void treeExpanded(TreeExpansionEvent e) {
                int dirDepth = e.getPath().getPathCount();
                String dirname = e.getPath().getPathComponent(0).toString();
                for(int i=1; i< dirDepth; i++) {
                    dirname = dirname + File.separator+ e.getPath().getPathComponent(i);
                    System.out.println(dirname);
                }

                /*File dir = new File(dirname);
                File[] futureDirs = dir.listFiles();
                for (File file: futureDirs) {
                    if (file.isDirectory()) {

                    }
                }*/
                //dirname.replaceAll(", ", "\\");
                //jLab.setText("Selection is "+ dirname);

                MyFileVisitor myFileVisitor = new MyFileVisitor(dirname);
                try {
                    Files.walkFileTree(Paths.get(dirname), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, myFileVisitor);
                    /*myFileVisitor.setPathes();*/
                    //tree.Getmodel;
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                    Vector treePathes = myFileVisitor.getPathes();
                    if (myFileVisitor.getPathes().size() != 0){
                        node.remove(0);
                    }
                   /* myFileVisitor.updateModel((DefaultTreeModel) tree.getModel(), myFileVisitor.getTop());*/
                    for (int fnum = 0; fnum < treePathes.size(); fnum++) {
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(treePathes.elementAt(fnum));
                        node.add(newNode);

                        String wannaBeDirName = new String();
                        TreeNode[] wannaBeDir = newNode.getPath();
                        for(TreeNode wannaBeDirPart: wannaBeDir) {
                            wannaBeDirName = wannaBeDirName + wannaBeDirPart.toString()+ File.separator;
                            //System.out.println(wannaBeDirName);
                        }
                        File wannaBeDirFile = new File(wannaBeDirName);
                        System.out.println(wannaBeDirName);
                        if (wannaBeDirFile.isDirectory()) {
                            newNode.setAllowsChildren(true);
                            //newNode.add(null);

                            DefaultMutableTreeNode plug = new DefaultMutableTreeNode("Empty folder");
                            newNode.add(plug);
                            treeModel.nodeStructureChanged(newNode);
                            System.out.println('1');

                        }

                        //treeMode
                        // l.insertNodeInto((DefaultMutableTreeNode) treePathes.elementAt(fnum), node, node.getChildCount()-1);
                        treeModel.nodeStructureChanged(node);
                    }

                   //node.add(myFileVisitor.getTop());

                    //tree.makeVisible(e.getPath().pathByAddingChild(myFileVisitor.getTop()));

                } catch (IOException exc) {
                    System.out.println("IO Error");
                }
                /*DefaultMutableTreeNode node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());

                //this.();


                for (TreePath treepath: myFileVisitor.getPathes()) {
                    MutableTreeNode newChild = (MutableTreeNode) treepath;
                    node.add(newChild);
                }*/

            }
            @Override
            public void treeCollapsed(TreeExpansionEvent e) {
                System.out.println("Closed");
            }
        });
    }
}