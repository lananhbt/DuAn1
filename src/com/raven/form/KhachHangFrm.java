/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModels.KhachHang;
import ServiceImpl.KhachHangSV;
import ServiceImpl.NguoiDungServiceImpl;
import Services.NguoiDungService;
import ViewModels.QLKH;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHangFrm extends javax.swing.JPanel {

    private final KhachHangSV khSV;
    DefaultTableModel model;
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private DomainModels.dangNhap n;
    
    public KhachHangFrm() {
        initComponents();
        khSV = new KhachHangSV();
        PQ();
        model = new DefaultTableModel();
        model = (DefaultTableModel) tblKH.getModel();
        model.setRowCount(0);
        loadtable();
        this.clearForm();
    }

    public void setStatus(boolean insertable){ 
        //txtMaCD.setEditable(insertable); 
        btnClear.setEnabled(!insertable); 
        btnSua.setEnabled(!insertable); 
        btnThem.setEnabled(!insertable); 
        btnTK.setEnabled(!insertable); 
        btnXoa.setEnabled(!insertable); 
//        btnFisrt.setEnabled(!insertable && first); 
//        btnPrev.setEnabled(!insertable && first); 
//        btnLast.setEnabled(!insertable && last); 
//        btnNext.setEnabled(!insertable && last); 
    }    
    public void PQ(){
        if(nguoiDungService.findND(n.getCurrentLoginUsername()).getChucVu().equals("Quản lý")){
            setStatus(false);
        }else{
            setStatus(true);
        }
    }
    void loadtable() {
            List<QLKH> ql = khSV.findALL();
            if (ql == null) {
                JOptionPane.showMessageDialog(null, "lỗi");
                return;
            }
            for (QLKH qlkh : ql) {
                model.addRow(new Object[]{
                    qlkh.getMaKH(),
                    qlkh.getTenKH(),
                    qlkh.getGioiTinh(),
                    qlkh.getDiaChi(),
                    qlkh.getNgaySinh(),
                    qlkh.getSdt(),
                    qlkh.getNgayDki()
                });
            }


    }

    void clearForm() {
        String a = this.khSV.findALL().size() + 1 + "";
        this.txtMa.setText("KH" + a);
        this.txtTen.setText("");
        this.txtDiaChi.setText("");
        this.txtSDT.setText("");
        this.txtNgaySinh.setText("");
        this.txtNgayDki.setText("");
        this.rdbNam.setSelected(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtNgayDki = new javax.swing.JTextField();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        txtMa = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Quản lý khách hàng");

        jLabel4.setText("Mã KH");

        jLabel5.setText("Tên KH");

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Giới tính");

        jLabel8.setText("Ngày sinh");

        jLabel9.setText("SĐT");

        jLabel10.setText("Ngày đăng ký");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTK.setText("Tìm kiếm");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Giới tính ", "Địa chỉ", "Ngày sinh", "sđt", "Ngày đăng ký"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        buttonGroup1.add(rdbNam);
        rdbNam.setText("Nam ");

        buttonGroup1.add(rdbNu);
        rdbNu.setText("Nữ");

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setText("--");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rdbNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdbNu))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(203, 203, 203)
                                        .addComponent(jLabel3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(btnSua)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnXoa)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnTK)))))
                        .addGap(98, 98, 98)
                        .addComponent(btnClear))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNgayDki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNgayDki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rdbNam)
                    .addComponent(rdbNu))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnTK)
                    .addComponent(btnClear))
                .addGap(90, 90, 90)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String Ma = txtMa.getText();
        String Ten = txtTen.getText();
        String GioiTinh = "";
        String DiaChi = txtDiaChi.getText();
        String NgaySinh = txtNgaySinh.getText();
        String Sdt = txtSDT.getText();
        String NgayDki = txtNgayDki.getText();
        String dangSDT = "0\\d{9,10}";
        if (rdbNam.isSelected() == true) {
            GioiTinh = "NAM";
        } else {
            GioiTinh = "NỮ";
        }

        if (Ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "mã ko đc để trống");
            return;
        }
        if (Ten.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "tên ko đc để trống");
            return;
        }
        if (DiaChi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "địa chỉ ko đc để trống");
            return;
        }
        if (NgaySinh.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ngày sinh ko đc để trống");
            return;
        }
        if (Sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sdt ko đc để trống");
            return;
        }
        if (NgayDki.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ngày đăng kí ko đc để trống");
            return;
        }
        if (!Sdt.matches(dangSDT)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng!");
            return;
        }

        List<KhachHang> kh1 = khSV.insert(Ma, Ten, GioiTinh, DiaChi, NgaySinh, Sdt, NgayDki);
        if (kh1 == null) {
            JOptionPane.showMessageDialog(null, " mã bị trùng ");
            return;
        } else {
            model.setRowCount(0);
            loadtable();
            clearForm();
            JOptionPane.showMessageDialog(null, "thêm thành công");
            return;
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "vui lòng chọn từ table");
            return;
        } else {
            String Ma = txtMa.getText();
            String Ten = txtTen.getText();
            String GioiTinh = "";
            String DiaChi = txtDiaChi.getText();
            String NgaySinh = txtNgaySinh.getText();
            String Sdt = txtSDT.getText();
            String NgayDki = txtNgayDki.getText();
            String dangSDT = "0\\d{9,10}";
            if (rdbNam.isSelected() == true) {
                GioiTinh = "NAM";
            } else {
                GioiTinh = "NỮ";
            }

            if (Ma.trim().isEmpty()
                || Ten.trim().isEmpty()
                || GioiTinh.trim().isEmpty()
                || DiaChi.trim().isEmpty()
                || NgaySinh.trim().isEmpty()
                || Sdt.trim().isEmpty()
                || NgayDki.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ko đc để trống");
                return;
            }
            if (!Sdt.matches(dangSDT)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng!");
                return;
            }
            try {
                khSV.update(Ma, Ten, GioiTinh, DiaChi, NgaySinh, Sdt, NgayDki);
                model.setRowCount(0);
                loadtable();
                clearForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "sửa thất bại");
                return;
            }
            JOptionPane.showMessageDialog(null, "sửa thành công");
            return;

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "chọn 1 cái để xóa");
            return;
        } else {
            int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn xóa không");
            if (cofirm != JOptionPane.YES_OPTION) {
                return;
            }
            String Ma = txtMa.getText();
            khSV.delete(Ma);
            model.setRowCount(0);
            loadtable();
            clearForm();
            JOptionPane.showMessageDialog(null, "xóa thành công");
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        // TODO add your handling code here:
        String ma = JOptionPane.showInputDialog(null, "vui lòng nhập mã khách hàng muốn tìm");
        try {
            List<KhachHang> k = khSV.select1(ma);
            int check = 0;
//            if (ma.trim().length() == 0) {
//                JOptionPane.showMessageDialog(this, "Không được để trống mã khách hàng");
//                return;
//            } else {
                for (KhachHang sp : k) {
                    check++;
                    this.txtMa.setText(sp.getMaKH());
                    this.txtTen.setText(sp.getTenKH());
                    this.txtDiaChi.setText(sp.getDiaChi());
                    this.txtSDT.setText(sp.getSdt());
                    this.txtNgaySinh.setText(sp.getNgaySinh());
                    this.txtNgayDki.setText(sp.getNgayDki());
                    if (sp.getGioiTinh().equals("Nam")) {
                        this.rdbNam.setSelected(true);
                    } else {
                        this.rdbNu.setSelected(true);
                    }
                    for (int i = 0; i < this.khSV.findALL().size() - 1; i++) {
                        if (tblKH.getValueAt(i, 0).equals(ma)) {
                            this.tblKH.setRowSelectionInterval(i, i);
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Đã tìm thấy khách hàngi");
                    return;
                }
            
            if (check == 0) {
                this.clearForm();
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTKActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        // TODO add your handling code here:
        int row = tblKH.getSelectedRow();
        txtMa.setText(tblKH.getValueAt(row, 0).toString());
        txtTen.setText(tblKH.getValueAt(row, 1).toString());
        String gioiTinh = tblKH.getValueAt(row, 2).toString();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdbNam.setSelected(true);
        } else {
            rdbNu.setSelected(true);
        }
        txtDiaChi.setText(tblKH.getValueAt(row, 3).toString());
        txtNgaySinh.setText(tblKH.getValueAt(row, 4).toString());
        txtSDT.setText(tblKH.getValueAt(row, 5).toString());
        txtNgayDki.setText(tblKH.getValueAt(row, 6).toString());
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtMa;
    private javax.swing.JTextField txtNgayDki;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
