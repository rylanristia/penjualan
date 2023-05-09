/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_barang;
import DAO.Model_DAO;
import Model.Ukuran;
import View.Mukuran;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Praktek
 */
public class Controller_Ukuran {
    Mukuran form;
    Model_DAO<Ukuran> model;
    List<Ukuran> list;
    String[] header;
    
    public Controller_Ukuran(Mukuran form) {
        this.form = form;
        model = new DAO_barang();
        list = model.getAll();
        header = new String[]{"KODE BARANG", "KATEGORI BARANG", "NAMA BARANG", "SATUAN", "STOK", "HARGA BARANG"};
        
        form.getTblbrg().setShowGrid(true);
        form.getTblbrg().setShowVerticalLines(true);
        form.getTblbrg().setGridColor(Color.blue);
    }
    
    public void tampilurutcode(){
        Ukuran p = new Ukuran();
        model.autonumber(p);
        form.getTxtkdbrg().setText(String.valueOf(model.autonumber(p)));
    }
    
    public void reset() {
        form.getTxtkdbrg().setText("");
        form.getTxtnmbrg().setText("");
        form.getTxtsatuan().setText("");
        form.getTxtstok().setText("");
        form.getTxtharga().setText("");
        form.getTxtkategori().setText("");
        
        form.getTxtnmbrg().requestFocus();
        tampilurutcode();
        isiTable();
    }
    
    public void isiTable(){
        list = model.getAll();
        
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {}, header) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        Object[] data = new Object[header.length];
        for (Ukuran p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            data[2] = p.getSatuan();
            data[3] = p.getStok();
            data[4] = p.getHarga();
            data[5] = p.getKategori();
            
            tblModel.addRow(data);
        }
        
        form.getTblbrg().setModel(tblModel);
    }

    public void isiField(int row) {
        form.getTxtkdbrg().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmbrg().setText(list.get(row).getNama());
        form.getTxtsatuan().setText(list.get(row).getSatuan());
        form.getTxtstok().setText(String.valueOf(list.get(row).getStok()));
        form.getTxtharga().setText(String.valueOf(list.get(row).getHarga()));
        form.getTxtkategori().setText(String.valueOf(list.get(row).getHarga()));
    }
    
    
    public void insert() {
        Ukuran p = new Ukuran();
        p.setKode(Integer.parseInt(form.getTxtkdbrg().getText()));
        p.setNama(form.getTxtnmbrg().getText());
        p.setSatuan(form.getTxtsatuan().getText());
        p.setStok(Integer.parseInt(form.getTxtstok().getText()));
        p.setHarga(Integer.parseInt(form.getTxtharga().getText()));
        p.setKategori(form.getTxtkategori().getText());
        
        model.insert(p);
    }
    
    public void update() {
        Ukuran p = new Ukuran();
        p.setKode(Integer.parseInt(form.getTxtkdbrg().getText()));
        p.setNama(form.getTxtnmbrg().getText());
        p.setSatuan(form.getTxtsatuan().getText());
        p.setStok(Integer.parseInt(form.getTxtstok().getText()));
        p.setHarga(Integer.parseInt(form.getTxtharga().getText()));
        p.setKategori(form.getTxtkategori().getText());
        
        model.update(p);
    }
    
    public void delete() {
        if(!form.getTxtkdbrg().getText().trim().isEmpty()){
            int kode = Integer.parseInt(form.getTxtkdbrg().getText());
            model.delete(kode);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus!");
        }
    }
    
    public void isiTablecari() {
        list = model.getCari(form.getTxtkdbrg().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {}, header);
        Object[] data = new Object[header.length];
        for (Ukuran p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            data[2] = p.getSatuan();
            data[3] = p.getStok();
            data[4] = p.getHarga();
            data[5] = p.getKategori();
            
            tblModel.addRow(data);
        }
        
        form.getTblbrg().setModel(tblModel);
    }
}
