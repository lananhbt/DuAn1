/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import ServiceImpl.ManageLoaiSanPhamService;
import ServiceImpl.ManageSanPhamLoiService;
import ServiceImpl.ManageSanPhamService;
import Services.IManageLoaiSanPhamService;
import Services.IManageSanPhamLoiService;
import Services.IManageSanPhamService;
import ViewModels.ManageSanPhamLoi;
import ViewModels.QLLoaiSanPham;
import ViewModels.QLSanPham;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SanPhamLoiFrm extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private IManageSanPhamLoiService sanPhamLoiService;
    private IManageSanPhamService sanPhamService;
    private IManageLoaiSanPhamService loaiSanPhamService;
    private static Webcam webcam1 = null;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public SanPhamLoiFrm() {
        initComponents();
        initwebcam();
        this.sanPhamLoiService = new ManageSanPhamLoiService();
        this.sanPhamService = new ManageSanPhamService();
        this.loaiSanPhamService = new ManageLoaiSanPhamService();

        List<QLLoaiSanPham> dsSP = loaiSanPhamService.ALL();
        this.cbbLocSP.getModel();
        String[] sp = new String[dsSP.size()];
        for (int i = 0; i < dsSP.size(); i++) {
            sp[i] = dsSP.get(i).getMaLSP();
        }
        cbbLocSP.setModel(new DefaultComboBoxModel(sp));
        List<QLSanPham> dsSP1 = sanPhamService.ALL();
        this.cbbLocSPL.getModel();
        String[] sp1 = new String[dsSP1.size()];
        for (int i = 0; i < dsSP1.size(); i++) {
            sp1[i] = dsSP1.get(i).getMaSP();
        }
        cbbLocSPL.setModel(new DefaultComboBoxModel(sp1));

        this.loadTableSP();
        this.loadTableSPL();
        this.clearForm();
    }

    public void loadTableSPL() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSPL.getModel();
        dtm.setRowCount(0);
        for (ManageSanPhamLoi sp : this.sanPhamLoiService.ALL()) {
            Object[] rowData = {
                sp.getMaSPL(),
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getLyDoLoi()
            };
            dtm.addRow(rowData);
        }
    }

    public void loadTableSP() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSP.getModel();
        dtm.setRowCount(0);
        for (QLSanPham sp : this.sanPhamService.ALL()) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getSoLuong()
            };
            dtm.addRow(rowData);
        }
    }

    public void clearForm() {
//        List<ManageSanPhamLoi> list = this.sanPhamLoiService.ALL();
//        String[] splits = list.split("-", 3);
//        this.lblMaSPL.setText(list.get((list.size() - 1)).getMaSPL() + 1);
        String a = this.sanPhamLoiService.ALL().size() + 1 + "";
        this.txtMaSPL.setText("SPL" + a);
        //this.txtMaSPL.setText("");
        this.lblMaSP.setText("-");
        this.lblTen.setText("-");
        this.txtLyDoLoi.setText("");

    }

    private ManageSanPhamLoi getFormData() {
        String maSPL = this.txtMaSPL.getText().trim();
        String maSP = this.lblMaSP.getText().trim();
        String tenSP = this.lblTen.getText().trim();
        String loi = this.txtLyDoLoi.getText().trim();

        ManageSanPhamLoi s = new ManageSanPhamLoi(maSPL, maSP, tenSP, loi);
        return s;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        txtLyDoLoi = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblSPL = new javax.swing.JTable();
        btnTimKiemSPL = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        cbbLocSPL = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        txtMaSPL = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnTimKiemSP = new javax.swing.JButton();
        txtMaSP = new javax.swing.JTextField();
        cbbLocSP = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblSP);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel73.setText("Lý do lỗi");

        jLabel74.setText("Mã SP");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblSPL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SPL", "Mã SP", "Tên SP", "Lý do lỗi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPLMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tblSPL);

        btnTimKiemSPL.setText("Tìm kiếm");
        btnTimKiemSPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPLActionPerformed(evt);
            }
        });

        jLabel76.setText("Mã SPL");

        lblMaSP.setText("--");

        cbbLocSPL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Tên SP");

        lblTen.setText("-");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        txtMaSPL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaSPL.setText("--");

        jButton2.setText("Hiển thị");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel74))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel73)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(txtMaSPL, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(37, 37, 37)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLyDoLoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(129, 129, 129))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(cbbLocSPL, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3)
                        .addGap(30, 30, 30)
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiemSPL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
            .addComponent(jScrollPane17)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(txtMaSPL))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(lblMaSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblTen))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(txtLyDoLoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnCapNhat)
                    .addComponent(btnThem)
                    .addComponent(btnTimKiemSPL)
                    .addComponent(cbbLocSPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("Quản lý sản phẩm lỗi");

        jLabel26.setText("Mã SP");

        btnTimKiemSP.setText("Tìm kiếm");
        btnTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPActionPerformed(evt);
            }
        });

        cbbLocSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocSPActionPerformed(evt);
            }
        });

        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Hiển thị");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnTat.setText("Tắt cam");
        btnTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 160));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTat)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel26)
                        .addGap(28, 28, 28)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnTimKiemSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)))
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(140, 140, 140))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(70, 70, 70))
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnTimKiemSP)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = this.tblSP.getSelectedRow();
        if (row == -1) {
            return;
        }
        String ma = this.tblSP.getValueAt(row, 0).toString();
        String ten = this.tblSP.getValueAt(row, 1).toString();

        this.lblMaSP.setText(ma);
        this.lblTen.setText(ten);
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = this.tblSPL.getSelectedRow();
        if (row == -1) {
            return;
        }
        int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn xóa không");
        if (cofirm != JOptionPane.YES_OPTION) {
            return;
        }
        String id = this.tblSPL.getValueAt(row, 0).toString();
        this.sanPhamLoiService.delete(id);
        this.loadTableSPL();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        ManageSanPhamLoi sp = this.getFormData();
        if (sp == null) {
            return;
        }
        String id = this.txtMaSPL.getText();
        this.sanPhamLoiService.update(id, sp);
        this.loadTableSPL();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ManageSanPhamLoi sp = this.getFormData();
        if (sp == null) {
            return;
        }
        this.sanPhamLoiService.insert(sp);
        this.sanPhamService.updateSL(sp.getMaSP());
        this.loadTableSPL();
        this.loadTableSP();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "thêm thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSPLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPLMouseClicked
        int row = this.tblSPL.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSPL = this.tblSPL.getValueAt(row, 0).toString();
        String maSP = this.tblSPL.getValueAt(row, 1).toString();
        String ten = this.tblSPL.getValueAt(row, 2).toString();
        String lyDoLoi = this.tblSPL.getValueAt(row, 3).toString();

        this.txtMaSPL.setText(maSPL);
        this.lblMaSP.setText(maSP);
        this.lblTen.setText(ten);
        this.txtLyDoLoi.setText(lyDoLoi);
    }//GEN-LAST:event_tblSPLMouseClicked

    private void btnTimKiemSPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPLActionPerformed
        String ma = JOptionPane.showInputDialog(null, "vui lòng nhập mã SPL muốn tìm");
        List<ManageSanPhamLoi> ds = this.sanPhamLoiService.ALL();
        int check = 0;
        int i= 0;
        if (ma.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã sản phẩm lỗi");
            return;
        } else {
            for (ManageSanPhamLoi sp : ds) {
                if (sp.getMaSPL().equalsIgnoreCase(ma)) {
                    check++;
                    this.txtMaSPL.setText(sp.getMaSPL());
                    this.lblMaSP.setText(sp.getMaSP());
                    this.lblMaSP.setText(sp.getMaSP());
                    
                    this.txtLyDoLoi.setText(sp.getLyDoLoi());
                    for (i = 0; i < this.sanPhamService.ALL().size() - 1; i++) {
                        if (tblSPL.getValueAt(i, 0).equals(ma)) {
                            this.tblSPL.setRowSelectionInterval(i, i);
                        }
                    }
                    this.lblTen.setText(tblSPL.getValueAt(i, 2).toString());
                    JOptionPane.showMessageDialog(this, "Tìm thấy sản phẩm lỗi");
                    this.tblSPL.getSelectedRow();
                    return;
                }
            }
        }
        if (check == 0) {
            this.clearForm();
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm lỗi");

        }
    }//GEN-LAST:event_btnTimKiemSPLActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String ma = (String) this.cbbLocSPL.getSelectedItem();
        List<ManageSanPhamLoi> ds = this.sanPhamLoiService.ALL();
        int check = 0;
        DefaultTableModel dtm = (DefaultTableModel) this.tblSPL.getModel();
        dtm.setRowCount(0);
        for (ManageSanPhamLoi sp : ds) {
            if (sp.getMaSP().equalsIgnoreCase(ma)) {
                check++;
                Object[] rowData = {
                    sp.getMaSPL(),
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getLyDoLoi()
                };
                dtm.addRow(rowData);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "tìm thấy sản phẩm");
            return;
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPActionPerformed
        String maSP = this.txtMaSP.getText();
        List<QLSanPham> ds = this.sanPhamService.ALL();
        int check = 0;
        int i = 0;
        if (maSP.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã sản phẩm");
            return;
        } else {
            for (QLSanPham sp : ds) {
                if (sp.getMaSP().equalsIgnoreCase(txtMaSP.getText())) {
                    check++;
                    this.lblMaSP.setText(sp.getMaSP());
                    this.lblTen.setText(sp.getTenSP());
                    for (i = 0; i < this.sanPhamService.ALL().size() - 1; i++) {
                        if (tblSP.getValueAt(i, 0).equals(txtMaSP.getText())) {
                            this.tblSP.setRowSelectionInterval(i, i);
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Tìm thấy sản phẩm");
                    return;
                }
            }
        }

        if (check == 0) {
            this.clearForm();
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");

        }
    }//GEN-LAST:event_btnTimKiemSPActionPerformed

    private void cbbLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocSPActionPerformed

    }//GEN-LAST:event_cbbLocSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ma = (String) this.cbbLocSP.getSelectedItem();
        List<QLSanPham> ds = this.sanPhamService.ALL();
        int check = 0;
        DefaultTableModel dtm = (DefaultTableModel) this.tblSP.getModel();
        dtm.setRowCount(0);
        for (QLSanPham sp : ds) {
            if (sp.getMaLSP().equalsIgnoreCase(ma)) {
                check++;
                Object[] rowData = {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuong()
                };
                dtm.addRow(rowData);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "tìm thấy sản phẩm");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tblSPL.getRowCount() < sanPhamLoiService.ALL().size()) {
            loadTableSPL();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiển thị danh sách sản phẩm lỗi");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.clearForm();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (tblSP.getRowCount() < sanPhamService.ALL().size()) {
            loadTableSP();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiển thị danh sách sản phẩm");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatActionPerformed
        if (webcam1 == null) {
            return;
        }
        webcam1.close();
    }//GEN-LAST:event_btnTatActionPerformed

    private void initwebcam() {
        Dimension size = WebcamResolution.QQVGA.getSize();
        webcam1 = Webcam.getWebcams().get(0);
        webcam1.setViewSize(size);
        panel = new WebcamPanel(webcam1);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        //jPanel1.setSize(size);
        jPanel4.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 200));
        executor.execute(this);

    }

    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            Result result = null;
            BufferedImage image = null;
            if (webcam1.isOpen()) {
                if ((image = webcam1.getImage()) == null) {
                    continue;
                }
            }
            if (image == null) {
                continue;
            }
            LuminanceSource soure = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(soure));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            int i;
            if (result != null) {
                List<QLSanPham> sanPhams = sanPhamService.getByCode(result.getText());
                if (sanPhams.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
                } else {
                    for (i = 0; i < this.sanPhamService.ALL().size() - 1; i++) {
                        if (tblSP.getValueAt(i, 0).equals(result.getText())) {
                            this.tblSP.setRowSelectionInterval(i, i);
                        }
                    }
                    lblMaSP.setText(result.getText());
                    lblTen.setText(tblSP.getValueAt(i, 1).toString());
                    JOptionPane.showMessageDialog(this, "Đã tìm thấy sản phẩm");
                }
            }

        } while (true);
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnTimKiemSPL;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLocSP;
    private javax.swing.JComboBox<String> cbbLocSPL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblTen;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSPL;
    private javax.swing.JTextField txtLyDoLoi;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JLabel txtMaSPL;
    // End of variables declaration//GEN-END:variables
}
