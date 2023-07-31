package view.booking.miniView;

import controller.InstantDateTimeInfo;
import dao.MenuDAO;
import dao.MenuNameDAO;
import dao.PersonDAO;
import model.Menu;
import model.MenuName;
import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MenuListView  extends JPanel {
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private JButton selectTableBTN = new JButton("Choose a Menu for Booking ");

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public MenuListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        loadData();
        this.add(scrollPane, BorderLayout.CENTER);

    }

    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {

                },
                new String [] {"ID", "Menu name","Date Creat","Date update", "Price","Status"}
        ){
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
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
        // lấy dữ liệu từ sever
        List<MenuName> menuList = MenuNameDAO.getInstance().getAll();
        Object[][] data = menuList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateUpdate(), 2),
                        MenuDAO.getInstance().getTotalPriceByMenuNameID(s.getId()),
                        s.getFlag(),
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
        List<MenuName> menuList = MenuNameDAO.getInstance().getAll();
        Object[][] data = menuList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateUpdate(), 2),
                        MenuDAO.getInstance().getTotalPriceByMenuNameID(s.getId()),
                        s.getFlag(),
                }
        ).toArray(Object[][]::new);

        Object[][] filteredData = Arrays.stream(data)
                .filter(row -> {
                    // Lấy giá trị từ cột thứ 4 (đếm từ 0)
                    Object value = row[4];
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
