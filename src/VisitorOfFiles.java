import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.EnumSet;

import static javax.swing.tree.TreeSelectionModel.*;


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
        JTree tree = myFileVisitor.getTree();

        JScrollPane jsp = new JScrollPane(tree);
        JFrame jfrm = new JFrame();
        jfrm.setSize(500,500);
        jfrm.add(jsp);
        jfrm.setVisible(true);

        final JLabel jLab = new JLabel();
        jfrm.add(jLab, BorderLayout.SOUTH);

        tree.addTreeSelectionListener(new TreeExpansionListener() {
            @Override
            public void valueChanged(TreeExpansionEvent e) {
                int dirDepth = e.path().getPathCount();
                String dirname = e.getPath().getPathComponent(0).toString();
                for(int i=1; i< dirDepth; i++) {
                    dirname = dirname + "\\"+ e.getPath().getPathComponent(i);
                    System.out.println(dirname);
                }
                //dirname.replaceAll(", ", "\\");
                //jLab.setText("Selection is "+ dirname);

                MyFileVisitor myFileVisitor = new MyFileVisitor(dirname);
                try {
                    Files.walkFileTree(Paths.get(dirname), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, myFileVisitor);
                } catch (IOException exc) {
                    System.out.println("IO Error");
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());

                tree.Getmodel();


                for (TreePath treepath: myFileVisitor.getPathes()) {
                    MutableTreeNode newChild = (MutableTreeNode) treepath;
                    node.add(newChild);
                }

            }
        });
    }
}