import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.EnumSet;

class MyFileVisitor extends SimpleFileVisitor<Path> {
    private JTree directoryTree;
    private DefaultMutableTreeNode top;

    MyFileVisitor(String dirname) {
        this.top = new DefaultMutableTreeNode(dirname);

    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException {
        System.out.println(path);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(path);
        top.add(node);
        return FileVisitResult.CONTINUE;
    }

    public JTree getTree() {
        this.directoryTree  = new JTree(top);
        return this.directoryTree;
    }
}


class DirTreeList {
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
        JScrollPane jsp = new JScrollPane(myFileVisitor.getTree());
        JFrame jfrm = new JFrame();
        jfrm.setSize(500,500);
        jfrm.add(jsp);
        jfrm.setVisible(true);
    }
}