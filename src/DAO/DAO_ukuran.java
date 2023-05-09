/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connect.Database;
import Model.Ukuran;
import Model.Ukuran;
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
 * @author Praktek
 */
public class DAO_ukuran implements Model_DAO<Ukuran>{
    
    public DAO_ukuran(){
        connection = Database.connectionDB();
    }
    
    Connection connection;
    String INSERT   = "INSERT INTO ukuran_2011501174 (kdukuran, ukuran) value (?,?)";
    String UPDATE   = "UPDATE barang_2011501174 SET nmbrg = ?, nmbrg = ?, satuan = ?, hargabrg = ?, stok = ?, kdkategori = ? WHERE kdbrg = ?";
    String DELETE    = "DELETE FROM ukuran_2011501174 where kdukuran = ?";
    String SELECT   = "SELECT * FROM ukuran_2011501174";
    String CARI     = "SELECT * FROM ukuran_2011501174 WHERE kdukuran LIKE ?";
    String COUNTER  = "SELECT max(kdukuran) as kode FROM ukuran_2011501174";

    @Override
    public int autonumber(Ukuran object) {
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
    public void insert(Ukuran object) {
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
                statement2.setString(2, object.getUkuran());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasul disimpan!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_ukuran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(INSERT);            
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_ukuran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Ukuran> getAll() {
        List<Ukuran> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Ukuran>();
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Ukuran p = new Ukuran();
                p.setKode(rs.getInt("kdukuran"));
                p.setUkuran(rs.getString("ukuran"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ukuran.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Ukuran> getCari(String key) {
        List<Ukuran> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Ukuran>();
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Ukuran p = new Ukuran();
                p.setKode(rs.getInt("kdbrg"));
                p.setUkuran(rs.getString("ukuran"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ukuran.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void update(Ukuran object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
