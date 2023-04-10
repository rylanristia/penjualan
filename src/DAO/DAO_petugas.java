/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connect.Database;
import Model.Pelanggan;
import Model.Petugas;
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
public class DAO_petugas implements Model_DAO<Petugas>  {
    
    public DAO_petugas(){
        connection = Database.connectionDB();
    }
    
    Connection connection;
    String INSERT   = "INSERT INTO petugas_2011501174 (kdpetugas, nmpetugas, alamatpetugas, telppetugas) value (?,?,?,?)";
    String UPDATE   = "UPDATE petugas_2011501174 SET nmpetugas = ?, alamatpetugas = ?, telppetugas = ? WHERE kdpetugas = ?";
    String DELTE    = "DELTE FROM petugas_2011501174 where kdpetugas = ?";
    String SELECT   = "SELECT * FROM petugas_2011501174";
    String CARI     = "SELECT * FROM petugas_2011501174 WHERE kdpetugas LIKE ?";
    String COUNTER  = "SELECT max(kdpetugas) as kode FROM petugas_2011501174";

    @Override
    public int autonumber(Petugas object) {
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
    public void insert(Petugas object) {
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
                Logger.getLogger(DAO_petugas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void update(Petugas object) {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.prepareStatement(UPDATE);            
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
                Logger.getLogger(DAO_petugas.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DAO_petugas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Petugas> getAll() {
        List<Petugas> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Petugas>();
            statement = (PreparedStatement) connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Petugas p = new Petugas();
                p.setKode(rs.getInt("kdpetugas"));
                p.setNama(rs.getString("nmpetugas"));
                p.setAlamat(rs.getString("alamatpetugas"));
                p.setTelp(rs.getString("telppetugas"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_petugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Petugas> getCari(String key) {
        List<Petugas> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Petugas>();
            statement = (PreparedStatement) connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Petugas p = new Petugas();
                p.setKode(rs.getInt("kdpetugas"));
                p.setNama(rs.getString("nmpetugas"));
                p.setAlamat(rs.getString("alamatpetugas"));
                p.setTelp(rs.getString("telppetugas"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_petugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
