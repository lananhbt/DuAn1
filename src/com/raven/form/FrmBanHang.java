/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModels.KhachHang;
import DomainModels.dangNhap;
import ServiceImpl.HoaDonService;
import ServiceImpl.KhachHangSV;
import ServiceImpl.ManageHoaDonChiTietService;
import ServiceImpl.ManageHoaDonService;
import ServiceImpl.ManageSanPhamService;
import ServiceImpl.NguoiDungServiceImpl;
import Services.IManageHoaDon;
import Services.IManageHoaDonChiTiet;
import Services.IManageSanPhamService;
import Services.KhachHangIF;
import Services.NguoiDungService;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class FrmBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private IManageSanPhamService banHangService;
    private DefaultTableModel dtm;
    private HoaDonService hdSV;
    private IManageHoaDon hdService;
    private IManageHoaDonChiTiet ctService;
    private IManageSanPhamService spService;
    private NguoiDungService ndService;
    private dangNhap n;
    private KhachHangIF kh;
    private ArrayList<ManageHoaDonChiTiet> list = new ArrayList<>();
    List<ManageHoaDon> listHoaDons;
    List<ManageHoaDonChiTiet> listHoaDonChiTiets;
    private static Webcam webcam1 = null;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public FrmBanHang() {
        initComponents();
        initwebcam();
        banHangService = new ManageSanPhamService();
        hdSV = new HoaDonService();
        listHoaDons = new ArrayList<>();
        listHoaDonChiTiets = new ArrayList<>();
        banHangService = new ManageSanPhamService();
        this.hdService = new ManageHoaDonService();
        this.ctService = new ManageHoaDonChiTietService();
        this.spService = new ManageSanPhamService();
        this.ndService = new NguoiDungServiceImpl();
        this.kh = new KhachHangSV();
        this.loadHDC();
        loadToTable();
        this.loadTableHoaDon();
        this.clear();
        this.clearHDCT();
        //this.PQ();
    }

    public void clear() {
        String a = hdService.AllHD().size() + 1 + "";
        this.lblHD.setText("HD" + a);
        this.txtMaKH.setText("");
        //this.lbl.setText(ndService.findND(n.getCurrentLoginUsername()).getTen());
        this.lblMaNV.setText("");
        this.txtNgayTao.setText(java.time.LocalDate.now().toString());
        this.lbThanhTien.setText("--");
        this.cbxTT.setSelectedIndex(0);

    }

