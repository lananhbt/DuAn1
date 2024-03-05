package Views;

import ServiceImpl.ManageHoaDonService;
import Services.IManageHoaDon;
import ViewModels.ManageHoaDon;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xuatExcel {

    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Hóa Đơn");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH HÓA ĐƠN");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã HD");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã KH");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày tạo");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Trạng thái");

            IManageHoaDon hocVienService = new ManageHoaDonService();

            List<ManageHoaDon> listItem = hocVienService.All();

            for (int i = 0; i < listItem.size(); i++) {
                ManageHoaDon hd = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(hd.getMaHD());
                row.createCell(2).setCellValue(hd.getMaND());
                row.createCell(3).setCellValue(hd.getMaKH());
                row.createCell(4).setCellValue(hd.getNgayTao().toString());
                row.createCell(5).setCellValue(hd.getTongTien());
                row.createCell(6).setCellValue(hd.getTrangThai());
            }

            FileOutputStream out = new FileOutputStream(new File("D:\\hoaDon5.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

