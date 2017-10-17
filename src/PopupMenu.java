import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class PopupMenu extends JPopupMenu implements ActionListener{
    private String currentFolder;

    public PopupMenu() {
        JMenuItem jmiAdd = new JMenuItem("Add", KeyEvent.VK_A);
        jmiAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        jmiAdd.setToolTipText("Press to create a directory");

        jmiAdd.addActionListener(this);

        this.add(jmiAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comStr = e.getActionCommand();

        if(comStr.equals("Add")) {
            System.out.println(currentFolder);
            new File(currentFolder+File.separator+"New Folder").mkdir();
        }
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    public String getCurrentFolder() {return this.currentFolder;}
}
