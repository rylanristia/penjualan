/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connect.Database;
import Model.Barang;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WINDOWS 10
 */
public class DAO_barang implements Model_DAO<Barang> {
    
    public DAO_barang(){
        connection = Database.connectionDB();
    }
    
    Connection connection;
    String INSERT   = "INSERT INTO barang_2011501174 (kdbrg, nmbrg, satuan, hargabrg, stok, kdkategori) value (?,?,?,?,?,?)";
    String UPDATE   = "UPDATE barang_2011501174 SET nmbrg = ?, nmbrg = ?, satuan = ?, hargabrg = ?, stok = ?, kdkategori = ? WHERE kdbrg = ?";
    String DELTE    = "DELTE FROM barang_2011501174 where kdbrg = ?";
    String SELECT   = "SELECT * FROM barang_2011501174";
    String CARI     = "SELECT * FROM barang_2011501174 WHERE kdbrg LIKE ?";
    String COUNTER  = "SELECT max(kdbrg) as kode FROM barang_2011501174";

    @Override
    public int autonumber(Barang object) {
        PreparedStatement statement = null;
        int nomor = 0;
        try {
            statement = (PreparedStatement) connection.prepareStatement(COUNTER);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                nomor = rs.getInt("Kode") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nomor;
    }

    @Override
    public void insert(Barang object) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setInt(1, object.getKode());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan");
            }else{
                PreparedStatement statement2 = null;
                statement2 = (PreparedStatement) connection.prepareStatement(INSERT);
                statement2.setInt(1, object.getKode());
                statement2.setString(2, object.getNama());
                statement2.setString(3, object.getSatuan());
                statement2.setInt(4, object.getHarga());
                statement2.setInt(5, object.getStok());
                statement2.setString(6, object.getKategori());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasul disimpan!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void update(Barang object) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(UPDATE);            
            statement.setString(1, object.getNama());
            statement.setInt(2, object.getKode());
            statement.setString(3, object.getSatuan());
            statement.setInt(4, object.getHarga());
            statement.setInt(5, object.getStok());
            statement.setString(6, object.getKategori());
            statement.setInt(7, object.getKode());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(DELTE);            
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Barang> getAll() {
        List<Barang> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Barang>();
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Barang p = new Barang();
                p.setKode(rs.getInt("kdbrg"));
                p.setNama(rs.getString("nmbrg"));
                p.setSatuan(rs.getString("satuan"));
                p.setStok(rs.getInt("stok"));
                p.setHarga(rs.getInt("hargabrg"));
                p.setKategori(rs.getString("kdkategori"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Barang> getCari(String key) {
        List<Barang> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Barang>();
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Barang p = new Barang();
                p.setKode(rs.getInt("kdbrg"));
                p.setNama(rs.getString("nmbrg"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
