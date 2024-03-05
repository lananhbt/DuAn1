/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModels.dangNhap;
import ServiceImpl.ManageHoaDonChiTietService;
import ServiceImpl.ManageHoaDonService;
import ServiceImpl.ManagePhieuTraHangCTService;
import ServiceImpl.ManagePhieuTraHangService;
import ServiceImpl.ManageSanPhamService;
import ServiceImpl.NguoiDungServiceImpl;
import Services.IManageHoaDon;
import Services.IManageHoaDonChiTiet;
import Services.IManagePhieuTraHangCTService;
import Services.IManagePhieuTraHangService;
import Services.IManageSanPhamService;
import Services.NguoiDungService;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.ManagePhieuTraHang;
import ViewModels.ManagePhieuTraHangCT;
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
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TraHangFrm extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private IManagePhieuTraHangService traHangService;
    private IManagePhieuTraHangCTService traHangCTService;
    private IManageHoaDon hdService;
    private IManageHoaDonChiTiet ctService;
    private IManageSanPhamService spService;
    private NguoiDungService ndService;
    private DefaultTableModel dtm;
    private ArrayList<ManagePhieuTraHangCT> list = new ArrayList<>();
    private DomainModels.dangNhap n;
    private static Webcam webcam2;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public TraHangFrm() {
        initComponents();
        initwebcam();
        this.traHangService = new ManagePhieuTraHangService();
        this.traHangCTService = new ManagePhieuTraHangCTService();
        this.hdService = new ManageHoaDonService();
        this.ctService = new ManageHoaDonChiTietService();
        this.spService = new ManageSanPhamService();
        this.ndService = new NguoiDungServiceImpl();
        this.loadTableTH();
        this.clearForm();
        this.clear();
    }

    public void clearForm() {
        String a = traHangService.ALL().size() + 1 + "";
        this.lblMaPT.setText("PT" + a);
        this.lblMaNV.setText(ndService.findND(n.getCurrentLoginUsername()).getMaND());
        this.txtMaHDTim.setText("");
        this.txtMaSPTim.setText("");
        this.lblMaSP.setText("--");
        this.spnSoLuong.setValue(0);
    }

    public void clear() {
        this.lblMaKH.setText("--");
        this.lblMaHD.setText("--");
        this.txtNgayTra.setText(java.time.LocalDate.now().toString());
        this.txtLyDo.setText("");
        this.lblTienTraLai.setText("--");
    }

    public void loadTableTH() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblTraHang.getModel();
        dtm.setRowCount(0);
        for (ManagePhieuTraHang sp : this.traHangService.ALL()) {
            Object[] rowData = {
                sp.getMaPTH(),
                sp.getMaHD(),
                sp.getMaND(),
                sp.getMaKH(),
                sp.getNgayTra(),
                sp.getTienTraLaiKhach(),
                sp.getLyDoTra()
            };
            dtm.addRow(rowData);
        }
    }

