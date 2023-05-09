/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_ukuran;
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
        model = new DAO_ukuran();
        list = model.getAll();
        header = new String[]{"KODE UKURAN", "NAMA UKURAN"};
        
        form.getTblukuran().setShowGrid(true);
        form.getTblukuran().setShowVerticalLines(true);
        form.getTblukuran().setGridColor(Color.blue);
    }
    
    public void tampilurutcode(){
        Ukuran p = new Ukuran();
        model.autonumber(p);
        form.getTxtkdukuran().setText(String.valueOf(model.autonumber(p)));
    }
    
    public void reset() {
        form.getTxtkdukuran().setText("");
        form.getTxtnmukuran().setText("");
        
        form.getTxtnmukuran().requestFocus();
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
            data[1] = p.getUkuran();
            
            tblModel.addRow(data);
        }
        
        form.getTblukuran().setModel(tblModel);
    }

    public void isiField(int row) {
        form.getTxtkdukuran().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmukuran().setText(list.get(row).getUkuran());
    }
    
    
    public void insert() {
        Ukuran p = new Ukuran();
        p.setKode(Integer.parseInt(form.getTxtkdukuran().getText()));
        p.setUkuran(form.getTxtnmukuran().getText());
        
        model.insert(p);
    }
    
    public void isiTablecari() {
        list = model.getCari(form.getTxtkdukuran().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][] {}, header);
        Object[] data = new Object[header.length];
        for (Ukuran p : list) {
            data[0] = p.getKode();
            data[1] = p.getUkuran();
            
            tblModel.addRow(data);
        }
        
        form.getTblukuran().setModel(tblModel);
    }
}
