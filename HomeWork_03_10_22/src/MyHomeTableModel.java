import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;


public class MyHomeTableModel extends AbstractTableModel {
    private ArrayList<File> files;

    public MyHomeTableModel(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public int getRowCount() {
        return files.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Name";
            case 1:
                return "Extension";
            case 2:
                return "Size";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 2:
                return Long.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return files.get(rowIndex).getName().substring(0, files.get(rowIndex).getName().lastIndexOf("."));
            case 1:
                return files.get(rowIndex).getName().substring(files.get(rowIndex).getName().lastIndexOf(".")+1);
            case 2:
                return files.get(rowIndex).length();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }
}