//    public void PQ(){
//        if(ndService.findND(n.getCurrentLoginUsername()).getChucVu().equals("Nhân viên")){
//            this.jPanel30.removeAll();
//        }
//    }
    public void clearHDCT() {
        String a = ctService.getAll().size() + 1 + "";
        this.lblMaHDCT.setText("CT" + a);
        this.spnSoLuong.setValue(0);
//        dtm = (DefaultTableModel) this.tblDSCho.getModel();
//        dtm.setRowCount(0);
        this.lbMaSP.setText("--");

    }

    private void loadToTable() {
        List<QLSanPham> spList = banHangService.ALL();
        dtm = (DefaultTableModel) tblDSSanPham.getModel();

        dtm.setRowCount(0);
        for (QLSanPham sanPham : spList) {
            dtm.addRow(new Object[]{
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
                sanPham.getGiaBan()
            });
        }
    }

    public void addSP(List<ManageHoaDonChiTiet> sanPhams) {

        dtm = (DefaultTableModel) tblDSCho.getModel();

        dtm.setRowCount(0);

        for (ManageHoaDonChiTiet sanPham : sanPhams) {
            Object[] rowData = {
                sanPham.getMaHDCT(),
                sanPham.getMaSP(),
                sanPham.getSoLuong(),
                sanPham.getGiaBan(),
                sanPham.getThanhTien()
            };
            dtm.addRow(rowData);
        }
    }

    public void loadHDC() {
        dtm = (DefaultTableModel) this.tblHoaDonCho.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDon sp : this.hdService.AllCho()) {
            Object[] rowData = {
                sp.getMaHD()
            };
            dtm.addRow(rowData);
        }
    }

    public void loadDS() {
        dtm = (DefaultTableModel) this.tblDSCho.getModel();
        dtm.setRowCount(0);

    }

    public ManageHoaDonChiTiet getFormData() {
        int row = this.tblDSSanPham.getSelectedRow();
        String DG = this.tblDSSanPham.getValueAt(row, 3).toString();
        String maSP = this.lbMaSP.getText();
        int sLg = (int) this.spnSoLuong.getValue();
        float donGia = Float.valueOf(DG);
        ManageHoaDonChiTiet s = new ManageHoaDonChiTiet(maSP, sLg, donGia, donGia * sLg);
        return s;
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

    public ManageHoaDon getFormDataHD() {
        String maHD = this.lblHD.getText();
        String maKH = this.txtMaKH.getText();
        String maND = this.lblMaNV.getText();
        String ngTao = this.txtNgayTao.getText();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        Date d;
        try {
            d = sdf.parse(ngTao);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày");
            return null;
        }
        float tongTien = Float.valueOf(this.lbThanhTien.getText());
        int tt = this.cbxTT.getSelectedIndex();
        ManageHoaDon s = new ManageHoaDon(maHD, maND, maKH, d, tongTien, tt);
        return s;
    }

    void loadTableHoaDon() {
        dtm = (DefaultTableModel) this.tblHoaDon.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDon sp : this.hdService.All()) {
            Object[] rowData = {
                sp.getMaHD(),
                sp.getMaND(),
                sp.getMaKH(),
                sp.getNgayTao(),
                sp.getTongTien(),
                sp.getTrangThai() == 1 ? "Đã thanh toán" : "",};
            dtm.addRow(rowData);
        }
    }

    void loadTableHoaDonChiTiet() {
//        listHoaDonChiTiets = hdctService.getAllHoaDonChiTiet();
//        if (listHoaDonChiTiets == null) {
//            JOptionPane.showMessageDialog(this, "Lỗi");
//        } else if (listHoaDonChiTiets.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "CSDL Rỗng");
//        } else {
//            model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
//            model.setRowCount(0);
//            for (hoaDonChiTiet hdct : listHoaDonChiTiets) {
//                Object[] row = new Object[7];
//                row[0] = hdct.getMaSP();
//                row[1] = hdct.getMaHD();
//                row[2] = hdct.getMaHDCT();
//                row[3] = hdct.getSoLuong();
//                row[4] = hdct.getDonGia();
//                row[5] = hdct.getThanhTien();
//
//                model.addRow(row);
//            }
//        }
    }

    void fillShearchHoaDon() {

//        String shearchHD = txtShearchMaHD.getText();
//
//        if (shearchHD == null || shearchHD.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Cần Shearch");
//            return;
//        } else {
//            listHoaDons = hdService.findShearchHoaDon(shearchHD);
//            model = (DefaultTableModel) tblHoaDon.getModel();
//            model.setRowCount(0);
//            for (hoaDon hd : listHoaDons) {
//                Object[] row = new Object[7];
//                row[0] = hd.getMaND();
//                row[1] = hd.getMaKH();
//                row[2] = hd.getMaHD();
//                row[3] = hd.getNgayTao();
//                row[4] = hd.getTongTien();
//                row[5] = hd.getTrangThai();
//
//                model.addRow(row);
//
//            }
//        }
//        if (listHoaDons.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Shearch Thất Bại");
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Shearch Thành Công");
//            return;
//        }
    }

    void fillShearchHoaDonChiTiet() {

//        String shearchHDCT = txtShearchMaSP.getText();
//
//        if (shearchHDCT == null || shearchHDCT.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã Cần Shearch");
//            return;
//        } else {
//            listHoaDonChiTiets = hdctService.fillShearchMaSP(shearchHDCT);
//            model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
//            model.setRowCount(0);
//            for (hoaDonChiTiet hdct : listHoaDonChiTiets) {
//                Object[] row = new Object[7];
//                row[0] = hdct.getMaSP();
//                row[1] = hdct.getMaHD();
//                row[2] = hdct.getMaHDCT();
//                row[3] = hdct.getSoLuong();
//                row[4] = hdct.getDonGia();
//                row[5] = hdct.getThanhTien();
//
//                model.addRow(row);
//            }
//        }
//        if (listHoaDonChiTiets.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Shearch Thất Bại");
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Shearch Thành Công");
//            return;
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnTimSP = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblDSSanPham = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        cbxTT = new javax.swing.JComboBox<>();
        btnTao = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        lbThanhTien = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        l = new javax.swing.JLabel();
        lblHD = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        btnLuuTam = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbMaSP = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDSCho = new javax.swing.JTable();
        spnSoLuong = new javax.swing.JSpinner();
        lblMaHDCT = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        btnHuyHoaDon = new javax.swing.JButton();
        cbxAll = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSDTKH = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        txtTimHD = new javax.swing.JTextField();
        btnTimHD = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        txtTimSPHD = new javax.swing.JTextField();
        btnTimSPHD = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblDHCT = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(846, 654));

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setPreferredSize(new java.awt.Dimension(800, 650));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Sản phẩm");

        btnTimSP.setBackground(new java.awt.Color(255, 255, 255));
        btnTimSP.setText("Tìm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        tblDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSanPhamMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tblDSSanPham);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnTimSP)
                        .addGap(0, 84, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setText("Mã KH");

        jLabel25.setText("Mã NV");

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel42.setText("Ngày tạo");

        jLabel43.setText("Trạng thái");

        btnThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        cbxTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));

        btnTao.setBackground(new java.awt.Color(255, 255, 255));
        btnTao.setText("Thêm vào hàng chờ");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        lbThanhTien.setText("--");

        jLabel48.setText("Tổng tiền");

        l.setText("Mã HD");

        lblHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHD.setText("--");

        txtNgayTao.setText("--");

        lbl.setText("--");

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(73, 73, 73)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(l)
                                .addGap(76, 76, 76)
                                .addComponent(lblHD, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(btnThem)))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(65, 65, 65)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnTao))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnThanhToan)
                .addGap(71, 71, 71)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(btnThem)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l)
                    .addComponent(lblHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(lbThanhTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTao)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(jButton1))
                .addGap(50, 50, 50)
                .addComponent(jLabel47)
                .addGap(22, 22, 22))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel80.setText("Mã HDCT");

        jLabel81.setText("Mã SP");

        jLabel82.setText("Số lượng ");

        btnLuuTam.setBackground(new java.awt.Color(255, 255, 255));
        btnLuuTam.setText("Lưu tạm");
        btnLuuTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTamActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật ");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbMaSP.setText("--");

        tblDSCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSChoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDSCho);

        spnSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnSoLuongMouseClicked(evt);
            }
        });

        lblMaHDCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaHDCT.setText("--");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81)
                            .addComponent(jLabel80)
                            .addComponent(jLabel82))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 74, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(btnLuuTam)
                        .addGap(27, 27, 27)
                        .addComponent(btnCapNhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(lblMaHDCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(lbMaSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuTam)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hoá đơn chờ");

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hoá đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDonCho);

        btnHuyHoaDon.setText("Huỷ hoá đơn chờ");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        cbxAll.setText("Chọn tất cả");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxAll)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxAll)
                .addGap(18, 18, 18)
                .addComponent(btnHuyHoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("SĐT KH");

        btnTim.setBackground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnTim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim)
                    .addComponent(jLabel2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane3.addTab("Bán hàng", jPanel27);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel95.setText("Mã HD");

        btnTimHD.setBackground(new java.awt.Color(255, 255, 255));
        btnTimHD.setText("Tìm kiếm");
        btnTimHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHDActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã ND", "Mã KH", "Ngày tạo", "Tổng tiền", "trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addGap(30, 30, 30)
                        .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimHD)
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel96.setText("Mã SP");

        btnTimSPHD.setBackground(new java.awt.Color(255, 255, 255));
        btnTimSPHD.setText("Tìm kiếm");
        btnTimSPHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPHDActionPerformed(evt);
            }
        });

        tblDHCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane21.setViewportView(tblDHCT);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel96)
                        .addGap(36, 36, 36)
                        .addComponent(txtTimSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnTimSPHD)
                        .addGap(58, 58, 58))))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtTimSPHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSPHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("DS hóa đơn bán hàng", jPanel30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        String maSP = txtTimSP.getText();
        if (maSP == null || maSP.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<QLSanPham> sanPhams = banHangService.getByCode(maSP);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblDSSanPham.getModel();

                dtm.setRowCount(0);
                for (QLSanPham sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
                        sanPham.getGiaBan()
                    });
                }
                lbMaSP.setText(maSP);
            }

        }
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void tblDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSanPhamMouseClicked
        int row = this.tblDSSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSP = this.tblDSSanPham.getValueAt(row, 0).toString();
        this.lbMaSP.setText(maSP);
    }//GEN-LAST:event_tblDSSanPhamMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ThemNhanhKH diem = new ThemNhanhKH();
        diem.setSize(200, 200);
        diem.setVisible(true);
        //dispose();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        List<ManageHoaDon> listHD = this.hdService.AllCho1(this.lblHD.getText());
        if (this.cbxTT.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Khách hàng chưa thanh toán vui lòng thanh toán");
            return;
        } else {
            if (listHD.size() > 0) {
                this.hdService.update(this.lblHD.getText(), Double.valueOf(this.lbThanhTien.getText()));
                return;
            }
            ManageHoaDon hd = this.getFormDataHD();
            if (hd == null) {
                return;
            }
            this.hdService.insert(hd);
            int row = 0;
            ArrayList<ManageHoaDonChiTiet> listHDCT = new ArrayList<>();
            for (ManageHoaDonChiTiet a : list) {
                a.setMaHDCT(tblDSCho.getValueAt(row, 0).toString());
                a.setMaHD(this.lblHD.getText());
                a.setMaSP(tblDSCho.getValueAt(row, 1).toString());
                a.setSoLuong((int) tblDSCho.getValueAt(row, 2));
                a.setGiaBan((Float) tblDSCho.getValueAt(row, 3));
                a.setThanhTien((Float) tblDSCho.getValueAt(row, 4));
                row++;
                listHDCT.add(a);
            }
            for (ManageHoaDonChiTiet h : listHDCT) {
                ctService.insert(h);
                this.spService.updateSLGH(h.getSoLuong(), h.getMaSP());
            }
            this.loadHDC();
            this.loadTableHoaDon();
            this.loadToTable();
            this.clear();
            this.clearHDCT();
            this.loadDS();
            this.list.removeAll(list);
            JOptionPane.showMessageDialog(this, "thành công");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String sdt = this.txtSDTKH.getText();
        String dangSDT = "0\\d{9,10}";
        if (sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sdt ko đc để trống");
            return;
        }
        if (!sdt.matches(dangSDT)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng!");
            return;
        }
        try {
            List<KhachHang> k = kh.tim(sdt);
            for (KhachHang kh : k) {
                txtMaKH.setText(kh.getMaKH());
            }
            JOptionPane.showMessageDialog(null, "đã hiện mã khách hàng muốn tìm");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed

        if (this.cbxTT.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(this, "Khách hàng đã thanh toán vui lòng chọn thanh toán");
            return;
        } else {
            ManageHoaDon hd = this.getFormDataHD();
            if (hd == null) {
                return;
            }
            this.hdService.insert(hd);
            int row = 0;
            ArrayList<ManageHoaDonChiTiet> listHDCT = new ArrayList<>();
            for (ManageHoaDonChiTiet a : list) {
                a.setMaHDCT((String) tblDSCho.getValueAt(row, 0));
                a.setMaHD(this.lblHD.getText());
                a.setMaSP((String) tblDSCho.getValueAt(row, 1));
                a.setSoLuong((int) tblDSCho.getValueAt(row, 2));
                a.setGiaBan((Float) tblDSCho.getValueAt(row, 3));
                a.setThanhTien((Float) tblDSCho.getValueAt(row, 4));
                row++;
                listHDCT.add(a);
            }
            for (ManageHoaDonChiTiet hoaDonChiTiet : listHDCT) {
                ctService.insert(hoaDonChiTiet);
                this.spService.updateSLGH(hoaDonChiTiet.getSoLuong(), hoaDonChiTiet.getMaSP());
            }
            this.loadHDC();
            this.loadTableHoaDon();
            this.loadToTable();
            this.clear();
            this.clearHDCT();
            this.loadDS();
            this.list.removeAll(list);
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được thêm vào hàng chờ");
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnLuuTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTamActionPerformed
        //ArrayList<ManageHoaDonChiTiet> listS = new ArrayList<>();
        ManageHoaDonChiTiet chiTietHoaDonViewModel = new ManageHoaDonChiTiet();
        int row = tblDSSanPham.getSelectedRow();
        String maHDCT = this.lblMaHDCT.getText();
        String maSP = (String) tblDSSanPham.getValueAt(row, 0);
        int so = (int) spnSoLuong.getValue();
        float gia = (Float) tblDSSanPham.getValueAt(row, 3);

        if (so < 0) {
            JOptionPane.showMessageDialog(this, "số lượng không dược âm ");
            return;
        }
        List<QLSanPham> s = spService.getByCode(maSP);
        for (QLSanPham sanPham : s) {
            if (sanPham.getSoLuong() < so) {
                JOptionPane.showMessageDialog(this, "Trong kho không đủ số lượng cần mua");
                return;
            }
        }
        chiTietHoaDonViewModel.setMaHDCT(maHDCT);
        chiTietHoaDonViewModel.setMaSP(maSP);
        chiTietHoaDonViewModel.setSoLuong(so);
        chiTietHoaDonViewModel.setGiaBan(gia);
        list.add(chiTietHoaDonViewModel);
        addSP(list);
        int r = tblDSCho.getRowCount();
        this.clearHDCT();
        String a = ctService.getAll().size() + 1 + r + "";
        this.lblMaHDCT.setText("CT" + a);
        int thanhTien = 0;
        for (ManageHoaDonChiTiet ct : list) {
            thanhTien = (int) (thanhTien + ct.getThanhTien());
            lbThanhTien.setText("" + thanhTien);
        }

    }//GEN-LAST:event_btnLuuTamActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        DefaultTableModel models = (DefaultTableModel) tblDSCho.getModel();
        ManageHoaDonChiTiet chiTietHoaDonViewModel = new ManageHoaDonChiTiet();
        int row = this.tblDSCho.getSelectedRow();
        if (row == -1) {
            return;
        }

        String maHDCT = tblDSCho.getValueAt(row, 0).toString();
        String maSP = tblDSCho.getValueAt(row, 1).toString();
        int so = (int) spnSoLuong.getValue();
        float gia = (Float) tblDSCho.getValueAt(row, 3);

        if (so < 0) {
            JOptionPane.showMessageDialog(this, "số lượng không dược âm ");
            return;
        }
        List<QLSanPham> s = spService.getByCode(maSP);
        for (QLSanPham sanPham : s) {
            if (sanPham.getSoLuong() < so) {
                JOptionPane.showMessageDialog(this, "Trong kho không đủ số lượng cần mua");
                return;
            }
        }
        models.removeRow(row);
        list.remove(row);
        chiTietHoaDonViewModel.setMaHDCT(maHDCT);
        chiTietHoaDonViewModel.setMaSP(maSP);
        chiTietHoaDonViewModel.setSoLuong(so);
        chiTietHoaDonViewModel.setGiaBan(gia);
        list.add(chiTietHoaDonViewModel);
        addSP(list);
        int r = tblDSCho.getRowCount();
        this.clearHDCT();
        String a = ctService.getAll().size() + 1 + r + "";
        this.lblMaHDCT.setText("CT" + a);
        float thanhTien = 0;
        for (ManageHoaDonChiTiet ct : list) {
            thanhTien = (float) (thanhTien + ct.getThanhTien());
            lbThanhTien.setText("" + thanhTien);
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        DefaultTableModel models = (DefaultTableModel) tblDSCho.getModel();
        int row = this.tblDSCho.getSelectedRow();
        if (row == -1) {
            return;
        }
        models.removeRow(row);
        list.remove(row);
        float thanhTien = 0;
        for (ManageHoaDonChiTiet ct : list) {
            thanhTien = (float) (thanhTien + ct.getThanhTien());
            lbThanhTien.setText("" + thanhTien);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void spnSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnSoLuongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spnSoLuongMouseClicked

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        int row = tblHoaDonCho.getSelectedRow();
        String maHD = tblHoaDonCho.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) this.tblDSCho.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : this.ctService.AllMa(maHD)) {
            Object[] rowData = {
                sp.getMaHDCT(),
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getGiaBan(),
                sp.getSoLuong() * sp.getGiaBan()
            };
            dtm.addRow(rowData);
        }
        for (ManageHoaDon sp : this.hdService.AllMa(maHD)) {
            if (sp.getMaHD().equalsIgnoreCase(maHD)) {
                this.txtMaKH.setText(sp.getMaKH());
                //this.lblMaNV.setText(sp.getMaND());
                this.lbThanhTien.setText(String.valueOf(sp.getTongTien()));
                this.cbxTT.setSelectedIndex(sp.getTrangThai());
                this.lblHD.setText(sp.getMaHD());
                this.txtNgayTao.setText(sp.getNgayTao().toString());
            }
        }

        for (ManageHoaDonChiTiet sp1 : this.ctService.AllMa(maHD)) {
            if (sp1.getMaHD().equalsIgnoreCase(maHD)) {
                //this.lblMaHDCT.setText(sp1.getMaHDCT());
            }
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        if (cbxAll.isSelected() == true) {
            int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn hủy tất cả không");
            if (cofirm != JOptionPane.YES_OPTION) {
                return;
            }
            this.ctService.deleteALL();
            this.hdService.delete();
            this.loadHDC();
            this.clear();
            JOptionPane.showMessageDialog(this, "Hủy thành công");
        } else {
            int row = tblHoaDonCho.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "vui lòng chọn 1 dòng");
                return;
            }
            int cofirm = JOptionPane.showConfirmDialog(this, "bạn muốn hủy không");
            if (cofirm != JOptionPane.YES_OPTION) {
                return;
            }
            String ma = this.tblHoaDonCho.getValueAt(row, 0).toString();
            this.ctService.deleteMaHD(ma);
            this.hdService.deleteMa(ma);
            this.loadHDC();
            this.clear();
            JOptionPane.showMessageDialog(this, "Hủy thành công");
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String ma = this.tblHoaDon.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) this.tblDHCT.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : this.ctService.AllMa(ma)) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getGiaBan(),
                sp.getThanhTien()
            };
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTimHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHDActionPerformed
        String ma = txtTimHD.getText();
        if (ma == null || ma.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<ManageHoaDon> sanPhams = hdService.AllMa1(ma);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblHoaDon.getModel();

                dtm.setRowCount(0);
                for (ManageHoaDon sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaHD(), sanPham.getMaND(), sanPham.getMaKH(),
                        sanPham.getNgayTao(), sanPham.getTongTien(), sanPham.getTrangThai() == 1 ? "Đã thanh toán" : ""
                    });
                }
            }

        }
    }//GEN-LAST:event_btnTimHDActionPerformed

    private void btnTimSPHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPHDActionPerformed
        String ma = txtTimSPHD.getText();
        if (ma == null || ma.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");

        } else {
            List<ManageHoaDonChiTiet> sanPhams = ctService.All(ma);

            if (sanPhams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
            } else {
                dtm = (DefaultTableModel) tblDSSanPham.getModel();

                dtm.setRowCount(0);
                for (ManageHoaDonChiTiet sanPham : sanPhams) {
                    dtm.addRow(new Object[]{
                        sanPham.getMaSP(), sanPham.getSoLuong(), sanPham.getGiaBan(),
                        sanPham.getThanhTien()
                    });
                }
            }

        }
    }//GEN-LAST:event_btnTimSPHDActionPerformed

    private void tblDSChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSChoMouseClicked
        int row = this.tblDSCho.getSelectedRow();
        String maHD = this.tblDSCho.getValueAt(row, 0).toString();
        String ma = this.tblDSCho.getValueAt(row, 1).toString();
        String so = this.tblDSCho.getValueAt(row, 2).toString();
        int s = Integer.parseInt(so);
        this.lblMaHDCT.setText(maHD);
        this.lbMaSP.setText(ma);
        this.spnSoLuong.setValue(s);
    }//GEN-LAST:event_tblDSChoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.clear();
        this.clearHDCT();
        this.loadDS();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            if (result != null) {
                List<QLSanPham> sanPhams = banHangService.getByCode(result.getText());
                if (sanPhams.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có sản phẩm mà bạn đang tìm");
                } else {
                    for (int i = 0; i < this.spService.ALL().size() - 1; i++) {
                        if (tblDSSanPham.getValueAt(i, 0).equals(result.getText())) {
                            this.tblDSSanPham.setRowSelectionInterval(i, i);
                        }
                    }
                    lbMaSP.setText(result.getText());
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
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTat;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimHD;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnTimSPHD;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox cbxAll;
    private javax.swing.JComboBox<String> cbxTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel l;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbThanhTien;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblMaHDCT;
    private javax.swing.JTextField lblMaNV;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblDHCT;
    private javax.swing.JTable tblDSCho;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JLabel txtNgayTao;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTimHD;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txtTimSPHD;
    // End of variables declaration//GEN-END:variables
}
