import sun.swing.ImageIconUIResource;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

public class MyRenderer extends DefaultTreeCellRenderer {
    ImageIcon loadIcon = createImageIcon("load.png", "loading icon");
    ImageIcon fileIcon = createImageIcon("file.png", "file icon");
    ImageIcon folderIcon = createImageIcon("folder.png", "file icon");
    ImageIcon openfolderIcon = createImageIcon("openfolder.png", "file icon");
    private Object currentLoading;
    private FileTreeNode currentLoadingNode;
    private Object plug;
    private TreePath currentLoadingNodePath;
    private JLabel lblNull = new JLabel();

    public MyRenderer() {
        super();
        currentLoading = new Object();
    }

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,boolean sel,boolean expanded,boolean leaf,
                                                  int row,boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);


        Component thingToPaint = this;

        if (value.toString() == currentLoading) {
            setIcon(loadIcon);
            thingToPaint = this;
        }


        else if (!expanded){
            setIcon(folderIcon);
            thingToPaint = this;
        }

        else if (expanded){
            setIcon(openfolderIcon);
            thingToPaint = this;
        }

        if (leaf) {
            setIcon(fileIcon);
            thingToPaint = this;
        }
        if (value.toString() == plug) {
            thingToPaint = lblNull;
        }


        return thingToPaint;

    }

    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon imageIcon = new ImageIcon(imgURL, description);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back
            return imageIcon;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    public void setCurrentLoading(Object currentLoading) {
        this.currentLoading = currentLoading;
    }

    public Object getCurrentLoading() {
        return currentLoading;
    }

    public FileTreeNode getCurrentLoadingNode() {
        return currentLoadingNode;
    }

    public void setCurrentLoadingNode(FileTreeNode currentLoadingNode) {
        this.currentLoadingNode = currentLoadingNode;
    }

    public TreePath getCurrentLoadingNodePath() {
        return currentLoadingNodePath;
    }

    public void setCurrentLoadingNodePath(TreePath currentLoadingNodePath) {
        this.currentLoadingNodePath = currentLoadingNodePath;
    }

    public void setPlug(Object plug) {
        this.plug = plug;
    }
}
