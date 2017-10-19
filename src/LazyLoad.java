import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Vector;

public class LazyLoad implements Runnable{
    private int dirDepth;
    private String dirname;
    private MyFileVisitor myFileVisitor;
    private FileTreeNode node;
    private FileTreeNode subNode;
    private Object currentLoading;



    LazyLoad(TreeExpansionEvent e){
        dirDepth = e.getPath().getPathCount();
        if (dirDepth > 1) {
            dirname = e.getPath().getPathComponent(1).toString();
            for (int i = 2; i < dirDepth; i++) {
                dirname = dirname + File.separator + e.getPath().getPathComponent(i);
            }
        }
        else {


            dirname = e.getPath().getPathComponent(0).toString();
        }

            myFileVisitor = new MyFileVisitor(dirname);
            node = (FileTreeNode) e.getPath().getLastPathComponent();
            currentLoading = node.getUserObject().toString();


    }



    public void run() {



        try {

            Files.walkFileTree(Paths.get(dirname), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, myFileVisitor);
            Vector treePathes = myFileVisitor.getPathes();
            if (myFileVisitor.getPathes().size() != 0){
                node.remove(0);
            }
            for (int fnum = 0; fnum < treePathes.size(); fnum++) {
                FileTreeNode newNode = new FileTreeNode(treePathes.elementAt(fnum));
                node.add(newNode);

                String wannaBeDirName = new String();
                TreeNode[] wannaBeDir = newNode.getPath();
                for(int i = 1; i< wannaBeDir.length; i++) {
                    TreeNode wannaBeDirPart = wannaBeDir[i];
                    wannaBeDirName = wannaBeDirName + wannaBeDirPart.toString()+ File.separator;
                }
                File wannaBeDirFile = new File(wannaBeDirName);
                if (wannaBeDirFile.isDirectory()) {
                    newNode.setAllowsChildren(true);

                    FileTreeNode plug = new FileTreeNode("Empty folder");

                    newNode.add(plug);

                }

            }

        } catch (IOException exc) {
            System.out.println("IO Error");

        }

//        currentLoading = node.getUserObject().toString();

    }

    public FileTreeNode getNode() {
        return node;
    }


    public Object getCurrentLoading() {
        return currentLoading;
    }

    public Object getCurrentLoading(Object object) {
        object = currentLoading;
        return object;
    }

    public String getDirname() {
        return dirname;
    }

    public FileTreeNode getSubNode() {
        return subNode;
    }

    public void setSubNode(FileTreeNode subNode) {
        this.subNode = subNode;
    }
}