//    public void loadTableCT() {
//        DefaultTableModel dtm = (DefaultTableModel) this.tblTraHangCT.getModel();
//        dtm.setRowCount(0);
//        for (ManagePhieuTraHangCT sp : this.traHangCTService.ALL()) {
//            Object[] rowData = {
//                sp.getMaSP(),
//                sp.getSoLuong(),
//                sp.getDonGia(),
//                sp.getSoLuong()
//            };
//            dtm.addRow(rowData);
//        }
//    }
    public void loadTableDS() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblCT.getModel();
        dtm.setRowCount(0);
    }

    public void loadTableSPHD() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblCTHD.getModel();
        dtm.setRowCount(0);
    }

    public void loadTableHD() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblCTHD.getModel();
        dtm.setRowCount(0);
        for (ManagePhieuTraHangCT sp : this.traHangCTService.ALL()) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getSoLuong()
            };
            dtm.addRow(rowData);
        }
    }

    public void addSP(List<ManagePhieuTraHangCT> sanPhams) {

        dtm = (DefaultTableModel) tblCT.getModel();

        dtm.setRowCount(0);

        for (ManagePhieuTraHangCT sanPham : sanPhams) {
            Object[] rowData = {
                sanPham.getMaSP(),
                sanPham.getSoLuong(),
                sanPham.getDonGia(),
                sanPham.getThanhTien()
            };
            dtm.addRow(rowData);
        }
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

    public ManagePhieuTraHang getForm() {
        String maPTH = this.lblMaPT.getText();
        String maHD = this.lblMaHD.getText();
        String maND = this.lblMaNV.getText();
        String maKH = this.lblMaKH.getText();
        String ngay = this.txtNgayTra.getText();
        String tien = this.lblTienTraLai.getText();
        String lyDo = this.txtLyDo.getText();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        Date d;
        try {
            d = sdf.parse(ngay);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày");
            return null;
        }
        double tongTien = Double.valueOf(tien);
        ManagePhieuTraHang p = new ManagePhieuTraHang(maHD, maKH, maND, maPTH, d, tongTien, lyDo);
        return p;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaneDS = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnHoanTra = new javax.swing.JButton();
        lblMaHD = new javax.swing.JLabel();
        lblTienTraLai = new javax.swing.JLabel();
        lblMaPT = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();
        lblMaKH = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuuTam = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblCT = new javax.swing.JTable();
        lblMaSP = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jLabel75 = new javax.swing.JLabel();
        txtMaHDTim = new javax.swing.JTextField();
        btnTimKiemMaHD = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        txtMaSPTim = new javax.swing.JTextField();
        btnTimMaSP = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTat = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtMaPTH = new javax.swing.JTextField();
        btnTimPTH = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblTraHang = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        txtMaSPTH = new javax.swing.JTextField();
        btnTimSPTH = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblTraHangCT = new javax.swing.JTable();

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHDMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblCTHD);

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setText("Mã phiếu trả");

        jLabel31.setText("Mã KH");

        jLabel32.setText("Mã NV");

        jLabel33.setText("Mã HD");

        jLabel34.setText("Ngày trả");

        jLabel35.setText("Tiền trả lại");

        jLabel36.setText("Lý do hoàn trả");

        btnHoanTra.setText("Hoàn trả");
        btnHoanTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTraActionPerformed(evt);
            }
        });

        lblMaHD.setText("--");

        lblTienTraLai.setText("--");

        lblMaPT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaPT.setText("--");

        lblMaKH.setText("--");

        lblMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNV.setText("--");

        txtNgayTra.setText("--");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                            .addComponent(btnHoanTra)
                                            .addGap(147, 147, 147))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                            .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(118, 118, 118)))
                                    .addComponent(lblTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(lblMaPT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblMaKH))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(lblMaNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(txtNgayTra))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaHD)
                            .addComponent(jLabel33))
                        .addGap(40, 40, 40)))
                .addGap(26, 26, 26)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienTraLai)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(28, 28, 28)
                .addComponent(btnHoanTra)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setText("Mã SP");

        jLabel29.setText("Số lượng");

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

        btnLuuTam.setText("Lưu tạm");
        btnLuuTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTamActionPerformed(evt);
            }
        });

        tblCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblCT);

        lblMaSP.setText("--");

        spnSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnSoLuongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(28, 28, 28)
                        .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addGap(41, 41, 41)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnLuuTam)
                        .addGap(47, 47, 47)
                        .addComponent(btnCapNhat)
                        .addGap(49, 49, 49)
                        .addComponent(btnXoa)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblMaSP)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuTam)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel75.setText("Mã HD");

        btnTimKiemMaHD.setText("Tìm kiếm");
        btnTimKiemMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemMaHDActionPerformed(evt);
            }
        });

        jLabel77.setText("Mã SP");

        txtMaSPTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPTimActionPerformed(evt);
            }
        });

        btnTimMaSP.setText("Tìm kiếm");
        btnTimMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaSPActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 180));

        btnTat.setText("Tắt cam");
        btnTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaHDTim, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnTimKiemMaHD)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTat)
                                .addGap(0, 14, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel77)
                        .addGap(37, 37, 37)
                        .addComponent(txtMaSPTim, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimMaSP)
                        .addGap(51, 51, 51))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimKiemMaHD)
                            .addComponent(txtMaHDTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimMaSP)
                            .addComponent(txtMaSPTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(btnTat)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PaneDS.addTab("Trả hàng", jPanel24);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setText("Mã PTH");

        btnTimPTH.setText("Tìm kiếm");
        btnTimPTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimPTHActionPerformed(evt);
            }
        });

        tblTraHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PTH", "Mã HD", "Mã ND", "Mã KH", "Ngày trả", "Tiền trả lại", "Lý do trả"
            }
        ));
        tblTraHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTraHangMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblTraHang);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(30, 30, 30)
                .addComponent(txtMaPTH, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimPTH)
                .addContainerGap(83, Short.MAX_VALUE))
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtMaPTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimPTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel79.setText("Mã SP");

        btnTimSPTH.setText("Tìm kiếm");
        btnTimSPTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPTHActionPerformed(evt);
            }
        });

        tblTraHangCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane18.setViewportView(tblTraHangCT);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79)
                .addGap(36, 36, 36)
                .addComponent(txtMaSPTH, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btnTimSPTH)
                .addGap(58, 58, 58))
            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtMaSPTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSPTH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        PaneDS.addTab("DS phiếu trả hàng", jPanel25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneDS)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneDS)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemMaHDActionPerformed
        String maHD = this.txtMaHDTim.getText();
        if (maHD.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã hóa đơn");
            return;
        }
        List<ManagePhieuTraHang> ds1 = this.traHangService.TimHD(maHD);
        if(ds1.size() > 0){
            JOptionPane.showMessageDialog(this, "HD đã được trả 1 lần không được phép trả lần 2");
            this.txtMaHDTim.setText("");
            return;
        }
        List<ManageHoaDonChiTiet> ds = this.ctService.AllMa(maHD);
        if (maHD.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã hóa đơn");
            return;
        } else {
            List<ManageHoaDonChiTiet> sanPhams = ctService.AllMa(maHD);
            List<ManageHoaDon> sp = hdService.AllMa1(maHD);
            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có HD mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblCTHD.getModel();

                dtm.setRowCount(0);
                for (ManageHoaDonChiTiet sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getSoLuong(),
                        sanPham.getGiaBan(), sanPham.getThanhTien()
                    });
                }
                for (ManageHoaDon hd : sp) {
                    this.lblMaHD.setText(hd.getMaHD());
                    this.lblMaKH.setText(hd.getMaKH());
                }
            }
        }
    }//GEN-LAST:event_btnTimKiemMaHDActionPerformed

    private void txtMaSPTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPTimActionPerformed

    private void spnSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnSoLuongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spnSoLuongMouseClicked

    private void tblCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseClicked
        int row = this.tblCTHD.getSelectedRow();
        if (row == -1) {
            return;
        }
        String ma = this.tblCTHD.getValueAt(row, 0).toString();
        this.lblMaSP.setText(ma);
    }//GEN-LAST:event_tblCTHDMouseClicked

    private void tblTraHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTraHangMouseClicked
        int row = this.tblTraHang.getSelectedRow();
        if (row == -1) {
            return;
        }
        String ma = this.tblTraHang.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) this.tblTraHangCT.getModel();
        dtm.setRowCount(0);
        for (ManagePhieuTraHangCT sp : this.traHangCTService.ALLP(ma)) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getThanhTien()
            };
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_tblTraHangMouseClicked

    private void btnLuuTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTamActionPerformed
        ManagePhieuTraHangCT ct = new ManagePhieuTraHangCT();
        int row = tblCTHD.getSelectedRow();
        //ct.setMaPTH(this.lblMaPT.getText());
        ct.setMaSP((String) tblCTHD.getValueAt(row, 0));
        ct.setSoLuong((int) spnSoLuong.getValue());
        ct.setDonGia((Float) tblCTHD.getValueAt(row, 2));
        String maHD = this.lblMaHD.getText();
        String maSP = this.lblMaSP.getText();
        if ((int) spnSoLuong.getValue() < 0) {
            JOptionPane.showMessageDialog(this, "số lượng không dược âm ");
            return;
        }
        List<ManageHoaDonChiTiet> sanPhams = ctService.AllMaSP(maHD, maSP);
        for (ManageHoaDonChiTiet sanPham : sanPhams) {
            if (sanPham.getSoLuong() < (int) this.spnSoLuong.getValue()) {
                JOptionPane.showMessageDialog(this, "Số lượng trả phải nhỏ hơn số lượng mua");
                return;
            }
        }
        list.add(ct);
        addSP(list);
        int thanhTien = 0;
        for (ManagePhieuTraHangCT c : list) {
            thanhTien = (int) (thanhTien + c.getThanhTien());
            lblTienTraLai.setText("" + thanhTien);
        }
    }//GEN-LAST:event_btnLuuTamActionPerformed

    private void btnTimMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaSPActionPerformed
        String maSP = txtMaSPTim.getText();
        String maHD = txtMaHDTim.getText();
        if (maSP == null || maSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<ManageHoaDonChiTiet> sanPhams = ctService.AllMaSP(maHD, maSP);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblCTHD.getModel();

                dtm.setRowCount(0);
                for (ManageHoaDonChiTiet sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getSoLuong(),
                        sanPham.getGiaBan(), sanPham.getThanhTien()
                    });
                }
                lblMaSP.setText(maSP);
            }

        }
    }//GEN-LAST:event_btnTimMaSPActionPerformed

    private void btnHoanTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTraActionPerformed
        ManagePhieuTraHang hd = this.getForm();
        if (hd == null) {
            return;
        }
        String maHD = this.lblMaHD.getText();
        this.traHangService.insert(hd);
        int row = 0;
        ArrayList<ManagePhieuTraHangCT> listHDCT = new ArrayList<>();
        for (ManagePhieuTraHangCT a : list) {
            a.setMaPTH(this.lblMaPT.getText());
            a.setMaSP((String) tblCT.getValueAt(row, 0));
            a.setSoLuong((int) tblCT.getValueAt(row, 1));
            a.setDonGia((Double) tblCT.getValueAt(row, 2));
            a.setThanhTien((Double) tblCT.getValueAt(row, 3));
            row++;
            listHDCT.add(a);
        }
        for (ManagePhieuTraHangCT CT : listHDCT) {
            traHangCTService.insert(CT);
            this.spService.updateSLTH(CT.getSoLuong(), CT.getMaSP());
            this.ctService.updateSLTH(maHD, CT.getMaSP(), CT.getSoLuong());

        }
        this.hdService.updateGia(maHD, hd.getTienTraLaiKhach());
        this.clearForm();
        this.clear();
        this.loadTableTH();
        this.loadTableDS();
        this.loadTableSPHD();
        JOptionPane.showMessageDialog(this, "thành công");
    }//GEN-LAST:event_btnHoanTraActionPerformed

    private void btnTimPTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimPTHActionPerformed
        String maHD = txtMaPTH.getText();
        if (maHD == null || maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<ManagePhieuTraHang> sanPhams = traHangService.Tim(maHD);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có phiếu trả hàng mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblTraHang.getModel();

                dtm.setRowCount(0);
                for (ManagePhieuTraHang sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaPTH(), sanPham.getMaHD(),
                        sanPham.getMaND(), sanPham.getMaKH(),
                        sanPham.getNgayTra(), sanPham.getTienTraLaiKhach(), sanPham.getLyDoTra()
                    });
                }
            }

        }
    }//GEN-LAST:event_btnTimPTHActionPerformed

    private void btnTimSPTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPTHActionPerformed
        String maHD = txtMaPTH.getText();
        String maSP = txtMaSPTH.getText();
        if (maHD == null || maHD.isEmpty() || maSP == null || maSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<ManagePhieuTraHangCT> sanPhams = traHangCTService.TimSP(maHD, maSP);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblTraHangCT.getModel();

                dtm.setRowCount(0);
                for (ManagePhieuTraHangCT sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getSoLuong(),
                        sanPham.getDonGia(), sanPham.getThanhTien()
                    });
                }
            }

        }
    }//GEN-LAST:event_btnTimSPTHActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        DefaultTableModel models = (DefaultTableModel) tblCT.getModel();
        ManagePhieuTraHangCT ct = new ManagePhieuTraHangCT();
        int row = this.tblCT.getSelectedRow();
        if (row == -1) {
            return;
        }

        ct.setMaSP((String) tblCT.getValueAt(row, 0));
        ct.setSoLuong((int) spnSoLuong.getValue());
        ct.setDonGia((Double) tblCT.getValueAt(row, 2));
        String maHD = this.lblMaHD.getText();
        String maSP = this.lblMaSP.getText();
        if ((int) spnSoLuong.getValue() < 0) {
            JOptionPane.showMessageDialog(this, "số lượng không dược âm ");
            return;
        }
        List<ManageHoaDonChiTiet> sanPhams = ctService.AllMaSP(maHD, maSP);
        for (ManageHoaDonChiTiet sanPham : sanPhams) {
            if (sanPham.getSoLuong() < (int) this.spnSoLuong.getValue()) {
                JOptionPane.showMessageDialog(this, "Số lượng trả phải nhỏ hơn số lượng mua");
                return;
            }
        }
        models.removeRow(row);
        list.remove(row);
        list.add(ct);
        addSP(list);
        int thanhTien = 0;
        for (ManagePhieuTraHangCT c : list) {
            thanhTien = (int) (thanhTien + c.getThanhTien());
            lblTienTraLai.setText("" + thanhTien);
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        DefaultTableModel models = (DefaultTableModel) tblCT.getModel();
        int row = this.tblCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        models.removeRow(row);
        list.remove(row);
        float thanhTien = 0;
        for (ManagePhieuTraHangCT ct : list) {
            thanhTien = (float) (thanhTien + ct.getThanhTien());
            lblTienTraLai.setText("" + thanhTien);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatActionPerformed
        if (webcam2 == null) {
            return;
        }
        webcam2.close();
    }//GEN-LAST:event_btnTatActionPerformed

    private void tblCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTMouseClicked
        int row = this.tblCT.getSelectedRow();
        String ma = this.tblCT.getValueAt(row, 0).toString();
        String so = this.tblCT.getValueAt(row, 1).toString();
        int s = Integer.parseInt(so);
        this.lblMaSP.setText(ma);
        this.spnSoLuong.setValue(s);
    }//GEN-LAST:event_tblCTMouseClicked

    private void initwebcam() {
        Dimension size = WebcamResolution.QQVGA.getSize();
        webcam2 = Webcam.getWebcams().get(0);
        webcam2.setViewSize(size);
        panel = new WebcamPanel(webcam2);
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
            if (webcam2.isOpen()) {
                if ((image = webcam2.getImage()) == null) {
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
            String maHD = txtMaHDTim.getText();
            if (result != null) {
                if (maHD == null || maHD.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

                } else {
                    List<ManageHoaDonChiTiet> sanPhams = ctService.AllMaSP(maHD, result.getText());

                    if (sanPhams.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
                    } else {
                        for (int i = 0; i < this.spService.ALL().size() - 1; i++) {
                            if (tblCTHD.getValueAt(i, 0).equals(result.getText())) {
                                this.tblCTHD.setRowSelectionInterval(i, i);
                            }
                        }
                        lblMaSP.setText(result.getText());
                    }

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
    private javax.swing.JTabbedPane PaneDS;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHoanTra;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnTat;
    private javax.swing.JButton btnTimKiemMaHD;
    private javax.swing.JButton btnTimMaSP;
    private javax.swing.JButton btnTimPTH;
    private javax.swing.JButton btnTimSPTH;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMaPT;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblTienTraLai;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblCT;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblTraHang;
    private javax.swing.JTable tblTraHangCT;
    private javax.swing.JTextField txtLyDo;
    private javax.swing.JTextField txtMaHDTim;
    private javax.swing.JTextField txtMaPTH;
    private javax.swing.JTextField txtMaSPTH;
    private javax.swing.JTextField txtMaSPTim;
    private javax.swing.JLabel txtNgayTra;
    // End of variables declaration//GEN-END:variables
}
