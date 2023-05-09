/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connect.Database;
import Model.Kategori;
import Model.Pelanggan;
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
public class DAO_kategori implements Model_DAO<Kategori> {
    public DAO_kategori(){
        connection = Database.connectionDB();
    }
    
    Connection connection;
    String INSERT   = "INSERT INTO kategori_2011501174 (kdkategori, nmkategori) value (?,?)";
    String UPDATE   = "UPDATE kategori_2011501174 SET nmkategori = ? WHERE kdkategori = ?";
    String DELETE    = "DELETE FROM kategori_2011501174 where kdkatgeori = ?";
    String SELECT   = "SELECT * FROM kategori_2011501174";
    String CARI     = "SELECT * FROM kategori_2011501174 WHERE kdkategori LIKE ?";
    String COUNTER  = "SELECT max(kdkategori) as kode FROM kategori_2011501174";

    @Override
    public int autonumber(Kategori object) {
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
    public void insert(Kategori object) {
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
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasul disimpan!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void update(Kategori object) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(UPDATE);            
            statement.setString(1, object.getNama());
            statement.setInt(2, object.getKode());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(DELETE);            
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_kategori.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Kategori> getAll() {
        List<Kategori> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Kategori>();
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Kategori p = new Kategori();
                p.setKode(rs.getInt("kdkategori"));
                p.setNama(rs.getString("nmkategori"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_kategori.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Kategori> getCari(String key) {
        List<Kategori> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Kategori>();
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Kategori p = new Kategori();
                p.setKode(rs.getInt("kdkategori"));
                p.setNama(rs.getString("nmkategori"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_kategori.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
