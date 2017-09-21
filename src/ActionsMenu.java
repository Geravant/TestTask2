
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionsMenu extends MouseAdapter {
    private PopupMenu jpu;

    public ActionsMenu(PopupMenu popupMenu){
        jpu = popupMenu;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()) {
            jpu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger()) {
            jpu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void setJpu(PopupMenu popupMenu) {
        jpu = popupMenu;
    }

}
