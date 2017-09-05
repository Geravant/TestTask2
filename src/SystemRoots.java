import java.io.File;

public class SystemRoots {
    private File[] roots;

    SystemRoots() {
        roots = File.listRoots();

    }

    public File[] getSystemRoots() {
        return this.roots;
    }
}
