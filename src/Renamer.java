import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;

public class Renamer {
    private String oldValue;
    private String newValue;

    public Renamer(String oldValue, String newValue){
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public void renameDirectories() {
        try {
            Boolean result = new File(oldValue).renameTo(new File(newValue));
            if (!result){
                System.out.println("Couldn't rename file " + oldValue+" to " + newValue);
            }
        }
        catch (SecurityException s){
            System.out.println("Security Error, while renaming " + oldValue);
        }
        catch (NullPointerException n){
            System.out.println("NullPointer Error, while renaming " + oldValue);
        }
    }

}
