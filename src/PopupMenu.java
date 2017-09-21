import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class PopupMenu extends JPopupMenu implements ActionListener{


    public PopupMenu() {
        JMenuItem jmiRemove = new JMenuItem("Remove", KeyEvent.VK_R);
        jmiRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        jmiRemove.setToolTipText("Press to remove selected directory");

        jmiRemove.addActionListener(this);

        this.add(jmiRemove);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comStr = e.getActionCommand();

        if(comStr.equals("Remove")) {}
    }
}
