package Pertemuan7;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class MyTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Nama", "No HP", "Jenis Kelamin", "Alamat"};
    private final ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();


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
        return data.get(row).get(col);
    }

    public boolean isCellEditable(int row, int col) {
        return true;
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

    public void edit(ArrayList<String> value, int row) {
        data.set(row, value);
        fireTableRowsUpdated(row, row);
    }

    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

}