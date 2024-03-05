package Views;

import Services.IManageLoaiSanPhamService;
import ServiceImpl.ManageLoaiSanPhamService;
import ViewModels.QLLoaiSanPham;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmQLLoaiSanPham extends javax.swing.JFrame {

    private IManageLoaiSanPhamService loaiSanPhamService;

    public FrmQLLoaiSanPham() {
        initComponents();
        this.loaiSanPhamService = new ManageLoaiSanPhamService();
        this.loadTable();
    }

    public void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_LSP.getModel();
        dtm.setRowCount(0);
        for (QLLoaiSanPham lsp : this.loaiSanPhamService.ALL()) {
            Object[] rowData = {
                lsp.getMaLSP(),
                lsp.getTenLSP(),
                lsp.getMoTa()
            };
            dtm.addRow(rowData);
        }
    }

    public void clearForm() {
        this.txtMaLSP.setText("");
        this.txtTenLSP.setText("");
        this.txtMoTa.setText("");
    }

    private QLLoaiSanPham getFormData() {
        String maLSP = this.txtMaLSP.getText().trim();
        String tenLSP = this.txtTenLSP.getText().trim();
        String moTa = this.txtMoTa.getText().trim();

        QLLoaiSanPham s = new QLLoaiSanPham(maLSP, tenLSP, moTa);
        return s;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_LSP = new javax.swing.JTable();
        txtMaLSP = new javax.swing.JTextField();
        txtTenLSP = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel58.setText("Quản lý loại sản phẩm");

        jLabel59.setText("Mã LSP");

        jLabel60.setText("Tên LSP");

        jLabel61.setText("Mô tả");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tbl_LSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã LSP", "Tên LSP", "Mô tả"
            }
        ));
        tbl_LSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LSPMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbl_LSP);

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane12.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel58))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel59)
                                        .addComponent(jLabel60))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaLSP)
                                        .addComponent(txtTenLSP, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(btnThem)
                                    .addGap(65, 65, 65)
                                    .addComponent(btnCapNhat)))
                            .addGap(70, 70, 70)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(btnXoa)
                                    .addGap(68, 68, 68)
                                    .addComponent(btnTimKiem))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel61)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(484, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel58)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(txtMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel61)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(txtTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa)
                    .addComponent(btnTimKiem))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1083, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_LSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LSPMouseClicked
        int row = this.tbl_LSP.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maLSP = this.tbl_LSP.getValueAt(row, 0).toString();
        String tenLSP = this.tbl_LSP.getValueAt(row, 1).toString();
        String moTa = this.tbl_LSP.getValueAt(row, 2).toString();

        this.txtMaLSP.setText(maLSP);
        this.txtTenLSP.setText(tenLSP);
        this.txtMoTa.setText(moTa);
    }//GEN-LAST:event_tbl_LSPMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        QLLoaiSanPham sp = this.getFormData();
        if (sp == null) {
            return;
        }
        this.loaiSanPhamService.insert(sp);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        QLLoaiSanPham sp = this.getFormData();
        if (sp == null) {
            return;
        }
        String maLSP = this.txtMaLSP.getText();
        this.loaiSanPhamService.update(maLSP, sp);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = this.tbl_LSP.getSelectedRow();
        if (row == -1) {
            return;
        }
        int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn xóa không");
        if (cofirm != JOptionPane.YES_OPTION) {
            return;
        }
        String id = this.tbl_LSP.getValueAt(row, 0).toString();
        this.loaiSanPhamService.delete(id);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String maLSP = this.txtMaLSP.getText();
        List<QLLoaiSanPham> ds = this.loaiSanPhamService.ALL();
        int check = 0;
        if (maLSP.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã loại sản phẩm");
            return;
        } else {
            for (QLLoaiSanPham sp : ds) {
                if (sp.getMaLSP().equalsIgnoreCase(txtMaLSP.getText())) {
                    check++;
                    this.txtMaLSP.setText(sp.getMaLSP());
                    this.txtTenLSP.setText(sp.getTenLSP());
                    this.txtMoTa.setText(sp.getMoTa());

                    JOptionPane.showMessageDialog(this, "Tìm thấy loại sản phẩm");
                    //this.tblSPL.getSelectedRow();
                    return;
                }
            }
        }
        if (check == 0) {
            this.clearForm();
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");

        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(FrmQLLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmQLLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmQLLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmQLLoaiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmQLLoaiSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JTable tbl_LSP;
    private javax.swing.JTextField txtMaLSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenLSP;
    // End of variables declaration//GEN-END:variables
}
