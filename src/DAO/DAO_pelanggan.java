/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Pelanggan;
import Connect.Database;
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
public class DAO_pelanggan implements Model_DAO<Pelanggan> {
    
    public DAO_pelanggan(){
        connection = Database.connectionDB();
    }
    
    Connection connection;
    String INSERT   = "INSERT INTO pelanggan_2011501174 (kdplg, nmplg, alamatplg, telpplg) value (?,?,?,?)";
    String UPDATE   = "UPDATE pelanggan_2011501174 SET nmplg = ?, alamatplg = ?, telpplg = ? WHERE kdplg = ?";
    String DELTE    = "DELTE FROM pelanggan_2011501174 where kdplg = ?";
    String SELECT   = "SELECT * FROM pelanggan_2011501174";
    String CARI     = "SELECT * FROM pelanggan_2011501174 WHERE kdplg LIKE ?";
    String COUNTER  = "SELECT max(kdplg) as kode FROM pelanggan_2011501174";

    @Override
    public int autonumber(Pelanggan object) {
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
    public void insert(Pelanggan object) {
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
                statement2.setString(3, object.getAlamat());
                statement2.setString(4, object.getTelp());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasul disimpan!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void update(Pelanggan object) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(INSERT);            
            statement.setString(1, object.getNama());
            statement.setString(2, object.getAlamat());
            statement.setString(3, object.getTelp());
            statement.setInt(4, object.getKode());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasul diubah!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DAO_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Pelanggan>();
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getInt("kdplg"));
                p.setNama(rs.getString("nmplg"));
                p.setAlamat(rs.getString("alamatplg"));
                p.setTelp(rs.getString("telpplg"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Pelanggan> getCari(String key) {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Pelanggan>();
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getInt("kdplg"));
                p.setNama(rs.getString("nmplg"));
                p.setAlamat(rs.getString("alamatplg"));
                p.setTelp(rs.getString("telpplg"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
