package Pertemuan7;

import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin", "Alamat"};
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ArrayList<String> row = data.get(rowIndex);
        row.set(columnIndex, (String) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void add(ArrayList<String> value) {
        data.add(value);

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(ArrayList<String> newValue, int row) {
        data.set(row, newValue);
        fireTableRowsUpdated(row, row);
    }

    public void delete(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
}