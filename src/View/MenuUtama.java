/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author Praktek
 */
public class MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        this.setSize(1000, 600);
        setLocationRelativeTo(this);
        Connect.Database.connectionDB();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        MMenu = new javax.swing.JMenu();
        MPelanggan = new javax.swing.JMenuItem();
        Mkategori = new javax.swing.JMenuItem();
        Mbarang = new javax.swing.JMenuItem();
        Mpetugas = new javax.swing.JMenuItem();
        Mukuran = new javax.swing.JMenuItem();
        MTransaksi = new javax.swing.JMenu();
        TBuktiPesan = new javax.swing.JMenuItem();
        MLaporan = new javax.swing.JMenu();
        LPelanggan = new javax.swing.JMenuItem();
        LBarang = new javax.swing.JMenuItem();
        MExit = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MMenu.setText("File Master");

        MPelanggan.setText("Entry pelanggan");
        MPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPelangganActionPerformed(evt);
            }
        });
        MMenu.add(MPelanggan);

        Mkategori.setText("Entry kategori");
        Mkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MkategoriActionPerformed(evt);
            }
        });
        MMenu.add(Mkategori);

        Mbarang.setText("Entry barang");
        Mbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MbarangActionPerformed(evt);
            }
        });
        MMenu.add(Mbarang);

        Mpetugas.setText("Entry petugas");
        Mpetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MpetugasActionPerformed(evt);
            }
        });
        MMenu.add(Mpetugas);

        Mukuran.setText("Entry ukuran barang");
        Mukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MukuranActionPerformed(evt);
            }
        });
        MMenu.add(Mukuran);

        jMenuBar1.add(MMenu);

        MTransaksi.setText("Transaksi");

        TBuktiPesan.setText("Bukti pesanan");
        TBuktiPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBuktiPesanActionPerformed(evt);
            }
        });
        MTransaksi.add(TBuktiPesan);

        jMenuBar1.add(MTransaksi);

        MLaporan.setText("Laporan");

        LPelanggan.setText("Pelanggan");
        MLaporan.add(LPelanggan);

        LBarang.setText("Barang");
        MLaporan.add(LBarang);

        jMenuBar1.add(MLaporan);

        MExit.setText("Exit");
        MExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(MExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPelangganActionPerformed
        // TODO add your handling code here:
        View.Mpelanggan p = new View.Mpelanggan();
        p.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_MPelangganActionPerformed

    private void MkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MkategoriActionPerformed
        // TODO add your handling code here:
        View.Mkategori p = new View.Mkategori();
        p.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_MkategoriActionPerformed

    private void MExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_MExitMouseClicked

    private void MpetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MpetugasActionPerformed
        // TODO add your handling code here:
        View.Mpetugas p = new View.Mpetugas();
        p.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_MpetugasActionPerformed

    private void MbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MbarangActionPerformed
        // TODO add your handling code here:
        View.Mbarang p = new View.Mbarang();
        p.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_MbarangActionPerformed

    private void MukuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MukuranActionPerformed
        // TODO add your handling code here:
        View.Mukuran p = new View.Mukuran();
        p.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_MukuranActionPerformed

    private void TBuktiPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBuktiPesanActionPerformed
        // TODO add your handling code here:
        View.TBuktiPesan t = new View.TBuktiPesan();
        t.setVisible(true);
        setLocationRelativeTo(this);
        t.getTxtkdplg().requestFocus();
    }//GEN-LAST:event_TBuktiPesanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LBarang;
    private javax.swing.JMenuItem LPelanggan;
    private javax.swing.JMenu MExit;
    private javax.swing.JMenu MLaporan;
    private javax.swing.JMenu MMenu;
    private javax.swing.JMenuItem MPelanggan;
    private javax.swing.JMenu MTransaksi;
    private javax.swing.JMenuItem Mbarang;
    private javax.swing.JMenuItem Mkategori;
    private javax.swing.JMenuItem Mpetugas;
    private javax.swing.JMenuItem Mukuran;
    private javax.swing.JMenuItem TBuktiPesan;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
