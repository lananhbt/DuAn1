/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import DomainModels.NguoiDung;
import Services.NguoiDungService;
import ServiceImpl.NguoiDungServiceImpl;
import ViewModels.NguoiDungViewModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class NhanVienFrm extends javax.swing.JPanel {

    private DefaultTableModel dtm;
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private DomainModels.dangNhap n;
    
    public NhanVienFrm() {
        initComponents();
         loadTable(nguoiDungService.listND());
        List<NguoiDungViewModel> nd = nguoiDungService.listND();
        this.clearForm();
        this.PQ();
    }

    public void loadTable(ArrayList<NguoiDungViewModel> list) {
        dtm = (DefaultTableModel) tblNguoiDung.getModel();
        dtm.setRowCount(0);
        for (NguoiDungViewModel nd : list) {
            dtm.addRow(new Object[]{
                nd.getMaND(),
                nd.getHo(),
                nd.getTenDem(),
                nd.getTen(),
                nd.getGioiTinh(),
                nd.getNgaySinh(),
                nd.getDiaChi(),
                nd.getSdt(),
                nd.geteMail(),
                nd.getChucVu()});
        }
    }
    public void setStatus(boolean insertable){ 
        //txtMaCD.setEditable(insertable); 
        btnClearForm.setEnabled(!insertable); 
        btnSua.setEnabled(!insertable); 
        btnThem.setEnabled(!insertable); 
        btnTimKiem.setEnabled(!insertable); 
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

    public NguoiDung getForm() {
        NguoiDung nd = new NguoiDung();
        if (txtMa.getText() == "" || txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã của người dùng");
        } else if (txtHo.getText() == "" || txtHo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập họ của người dùng");
        }else if(txtTenDem.getText() == "" || txtTenDem.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên đệm của người dùng");
        }else if(txtTen.getText() == "" || txtTen.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên của người dùng");
        
        }else if(txtNgaySinh.getText() == "" || txtNgaySinh.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sinh của người dùng");
        }else if(txtDiaChi.getText() == "" || txtDiaChi.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ của người dùng");
        }else if(txtSDT.getText() == "" || txtSDT.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại của người dùng");
        }else if(txtEmail.getText() == "" || txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email của người dùng");
        }else if(!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")){
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
        }else if(!txtSDT.getText().matches("0\\d{9}")){
            JOptionPane.showMessageDialog(this, "SDT không đúng định dạng");
        }
        String ngSinh = this.txtNgaySinh.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-mm-dd");
        Date d;
        try {
            d = sdf.parse(ngSinh);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày sinh");
            return null;
        }
        nd.setMaND(txtMa.getText());
        nd.setHo(txtHo.getText());
        nd.setTenDem(txtTenDem.getText());
        nd.setTen(txtTen.getText());
        if (rdoNam.isSelected() == true) {
            nd.setGioiTinh("Nam");
        } else {
            nd.setGioiTinh("Nữ");
        }
        nd.setNgaySinh(ngSinh);
        nd.setDiaChi(txtDiaChi.getText());
        nd.setSdt(txtSDT.getText());
        nd.seteMail(txtEmail.getText());
        nd.setChucVu((String) cboChucVu.getSelectedItem());
        return nd;
    }

    public void clearForm() {
        String a = this.nguoiDungService.listND().size() + 1 + "";
        this.txtMa.setText("NV" + a);
        txtHo.setText("");
        txtTenDem.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        cboChucVu.setSelectedIndex(0);
        rdoNam.setSelected(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClearForm = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cboChucVu_2 = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        txtMa = new javax.swing.JLabel();
        btnHT = new javax.swing.JButton();

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Quản lý người dùng");

        jLabel12.setText("Mã ");

        jLabel13.setText("Họ ");

        jLabel14.setText("Tên đệm");

        jLabel15.setText("Tên ");

        jLabel16.setText("Giới tính");

        jLabel66.setText("Địa chỉ");

        jLabel67.setText("SĐT");

        jLabel68.setText("Email");

        jLabel71.setText("Chức vụ");

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam ");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        btnThem.setText("Thêm");
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

        btnClearForm.setText("Xoá form");
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ ", "Tên đệm", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Sdt", "Email", "Chức vụ"
            }
        ));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblNguoiDung);

        jLabel72.setText("Ngày sinh");

        cboChucVu_2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cboChucVu_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVu_2ActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setText("--");

        btnHT.setText("Hiển thị");
        btnHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addGap(54, 54, 54)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTen)
                                            .addComponent(txtTenDem, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtHo)
                                            .addComponent(txtNgaySinh)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(cboChucVu_2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel67)
                                            .addComponent(jLabel68)
                                            .addComponent(jLabel71)
                                            .addComponent(jLabel66)
                                            .addComponent(jLabel16))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtSDT)
                                                .addComponent(cboChucVu, 0, 231, Short.MAX_VALUE)
                                                .addComponent(txtDiaChi)
                                                .addComponent(txtEmail))
                                            .addComponent(btnHT)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(rdoNam)
                                                .addGap(73, 73, 73)
                                                .addComponent(rdoNu))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(btnThem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSua)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnClearForm)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 21, Short.MAX_VALUE)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtMa))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel71)
                    .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(jLabel16)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHT)
                .addGap(4, 4, 4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnClearForm)
                    .addComponent(cboChucVu_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        NguoiDung nd = getForm();
        if (txtMa.getText() == "" || txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã của người dùng");
        } else if (txtHo.getText() == "" || txtHo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập họ của người dùng");
        }else if(txtTenDem.getText() == "" || txtTenDem.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên đệm của người dùng");
        }else if(txtTen.getText() == "" || txtTen.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên của người dùng");
        }else if(rdoNam.isSelected() == false && rdoNu.isSelected() == false){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn giới tính của người dùng");
        }else if(txtNgaySinh.getText() == "" || txtNgaySinh.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sinh của người dùng");
        }else if(txtDiaChi.getText() == "" || txtDiaChi.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ của người dùng");
        }else if(txtSDT.getText() == "" || txtSDT.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại của người dùng");
        }else if(txtEmail.getText() == "" || txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email của người dùng");
        }else {
            nguoiDungService.them(nd);
            loadTable(nguoiDungService.listND());
            JOptionPane.showMessageDialog(this, "Bạn đã thêm thành công");
            clearForm();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        String maND = txtMa.getText();
        
        int row = tblNguoiDung.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng muốn sửa thông tin");
        }else{
            NguoiDung nguoiDung = getForm();
            if (nguoiDungService.sua(nguoiDung, maND)) {
                loadTable(nguoiDungService.listND());
                JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công");
                clearForm();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String maND = txtMa.getText();
        int row = tblNguoiDung.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng chứa thông tin muốn xoá");
        }else{
            if (nguoiDungService.delete(maND)) {
                loadTable(nguoiDungService.listND());
                JOptionPane.showMessageDialog(this, "Bạn đã xóa thành công");
                clearForm();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        // TODO add your handling code here:
        int row = tblNguoiDung.getSelectedRow();
        txtMa.setText(tblNguoiDung.getValueAt(row, 0).toString() + "");
        txtHo.setText(tblNguoiDung.getValueAt(row, 1).toString() + "");
        txtTenDem.setText(tblNguoiDung.getValueAt(row, 2).toString() + "");
        txtTen.setText(tblNguoiDung.getValueAt(row, 3).toString() + "");
        if (tblNguoiDung.getValueAt(row, 4).toString().equals("Nam")) {
            rdoNam.setSelected(true);
        } else if (tblNguoiDung.getValueAt(row, 4).toString().equals("Nữ")) {
            rdoNu.setSelected(true);
        }
        txtNgaySinh.setText(tblNguoiDung.getValueAt(row, 5).toString() + "");
        txtDiaChi.setText(tblNguoiDung.getValueAt(row, 6).toString() + "");
        txtSDT.setText(tblNguoiDung.getValueAt(row, 7).toString() + "");
        txtEmail.setText(tblNguoiDung.getValueAt(row, 8).toString() + "");
        cboChucVu.setSelectedItem(tblNguoiDung.getValueAt(row, 9).toString() + "");
    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void cboChucVu_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVu_2ActionPerformed
        // TODO add your handling code here:
        String cV = (String) this.cboChucVu_2.getSelectedItem();
        List<NguoiDungViewModel> ds = this.nguoiDungService.listND();
        int index = 0;
        dtm = (DefaultTableModel) this.tblNguoiDung.getModel();
        dtm.setRowCount(0);
        for (NguoiDungViewModel nd : ds) {
            if (nd.getChucVu().equals(cV)) {
                index++;
                dtm.addRow(new Object[]{
                    nd.getMaND(),
                    nd.getHo(),
                    nd.getTenDem(),
                    nd.getTen(),
                    nd.getGioiTinh(),
                    nd.getNgaySinh(),
                    nd.getDiaChi(),
                    nd.getSdt(),
                    nd.geteMail(),
                    nd.getChucVu()});
        }
        }
        if (index == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin người dùng");
        } else {
            JOptionPane.showMessageDialog(this, "Đã lọc thông tin theo yêu cầu");
        }
    }//GEN-LAST:event_cboChucVu_2ActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String ma = JOptionPane.showInputDialog(null, "vui lòng nhập mã nhân viên muốn tìm");
        List<NguoiDungViewModel> list = this.nguoiDungService.listND();
        int index = 0;
        if (ma.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm được người dùng nếu nó trống");
        } else {
            for (NguoiDungViewModel nd : list) {
                if (nd.getMaND().equals(ma)) {
                    index++;
                    this.txtMa.setText(nd.getMaND());
                    this.txtHo.setText(nd.getHo());
                    this.txtTenDem.setText(nd.getTenDem());
                    this.txtTen.setText(nd.getTen());
                    this.txtNgaySinh.setText(nd.getNgaySinh());
                    this.txtDiaChi.setText(nd.getDiaChi());
                    this.txtEmail.setText(nd.geteMail());
                    this.txtSDT.setText(nd.getSdt());
                    this.cboChucVu.setSelectedItem(nd.getChucVu());
                    if (nd.getGioiTinh().equals("Nam")) {
                        this.rdoNam.setSelected(true);
                    } else {
                        this.rdoNu.setSelected(true);
                    }
                    for (int i = 0; i < this.nguoiDungService.listND().size() - 1; i++) {
                        if (tblNguoiDung.getValueAt(i, 0).equals(ma)) {
                            this.tblNguoiDung.setRowSelectionInterval(i, i);
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Đã tìm được thông tin người dùng");
                }
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHTActionPerformed
        if (tblNguoiDung.getRowCount() < nguoiDungService.listND().size()) {
            loadTable(nguoiDungService.listND());
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiển thị danh sách nhân viên");
        }
    }//GEN-LAST:event_btnHTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnHT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboChucVu_2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JLabel txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
