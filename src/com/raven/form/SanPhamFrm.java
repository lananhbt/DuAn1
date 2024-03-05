package com.raven.form;

import Services.IManageLoaiSanPhamService;
import Services.IManageSanPhamService;
import ServiceImpl.ManageLoaiSanPhamService;
import ServiceImpl.ManageSanPhamService;
import ServiceImpl.NguoiDungServiceImpl;
import Services.NguoiDungService;
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SanPhamFrm extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private IManageSanPhamService sanPhamService;
    private IManageLoaiSanPhamService loaiSanPhamService;
    private static Webcam webcam;
    private WebcamPanel panel = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private DomainModels.dangNhap n;

    public SanPhamFrm() {
        initComponents();
        initwebcam();
        PQ();
        this.sanPhamService = new ManageSanPhamService();
        this.loaiSanPhamService = new ManageLoaiSanPhamService();
        List<QLLoaiSanPham> dsLSP = loaiSanPhamService.ALL();
        this.cboMaLSP.getModel();
        String[] sp = new String[dsLSP.size()];
        for (int i = 0; i < dsLSP.size(); i++) {
            sp[i] = dsLSP.get(i).getMaLSP();
        }
        cboMaLSP.setModel(new DefaultComboBoxModel(sp));

        cboLocTheoMaLoaiSanPham.setModel(new DefaultComboBoxModel(sp));
        List<QLSanPham> dsSPl = sanPhamService.ALL();
        this.cboLocTheoMaLoaiSanPham.getModel();
        String[] spl = new String[dsSPl.size()];
        for (int i = 0; i < dsSPl.size(); i++) {
            spl[i] = dsSPl.get(i).getMaSP();
        }
        cboLocTheoMaLoaiSanPham.setModel(new DefaultComboBoxModel(spl));
        this.loadTable();
    }

    public static void closeCam() {
        if (webcam == null) {
            return;
        }
        webcam.close();
    }

        public void setStatus(boolean insertable){ 
        //txtMaCD.setEditable(insertable); 
        btnClear.setEnabled(!insertable); 
        btnSua.setEnabled(!insertable); 
        btnThem.setEnabled(!insertable); 
        btnTim.setEnabled(!insertable); 
        btnXoa.setEnabled(!insertable); 
        btnHT.setEnabled(!insertable);  
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
        this.txtMaSP.setText("--");
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
        if(txtMaSP == null || txtMaSP.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm");
        }else if(txtTenSP == null || txtTenSP.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm");
        }else if(txtSoluong == null || txtSoluong.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm");
        }else if(txtSoluong.getText().matches("[a-z][A-Z]")){
            JOptionPane.showMessageDialog(this, "Số lượng sai định dạng");
        }else if(txtGiaNhap == null || txtGiaNhap.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá nhập");
        }else if(txtGiaNhap.getText().matches("[a-z][A-Z]")){
            JOptionPane.showMessageDialog(this, "Giá nhập sai định dạng");
        }else if(txtGiaBan == null || txtGiaBan.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá bán");
        }else if(txtGiaBan.getText().matches("[a-z][A-Z]")){
            JOptionPane.showMessageDialog(this, "Giá bán sai định dạng");
        }else if(txtHanSuDung == null || txtHanSuDung.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập hạn sử dụng");
        }else if(!txtHanSuDung.getText().matches("\"dd/MM/yyyy\"")){
            JOptionPane.showMessageDialog(this, "Hạn sử dụng phải là ngày tháng năm");
        }
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
            JOptionPane.showMessageDialog(this, "Sai định dạng hạn sử dụng");
            return null;
        }

        QLSanPham s = new QLSanPham(maSP, maLSP, tenSP, soLuong, giaNhap, giaBan, d);
        return s;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        cboMaLSP = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtHanSuDung = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboLocTheoMaLoaiSanPham = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        txtMaSP = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnHT = new javax.swing.JButton();

        jPanel4.setPreferredSize(new java.awt.Dimension(841, 650));

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

        cboLocTheoMaLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocTheoMaLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocTheoMaLoaiSanPhamActionPerformed(evt);
            }
        });

        jLabel3.setText("Giá nhập");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 200));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaSP.setText("--");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnHT.setText("Hiển thị");
        btnHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel49)))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(28, 28, 28)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnTim)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnHT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel52)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel50)
                                        .addComponent(jLabel21)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel56))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnHuy)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cboLocTheoMaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtMaSP))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(cboMaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnTim)
                    .addComponent(cboLocTheoMaLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnClear)
                    .addComponent(btnHT))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 841, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = this.tbl_SP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng để sửa");
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
        String ma = JOptionPane.showInputDialog(null, "vui lòng nhập mã SP muốn tìm");
        List<QLSanPham> ds = this.sanPhamService.ALL();
        int check = 0;
        if (ma.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã sản phẩm");
            return;
        } else {
            for (QLSanPham sp : ds) {
                if (sp.getMaSP().equalsIgnoreCase(ma)) {
                    check++;
                    this.txtMaSP.setText(sp.getMaSP());
                    this.cboMaLSP.setSelectedItem(sp.getMaLSP());
                    this.txtTenSP.setText(sp.getTenSP());
                    this.txtSoluong.setText(String.valueOf(sp.getSoLuong()));
                    this.txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
                    this.txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
                    this.txtHanSuDung.setText(String.valueOf(sp.getHanSuDung()));

                    for (int i = 0; i < this.sanPhamService.ALL().size() - 1; i++) {
                        if (tbl_SP.getValueAt(i, 0).equals(ma)) {
                            this.tbl_SP.setRowSelectionInterval(i, i);
                        }
                    }
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

    private void txtHanSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHanSuDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHanSuDungActionPerformed

    private void cboLocTheoMaLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocTheoMaLoaiSanPhamActionPerformed

        String ma = (String) this.cboLocTheoMaLoaiSanPham.getSelectedItem();
        List<QLSanPham> ds = this.sanPhamService.ALL();
        int check = 0;
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_SP.getModel();
        dtm.setRowCount(0);
        for (QLSanPham sp : ds) {
            if (sp.getMaSP().equalsIgnoreCase(ma)) {
                check++;
                Object[] rowData = {
                    sp.getMaSP(),
                    sp.getMaLSP(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    sp.getGiaNhap(),
                    sp.getGiaBan(),
                    sp.getHanSuDung()
                };
                dtm.addRow(rowData);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Tìm thấy sản phẩm");
            return;
        }
    }//GEN-LAST:event_cboLocTheoMaLoaiSanPhamActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (webcam == null) {
            return;
        }
        webcam.close();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHTActionPerformed
        if (tbl_SP.getRowCount() < sanPhamService.ALL().size()) {
            loadTable();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiển thị danh sách nhân viên");
        }
    }//GEN-LAST:event_btnHTActionPerformed

    private void initwebcam() {
        Dimension size = WebcamResolution.QQVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        //jPanel1.setSize(size);
        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 345, 231));
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
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
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
            }
            if (result != null) {
                txtMaSP.setText(result.getText());
            }

        } while (true);
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHT;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLocTheoMaLoaiSanPham;
    private javax.swing.JComboBox<String> cboMaLSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tbl_SP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtHanSuDung;
    private javax.swing.JLabel txtMaSP;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
