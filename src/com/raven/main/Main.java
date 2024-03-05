package com.raven.main;

import ServiceImpl.NguoiDungServiceImpl;
import Services.NguoiDungService;
import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.form.FrmBanHang;
import com.raven.form.KhachHangFrm;
import com.raven.form.LoaiSPFrm;
import com.raven.form.MainForm;
import com.raven.form.NhaPPFrm;
import com.raven.form.NhanVienFrm;
import com.raven.form.SanPhamFrm;
import com.raven.form.SanPhamLoiFrm;
import com.raven.form.TraHangFrm;
import com.raven.form.doanhThuFrm;
import com.raven.form.doiHangFrm;
import com.raven.form.doiMatKhau;
import com.raven.form.taiKhoanFrm;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    //private Header header;
    private MainForm main;
    private Animator animator;
    private NguoiDungService nguoiDungService = new NguoiDungServiceImpl();
    private DomainModels.dangNhap n;

    public Main() {
        initComponents();
        init();

    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        //header = new Header();
        main = new MainForm();
        //header.ten();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {
                    main.showForm(new FrmBanHang());
                } else if (menuIndex == 1) {
                    main.showForm(new doiHangFrm());
                } else if (menuIndex == 2) {
                    main.showForm(new TraHangFrm());
                } else if (menuIndex == 3) {
                    if (subMenuIndex == 0) {
                        main.showForm(new LoaiSPFrm());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new SanPhamFrm());
                    }
                } else if (menuIndex == 4) {
                    main.showForm(new SanPhamLoiFrm());
                } else if (menuIndex == 5) {
                    if (nguoiDungService.findND(n.getCurrentLoginUsername()).getChucVu().equals("Quản lý")) {
                        main.showForm(new NhanVienFrm());
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn không được phép truy cập");
                    }
                } else if (menuIndex == 6) {
                    main.showForm(new KhachHangFrm());
                } else if (menuIndex == 7) {
                    main.showForm(new NhaPPFrm());
                } else if (menuIndex == 8) {
                    if (nguoiDungService.findND(n.getCurrentLoginUsername()).getChucVu().equals("Quản lý")) {
                        main.showForm(new doanhThuFrm());
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn không được phép truy cập");
                    }
                } else if (menuIndex == 9) {
                    main.showForm(new taiKhoanFrm());
                } else if (menuIndex == 10) {
                    main.showForm(new doiMatKhau());
                } else if (menuIndex == 11) {
                    int xn = JOptionPane.showConfirmDialog(null, "Bạn muốn đăng xuất chứ");
                    if (xn == JOptionPane.YES_OPTION) {
                        dispose();
                        new dangNhap().setVisible(true);
                    }

                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        //bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
//        header.addMenuEvent(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                if (!animator.isRunning()) {
//                    animator.start();
//                }
//                menu.setEnableMenu(false);
//                if (menu.isShowMenu()) {
//                    menu.hideallMenu();
//                }
//            }
//        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new FrmBanHang());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);
        bg.setPreferredSize(new java.awt.Dimension(1000, 700));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
