import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyRenderer extends DefaultTreeCellRenderer {
    ImageIcon tutorialIcon = createImageIcon("load.png", "loading icon");
    private Object currentLoading;

    public MyRenderer() {
        super();
    }

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,boolean sel,boolean expanded,boolean leaf,
                                                  int row,boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);
        //FileTreeNode nodeObj = (FileTreeNode)value;
        // check whatever you need to on the node user object
        if (value.toString() == currentLoading) {
            setIcon(tutorialIcon);
        } else {
           // setIcon(tutorialIcon);
        }
        return this;
    }

    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon imageIcon = new ImageIcon(imgURL, description);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(15, 10,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
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
}
