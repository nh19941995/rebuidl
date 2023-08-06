package view.booking.miniView;

import dao.MenuDAO;
import dao.MenuNameDAO;
import model.Menu;
import model.MenuName;
import view.Tool.Grid;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewMenuListView extends JPanel {
    private static JTable table = new JTable();
    private DefaultTableModel tableModel;
    private static Object[][] data;
    private JButton buttonCreatNewMenu = new JButton("Creat new menu");
    private static JTextField inputNameNewMenu = new JTextField();
    private JLabel labelNameNewMenu = new JLabel("Menu name:");
    public static Object[][] getData() {
        return data;
    }
    public static void setData(Object[][] data) {
        NewMenuListView.data = data;
    }
    private static ArrayList<Menu> menus = new ArrayList<>();
    public NewMenuListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());
        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(nameBarBlock(),BorderLayout.NORTH);
        this.add(createNewMenuBlock(),BorderLayout.SOUTH);
        buttonCreatNewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tạo mới");
                loadData();
                if (inputNameNewMenu.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the menu name before creating a new one !", "Notice", JOptionPane.WARNING_MESSAGE);
                }else {
                    menus.forEach(s->s.setMenuName(MenuNameDAO.getByStringName(inputNameNewMenu.getText())));
                    menus.forEach(s-> MenuDAO.getInstance().insert(s));
                }
            }
        });
    }

    public static void addNewDish(Menu menu){
        MenuName menuName = MenuNameDAO.getByStringName(inputNameNewMenu.getText());
        menu.setMenuName(menuName);
        menus.add(menu);
        menus.stream().forEach(s->s.toString());
    }

    private JPanel nameBarBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(150, 75));

        jPanel.setLayout(new BorderLayout());
        Grid grid = new Grid();
        // đặt kích thước
        inputNameNewMenu.setPreferredSize(new Dimension(200, 20));
        grid.GridAdd(labelNameNewMenu,0,0,10,10,27);
        grid.GridAdd(inputNameNewMenu,1,0,10,10,10);
        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }
    private JPanel createNewMenuBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        Grid grid = new Grid();
        grid.GridAdd(buttonCreatNewMenu,0,0,20,20,20);

        buttonCreatNewMenu.setPreferredSize(new Dimension(150, 35));
        // Đặt màu cho nền của JButton
        buttonCreatNewMenu.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonCreatNewMenu.setForeground(Color.WHITE);

        jPanel.add(grid, BorderLayout.CENTER);
        // Đặt kích thước chiều ngang cho jPanel
        int width = 550; // Đặt kích thước mong muốn tại đây
        Dimension preferredSize = new Dimension(width, jPanel.getPreferredSize().height);
        jPanel.setPreferredSize(preferredSize);
        return jPanel;
    }

    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {
                },
                new String [] {"ID", "Dish name","Quantity","Price","Type"}
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
        final int count = 1;
        // Biến count là final, vì vậy nó sẽ không gây ra lỗi.
        Object[][] data = menus.stream().map(
                s -> {
                    Object[] row = new Object[5];
                    row[0] = 1;
                    row[1] = s.getDish().getDishName();
                    row[2] = s.getQuantity();
                    row[3] = s.getUnitPrice();
                    row[4] = s.getDish().getType().getType();
                    return row;
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
        // Biến count là final, vì vậy nó sẽ không gây ra lỗi.
        Object[][] data = menus.stream().map(
                s -> {
                    Object[] row = new Object[5];
                    row[0] = 1;
                    row[1] = s.getDish().getDishName();
                    row[2] = s.getQuantity();
                    row[3] = s.getUnitPrice();
                    row[4] = s.getDish().getType().getType();
                    return row;
                }
        ).toArray(Object[][]::new);
        setData(data);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }
}
