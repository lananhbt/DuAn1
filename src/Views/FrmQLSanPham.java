package Views;

import Services.IManageLoaiSanPhamService;
import Services.IManageSanPhamService;
import ServiceImpl.ManageLoaiSanPhamService;
import ServiceImpl.ManageSanPhamService;
import ViewModels.QLLoaiSanPham;
import ViewModels.QLSanPham;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmQLSanPham extends javax.swing.JFrame {

    private IManageSanPhamService sanPhamService;
    private IManageLoaiSanPhamService loaiSanPhamService;

    public FrmQLSanPham() {
        initComponents();
        this.sanPhamService = new ManageSanPhamService();
        this.loaiSanPhamService = new ManageLoaiSanPhamService();
        List<QLLoaiSanPham> dsLSP = loaiSanPhamService.ALL();
        this.cboMaLSP.getModel();
        String[] sp = new String[dsLSP.size()];
        for (int i = 0; i < dsLSP.size(); i++) {
            sp[i] = dsLSP.get(i).getMaLSP();
        }
        cboMaLSP.setModel(new DefaultComboBoxModel(sp));
        this.loadTable();
    }

    public void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_SP.getModel();
        dtm.setRowCount(0);
        for (QLSanPham sp : this.sanPhamService.ALL()) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getMaLSP(),
                sp.getTenSP(),
                sp.getSoLuong(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getHanSuDung(),};
            dtm.addRow(rowData);
        }
    }

    public void clearForm() {
        this.txtMaSP.setText("");
        this.cboMaLSP.setSelectedIndex(0);
        this.txtTenSP.setText("");
        this.txtSoluong.setText("");
        this.txtGiaNhap.setText("");
        this.txtGiaBan.setText("");
        this.txtHanSuDung.setText("");
    }

    public static Date toDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
        return d;
    }

    private QLSanPham getFormData() {
        String maSP = this.txtMaSP.getText().trim();
        String maLSP = (String) this.cboMaLSP.getSelectedItem();
        String tenSP = this.txtTenSP.getText().trim();
        String soLuongStr = this.txtSoluong.getText().trim();
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "So luong khong duoc am");
                return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "So luong phai la so");
            return null;
        }
        String giaNhapStr = this.txtGiaNhap.getText().trim();
        float giaNhap;
        try {
            giaNhap = Float.parseFloat(giaNhapStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sai dinh dang cua gia nhap");
            return null;
        }
        String giaBanStr = this.txtGiaBan.getText().trim();
        float giaBan;
        try {
            giaBan = Float.parseFloat(giaBanStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sai dinh dang gia ban");
            return null;
        }
        String hanSuDung = this.txtHanSuDung.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        Date d;
        try {
            d = sdf.parse(hanSuDung);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày sinh");
            return null;
        }
        String maQR = this.txtMaQR.getText().trim();

        QLSanPham s = new QLSanPham(maSP, maLSP, tenSP, soLuong, giaNhap, giaBan, d, maQR);
        return s;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_SP = new javax.swing.JTable();
        txtMaSP = new javax.swing.JTextField();
        cboMaLSP = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtHanSuDung = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtMaQR = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel49.setText("Quản lý sản phẩm");

        jLabel21.setText("Mã SP");

        jLabel50.setText("Mã LSP");

        jLabel52.setText("Tên SP");

        jLabel54.setText("Số lượng SP");

        jLabel56.setText("Giá bán");

        jLabel57.setText("Hạn sử dụng");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tbl_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Mã LSP", "Tên SP", "Số lượng", "Giá Nhập", "Giá bán", "Hạn sử dụng"
            }
        ));
        tbl_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_SP);

        cboMaLSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtHanSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHanSuDungActionPerformed(evt);
            }
        });

        jLabel1.setText("Lọc:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("QR Code");

        jButton2.setText("BarCode");

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Giá nhập");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel49))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel52))
                                .addGap(94, 94, 94))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnThem)
                                .addGap(34, 34, 34)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnSua)
                                .addGap(56, 56, 56)
                                .addComponent(btnXoa)
                                .addGap(61, 61, 61)
                                .addComponent(btnTim)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaSP)
                                    .addComponent(cboMaLSP, 0, 165, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(127, 127, 127)
                                        .addComponent(jLabel57))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel3))))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoluong)
                                    .addComponent(txtGiaBan)
                                    .addComponent(txtHanSuDung, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(124, 124, 124)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2))
                                    .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(85, Short.MAX_VALUE))
            .addComponent(jScrollPane6)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(cboMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTim)
                            .addComponent(btnXoa)
                            .addComponent(btnSua)
                            .addComponent(btnThem)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHanSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHanSuDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHanSuDungActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        QLSanPham sp = this.getFormData();
        if (sp == null) {
            return;
        }
        this.sanPhamService.insert(sp);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void tbl_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPMouseClicked
        int row = this.tbl_SP.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSP = this.tbl_SP.getValueAt(row, 0).toString();
        String maLSP = this.tbl_SP.getValueAt(row, 1).toString();
        String tenSP = this.tbl_SP.getValueAt(row, 2).toString();
        String soLuong = this.tbl_SP.getValueAt(row, 3).toString();
        String giaNhap = this.tbl_SP.getValueAt(row, 4).toString();
        String giaBan = this.tbl_SP.getValueAt(row, 5).toString();
        String hanSuDung = this.tbl_SP.getValueAt(row, 6).toString();

        this.txtMaSP.setText(maSP);
        this.cboMaLSP.setSelectedItem(maLSP);
        this.txtTenSP.setText(tenSP);
        this.txtSoluong.setText(soLuong);
        this.txtGiaNhap.setText(giaNhap);
        this.txtGiaBan.setText(giaBan);
        this.txtHanSuDung.setText(hanSuDung);
    }//GEN-LAST:event_tbl_SPMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        QLSanPham sp = this.getFormData();
        if (sp == null) {
            return;
        }
        String maSP = this.txtMaSP.getText();
        this.sanPhamService.update(maSP, sp);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = this.tbl_SP.getSelectedRow();
        if (row == -1) {
            return;
        }
        int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn xóa không");
        if (cofirm != JOptionPane.YES_OPTION) {
            return;
        }
        String id = this.tbl_SP.getValueAt(row, 0).toString();
        this.sanPhamService.delete(id);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String maSP = this.txtMaSP.getText();
        List<QLSanPham> ds = this.sanPhamService.ALL();
        int check = 0;
        if (maSP.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã sản phẩm");
            return;
        } else {
            for (QLSanPham sp : ds) {
                if (sp.getMaSP().equalsIgnoreCase(txtMaSP.getText())) {
                    check++;
                    this.txtMaSP.setText(sp.getMaSP());
                    this.cboMaLSP.setSelectedItem(sp.getMaLSP());
                    this.txtTenSP.setText(sp.getTenSP());
                    this.txtSoluong.setText(String.valueOf(sp.getSoLuong()));
                    this.txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
                    this.txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
                    this.txtHanSuDung.setText(String.valueOf(sp.getHanSuDung()));

                    JOptionPane.showMessageDialog(this, "Tìm thấy sản phẩm");
                    //this.tblSPL.getSelectedRow();
                    return;
                }
            }
        }
        if (check == 0) {
            this.clearForm();
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");

        }

    }//GEN-LAST:event_btnTimActionPerformed

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
            java.util.logging.Logger.getLogger(FrmQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmQLSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboMaLSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbl_SP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtHanSuDung;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
