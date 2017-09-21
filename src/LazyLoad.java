import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.DefaultTreeModel;
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
    private Object currentLoading;



    LazyLoad(TreeExpansionEvent e){
        dirDepth = e.getPath().getPathCount();
        dirname = e.getPath().getPathComponent(1).toString();
        for(int i=2; i< dirDepth; i++) {
            dirname = dirname + File.separator+ e.getPath().getPathComponent(i);
            System.out.println(dirname+"1");
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
                System.out.println(node.isLoading());
                System.out.println(newNode.isLoading());

                String wannaBeDirName = new String();
                TreeNode[] wannaBeDir = newNode.getPath();
                for(int i = 1; i< wannaBeDir.length; i++) {
                    TreeNode wannaBeDirPart = wannaBeDir[i];
                    wannaBeDirName = wannaBeDirName + wannaBeDirPart.toString()+ File.separator;
                    System.out.println(wannaBeDirName);
                }
                File wannaBeDirFile = new File(wannaBeDirName);
                System.out.println(wannaBeDirName+"2");
                if (wannaBeDirFile.isDirectory()) {
                    newNode.setAllowsChildren(true);

                    FileTreeNode plug = new FileTreeNode("Empty folder");

                    newNode.add(plug);
                    System.out.println('1');

                }

            }

        } catch (IOException exc) {
            System.out.println("IO Error");
            node.remove(0);

        }

        currentLoading = node.getUserObject().toString();
        Thread.currentThread().interrupt();

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

    public void nodeRendererUpdate(DefaultTreeModel treeModel) {
        treeModel.nodeStructureChanged(this.getNode());
    }

}
