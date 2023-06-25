/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connect.Database;
import Model.BuktiPesan;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS 10
 */
public class DAO_buktipesan implements Model_DAO<BuktiPesan> {
    
    public DAO_buktipesan() {
        connection = Database.connectionDB();
    }
    
    Connection connection;

    @Override
    public int autonumber(BuktiPesan object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String autonumber2() {
        PreparedStatement statement;
        
        int nomor_berikutnya = 0;;
        String urutan = ""; 
        
        try {
            String COUNTER = "select ifnull(max(convert(right(nopesan, 5), signed integer)), 0) as kode," + "ifnull(length(max(convert(right(nopesan, 5)+1, signed integer))), 0) as panjang from buktipesan_2011501174";
            statement = (PreparedStatement) connection.prepareStatement(COUNTER);
            ResultSet rs2 = statement.executeQuery();
            
            if (rs2.next()) {
                nomor_berikutnya = rs2.getInt("kode") + 1;
                if (rs2.getInt("kode") < 0) {
                    if (rs2.getInt("Panjang") == 1 ) {
                        urutan = "BP" + "0000" + nomor_berikutnya;
                    } else if (rs2.getInt("Panjang") == 2) {
                        urutan = "BP" + "000" + nomor_berikutnya;
                    } else if (rs2.getInt("Panjang") == 3) {
                        urutan = "BP" + "00" + nomor_berikutnya;
                    } else if (rs2.getInt("Panjang") == 4) {
                        urutan = "BP" + "0" + nomor_berikutnya;
                    } else if (rs2.getInt("Panjang") == 5) {
                        urutan = "BP" + nomor_berikutnya;
                    } 
                } else {
                    urutan = "BP" + "00001";
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return urutan;
    }

    @Override
    public void insert(BuktiPesan object) {
        PreparedStatement statement;
        
        try {
            String SELECT =  "select * from buktipesan_2011501174 where nopesan = ?";
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            statement.setString(1, object.getNobp());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah dismpan!");
            } else {
                PreparedStatement statement2;
                String INSERT = "insert into buktipesan_2011501174(nopesan, tglpesan, kdplg) values (?, ?, ?)";
                statement2 = (PreparedStatement) connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNobp());
                statement2.setString(2, object.getTglbp());
                statement2.setInt(3, object.getKodeplg());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dismpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertdetil(BuktiPesan object) {
        PreparedStatement statement;
            
        try {
            String INSERTDETIL = "insert into detilpesan_2011501174(nopesan, kdbrg, hrgpesan, jmlpesan) values (?, ?, ?, ?)";
            statement = (PreparedStatement) connection.prepareStatement(INSERTDETIL);
            statement.setString(1, object.getNobp());
            statement.setString(2, object.getKodebarang());
            statement.setInt(3, object.getHarga());
            statement.setInt(4, object.getGty());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updatestok(BuktiPesan object) {
        PreparedStatement statement;
        int stockAkhir = 0;
            
        try {
            String SELECT = "select stock from barang where kdbrg = ?";
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            statement.setString(1, object.getKodebarang());
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                if (rs.getInt("stok") < 0) {
                    JOptionPane.showMessageDialog(null, "Stok kode barang: " + object.getKodebarang() + "kosong!");
                    stockAkhir = 0;
                } else {
                    stockAkhir = rs.getInt("Stok") - object.getGty();
                    
                    PreparedStatement statement2;
                    String UPDATESTOCK = "update barang set stock = ? where kdbrg = ?";
                    statement2 = (PreparedStatement) connection.prepareStatement(UPDATESTOCK);
                    statement2.setInt(1, stockAkhir);
                    statement2.setString(2, object.getKodebarang());
                    statement2.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<BuktiPesan> isicomboplg(){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String TAMPILPELANGGAN = "select * from pelanggan_2011501174 order by nmplg";
            statement = (PreparedStatement) connection.prepareStatement(TAMPILPELANGGAN);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("nmplg"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getkdplg(String namaplg){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIPELANGGAN2 = "select * from pelanggan_2011501174 where nmplg = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIPELANGGAN2);
            statement.setString(1, namaplg);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("kdplg"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getnmplg(Integer kode){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIPELANGGAN = "select * from pelanggan_2011501174 where kdplg = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIPELANGGAN);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            } else {
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("nmplg"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> isicombokategori(){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String TAMPILKATEGORI = "select * from kategori_2011501174 order by nmkategori";
            statement = (PreparedStatement) connection.prepareStatement(TAMPILKATEGORI);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setNamaplg(rs.getString("nmkategori"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getkdkategori(String namakat){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIPELANGGAN2 = "select * from kategori_2011501174 where nmkategori = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIPELANGGAN2);
            statement.setString(1, namakat);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setKodekategori(rs.getInt("kdkategori"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getnmkategori(Integer kode){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIKATEGORI = "select * from kategori_2011501174 where kdkategori = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIKATEGORI);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            } else {
                BuktiPesan b = new BuktiPesan();
                b.setNamakategori(rs.getString("nmkategori"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> isicombobarang(Integer kode){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String TAMPILBARANG = "select * from barang_2011501174 where kdkategori = ? order by nmbrg";
            statement = (PreparedStatement) connection.prepareStatement(TAMPILBARANG);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setNamabarang(rs.getString("nmbrg"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> isicombobarang2(Integer kode){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String TAMPILBARANG = "select * from barang_2011501174 where kdbrg = ? order by nmbrg";
            statement = (PreparedStatement) connection.prepareStatement(TAMPILBARANG);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setNamabarang(rs.getString("nmbrg"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getkdbrg(String namaplg){
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIBARANG2 = "select * from barang_2011501174 where nmbrg = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIBARANG2);
            statement.setString(1, namaplg);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                BuktiPesan b = new BuktiPesan();
                b.setKodebarang(rs.getString("kdbrg"));
                b.setHarga(rs.getInt("hargabrg"));
                b.setStok(rs.getInt("stok"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public List<BuktiPesan> getnmbrg(Integer kode){
        int i = 0;
        PreparedStatement statement;
        List<BuktiPesan> list = null;
        
        try {
            list = new ArrayList();
            String CARIBARANG = "select a.*, b.* from barang_2011501174 a, kategori_2011501174 b where a.kdkategori = b.kdkategori and a.kdbrg = ?";
            statement = (PreparedStatement) connection.prepareStatement(CARIBARANG);
            statement.setInt(1, kode);
            ResultSet rs = statement.executeQuery();
            BuktiPesan b = new BuktiPesan();
            
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                b.setKodekategori(0);
                b.setNamakategori("- Pilih -");
                b.setKodebarang("");
                b.setNamabarang("");
                b.setHarga(0);
                b.setStok(0);
                list.add(b);
            } else {
                b.setKodekategori(rs.getInt("a.kdkategori"));
                b.setNamakategori(rs.getString("nmkategori"));
                b.setNamabarang(rs.getString("a.nmbrg"));
                b.setHarga(rs.getInt("hargabrg"));
                b.setStok(rs.getInt("a.stok"));
                list.add(b);
            }
            
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public void update(BuktiPesan object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BuktiPesan> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BuktiPesan> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
