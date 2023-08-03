package view.booking.miniView;
import controller.BookingController;
import view.ClientListView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BookingListView extends JPanel {
    private Object lockObject = new Object();
    private static JTable table = new JTable();
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private JButton buttonAddToNewBooking  = new JButton("Add to list ");
    private JButton buttonremoveFromNewBooking = new JButton("Remove from list");
    private JLabel BookingList = new JLabel("New booking list");
    private static int idSelect;
    public static int getIdSelect() {
        return idSelect;
    }
    public static void setIdSelect(int idSelect) {
        BookingListView.idSelect = idSelect;
    }
    public static JTable getTable() {
        return table;
    }
    public static void setTable(JTable table) {
        BookingListView.table = table;
    }
    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        BookingListView.data = data;
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
        // sự kiện click vào bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        setIdSelect(Integer.parseInt(id));
                        System.out.println("Bảng BookingListView đang chọn hàng có id là: "+ id);
                    }
                }
            }
        });
    }

    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {
                },
                new String [] {"ID","Table ID", "Table type","Menu name","Status"}
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

    public static void loadData(){
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa hết dữ liệu hiện có trong bảng
        model.setRowCount(0);
        Object[][] data = BookingController.getBookings().stream().map(
                s -> new Object[]{
                        null,
                        s.getTable() != null ? s.getTable().getId() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getTable() != null ? s.getTable().getType().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                        s.getMenuName() != null ? s.getMenuName().getName() : "", // Thay thế bằng giá trị mặc định nếu s.getMenuName() là null
                        s.getTable() != null ? s.getTable().getFlag() : "", // Thay thế bằng giá trị mặc định nếu s.getTable() là null
                }
        ).toArray(Object[][]::new);
        int n = 1;
        for (Object[] a : data) {
            a[0] = n++;
        }
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
