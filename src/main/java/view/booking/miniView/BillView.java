package view.booking.miniView;

import controller.BookingController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BillView extends JPanel {
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private static JTable table = new JTable();

    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        BillView.data = data;
    }

    public BillView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
    }
    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {
                },
                new String [] {"ID","Dish name", "Quantity","Price","Total"}
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

        Object[][] data = BookingController.getBookings().stream().map(
                s -> new Object[]{
                        null,
                        s.getTable() != null ? s.getTable().getId() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getTable() != null ? s.getTable().getType().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getMenuName() != null ? s.getMenuName().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getMenuName() là null
                        s.getTable() != null ? s.getTable().getFlag() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
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
        table.getColumnModel().getColumn(0).setMaxWidth(35); // Cột ID
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(60);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        JScrollPane scrollPane = new JScrollPane(table);
        // Đặt layout cho table_Panel là BorderLayout
        return scrollPane;
    }
}
