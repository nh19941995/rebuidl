package view.booking.miniView;

import dao.*;
import model.Dish;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DishListView  extends JPanel {
    private Object lockObject = new Object();
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private JButton buttonAddToNewMenu = new JButton("Add to new menu ");

    private JLabel labelEnterNumber = new JLabel("Enter number: ");
    private JTextField inputEnterNumber = new JTextField();

    private JLabel labelEnterPrice = new JLabel("Enter price: ");
    private JTextField inputEnterPrice = new JTextField();

    private JLabel labelFilerByType = new JLabel("Select type: ");
    private JComboBox<String> SelecType = new JComboBox<>();
    private JButton buttonSeach = new JButton("Seach");

    private JLabel labelFilerByPrice = new JLabel("Enter price: ");
    private JTextField inputFilerByPrice = new JTextField();


    public Object getLockObject() {
        return lockObject;
    }

    public void setLockObject(Object lockObject) {
        this.lockObject = lockObject;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public DishListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();

        // Khởi tạo JComboBox SelecType
        SelecType = new JComboBox<>();
        // thêm data cho boder box
        String[] selectList = DishTypeDAO.getInstance().getAll().stream()
                .map(s -> s.getType())
                .toArray(String[]::new);
        SelecType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));




//        loadData();
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(searchBarBlock(),BorderLayout.NORTH);


        // sự kiện click vào bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        BookingView.setIdClientList(id);
                        System.out.println("Bảng Dishlist đang chọn hàng có id là: "+ id);
                    }
                }
            }
        });

        buttonSeach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tìm kiếm");
                searchDish();
//                loadData();
            }
        });

    }


    private JPanel searchBarBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        // đặt kích thước
        SelecType.setPreferredSize(new Dimension(150, 20));
        inputFilerByPrice.setPreferredSize(new Dimension(200, 20));
        buttonSeach.setPreferredSize(new Dimension(150, 30));
        Grid grid = new Grid();
        grid.GridAddCustom(labelFilerByType, 0,0,10,10,10,0,1);
        grid.GridAdd(SelecType, 0,1,10,10,10);
        grid.GridAddCustom(labelFilerByPrice, 1,0,10,10,10,0,1);
        grid.GridAdd(inputFilerByPrice, 1,1,10,10,10);
        Grid grid2 = new Grid();
        grid2.GridAddCustom(buttonSeach,0,0,10,10,10,10,1);
        jPanel.add(grid,BorderLayout.CENTER);
        jPanel.add(grid2,BorderLayout.EAST);
        return jPanel;
    }

    public synchronized  void searchDish() {
//        table.setModel(tableModel);
        String dishPrice = inputFilerByPrice.getText();
        String dishType = (String) SelecType.getSelectedItem();
        System.out.println(dishPrice);
        System.out.println(dishType);
        Object[][] arr = data;
        synchronized (getLockObject()) {
            // Tạo luồng dữ liệu từ mảng
            Stream<Object[]> dataStream1 = Arrays.stream(data);
            if (!dishType.equals("")) {
                arr = dataStream1.filter(row -> row[3].equals(dishType)).toArray(Object[][]::new);
            }

            // In ra giá trị của dataStream2
            if (!dishPrice.equals("")) {
                Stream<Object[]> dataStream2 = Arrays.stream(arr);
                arr = dataStream2.filter(row -> {
                    String priceString = row[2].toString(); // Chuyển đổi từ kiểu dữ liệu thành String
                    return priceString.equals(dishPrice);
                }).toArray(Object[][]::new);
            }
//            Stream<Object[]> dataStream3 = Arrays.stream(arr);
//            if (!dateInput.equals("")) {
//                arr = dataStream3.filter(row -> row[5].toString().contains(dateInput)).toArray(Object[][]::new);
//            }
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





    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {

                },
                new String [] {"ID", "Dish name","reference price","Dish type", "Note"}
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
        List<Dish> dishList = DishDAO.getInstance().getAll();
        Object[][] data = dishList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getDishName(),
                        s.getPrice(),
                        s.getType().getType(),
                        s.getComment(),
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




    private void loadData(){
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa hết dữ liệu hiện có trong bảng
        model.setRowCount(0);

        // Tải lại dữ liệu mới từ cơ sở dữ liệu hoặc từ nguồn dữ liệu mới
        List<Dish> dishList = DishDAO.getInstance().getAll();
        Object[][] data = dishList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getDishName(),
                        s.getPrice(),
                        s.getType().getType(),
                        s.getComment(),
                }
        ).toArray(Object[][]::new);
        setData(data);

        Object[][] filteredData = Arrays.stream(data)
                .filter(row -> {
                    // Lấy giá trị từ cột thứ 4 (đếm từ 0)
                    Object value = row[2];
                    if (value instanceof Double) {
                        return (Double) value > 0.0;
                    } else if (value instanceof Float) {
                        return (Float) value > 0.0;
                    } else if (value instanceof Integer) {
                        return (Integer) value > 0;
                    } else {
                        // Xử lý các kiểu dữ liệu khác nếu cần
                        return false;
                    }
                })
                .toArray(Object[][]::new);


        setData(filteredData);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : filteredData) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }
}
