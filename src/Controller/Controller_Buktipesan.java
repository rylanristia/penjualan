/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_buktipesan;
import Model.BuktiPesan;
import View.TBuktiPesan;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WINDOWS 10
 */
public class Controller_Buktipesan {
    
    TBuktiPesan form;
    DAO_buktipesan model;
    
    String[] header = new String[]{"KODE KATEGORI", "NAMA KATEGORI", "KODE BARANG", "HARGA", "QTY", "TOTAL"};
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
    
    public boolean isCellEdittable(int rowIndex, int columnIndex){
        return false;
    }
    
    public Controller_Buktipesan(TBuktiPesan form){
        this.form = form;
        model = new DAO_buktipesan();
        form.getTbldetil().setModel(tblModel);
        form.getTbldetil().setShowGrid(true);
        form.getTbldetil().setShowVerticalLines(true);
        form.getTbldetil().setGridColor(Color.blue);
    }
    
    public void tampilurutankode(){
        form.getTxtnobp().setText(model.autonumber2());
    }
    
    public void reset(){
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglbp().setText(String.valueOf(tgl.format(new Date())));
        
        form.getTxttotal().setText("Rp. 0");
        form.getCmbpelanggan().setSelectedIndex(0);
        form.getCmbkategori().setSelectedIndex(0);
        form.getCmbbarang().setSelectedIndex(0);
        form.getTxtkdplg().setText("");
        form.getTxtkdplg().setText("");
        form.getTxtkdbrg().setText("");
        form.getTxtkdkategori().setText("");
        form.getTxtharga().setText("");
        form.getTxtqty().setText("");
        form.getTxtstok().setText("");
        
        tampilurutankode();
        isicombopelanggan();
        isicombokategori();
        tblModel.setRowCount(0);
        form.getTxtkdplg().requestFocus();
        form.getTxtnobp().setEditable(false);
        form.getTxttglbp().setEditable(false);
        form.getTxtstok().setEditable(false);
    }
    
    public void reset2(){
        form.getTxtharga().setText("");
        form.getTxtqty().setText("");
        form.getTxtstok().setText("");
        form.getTxtkdbrg().requestFocus();
        form.getTxtstok().setEditable(false);
    }
    
    public void reset3(){
       form.getCmbkategori().setSelectedIndex(0);
       form.getCmbbarang().setSelectedIndex(0);
       form.getTxtkdbrg().setText("");
       form.getTxtkdkategori().setText("");
       form.getTxtharga().setText("");
       form.getTxtstok().setText("");
       form.getTxtkdkategori().requestFocus();
       form.getTxtstok().setEditable(false);
    }
    
    public void resetrow(){
        int[] selectedrows = form.getTbldetil().getSelectedRows();
        if (selectedrows.length < 0){
            for (int i = selectedrows.length - 1; i >= 0; i--) {
                tblModel.removeRow(selectedrows[i]);
            }
        }
        form.getTxtkdbrg().requestFocus();
    }
    
