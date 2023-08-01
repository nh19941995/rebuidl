package view.booking.miniView;

import controller.InstantDateTimeInfo;
import dao.DishDAO;
import dao.MenuNameDAO;
import dao.PersonDAO;
import model.*;
import model.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookingListView extends JPanel {
    private Object lockObject = new Object();
    private static JTable table = new JTable();
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private JButton buttonAddToNewBooking  = new JButton("Add to list ");
    private JButton buttonremoveFromNewBooking = new JButton("Remove from list");
    private JLabel BookingList = new JLabel("New booking list");

    private static ArrayList<Booking> bookings = new ArrayList<>();

    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        BookingListView.data = data;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public static void setBookings(ArrayList<Booking> bookings) {
        BookingListView.bookings = bookings;
    }

    public BookingListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        //        loadData();
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {

                },
                new String [] {"ID", "Table type","Menu name","Status"}
        ){
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
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

        Object[][] data = bookings.stream().map(
                s -> new Object[]{
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
        table.getColumnModel().getColumn(0).setMinWidth(30); // Cột ID
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột ID

        JScrollPane scrollPane = new JScrollPane(table);
        // Đặt layout cho table_Panel là BorderLayout
        return scrollPane;
    }

    public static void loadData(){
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa hết dữ liệu hiện có trong bảng
        model.setRowCount(0);
        Object[][] data = bookings.stream().map(
                s -> new Object[]{
                        s.getTable() != null ? s.getTable().getId() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getTable() != null ? s.getTable().getType().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getMenuName() != null ? s.getMenuName().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getMenuName() là null
                        s.getTable() != null ? s.getTable().getFlag() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                }
        ).toArray(Object[][]::new);
        setData(data);
        ClientListView.setData(data);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }



}
