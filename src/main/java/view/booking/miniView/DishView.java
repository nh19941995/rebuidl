package view.booking.miniView;

import controller.DishController;
import dao.DishDAO;
import dao.DishTypeDAO;
import model.Dish;
import view.Tool.Grid;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DishView extends JPanel {
    // data
    private Object lockObject = new Object();
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;

    public Object[][] getData() {
        return data;
    }

    private int idSelectDish;

    public int getIdSelectDish() {
        return idSelectDish;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setIdSelectDish(int idSelectDish) {
        this.idSelectDish = idSelectDish;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    // label
    private JLabel labelEnterPrice = new JLabel("Enter price: ");
    private JLabel labelFilerByType = new JLabel("Select type: ");
    private JLabel labelFilerByPrice = new JLabel("Enter price: ");
    private JLabel labelEnterNumber = new JLabel("Enter number: ");
    private JLabel labelEnterComment = new JLabel("Enter Comment: ");
    private JLabel labelNewDishName = new JLabel("Enter the new dish name: ");
    private JLabel labelReferencePrice = new JLabel("Enter reference price: ");
    private JLabel LabelNewDishType = new JLabel("Select new dish type: ");
    // input
    private JTextField inputEnterNumber = new JTextField();
    private JTextField inputEnterPrice = new JTextField();
    private JTextField inputFilerByPrice = new JTextField();
    private JTextField inputNewDishName = new JTextField();
    private JTextField inputReferencePrice = new JTextField();
    private JTextField inputComment = new JTextField();

    public JTextField getInputEnterNumber() {
        return inputEnterNumber;
    }
    public JTextField getInputEnterPrice() {
        return inputEnterPrice;
    }
    public JTextField getInputFilerByPrice() {
        return inputFilerByPrice;
    }
    public JTextField getInputNewDishName() {
        return inputNewDishName;
    }
    public JTextField getInputReferencePrice() {
        return inputReferencePrice;
    }

    public JTextField getInputComment() {
        return inputComment;
    }

    // combo box
    private JComboBox<String> SelecType = new JComboBox<>();
    private JComboBox<String> SelecTypeForNewDish = new JComboBox<>();
    public JComboBox<String> getSelecType() {
        return SelecType;
    }
    public JComboBox<String> getSelecTypeForNewDish() {
        return SelecTypeForNewDish;
    }
    // button
    private JButton buttonAddToNewMenu = new JButton("Add to new menu ");
    private JButton buttonSeach = new JButton("Seach");
    private JButton buttonCreatNewDish = new JButton("Creat new dish");
    public JButton getButtonAddToNewMenu() {
        return buttonAddToNewMenu;
    }
    public JButton getButtonSeach() {
        return buttonSeach;
    }
    public JButton getButtonCreatNewDish() {
        return buttonCreatNewDish;
    }
    public DishView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());
        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = blockTable();
        // thêm data cho boder box
        String[] selectList = DishTypeDAO.getInstance().getAll().stream()
                .map(s -> s.getType())
                .toArray(String[]::new);
        SelecType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));
        SelecTypeForNewDish.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(blockSearchBar(),BorderLayout.NORTH);
        JPanel bot = blockAddNewDish();
        this.add(bot,BorderLayout.SOUTH);
