import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;

public class TreeModelTest implements TreeModel{

    private File root = new File("C:\\");

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File[] files = ((File) parent).listFiles();
        return files[index];
    }

    @Override
    public int getChildCount(Object parent) {
        String[] files = ((File) parent).list();
        return files == null? 0 : files.length;
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((File)node).isFile();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    public void setRoot(File selectedItem) {
        root = selectedItem;
    }
}
