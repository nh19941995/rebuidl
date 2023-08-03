package view.booking.miniView;

import controller.BookingController;
import controller.InstantDateTimeInfo;
import controller.ObjectNullChecker;
import dao.BookingDAO;
import dao.TableListDAO;
import dao.TableTypeDAO;
import model.Booking;
import model.TableList;
import view.Tool.Grid;
import view.booking.BookingView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class TableListView extends JPanel {
    private Object lockObject = new Object();
    private static JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private JTextField filterBySeatingCapacity = new JTextField();
    private JTextField filterByDate = new JTextField();
    private JLabel labelSeatingCapacity = new JLabel("Filter by seating capacity");
    private JLabel labelDate = new JLabel("Filter by date");
    private JLabel labelType = new JLabel("Select type");
    private JComboBox<String> SelecType;
    private static JButton buttonSelectTable = new JButton("Select table");
    private JButton buttonSearch = new JButton("Search");
    private static int tableIdSelect;

    public static int getTableIdSelect() {
        return tableIdSelect;
    }

    // tạo set chứa id bàn
    private static Set<Integer> tableId = new HashSet<>();

    public static JButton getButtonSelectTable() {
        return buttonSelectTable;
    }
    public static void setButtonSelectTable(JButton buttonSelectTable) {
        TableListView.buttonSelectTable = buttonSelectTable;
    }
    public static Set<Integer> getTableId() {
        return tableId;
    }
    public static void setTableId(Set<Integer> tableId) {
        TableListView.tableId = tableId;
    }
    public static JTable getTable() {
        return table;
    }
    public static void setTable(JTable table) {
        TableListView.table = table;
    }
    // còn phải sửa chỉ hiển thị các booking tính từ ngày hiện tại trở đi.

    public TableListView() {
//        set layout chính
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
//        Phần bảng ----------------------------------------------------------------------------------------------------
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {"ID", "Table type", "Seating Capacity","Rental start time","Rental end time","Date time","Status"}
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        // Khởi tạo mô hình dữ liệu cho bảng
        tableModel = (DefaultTableModel) table.getModel();
        // Xóa dữ liệu hiện có trong bảng
        tableModel.setRowCount(0);
        //        sắp xếp theo id trước khi thêm vào bảng
        addDataToTable();
        // Thêm từng hàng dữ liệu vào bảng
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
        // Cập nhật bảng để hiển thị dữ liệu mới
        tableModel.fireTableDataChanged();
        //        căn giữa chữ trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Thiết lập chiều rộng cho các cột
        table.getColumnModel().getColumn(0).setMinWidth(30); // Cột ID
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột ID
        //        Tạo một JScrollPane để chứa bảng (thanh cuộn trang)
        JScrollPane scrollPane = new JScrollPane(table);
        //        Đặt layout cho table_Panel là BorderLayout
        add(scrollPane,BorderLayout.CENTER);
        //        thêm dữ liệu vào select list
        String[] selectList = TableTypeDAO.getInstance().getAll().stream()
                .map(s -> s.getName())
                .toArray(String[]::new);
        SelecType = new JComboBox<String>();
        //        đăt kích thươc cho các thành phần
        SelecType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));
        filterBySeatingCapacity.setPreferredSize(new Dimension(150, 30));
        filterByDate.setPreferredSize(new Dimension(150, 30));
        SelecType.setPreferredSize(new Dimension(150, 30));
        buttonSelectTable.setPreferredSize(new Dimension(150, 30));
        // Đặt màu cho nền của JButton
        buttonSelectTable.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonSelectTable.setForeground(Color.WHITE);
        this.add(scrollPane,BorderLayout.CENTER);
        Grid gridAllbutton = new Grid();
        gridAllbutton.GridAdd(labelDate,0,0,10,10,10);
        gridAllbutton.GridAdd(filterByDate,1,0,10,10,10);
        gridAllbutton.GridAdd(labelSeatingCapacity,2,0,10,10,10);
        gridAllbutton.GridAdd(filterBySeatingCapacity,3,0,10,10,10);
        gridAllbutton.GridAdd(labelType,4,0,10,10,10);
        gridAllbutton.GridAdd(SelecType,5,0,10,10,10);
        gridAllbutton.GridAdd(buttonSearch,6 ,0,10,10,10);
        gridAllbutton.GridAdd(buttonSelectTable,7 ,0,10,10,10);
        this.add(gridAllbutton,BorderLayout.SOUTH);
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                searchTableList();
            }
        });
        getTableFromList();
    }

    public static void getTableFromList(){
        // sự kiện click vào bảng table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                    String idTable = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                    System.out.println("Select:" + idTable);
                    tableIdSelect = Integer.parseInt(idTable);
                    System.out.println("Table: "+ idTable);
                }
            }
            }

        });
    }

    // hàm nối mảng
    public static Object[][] concatenateArrays(Object[][] arr1, Object[][] arr2) {
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;
        Object[][] result = new Object[arr1Length + arr2Length][];
        System.arraycopy(arr1, 0, result, 0, arr1Length);
        System.arraycopy(arr2, 0, result, arr1Length, arr2Length);
        return result;
    }

    //    lấy dữ liệu từ sever và đẩy vào this.data
    private void addDataToTable(){
        List<model.Booking> bookingList = BookingDAO.getInstance().getAll();
        // Sử dụng HashSet để lưu trữ các phần tử không trùng lặp
        Set<Integer> uniqueElements = new HashSet<>();
        Object[][] dataOnBooking = bookingList.stream().map(
                s -> new Object[]{
                        s.getTable().getId(),  // id bàn
                        s.getTable().getType().getName(),  // loại bàn
                        s.getTable().getSeatingCapacity(),   // số ghế
                        InstantDateTimeInfo.getTimeStringToInstance(s.getInfo().getStart(),1),  // giờ bắt đầu
                        InstantDateTimeInfo.getTimeStringToInstance(s.getInfo().getEnd(),1),    // giờ kết thúc
                        InstantDateTimeInfo.getTimeStringToInstance(s.getInfo().getStart(),2),  // ngày
                        s.getTable().getFlag(),  // trạng thái của bàn
                        uniqueElements.add(s.getTable().getId())
                }
        ).toArray(Object[][]::new);
        List<model.TableList> tableLists = TableListDAO.getInstance().getAll();
        Object[][] dataNoneBooking = tableLists.stream()
                .map(s -> {
                    int tableId = s.getId().intValue();
                    if (!uniqueElements.contains(tableId)) { // Đảo ngược điều kiện từ contains thành không contains
                        return new Object[]{
                                s.getId(),    // id bàn
                                s.getType().getName(),  // loại bàn
                                s.getSeatingCapacity(),  // số ghế
                                "",
                                "",
                                "",
                                s.getFlag()
                        };
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(n->(int)n[6]>0)
                .toArray(Object[][]::new);
        Object[][] allBooking = concatenateArrays(dataNoneBooking,dataOnBooking);
        //        sắp xếp theo id
        Arrays.sort(allBooking, Comparator.comparingInt(arr -> (int) arr[0]));
        this.data = allBooking;
        Arrays.stream(data).map(s->new Object[]{
        }).toArray(Object[][]::new);
    }

    public synchronized  void searchTableList() {
        String dateInput = filterByDate.getText();
        String CapacityInput = filterBySeatingCapacity.getText();
        String filterTypeInput = (String) SelecType.getSelectedItem();
        System.out.println(dateInput);
        System.out.println(CapacityInput);
        System.out.println(filterTypeInput);
        Object[][] arr = data;
        synchronized (lockObject) {
            // Tạo luồng dữ liệu từ mảng
            Stream<Object[]> dataStream1 = Arrays.stream(data);
            if (!filterTypeInput.equals("")) {
                arr = dataStream1.filter(row -> row[1].equals(filterTypeInput)).toArray(Object[][]::new);
            }
            Stream<Object[]> dataStream2 = Arrays.stream(arr);
            // In ra giá trị của dataStream2
            if (!CapacityInput.equals("")) {
                // Ép về kiểu string trước khi so sánh
                arr = dataStream2.filter(row -> (row[2].toString()).equals(CapacityInput)).toArray(Object[][]::new);
            }
            Stream<Object[]> dataStream3 = Arrays.stream(arr);
            if (!dateInput.equals("")) {
                arr = dataStream3.filter(row -> row[5].toString().contains(dateInput)).toArray(Object[][]::new);
            }
        }
        // Xóa dữ liệu hiện có trong bảng
        tableModel.setRowCount(0);

        // Thêm từng hàng dữ liệu vào bảng
        for (Object[] row : arr) {
            tableModel.addRow(row);
        }
        // Cập nhật bảng để hiển thị dữ liệu mới
        tableModel.fireTableDataChanged();
    }

}
