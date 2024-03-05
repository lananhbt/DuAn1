/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import ServiceImpl.DoiHangServiceIml;
import ServiceImpl.ManageHoaDonChiTietService;
import ServiceImpl.ManageHoaDonService;
import ServiceImpl.ManageSanPhamService;
import ServiceImpl.NguoiDungServiceImpl;
import Services.DoiHangService;
import Services.IManageHoaDon;
import Services.IManageHoaDonChiTiet;
import Services.IManageSanPhamService;
import Services.NguoiDungService;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.QLHoaDon;
import ViewModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class doiHangFrm extends javax.swing.JPanel {

    DefaultTableModel dtm;
    private DoiHangService doiHangService = new DoiHangServiceIml();
    private IManageSanPhamService banHangService;
    private NguoiDungService ndService;
    private DomainModels.dangNhap n;
    private IManageHoaDonChiTiet ctService;
    private IManageHoaDon hdService;

    public doiHangFrm() {
        initComponents();
        this.ndService = new NguoiDungServiceImpl();
        this.ctService = new ManageHoaDonChiTietService();
        banHangService = new ManageSanPhamService();
        this.hdService = new ManageHoaDonService();
        loadTableSP();
        loadTableHD(doiHangService.listHD());
        this.clearForm();
    }

    public void clearForm() {
        this.lbMaNV.setText(ndService.findND(n.getCurrentLoginUsername()).getMaND());
        String a = hdService.AllHD().size() + 1 + "";
        this.txtMaHDNew.setText("HD" + a);
    }

    private void loadTableHDCT(ArrayList<ManageHoaDonChiTiet> list) {
        dtm = (DefaultTableModel) tblSPHD.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet hdct : list) {
            dtm.addRow(new Object[]{
                hdct.getMaHDCT(),
                hdct.getMaSP(),
                hdct.getSoLuong(),
                hdct.getGiaBan(),
                hdct.getThanhTien()
            });
        }
    }

    private void loadTableHD(ArrayList<ManageHoaDon> list) {
        dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDon hd : list) {
            dtm.addRow(new Object[]{
                hd.getMaHD(), hd.getMaND(), hd.getMaKH(), hd.getMaHDcu()
            });
        }
    }

    public HoaDonChiTiet get() {
        HoaDonChiTiet hd = new HoaDonChiTiet();
        hd.setMaHDCT(lbMaHDCT.getText());
        hd.setMaSP(lbMaSP.getText());
        hd.setSoLuong((int) spnSoLuong.getValue());
        int row = tblSP.getSelectedRow();
        hd.setGiaBan(Float.valueOf(tblSP.getValueAt(row, 3).toString()));
        hd.setThanhTien(Float.valueOf(Float.valueOf(tblSP.getValueAt(row, 3).toString()) * (int) spnSoLuong.getValue()));
        return hd;
    }

    public void addSP(ArrayList<ManageHoaDonChiTiet> sanPham) {
        dtm = (DefaultTableModel) tblSPDoi.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : sanPham) {
            dtm.addRow(new Object[]{sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien()});
        }
    }

    public void addSPDoi(ArrayList<ManageHoaDonChiTiet> sanPham) {
        dtm = (DefaultTableModel) tblSPHD.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : sanPham) {
            dtm.addRow(new Object[]{sp.getMaHDCT(), sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien()});
        }
    }

    private void loadTableSP() {
        List<QLSanPham> spList = banHangService.ALL();
        dtm = (DefaultTableModel) tblSP.getModel();

        dtm.setRowCount(0);
        for (QLSanPham sanPham : spList) {
            dtm.addRow(new Object[]{
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
                sanPham.getGiaBan()
            });
        }
    }

    public ManageHoaDonChiTiet getFormHDCT() {
        int row = this.tblSP.getSelectedRow();
        String maSP = this.tblSP.getValueAt(row, 0).toString();
        int sLg = (int) this.spnSoLuong.getValue();
        float giaBan = Float.valueOf(tblSP.getValueAt(row, 3).toString());
        float thanhTien = giaBan * sLg;
        ManageHoaDonChiTiet hdct = new ManageHoaDonChiTiet(maSP, sLg, giaBan, thanhTien);
        return hdct;
    }

    public HoaDon getFormHD() {
        String maHD = txtMaHD.getText();
        String maHD_2 = txtMaHDNew.getText();
        String maKH = lbMaKH.getText();
        String maND = lbMaNV.getText();
        HoaDon hd = new HoaDon(maHD, maHD_2, maKH, maND);
        return hd;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lbMaKH = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        txtMaHDNew = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnDoi = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSPDoi = new javax.swing.JTable();
        jLabel92 = new javax.swing.JLabel();
        lbMaHDCT = new javax.swing.JLabel();
        lbMaSP = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel75 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        btnTimHD = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        btnTimSP = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSPHD = new javax.swing.JTable();
        txtMaSP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtTimMaHD = new javax.swing.JTextField();
        btnHD = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtSPD = new javax.swing.JTextField();
        btnSPD = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "Số lượng", "Giá bán"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblSP);

        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel31.setText("Mã KH");

        jLabel32.setText("Mã NV");

        jLabel33.setText("Mã HD");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lbMaKH.setText("--");

        lbMaNV.setText("--");

        jLabel34.setText("Tổng tiền");

        lbTongTien.setText("--");

        txtMaHDNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(35, 35, 35)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHDNew, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lbMaKH))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lbMaNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtMaHDNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(btnThanhToan)
                .addGap(46, 46, 46))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setText("Mã SP");

        jLabel29.setText("Số lượng");

        btnDoi.setText("Đổi");
        btnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiActionPerformed(evt);
            }
        });

        tblSPDoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane9.setViewportView(tblSPDoi);

        jLabel92.setText("Mã HD");

        lbMaHDCT.setText("--");

        lbMaSP.setText("--");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(lbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(jLabel29)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(lbMaHDCT))
                .addGap(24, 24, 24)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel28)
                    .addComponent(lbMaSP)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDoi)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel75.setText("Mã HD");

        btnTimHD.setText("Tìm kiếm");
        btnTimHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHDActionPerformed(evt);
            }
        });

        jLabel77.setText("Mã SP");

        btnTimSP.setText("Tìm kiếm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        tblSPHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HDCT", "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblSPHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPHDMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblSPHD);

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addGap(25, 25, 25)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnTimHD)
                                .addGap(94, 94, 94)
                                .addComponent(jLabel77)
                                .addGap(40, 40, 40)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnTimSP))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75)
                    .addComponent(btnTimHD)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77)
                    .addComponent(btnTimSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Đổi hàng", jPanel24);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setText("Mã HD");

        btnHD.setText("Tìm kiếm");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã HD Đổi", "Mã KH", "Mã ND"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(30, 30, 30)
                .addComponent(txtTimMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHD)
                .addContainerGap(83, Short.MAX_VALUE))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtTimMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel79.setText("Mã SP");

        btnSPD.setText("Tìm kiếm");
        btnSPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPDActionPerformed(evt);
            }
        });

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane18.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79)
                .addGap(36, 36, 36)
                .addComponent(txtSPD, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnSPD)
                .addGap(58, 58, 58))
            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtSPD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSPD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Danh sách phiếu đổi hàng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSP = tblSP.getValueAt(row, 0).toString();
        lbMaSP.setText(maSP);
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        ManageHoaDonChiTiet ct = this.getFormHDCT();
        HoaDon hd = this.getFormHD();
        if (ct == null || hd.equals("") || ct.equals("") || hd == null) {
            return;
        }
        String maHD_2 = txtMaHDNew.getText();
        String maHD = txtMaHD.getText();
        doiHangService.search(maHD);
        doiHangService.searchKH(maHD);

        loadTableHD(doiHangService.listHD());
        if (doiHangService.doiHD(hd, maHD)) {
            loadTableHD(doiHangService.listHD());
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtMaHDNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDNewActionPerformed

    private void btnDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiActionPerformed
        // TODO add your handling code here:
        ManageHoaDonChiTiet hdctView = new ManageHoaDonChiTiet();
        int row = tblSP.getSelectedRow();
        hdctView.setMaHD(txtMaHD.getText());
        hdctView.setMaSP((String) tblSP.getValueAt(row, 0));
        hdctView.setSoLuong((int) spnSoLuong.getValue());
        hdctView.setGiaBan((float) tblSP.getValueAt(row, 3));
        ArrayList<ManageHoaDonChiTiet> list = new ArrayList<>();
        list.add(hdctView);
        addSP(list);
        String maHD = txtMaHD.getText();
        String maHDCT = lbMaHDCT.getText();
        HoaDonChiTiet ct = get();
        lbMaHDCT.setText(lbMaHDCT.getText());
        if (doiHangService.doi(ct, maHDCT)) {
            loadTableHDCT(doiHangService.search(maHD));
        }
        float tongTien = 0;
        for (ManageHoaDonChiTiet hdc : list) {
            tongTien = (float) (tongTien + ct.getThanhTien());
            lbTongTien.setText("" + tongTien);
        }
    }//GEN-LAST:event_btnDoiActionPerformed

    private void btnTimHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHDActionPerformed
        // TODO add your handling code here:
        String maHD = txtMaHD.getText();
        if (maHD == null || maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã hoá đơn có sản phẩm đổi");
        }
        List<ManageHoaDon> ds1 = this.hdService.TimMaHD(maHD);
        if(ds1.size() > 0){
            JOptionPane.showMessageDialog(this, "HD đã được đổi 1 lần không được phép đổi lần 2");
            this.txtMaHD.setText("");
            return;
        }
        int row = tblHoaDon.getSelectedRow();
        if (maHD == null || maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã hoá đơn có sản phẩm đổi");
        } else {
            List<ManageHoaDonChiTiet> hdct = ctService.AllMa(maHD);
            if (hdct.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tồn tại mã hoá đơn này");
            } else {
                dtm = (DefaultTableModel) tblSPHD.getModel();
                dtm.setRowCount(0);
                for (ManageHoaDonChiTiet ct : hdct) {
                    dtm.addRow(new Object[]{ct.getMaHDCT(), ct.getMaSP(), ct.getSoLuong(),
                        ct.getGiaBan(), ct.getThanhTien()});
                }
                ArrayList<ManageHoaDon> hd = doiHangService.searchKH(maHD);
                for (ManageHoaDon h : hd) {
                    lbMaKH.setText(h.getMaKH());
                    //lbMaNV.setText(h.getMaND());
                }
            }
        }
    }//GEN-LAST:event_btnTimHDActionPerformed

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        // TODO add your handling code here:
        String maSP = txtMaSP.getText();
        if (maSP == null || maSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm");
        } else {
            List<QLSanPham> sanPham = doiHangService.searchSP(maSP);
            if (sanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại");
            } else {
                dtm = (DefaultTableModel) tblSP.getModel();
                dtm.setRowCount(0);
                for (QLSanPham sp : sanPham) {
                    dtm.addRow(new Object[]{
                        sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getGiaBan()
                    });
                }
                lbMaSP.setText(maSP);
            }
        }
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void tblSPHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPHDMouseClicked
        // TODO add your handling code here:
        int row = tblSPHD.getSelectedRow();
        //lbMaHDCT.setText(tblSPHD.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tblSPHDMouseClicked

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
        // TODO add your handling code here:
        String maHD_2 = txtTimMaHD.getText();
        if (maHD_2 == null || maHD_2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã hoá đơn cần tìm");
        } else {
            List<ManageHoaDon> hdd = doiHangService.listHDD(maHD_2);
            if (hdd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tồn tại mã hoá đơn đổi");
            } else {
                dtm = (DefaultTableModel) tblHoaDon.getModel();
                dtm.setRowCount(0);
                for (ManageHoaDon hd : hdd) {
                    dtm.addRow(new Object[]{hd.getMaHD(), hd.getMaHDcu(), hd.getMaKH(), hd.getMaND()});
                }
            }
        }
    }//GEN-LAST:event_btnHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maHD = tblHoaDon.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) tblHDCT.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : doiHangService.search(maHD)) {
            dtm.addRow(new Object[]{sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien()});
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnSPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPDActionPerformed
        // TODO add your handling code here:
        String maSPD = txtSPD.getText();
        int row = tblHoaDon.getSelectedRow();
        String maHD_2 = tblHoaDon.getValueAt(row, 1).toString();
        List<ManageHoaDon> hdd = doiHangService.listHDD(maHD_2);
        if (hdd.isEmpty()) {
            return;
        } else {
            List<ManageHoaDonChiTiet> sanPham = doiHangService.searchSP_2(maSPD);
            if (sanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tồn tại mã sản phẩm này trong hoá đơn đổi");
            } else {
                dtm = (DefaultTableModel) tblHDCT.getModel();
                dtm.setRowCount(0);
                for (ManageHoaDonChiTiet sp : sanPham) {
                    dtm.addRow(new Object[]{sp.getMaSP(), sp.getSoLuong(), sp.getGiaBan(), sp.getThanhTien()});
                }
            }
        }
    }//GEN-LAST:event_btnSPDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoi;
    private javax.swing.JButton btnHD;
    private javax.swing.JButton btnSPD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimHD;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbMaHDCT;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSPDoi;
    private javax.swing.JTable tblSPHD;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHDNew;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSPD;
    private javax.swing.JTextField txtTimMaHD;
    // End of variables declaration//GEN-END:variables
}
