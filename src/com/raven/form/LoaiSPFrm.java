/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import Services.IManageLoaiSanPhamService;
import ServiceImpl.ManageLoaiSanPhamService;
import ServiceImpl.NguoiDungServiceImpl;
import Services.NguoiDungService;
import ViewModels.QLLoaiSanPham;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
public class LoaiSPFrm extends javax.swing.JPanel {

    /**
     * Creates new form LoaiSPFrm
     */
    private IManageLoaiSanPhamService loaiSanPhamService;
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private DomainModels.dangNhap n;
    public LoaiSPFrm() {
        initComponents();
        PQ();
        this.loaiSanPhamService = new ManageLoaiSanPhamService();
        this.loadTable();
        this.clearForm();
    }

    public void setStatus(boolean insertable){ 
        //txtMaCD.setEditable(insertable); 
        btnCl.setEnabled(!insertable); 
        btnCapNhat.setEnabled(!insertable); 
        btnThem.setEnabled(!insertable); 
        btnTimKiem.setEnabled(!insertable); 
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
        String a = loaiSanPhamService.ALL().size() + 1 + "";
        this.txtMaLSP.setText("LSP" + a);
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
        txtTenLSP = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtMaLSP = new javax.swing.JLabel();
        btnCl = new javax.swing.JButton();

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

        txtMaLSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaLSP.setText("--");

        btnCl.setText("Clear");
        btnCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel58))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addComponent(jLabel59))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(jLabel61)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addGap(40, 40, 40)
                        .addComponent(btnCapNhat)
                        .addGap(34, 34, 34)
                        .addComponent(btnXoa)
                        .addGap(35, 35, 35)
                        .addComponent(btnTimKiem)
                        .addGap(28, 28, 28)
                        .addComponent(btnCl)
                        .addGap(114, 114, 114)))
                .addGap(88, 88, 88))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel58)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(txtMaLSP))
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiem)
                            .addComponent(btnXoa)
                            .addComponent(btnCapNhat)
                            .addComponent(btnThem)
                            .addComponent(btnCl))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        String ma = JOptionPane.showInputDialog(null, "vui lòng nhập mã LSP muốn tìm");
        List<QLLoaiSanPham> ds = this.loaiSanPhamService.ALL();
        int check = 0;
        if (ma.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã loại sản phẩm");
            return;
        } else {
            for (QLLoaiSanPham sp : ds) {
                if (sp.getMaLSP().equalsIgnoreCase(ma)) {
                    check++;
                    this.txtMaLSP.setText(sp.getMaLSP());
                    this.txtTenLSP.setText(sp.getTenLSP());
                    this.txtMoTa.setText(sp.getMoTa());

                    for (int i = 0; i < this.loaiSanPhamService.ALL().size() - 1; i++) {
                        if (tbl_LSP.getValueAt(i, 0).equals(ma)) {
                            this.tbl_LSP.setRowSelectionInterval(i, i);
                        }
                    }
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

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCl;
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
    private javax.swing.JLabel txtMaLSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenLSP;
    // End of variables declaration//GEN-END:variables
}
