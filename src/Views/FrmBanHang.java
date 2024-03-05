package Views;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import DomainModels.SanPham;
import Repositories.ISanPhamRepository;
import Repositories.SanPhamRepository;
import ServiceImpl.HoaDonService;
import ServiceImpl.KhachHangSV;
import ServiceImpl.ManageHoaDonChiTietService;
import ServiceImpl.ManageHoaDonService;
import Services.IManageSanPhamService;
import ServiceImpl.ManageSanPhamService;
import Services.IManageHoaDon;
import Services.IManageHoaDonChiTiet;
import ViewModels.ManageHoaDon;
import ViewModels.ManageHoaDonChiTiet;
import ViewModels.QLHoaDon;
import ViewModels.QLSanPham;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class FrmBanHang extends javax.swing.JFrame {

    private IManageSanPhamService banHangService;
    private DefaultTableModel dtm;
    private HoaDonService hdSV;
    private IManageHoaDon hdService;
    private IManageHoaDonChiTiet ctService;
    private IManageSanPhamService spService;
    private ArrayList<ManageHoaDonChiTiet> list = new ArrayList<>();
    List<ManageHoaDon> listHoaDons;
    List<ManageHoaDonChiTiet> listHoaDonChiTiets;

    public FrmBanHang() {
        initComponents();
        banHangService = new ManageSanPhamService();
        hdSV = new HoaDonService();
        listHoaDons = new ArrayList<>();
        listHoaDonChiTiets = new ArrayList<>();
        setLocationRelativeTo(null);
        loadToTable();
        banHangService = new ManageSanPhamService();
        this.hdService = new ManageHoaDonService();
        this.ctService = new ManageHoaDonChiTietService();
        this.spService = new ManageSanPhamService();
        setLocationRelativeTo(null);
        this.loadHDC();
        this.loadTableHoaDon();
        this.clear();
        this.clearHDCT();

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
            Object[] rowData  = {
                sanPham.getMaHDCT(), 
                sanPham.getMaSP(),
                sanPham.getTenSP(), 
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

//    public ManageHoaDonChiTiet getFormData() {
//        int row = this.tblDSSanPham.getSelectedRow();
//        String DG = this.tblDSSanPham.getValueAt(row, 3).toString();
//        String maHDCT = this.txtMaHDCT.getText();
//        String maSP = this.lbMaSP.getText();
//        String maHD = this.txtMaHD.getText();
//        int sLg = (int) this.spnSoLuong.getValue();
//        float donGia = Float.valueOf(DG);
//        ManageHoaDonChiTiet s = new ManageHoaDonChiTiet(maHDCT, maHD, maSP, sLg, donGia, donGia * sLg);
//        return s;
//    }

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
        String maND = this.txtMaNV.getText();
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

    public void clear() {
        String a = hdService.AllHD().size() + 1 + "" ;
        this.lblHD.setText("HD" + a);
        this.txtMaKH.setText("");
        this.txtMaNV.setText("");
        this.txtNgayTao.setText("");
        this.lbThanhTien.setText("--");
        this.cbxTT.setSelectedIndex(0);

    }

    public void clearHDCT() {
        String a = ctService.getAll().size() + 1 + "" ;
        this.lblMaHDCT.setText("CT" + a);
        this.spnSoLuong.setValue(0);
//        dtm = (DefaultTableModel) this.tblDSCho.getModel();
//        dtm.setRowCount(0);
        this.lbMaSP.setText("--");

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
        txtNgayTao = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        cbxTT = new javax.swing.JComboBox<>();
        btnTao = new javax.swing.JButton();
        txtMaNV = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        lbThanhTien = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        lblHD = new javax.swing.JLabel();
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
        jPanel30 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jButton41 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jTextField49 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblDHCT = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setText("Sản phẩm");

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
                .addComponent(jLabel20)
                .addGap(34, 34, 34)
                .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnTimSP)
                .addContainerGap(368, Short.MAX_VALUE))
            .addComponent(jScrollPane19)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setText("Mã KH");

        jLabel25.setText("Mã NV");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel42.setText("Ngày tạo");

        jLabel43.setText("Trạng thái");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        cbxTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));

        btnTao.setText("Thêm vào hàng chờ");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        lbThanhTien.setText("--");

        jLabel48.setText("Tổng tiền");

        lbMaHD.setText("Mã HD");

        lblHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHD.setText("--");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(73, 73, 73)
                                .addComponent(txtMaNV))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(73, 73, 73)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(lbMaHD)
                                .addGap(18, 18, 18)
                                .addComponent(lblHD, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnTao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan)
                .addGap(45, 45, 45))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(btnThem)
                    .addComponent(btnTim)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaHD)
                    .addComponent(lblHD))
                .addGap(13, 13, 13)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbThanhTien)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTao)
                    .addComponent(btnThanhToan))
                .addGap(22, 22, 22))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel80.setText("Mã HDCT");

        jLabel81.setText("Mã SP");

        jLabel82.setText("Số lượng ");

        btnLuuTam.setText("Lưu tạm");
        btnLuuTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTamActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật ");
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

        lbMaSP.setText("--");

        tblDSCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
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
                            .addComponent(btnLuuTam)
                            .addComponent(jLabel80))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(lbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addComponent(jLabel82)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(btnCapNhat)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                    .addComponent(jLabel82)
                    .addComponent(lbMaSP)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa)
                    .addComponent(btnLuuTam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
            .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbxAll)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuyHoaDon)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Bán hàng", jPanel27);

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel95.setText("Mã HD");

        jButton41.setText("Tìm kiếm");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã ND", "Mã KH", "Ngày tạo", "Tổng tiền", "trạng thái"
            }
        ));
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
                .addComponent(jLabel95)
                .addGap(30, 30, 30)
                .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton41)
                .addContainerGap(83, Short.MAX_VALUE))
            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel96.setText("Mã SP");

        jButton42.setText("Tìm kiếm");

        tblDHCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane21.setViewportView(tblDHCT);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addGap(36, 36, 36)
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton42)
                .addGap(58, 58, 58))
            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
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
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("DS hóa đơn bán hàng", jPanel30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pack();
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

    private void btnLuuTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTamActionPerformed
        //ArrayList<ManageHoaDonChiTiet> listS = new ArrayList<>();
        ManageHoaDonChiTiet chiTietHoaDonViewModel = new ManageHoaDonChiTiet();
        int row = tblDSSanPham.getSelectedRow();
        chiTietHoaDonViewModel.setMaHDCT(this.lblMaHDCT.getText());
        chiTietHoaDonViewModel.setMaSP((String) tblDSSanPham.getValueAt(row, 0));
        chiTietHoaDonViewModel.setTenSP((String) tblDSSanPham.getValueAt(row, 1));
        chiTietHoaDonViewModel.setSoLuong((int) spnSoLuong.getValue());
        chiTietHoaDonViewModel.setGiaBan((Float) tblDSSanPham.getValueAt(row, 3));
        list.add(chiTietHoaDonViewModel);
        addSP(list);
        int b = 0;
        int r = tblDSCho.getRowCount();
        this.clearHDCT();
        String a = ctService.getAll().size() + 1 + r + "" ;
        this.lblMaHDCT.setText("CT" + a);
        int thanhTien = 0;
        for (ManageHoaDonChiTiet ct : list) {
            thanhTien = (int) (thanhTien + ct.getThanhTien());
            lbThanhTien.setText("" + thanhTien);
        }


    }//GEN-LAST:event_btnLuuTamActionPerformed

    private void spnSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnSoLuongMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_spnSoLuongMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (this.cbxTT.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Khách hàng chưa thanh toán vui lòng thanh toán");
            return;
        }else{
        ManageHoaDon hd = this.getFormDataHD();
        if (hd == null) {
            return;
        }
        this.hdService.insert(hd);
        int row = 0;
        ArrayList<ManageHoaDonChiTiet> listHDCT = new ArrayList<>();
        for (ManageHoaDonChiTiet a : list) {
            a.setMaHDCT((String) tblDSCho.getValueAt(row, 0));
            a.setMaHD(this.lbMaHD.getText());
            a.setMaSP((String) tblDSCho.getValueAt(row, 1));
            a.setSoLuong((int) tblDSCho.getValueAt(row, 3));
            a.setGiaBan((Float) tblDSCho.getValueAt(row, 4));
            a.setThanhTien((Float) tblDSCho.getValueAt(row, 5));
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
        JOptionPane.showMessageDialog(this, "thành công");
        }
//        if (this.cbxTT.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(this, "Khách hàng chưa thanh toán vui lòng thanh toán");
//            return;
//        } else {
//            ManageHoaDonChiTiet ct = this.getFormData();
//            ManageHoaDon hd = this.getFormDataHD();
//            if (ct == null || hd == null) {
//                return;
//            }
//            this.hdService.insert(hd);
//            this.ctService.insert(ct);
//            this.spService.updateSLGH(ct.getSoLuong(), ct.getMaSP());
//            this.loadHDC();
//            this.loadTableHoaDon();
//            this.loadToTable();
//            this.clear();
//            JOptionPane.showMessageDialog(this, "thành công");
//        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
//        if (this.cbxTT.getSelectedIndex() == 1) {
//            JOptionPane.showMessageDialog(this, "Khách hàng đã thanh toán vui lòng chọn thanh toán");
//            return;
//        } else {
//            //ManageHoaDonChiTiet ct = this.getFormData();
//            ManageHoaDon hd = this.getFormDataHD();
//            if (ct == null || hd == null) {
//                return;
//            }
//            this.hdService.insert(hd);
//            this.ctService.insert(ct);
//            this.spService.updateSLGH(ct.getSoLuong(), ct.getMaSP());
//            this.loadToTable();
//            this.loadHDC();
//            this.clear();
//            this.clearHDCT();
//            JOptionPane.showMessageDialog(this, "Hóa đơn đã được thêm vào hàng chờ");
//        }

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
            a.setMaHD(this.lbMaHD.getText());
            a.setMaSP((String) tblDSCho.getValueAt(row, 1));
            a.setSoLuong((int) tblDSCho.getValueAt(row, 3));
            a.setGiaBan((Float) tblDSCho.getValueAt(row, 4));
            a.setThanhTien((Float) tblDSCho.getValueAt(row, 5));
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
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được thêm vào hàng chờ");
        }
    }//GEN-LAST:event_btnTaoActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        int row = tblHoaDonCho.getSelectedRow();
        String maHD = tblHoaDonCho.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) this.tblDSCho.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : this.ctService.All(maHD)) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getSoLuong(),
                sp.getGiaBan(),
                sp.getSoLuong() * sp.getGiaBan()
            };
            dtm.addRow(rowData);
        }
        for (ManageHoaDon sp : this.hdService.AllMa(maHD)) {
            if (sp.getMaHD().equalsIgnoreCase(maHD)) {
                this.txtMaKH.setText(sp.getMaKH());
                this.txtMaNV.setText(sp.getMaND());
                this.lbThanhTien.setText(String.valueOf(sp.getTongTien()));
                this.cbxTT.setSelectedIndex(sp.getTrangThai());
                this.lbMaHD.setText(sp.getMaHD());
                this.txtNgayTao.setText(sp.getNgayTao().toString());
            }
        }

        for (ManageHoaDonChiTiet sp1 : this.ctService.AllMa(maHD)) {
            if (sp1.getMaHD().equalsIgnoreCase(maHD)) {
                this.lblMaHDCT.setText(sp1.getMaHDCT());
            }
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String sdt = JOptionPane.showInputDialog(null, "nhập sdt để tìm");


    }//GEN-LAST:event_btnTimActionPerformed

    private void tblDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSanPhamMouseClicked
        int row = this.tblDSSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maSP = this.tblDSSanPham.getValueAt(row, 0).toString();
        this.lbMaSP.setText(maSP);
    }//GEN-LAST:event_tblDSSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String ma = this.tblHoaDon.getValueAt(row, 0).toString();
        dtm = (DefaultTableModel) this.tblDHCT.getModel();
        dtm.setRowCount(0);
        for (ManageHoaDonChiTiet sp : this.ctService.All(ma)) {
            Object[] rowData = {
                sp.getMaSP(),
                sp.getSoLuong(),
                sp.getGiaBan(),
                sp.getThanhTien()
            };
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int row = this.tblDSCho.getSelectedRow();
        if (row == -1) {
            return;
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = this.tblDSCho.getSelectedRow();
        if (row == -1) {
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        FrmThemNhanhKhachHang diem = new FrmThemNhanhKhachHang();
        diem.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnThemActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox cbxAll;
    private javax.swing.JComboBox<String> cbxTT;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbThanhTien;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblMaHDCT;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblDHCT;
    private javax.swing.JTable tblDSCho;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTimSP;
    // End of variables declaration//GEN-END:variables
}