    public void isitable(){
        int jmlbaris = tblModel.getRowCount();
        int i, datasama = 0;
        int stok = 1;
        
        if ((form.getTxtqty().getText().isEmpty() == true) || (Integer.parseInt(form.getTxtqty().getText()) > Integer.parseInt(form.getTxtstok().getText()))) {
            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong atau melebihi ketersediaan");
            stok = 0;
            
        }
        
        if (!form.getTxtkdkategori().getText().isEmpty() && stok > 0 ){
            if (jmlbaris == 0){
                tblModel.addRow(new Object[] {
                    form.getTxtkdkategori().getText(),
                    form.getCmbkategori().getSelectedItem().toString(),
                    form.getTxtkdbrg().getText(),
                    form.getCmbbarang().getSelectedItem().toString(),
                    form.getTxtharga().getText(),
                    form.getTxtqty().getText(),
                    (Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText()))
                });
            } else {
                for ( i = 0; i < jmlbaris; i++) {
                    if (form.getTxtkdbrg().getText().equals(tblModel.getValueAt(i, 2).toString())) {
                        datasama = 1;
                        JOptionPane.showMessageDialog(null, "Kode barang: " + tblModel.getValueAt(i, 2).toString() + "sudah pernah ada dan akan diupdate dengan data terbaru!");
                        tblModel.setValueAt(form.getTxtkdkategori().getText(), i, 0);
                        tblModel.setValueAt(form.getCmbkategori().getSelectedItem(), i, 1);
                        tblModel.setValueAt(form.getTxtkdbrg().getText(), i, 2);
                        tblModel.setValueAt(form.getCmbbarang().getSelectedItem(), i, 3);
                        tblModel.setValueAt(form.getTxtharga().getText(), i, 4);
                        tblModel.setValueAt(form.getTxtqty().getText(), i, 5);
                        tblModel.setValueAt((Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText())), i, 6);
                        
                        break;
                    } else {
                        datasama = 0;
                    }
                }
                
                if (datasama == 0) {
                    tblModel.addRow(new Object[] {
                        form.getTxtkdkategori().getText(),
                        form.getCmbkategori().getSelectedItem().toString(),
                        form.getTxtkdbrg().getText(),
                        form.getCmbbarang().getSelectedItem().toString(),
                        form.getTxtharga().getText(),
                        form.getTxtqty().getText(),
                        (Integer.parseInt(form.getTxtharga().getText()) * Integer.parseInt(form.getTxtqty().getText()))
                    });
                }
            }
        }
    }
    
    public void hitung_grandtotal(){
        int jmlbaris = tblModel.getRowCount();
        int total = 0;
        int Amount = 0;
        DecimalFormat konversi = new DecimalFormat("###,###,###.00");
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            Amount = Integer.parseInt(String.valueOf(tblModel.getValueAt(i, 6).toString()));
            total = Amount + total;
        }
        
        if (jmlbaris != 0) {
            form.getTxttotal().setText("Rp. " + konversi.format(Integer.parseInt(String.valueOf(total))));
        } else {
            form.getTxttotal().setText("Rp. 0");
        }
    }
    
    public void isifield(int row) {
        form.getTxtkdkategori().setText(String.valueOf(tblModel.getValueAt(row, 0)));
        form.getCmbkategori().setSelectedItem(tblModel.getValueAt(row, 1).toString());
        form.getTxtkdbrg().setText(tblModel.getValueAt(row, 2).toString());
        form.getCmbbarang().setSelectedItem(tblModel.getValueAt(row, 3).toString());
        form.getTxtharga().setText(String.valueOf(tblModel.getValueAt(row, 4)));
        form.getTxtqty().setText(String.valueOf(tblModel.getValueAt(row, 5)));
    }
    
    public void isicombopelanggan(){
        form.getCmbpelanggan().removeAllItems();
        form.getCmbpelanggan().addItem("- Pilih -");
        for (BuktiPesan b : model.isicomboplg()){
            form.getCmbpelanggan().addItem(b.getNamaplg());
        }
    }
    
    public void tampilkdplg(){
        if (form.getCmbpelanggan().getSelectedIndex() != 0) {
            for(BuktiPesan b : model.getkdplg(form.getCmbpelanggan().getSelectedItem().toString())){
                form.getTxtkdplg().setText(String.valueOf(b.getKodeplg()));
            }
        }
    }
    
    public void tampilnmplg(){
        for(BuktiPesan b : model.getnmplg(Integer.parseInt(form.getTxtkdplg().getText()))){
            form.getCmbpelanggan().setSelectedItem(b.getNamaplg());
        }
    }
    
    public void isicombokategori(){
        form.getCmbkategori().removeAllItems();
        form.getCmbkategori().addItem("- Pilih -");
        for (BuktiPesan b : model.isicombokategori()){
            form.getCmbkategori().addItem(b.getNamakategori());
        }
    }
    
    public void tampilkdkategori(){
        if (form.getCmbkategori().getSelectedIndex() != 0) {
            for(BuktiPesan b : model.getkdkategori(form.getCmbkategori().getSelectedItem().toString())){
                form.getTxtkdkategori().setText(String.valueOf(b.getKodekategori()));
            }
        }
    }
    
    public void tampilnmkategori(){
        for(BuktiPesan b : model.getnmkategori(Integer.parseInt(form.getTxtkdkategori().getText()))){
            form.getCmbkategori().setSelectedItem(b.getNamakategori());
        }
    }
    
    public void isicombobarang(){
        form.getCmbbarang().removeAllItems();
        form.getCmbbarang().addItem("- Pilih -");
        for (BuktiPesan b : model.isicombobarang(Integer.parseInt(form.getTxtkdkategori().getText()))){
            form.getCmbbarang().addItem(b.getNamakategori());
        }
    }
    
    public void isicombobarang2(){
        form.getCmbbarang().removeAllItems();
        form.getCmbbarang().addItem("- Pilih -");
        for (BuktiPesan b : model.isicombobarang2(Integer.parseInt(form.getTxtkdbrg().getText()))){
            form.getCmbbarang().addItem(b.getNamabarang());
            form.getCmbbarang().setSelectedItem(b.getNamabarang());
        }
    }
    
    public void tampilkdbarang(){
        if (form.getCmbkategori().getSelectedIndex() != 0) {
            for (BuktiPesan b : model.getkdbrg(form.getCmbbarang().getSelectedItem().toString())){
                form.getTxtkdbrg().setText(String.valueOf(b.getKodebarang()));
                form.getTxtharga().setText(String.valueOf(b.getHarga()));
                form.getTxtstok().setText(String.valueOf(b.getStok()));
            }
        }
    }
    
    public void tampilnmbarang(){
        for (BuktiPesan b : model.getnmbrg(Integer.parseInt(form.getTxtkdbrg().getText()))) {
            form.getCmbbarang().setSelectedItem(b.getNamabarang());
            form.getCmbkategori().setSelectedItem(b.getNamakategori());
            form.getTxtkdkategori().setText(String.valueOf(b.getKodekategori()));
            form.getTxtharga().setText(String.valueOf(b.getHarga()));
            form.getTxtstok().setText(String.valueOf(b.getStok()));
            
            if (b.getKodebarang().equals("")){
                form.getTxtkdbrg().setText("");
            }
        }
    }
    
    public void simpan_transaksi(){
        BuktiPesan b = new BuktiPesan();
        b.setNobp(form.getTxtnobp().getText());
        b.setTglbp(form.getTxttglbp().getText());
        b.setKodeplg(Integer.parseInt(form.getTxtkdplg().getText()));
        model.insert(b);
    }
    
    public void simpan_detiltransaksi(){
        int jmlbaris = tblModel.getRowCount();
        int simpan = 0;
        int i = 0;
        for (i = 0; i < jmlbaris; i++){
            BuktiPesan b = new BuktiPesan();
            
            b.setNobp(form.getTxtnobp().getText());
            b.setKodebarang(tblModel.getValueAt(i, 2).toString());
            b.setHarga(Integer.parseInt(tblModel.getValueAt(i, 4).toString()));
            b.setGty(Integer.parseInt(tblModel.getValueAt(i, 5).toString()));
            model.insertdetil(b);
            model.updatestok(b);
            simpan = simpan + 1;
        }
        
        if (simpan > 0){
            JOptionPane.showMessageDialog(null, "Detil belanja berhasil disimpan dan stok barang sudah diubah!");
        }
    }
    
}
