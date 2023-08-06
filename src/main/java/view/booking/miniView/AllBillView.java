package view.booking.miniView;

import controller.BookingController;
import dao.OderDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AllBillView extends JPanel {
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private static JTable table = new JTable();

    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        AllBillView.data = data;
    }

    public AllBillView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        add(scrollPane,BorderLayout.CENTER);
    }
    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {
                },
                new String [] {"ID","ID bill","Dish name", "Quantity","Price","Total"}
        ){
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        // Khởi tạo mô hình dữ liệu cho bảng
        table.setModel(model);
        this.tableModel = model;
        // lấy dữ liệu từ sever

        Object[][] data = OderDAO.getInstance().getAll().stream().map(
                s -> new Object[]{
                        s.getId() != null ? s.getId() : "", // Thay thế bằng giá trị mặc định nếu s.getId() là null
                        s.getBill()!= null ? s.getBill().getId() : "", // Thay thế bằng giá trị mặc định nếu s.getBill() là null
                        s.getDish() != null ? s.getDish().getDishName() : "", // Thay thế bằng giá trị mặc định nếu s.getDish() là null
                        s.getQuantity() != null ? s.getQuantity() : "", // Thay thế bằng giá trị mặc định nếu s.getQuantity() là null
                        s.getPrice() != null ? s.getPrice() : "", // Thay thế bằng giá trị mặc định nếu s.getPrice() là null
                        s.getQuantity() != null ? s.getQuantity() * (s.getPrice() != null ? s.getPrice() : 0) : "", // Tính toán tổng tiền
                }
        ).toArray(Object[][]::new);
        setData(data);
        // thêm dữ liệu vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        // Căn giữa chữ trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Thiết lập chiều rộng cho các cột
        table.getColumnModel().getColumn(0).setMinWidth(25); // Cột ID
//        table.getColumnModel().getColumn(0).setMaxWidth(35); // Cột ID
//        table.getColumnModel().getColumn(1).setMaxWidth(50);
//        table.getColumnModel().getColumn(2).setMaxWidth(60);
//        table.getColumnModel().getColumn(4).setMaxWidth(50);
//        table.getColumnModel().getColumn(5).setMaxWidth(50);
        JScrollPane scrollPane = new JScrollPane(table);
        // Đặt layout cho table_Panel là BorderLayout
        return scrollPane;
    }
}
