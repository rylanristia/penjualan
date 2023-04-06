/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_pelanggan;
import DAO.Model_DAO;
import Model.Pelanggan;
import View.Mpelanggan;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rylanristia
 */
public class Controller_Pelanggan {
    Mpelanggan form;
    Model_DAO<Pelanggan> model;
    List<Pelanggan> list;
    String[] header;
    
    public Controller_Pelanggan(Mpelanggan form) {
        this.form = form;
        model = new DAO_pelanggan();
        list = model.getAll();
        header = new String[]{"KODE", "NAMA PELANGGAN", "ALAMAT", "TELEPON"};
        
        form.getTblplg().setShowGrid(true);
        form.getTblplg().setShowVerticalLines(true);
        form.getTblplg().setGridColor(Color.blue);
    }
    
    public void tampilurutcode(){
        Pelanggan p = new Pelanggan();
        model.autonumber(p);
        form.getTxtkdplg().setText(String.valueOf(model.autonumber(p)));
    }
    
    public void reset() {
        form.getTxtkdplg().setText("");
        form.getTxtnmplg().setText("");
        form.getTxtalamat().setText("");
        form.getTxttlp().setText("");
        
        form.getTxtnmplg().requestFocus();
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
        for (Pelanggan p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            data[2] = p.getAlamat();
            data[3] = p.getTelp();
            
            tblModel.addRow(data);
        }
        
        form.getTblplg().setModel(tblModel);
    }

    public void isiField(int row) {
        form.getTxtkdplg().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmplg().setText(list.get(row).getNama());
        form.getTxtalamat().setText(list.get(row).getAlamat());
        form.getTxttlp().setText(list.get(row).getTelp());
    }
    
    
    public void insert() {
        Pelanggan p = new Pelanggan();
        p.setKode(Integer.parseInt(form.getTxtkdplg().getText()));
        p.setNama(form.getTxtnmplg().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelp(form.getTxttlp().getText());
        
        model.insert(p);
    }
    
    public void update() {
        Pelanggan p = new Pelanggan();
        p.setKode(Integer.parseInt(form.getTxtkdplg().getText()));
        p.setNama(form.getTxtnmplg().getText());
        p.setAlamat(form.getTxtalamat().getText());
        p.setTelp(form.getTxttlp().getText());
        
        model.update(p);
    }
    
    public void delete() {
        if(!form.getTxtkdplg().getText().trim().isEmpty()){
            int kode = Integer.parseInt(form.getTxtkdplg().getText());
            model.delete(kode);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus!");
        }
    }
    
    public void isiTablecari() {
        list = model.getCari(form.getTxtkdplg().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {}, header);
        Object[] data = new Object[header.length];
        for (Pelanggan p : list) {
            data[0] = p.getKode();
            data[1] = p.getNama();
            data[2] = p.getAlamat();
            data[3] = p.getTelp();
            
            tblModel.addRow(data);
        }
        
        form.getTblplg().setModel(tblModel);
    }
}
