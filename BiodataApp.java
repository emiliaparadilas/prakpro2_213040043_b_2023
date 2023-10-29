package Pertemuan7;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class BiodataApp extends JFrame {
    private void simpanFile(ArrayList<ArrayList<String>> data) {
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menyimpan file '.txt' ?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if(konfirmasi == JOptionPane.YES_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

                for(ArrayList<String> row : data) {
                    for (String s : row) {
                        writer.write(s + "\t");
                    }

                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file ini");
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan ke file ini.");
                e.printStackTrace();
            }
        }
    }

    public BiodataApp() {
        this.setLayout(null);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 100, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelNomorHP = new JLabel("Nomor HP:");
        labelNomorHP.setBounds(15, 100, 100, 10);

        JTextField textFieldNomorHP = new JTextField();
        textFieldNomorHP.setBounds(15, 120, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki");
        radioButton1.setBounds(15, 180, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 210, 350, 30);

        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 240, 350, 30);

        JTextArea textAreaAlamat = new JTextArea(5, 20);
        textAreaAlamat.setBounds(15, 270, 350, 100);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JButton buttonAdd = new JButton("Simpan");
        buttonAdd.setBounds(15, 380, 100, 40);

        JButton buttonUpdate = new JButton("Edit");
        buttonUpdate.setBounds(120, 380, 100, 40);

        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(225, 380, 100, 40);

        JButton buttonSave = new JButton("Simpan Ke File");
        buttonSave.setBounds(330, 380, 120, 40);

        javax.swing.JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 430, 560, 150);

        MyTableModel tableModel = new MyTableModel();
        table.setModel(tableModel);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText();

                if(radioButton1.isSelected() && radioButton2.isSelected()) {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Hanya satu jenis kelamin dipilih!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                else if(radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                else {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Pilih jenis kelamin terlebih dahulu!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Semua input harus diisi!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int konfirmasi = JOptionPane.showConfirmDialog(BiodataApp.this, "Apakah anda yakin menyimpan data ?",
                            "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if(konfirmasi == JOptionPane.YES_OPTION) {
                        tableModel.add(new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));
                        textFieldNama.setText("");
                        textFieldNomorHP.setText("");
                        textAreaAlamat.setText("");
                        labelRadio.setText("Jenis Kelamin: ");
                    }
                }
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row == -1) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data akan diubah!");
                    return;
                }

                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText();

                if(radioButton1.isSelected() && radioButton2.isSelected()) {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Hanya satu jenis kelamin dipilih!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                else if(radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                else {
                    // Memberikan notifikasi jika jenis kelamin belum dipilih
                    JOptionPane.showMessageDialog(BiodataApp.this, "Pilih jenis kelamin terlebih dahulu!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(nama.isEmpty() || nomorHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Semua input harus diisi!",
                            "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int konfirmasi = JOptionPane.showConfirmDialog(BiodataApp.this, "Apakah anda yakin mengubah data ?",
                            "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if(konfirmasi == JOptionPane.YES_OPTION) {
                        ArrayList<String> data = new ArrayList<String>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat));
                        tableModel.update(data, row);
                        textFieldNama.setText("");
                        textFieldNomorHP.setText("");
                        textAreaAlamat.setText("");
                        labelRadio.setText("Jenis Kelamin: ");
                    }
                }
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >= 0) {
                    int option = JOptionPane.showConfirmDialog(BiodataApp.this, "Apakah anda yakin menghapus data ?",
                            "Konfirmasi", JOptionPane.YES_NO_OPTION);

                    if(option == JOptionPane.YES_OPTION) {
                        tableModel.delete(selectedRow);
                        JOptionPane.showMessageDialog(BiodataApp.this, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Pilih baris yang akan dihapus!",
                            "Konfirmasi", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanFile(tableModel.getData());
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int konfirmasi = JOptionPane.showConfirmDialog(BiodataApp.this,
                        "Anda yakin ingin keluar ?", "Konfirmasi",JOptionPane.YES_NO_OPTION);
                if(konfirmasi == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.add(buttonAdd);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(buttonSave);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelNomorHP);
        this.add(textFieldNomorHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(scrollableTable);

        this.setSize(600, 650);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BiodataApp m = new BiodataApp();
                m.setVisible(true);
            }
        });
    }
}