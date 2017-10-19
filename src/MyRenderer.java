import sun.swing.ImageIconUIResource;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Vector;

public class MyRenderer extends DefaultTreeCellRenderer {
    private ImageIcon loadIcon = createImageIcon("load.png", "loading icon");
    private ImageIcon fileIcon = createImageIcon("file.png", "file icon");
    private ImageIcon folderIcon = createImageIcon("folder.png", "file icon");
    private ImageIcon openfolderIcon = createImageIcon("openfolder.png", "file icon");
    private Vector<Object> currentLoading;
    private FileTreeNode currentLoadingNode;
    private Object plug;
    private Vector<TreePath> currentLoadingNodePath;
    private JLabel lblNull = new JLabel();
    private Vector currentLoadingRow;

    public MyRenderer() {
        super();
        setOpenIcon(openfolderIcon);
        setIcon(fileIcon);
        setClosedIcon(folderIcon);
        currentLoading = new Vector<Object>();
        currentLoadingNodePath = new Vector<TreePath>();
        currentLoadingRow = new Vector();
    }

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,boolean sel,boolean expanded,boolean leaf,
                                                  int row,boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);


        Component thingToPaint = this;

            if (currentLoading.contains(value.toString()) & currentLoadingNodePath.contains(tree.getPathForRow(row))) {
                setIcon(loadIcon);
                thingToPaint = this;
            } else if (!expanded) {
                setIcon(folderIcon);
                thingToPaint = this;
            } else if (expanded) {
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
        this.currentLoading.addElement(currentLoading);
    }

    public void removeCurrentLoading(Object currentLoading) {
        this.currentLoading.removeElement(currentLoading);
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

    public Vector<TreePath> getCurrentLoadingNodePath() {
        return currentLoadingNodePath;
    }

    public void setCurrentLoadingNodePath(TreePath currentLoadingNodePath) {
        this.currentLoadingNodePath.addElement(currentLoadingNodePath);
    }

    public void setPlug(Object plug) {
        this.plug = plug;
    }

    public void setCurrentLoadingRow(int currentLoadingRow) {
        this.currentLoadingRow.add(currentLoadingRow);
    }

    public void removeCurrentLoadingRow(int currentLoadingRow){
        this.currentLoadingRow.remove(currentLoadingRow);
    }

    public Vector getCurrentLoadingRow() {
        return  currentLoadingRow;
    }
}
