import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
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

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                jLab.setText("Selection is "+ e.getPath());
                MyFileVisitor myFileVisitor = new MyFileVisitor("C:\\Users");
                try {
                    Files.walkFileTree(Paths.get("C:\\Users"), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, myFileVisitor);
                } catch (IOException exc) {
                    System.out.println("IO Error");
                }

            }
        });
    }
}