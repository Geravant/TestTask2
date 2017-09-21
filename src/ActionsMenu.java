import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.util.Arrays;

public class ActionsMenu implements MenuKeyListener {

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String comStr = e.getActionCommand();
//
//        if(comStr.equals("Remove")) {}
//
//        if(comStr.equals("Exit")) {System.exit(0);}
//    }

    @Override
    public void menuKeyTyped(MenuKeyEvent e) {
        String comStr = Arrays.toString(e.getPath());

        if(comStr.equals("Remove")) {}

        if(comStr.equals("Exit")) {System.exit(0);}

        System.out.println(comStr);
    }

    @Override
    public void menuKeyPressed(MenuKeyEvent e) {
        String comStr = Arrays.toString(e.getPath());

        if(comStr.equals("Remove")) {}

        if(comStr.equals("Exit")) {System.exit(0);}

        System.out.println(comStr);
    }

    @Override
    public void menuKeyReleased(MenuKeyEvent e) {

    }
}
