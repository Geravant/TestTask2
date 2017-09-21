import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener{
    public MenuBar() {
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiRemove = new JMenuItem("Remove");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(this);
        jmiRemove.addActionListener(this);
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
