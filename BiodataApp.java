package Pertemuan7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BiodataApp extends JFrame {

    private void saveToFile(ArrayList<ArrayList<String>> data) {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Apakah Anda yakin ingin menyimpan data ke file?",
                "Konfirmasi Simpan",
                JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter
                        ("C:\\Users\\EMIL\\IdeaProjects\\PraktikumPemogramanII\\src\\Pertemuan7\\data.txt"));
                for (ArrayList<String> row : data) {
                    for (String s : row) {
                        writer.write(s + "\t");
                    }
                    writer.newLine();
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data");
                e.printStackTrace();
            }
        }
    }

    public BiodataApp(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menutup aplikasi ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JLabel labelInput = new JLabel("Nama : ");
        labelInput.setBounds(15,40,350,10);

        JTextField textFieldInput = new JTextField();
        textFieldInput.setBounds(15,60,350,30);

        JLabel labelInputTelp = new JLabel("No. HP : ");
        labelInputTelp.setBounds(15,95,350,10);

        JTextField textFieldInputTelp = new JTextField();
        textFieldInputTelp.setBounds(15,110,350,30);

        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15,150,350,10);

        JRadioButton radioButton1 = new JRadioButton("Laki - Laki");
        radioButton1.setBounds(15,170,350,30);
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15,210,350,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JLabel labelAlamat = new JLabel("Alamat : ");
        labelAlamat.setBounds(15,250,350,10);

        JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15,270,350,110);

        JButton button = new JButton("Simpan");
        button.setBounds(15,400,100,40);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(120,400,100,40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(225,400,100,40);

        JButton buttonSimpan = new JButton("Simpan ke File");
        buttonSimpan.setBounds(330,400,150,40);

        JTable table = new JTable();
        JScrollPane scrolllableTable = new JScrollPane(table);
        scrolllableTable.setBounds(15,450,750,300);

        MyTableModel tableModel = new MyTableModel();
        table.setModel(tableModel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldInput.getText();
                String noHP = textFieldInputTelp.getText();
                String jenisKelamin;
                if (radioButton1.isSelected()) {
                    jenisKelamin = "Laki - Laki";
                } else if (radioButton2.isSelected()) {
                    jenisKelamin = "Perempuan";
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih jenis kelamin terlebih dahulu!");
                    return;
                }
                String alamat = textAreaAlamat.getText();

                if (nama.isEmpty() || noHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tolong isi semua data terlebih dahulu!");
                    return;
                }

//                Ketika button simpan ditekan tampilkan konfirmasi
                if (JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menyimpan data ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                } else {
//                    Jika tombol "No" ditekan maka program akan tetap berjalan
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }

                ArrayList<String> data = new ArrayList<String>(Arrays.asList(nama, noHP, jenisKelamin, alamat));
                tableModel.add(data);

                textFieldInput.setText("");
                textFieldInputTelp.setText("");
                labelRadio.setText("");
                textAreaAlamat.setText("");
            }
        });

        // Edit Data
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang ingin diubah!");
                    return;
                }

                String nama = textFieldInput.getText();
                String noHP = textFieldInputTelp.getText();
                String jenisKelamin;
                if (radioButton1.isSelected()) {
                    jenisKelamin = "Laki - Laki";
                } else if (radioButton2.isSelected()) {
                    jenisKelamin = "Perempuan";
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih jenis kelamin terlebih dahulu!");
                    return;
                }
                String alamat = textAreaAlamat.getText();

                if (nama.isEmpty() || noHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tolong isi semua data terlebih dahulu!");
                    return;
                }

                if (JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin mengubah data ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                } else {
//                    Jika tombol "No" ditekan maka program akan tetap berjalan
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }

                ArrayList<String> data = new ArrayList<String>(Arrays.asList(nama, noHP, jenisKelamin, alamat));
                tableModel.edit(data, row);

                textFieldInput.setText("");
                textFieldInputTelp.setText("");
                labelRadio.setText("");
                textAreaAlamat.setText("");
            }
        });


        // Simpan File
        buttonSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile(tableModel.getData());
            }
        });

        // hapus data
        buttonHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang ingin dihapus!");
                    return;
                }

                if (JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                } else {
//                    Jika tombol "No" ditekan maka program akan tetap berjalan
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                tableModel.remove(row);
            }
        });

        this.add(button);
        this.add(buttonEdit);
        this.add(buttonHapus);
        this.add(buttonSimpan);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(textFieldInput);
        this.add(textFieldInputTelp);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelInput);
        this.add(labelInputTelp);
        this.add(scrolllableTable);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataApp h = new BiodataApp();
                h.setVisible(true);
            }
        });
    }
}