//        DishController.selectEven(this);
        DishController.creatNewDish(this);


        buttonSeach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tìm kiếm");
                search();
            }
        });

    }









    private JPanel blockSearchBar(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        // đăt chiều cao cho jpanel
        jPanel.setPreferredSize(new Dimension(jPanel.getPreferredSize().width, 75));
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

    private JPanel blockBotForTabDish(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        // đăt chiều cao cho jpanel
        jPanel.setPreferredSize(new Dimension(jPanel.getPreferredSize().width, 75));
        // đặt kích thước
        inputEnterPrice.setPreferredSize(new Dimension(150, 20));
        inputEnterNumber.setPreferredSize(new Dimension(150, 20));
        buttonAddToNewMenu.setPreferredSize(new Dimension(150, 35));
        // Đặt màu cho nền của JButton
        buttonAddToNewMenu.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonAddToNewMenu.setForeground(Color.WHITE);

        Grid right = new Grid();
        Grid center = new Grid();

        right.GridAddCustom(buttonAddToNewMenu,0,0,20,20,10,10,1);
        center.GridAddCustom(labelEnterPrice,0,0,20,20,5,5,1);
        center.GridAddCustom(inputEnterPrice,0,1,20,20,5,15,1);
        center.GridAddCustom(labelEnterNumber,1,0,20,20,5,5,1);
        center.GridAddCustom(inputEnterNumber,1,1,20,20,5,15,1);

        jPanel.add(right,BorderLayout.EAST);
        jPanel.add(blockAddNewDish(),BorderLayout.WEST);
        jPanel.add(center,BorderLayout.CENTER);
        return  jPanel;
    }


    private JPanel blockAddNewDish (){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setPreferredSize(new Dimension(150, 75));

        Grid grid = new Grid();
        grid.GridAddCustom(labelNewDishName,0,0,20,20,5,5,1);
        grid.GridAddCustom(inputNewDishName,0,1,20,20,5,5,1);
        inputNewDishName.setPreferredSize(new Dimension(150, 20));
        grid.GridAddCustom(labelReferencePrice,1,0,20,20,5,5,1);
        grid.GridAddCustom(inputReferencePrice,1,1,20,20,5,5,1);
        inputReferencePrice.setPreferredSize(new Dimension(150, 20));
        grid.GridAddCustom(labelEnterComment,2,0,20,20,5,5,1);
        grid.GridAddCustom(inputComment,2,1,20,20,5,5,1);
        inputComment.setPreferredSize(new Dimension(150, 20));
        grid.GridAddCustom(SelecTypeForNewDish,3,1,20,20,5,5,1);
        grid.GridAddCustom(LabelNewDishType,3,0,20,20,5,5,1);
        SelecTypeForNewDish.setPreferredSize(new Dimension(150, 20));



        Grid grid1 = new Grid();
        grid1.GridAddCustom2(buttonCreatNewDish,0,0,20,20,5,5,1,1);
        buttonCreatNewDish.setPreferredSize(new Dimension(150, 35));
        // Đặt màu cho nền của JButton
        buttonCreatNewDish.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonCreatNewDish.setForeground(Color.WHITE);
        jPanel.add(grid,BorderLayout.CENTER);
        jPanel.add(grid1,BorderLayout.EAST);
        return  jPanel;
    }


    private JScrollPane blockTable() {
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
    public void reloadData() {
        // lấy dữ liệu từ server
        List<Dish> dishList = DishDAO.getInstance().getAll();
        Object[][] newData = dishList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getDishName(),
                        s.getPrice(),
                        s.getType().getType(),
                        s.getComment(),
                }
        ).toArray(Object[][]::new);

        // Cập nhật dữ liệu trong table model
        tableModel.setDataVector(newData, new String[]{"ID", "Dish name", "reference price", "Dish type", "Note"});
        // Căn giữa chữ trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Thiết lập chiều rộng cho các cột
        table.getColumnModel().getColumn(0).setMinWidth(30); // Cột ID
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột ID

        // Cập nhật lại giao diện của bảng
        table.revalidate();
        table.repaint();
    }

    public void search() {
        String price = inputFilerByPrice.getText();
        String filterTypeInput = (String) getSelecType().getSelectedItem();

        Object[][] arr = data;

        synchronized (lockObject) {
            Stream<Object[]> dataStream = Arrays.stream(data);

            // Áp dụng tất cả các điều kiện lọc trong một Stream duy nhất
            if (!filterTypeInput.equals("")) {
                dataStream = dataStream.filter(row -> row[3].equals(filterTypeInput));
            }
            if (!price.equals("")) {
                Double targetPrice = Double.parseDouble(price); // Chuyển đổi price sang kiểu Double
                dataStream = dataStream.filter(row -> {
                    Double currentPrice = Double.parseDouble(row[2].toString()); // Chuyển đổi dữ liệu của hàng hiện tại sang kiểu Double
                    return currentPrice <= targetPrice; // So sánh giá trị hiện tại với giá trị target
                });
            }
            // Gán kết quả vào mảng arr
            arr = dataStream.toArray(Object[][]::new);
        }
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa dữ liệu hiện có trong bảng
        model.setRowCount(0);

        // Thêm từng hàng dữ liệu vào bảng
        for (Object[] row : arr) {
            model.addRow(row);
        }
        // Cập nhật bảng để hiển thị dữ liệu mới
        model.fireTableDataChanged();
    }
}
