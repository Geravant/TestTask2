import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class MenuBar extends JMenuBar implements ActionListener{
    private String currentFolder;

    public MenuBar() {
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem jmiAdd = new JMenuItem("Add",KeyEvent.VK_A);
        jmiAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        jmiAdd.addActionListener(this);
        jmiAdd.setToolTipText("Press to create a directory");

        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        jmiExit.addActionListener(this);
        jmiExit.setToolTipText("Press to exit");

        jmFile.add(jmiAdd);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        this.add(jmFile);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comStr = e.getActionCommand();

        if(comStr.equals("Add")) {if(comStr.equals("Add")) {
            System.out.println(currentFolder);
            new File(currentFolder+File.separator+"New Folder").mkdir();
        }}

        if(comStr.equals("Exit")) {System.exit(0);}
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }
}
