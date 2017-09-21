import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar implements ActionListener{
    public MenuBar() {
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem jmiRemove = new JMenuItem("Remove",KeyEvent.VK_R);
        jmiRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        jmiRemove.addActionListener(this);
        jmiRemove.setToolTipText("Press to remove selected directory");

        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        jmiExit.addActionListener(this);
        jmiExit.setToolTipText("Press to exit");

        jmFile.add(jmiRemove);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        this.add(jmFile);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comStr = e.getActionCommand();

        if(comStr.equals("Remove")) {}

        if(comStr.equals("Exit")) {System.exit(0);}
    }
}
