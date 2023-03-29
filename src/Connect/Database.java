/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Praktek
 */
public class Database {
    
    static Properties mypanel;
    static String driver, database, user, password;
    static Connection conn;
    
    public static Connection connectionDB(){
        if(conn == null) {
            try {
                mypanel = new Properties();
                mypanel.load(new FileInputStream("lib/database.ini"));
                driver    = mypanel.getProperty("DBDriver");
                database  =  mypanel.getProperty("DBDatabase");
                user  =  mypanel.getProperty("DBUsername");
                password  =  mypanel.getProperty("DBPassword");
            
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(database,user,password);                
                JOptionPane.showMessageDialog(null,"Koneksi Berhasil!","Pesan",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {                
                JOptionPane.showMessageDialog(null,"Koneksi Tidak Berhasil!","Pesan",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Error : " + ex.getMessage());
            } 
        }
        return conn;
    }
}
