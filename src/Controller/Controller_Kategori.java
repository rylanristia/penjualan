/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_kategori;
import DAO.Model_DAO;
import Model.Kategori;
import View.Mkategori;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WINDOWS 10
 */
public class Controller_Kategori {
    Mkategori form;
    Model_DAO<Kategori> model;
    List<Kategori> list;
    String[] header;
    
    public Controller_Kategori(Mkategori form) {
        this.form = form;
        model = new DAO_kategori();
        list = model.getAll();
        header = new String[]{"KODE", "NAMA KATEGORI"};
        
        form.getTblkategori().setShowGrid(true);
        form.getTblkategori().setShowVerticalLines(true);
        form.getTblkategori().setGridColor(Color.blue);
    }
    
    public void tampilurutcode(){
        Kategori p = new Kategori();
        model.autonumber(p);
        form.getTxtkdkategori().setText(String.valueOf(model.autonumber(p)));
    }
    
    public void reset() {
        form.getTxtkdkategori().setText("");
        form.getTxtnmkategori().setText("");
        
        form.getTxtnmkategori().requestFocus();
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
        for (Kategori p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            
            tblModel.addRow(data);
        }
        
        form.getTblkategori().setModel(tblModel);
    }

    public void isiField(int row) {
        form.getTxtkdkategori().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmkategori().setText(list.get(row).getNama());
    }
    
    
    public void insert() {
        Kategori p = new Kategori();
        p.setKode(Integer.parseInt(form.getTxtkdkategori().getText()));
        p.setNama(form.getTxtnmkategori().getText());
        
        model.insert(p);
    }
    
    public void update() {
        Kategori p = new Kategori();
        p.setKode(Integer.parseInt(form.getTxtkdkategori().getText()));
        p.setNama(form.getTxtnmkategori().getText());
        
        model.update(p);
    }
    
    public void delete() {
        if(!form.getTxtkdkategori().getText().trim().isEmpty()){
            int kode = Integer.parseInt(form.getTxtkdkategori().getText());
            model.delete(kode);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus!");
        }
    }
    
    public void isiTablecari() {
        list = model.getCari(form.getTxtkdkategori().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {}, header);
        Object[] data = new Object[header.length];
        for (Kategori p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            
            tblModel.addRow(data);
        }
        
        form.getTblkategori().setModel(tblModel);
    }
}
