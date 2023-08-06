package view.booking.miniView;

import controller.BookingController;
import controller.InstantDateTimeInfo;
import dao.OderDAO;
import dao.PersonDAO;
import model.Person;
import view.Tool.Boder;
import view.Tool.Grid;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BillView extends JPanel {
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private static JTable table = new JTable();

    private static JButton buttonCreatOrder = new JButton("Creat order");
    private static JButton buttonDeleteRow = new JButton("Delete a row");

    private static JLabel labelTotal = new JLabel("Total bill: ");
    private static JLabel labelTotalOutput = new JLabel();

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
        add(scrollPane,BorderLayout.CENTER);
        add(blockBot(),BorderLayout.SOUTH);
        add(blockTop(),BorderLayout.NORTH);
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
        
        Object[][] data = OderDAO.getInstance().getAll().stream().map(
                s -> new Object[]{
                        "",
                        s.getDish() != null ? s.getDish().getDishName() : "", // Thay thế bằng giá trị mặc định nếu s.getDish() là null
                        s.getQuantity() != null ? s.getQuantity() : "", // Thay thế bằng giá trị mặc định nếu s.getQuantity() là null
                        s.getPrice() != null ? s.getPrice() : "", // Thay thế bằng giá trị mặc định nếu s.getPrice() là null
                        s.getQuantity() != null ? s.getQuantity() * (s.getPrice() != null ? s.getPrice() : 0) : "", // Tính toán tổng tiền
                }
        ).toArray(Object[][]::new);
        int count = 1;
        for (Object[] s : data) {
            s[0] = count++;
        }
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

    private void loadData(){
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa hết dữ liệu hiện có trong bảng
        model.setRowCount(0);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }

    private JPanel blockBot(){
        Boder boder = new Boder();
        // đặt kích thước
        boder.setPreferredSize(new Dimension(150, 75));
        Grid grid = new Grid();
        grid.GridAddCustom(buttonCreatOrder,0,0,20,20,20,20,1);
        buttonCreatOrder.setPreferredSize(new Dimension(150, 35));
        Grid grid1 = new Grid();
        grid1.GridAddCustom(buttonDeleteRow,0,0,20,20,20,20,1);
        buttonDeleteRow.setPreferredSize(new Dimension(150, 35));
        boder.add(grid,BorderLayout.EAST);
        boder.add(grid1,BorderLayout.CENTER);
        return boder;
    }

    private JPanel blockTop(){
        Boder boder = new Boder();
        // đặt kích thước
        boder.setPreferredSize(new Dimension(150, 75));
        Grid grid = new Grid();
        grid.GridAddCustom(labelTotal,0,0,20,20,20,20,1);
        grid.GridAddCustom(labelTotalOutput,1,0,20,20,20,20,1);
        boder.add(grid,BorderLayout.CENTER);
        return boder;
    }
}
