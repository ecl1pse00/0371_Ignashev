import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class GUIHome1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cont1(frame);

        frame.setVisible(true);
    }

    private static void cont1(JFrame frame) {

        JPanel panel = new JPanel(new BorderLayout());
        TreeModelTest modelTest = new TreeModelTest();
        JTree tree = new JTree(modelTest);
        tree.setCellRenderer(new TreeCellRendererTest());

        JComboBox comboBox = new JComboBox(File.listRoots());
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                modelTest.setRoot((File)comboBox.getSelectedItem());
                tree.expandRow(0);
                tree.updateUI();
            }
        });

        panel.add(comboBox, BorderLayout.NORTH);
        panel.add(new JScrollPane(tree));

        ArrayList<File> files = new ArrayList<>();
        MyHomeTableModel tableModel = new MyHomeTableModel(files);
        JTable table = new JTable(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, new JScrollPane(table));
        frame.add(splitPane);

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2) {
                    files.clear();
                    File file = (File) ((JTree) e.getSource()).getLastSelectedPathComponent();
                    System.out.println(file.getName());
                    File[] arrfiles = file.listFiles();
                    for (File f : arrfiles) {
                        if (f.isFile()) {
                            files.add(f);
                        }
                    }
                    tableModel.fireTableDataChanged();
                }
            }
        });
    }
}
