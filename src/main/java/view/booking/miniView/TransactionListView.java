package view.booking.miniView;

import controller.BookingController;
import controller.ExcelExporter;
import controller.InstantDateTimeInfo;
import dao.PersonDAO;
import dao.TransactionDAO;
import dao.TransactionsTypeDAO;
import model.Person;
import view.Tool.Boder;
import view.Tool.Grid;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionListView extends JPanel {

    private static Object[][] data;
    private static String[] columnName =  new String [] {"ID", "Content","Type","Value", "Date","Time","Person Name", "Phone number", "Status"};

    private static JTable table = new JTable();
    private static Person person = new Person();
    private JLabel labelType = new JLabel("Transaction type");
    private JLabel labelVale = new JLabel("Value:");
    private JLabel labelDate = new JLabel("Date:");
    private JLabel labelTime = new JLabel("Time:");
    private JLabel labelFilerByType = new JLabel("Filer by type:");
    private JLabel labelFilerByPhone = new JLabel("Filer by phone number:");
    private JLabel labelComment = new JLabel("Transaction content:");
    private JButton buttonFilter = new JButton("Filter");

    // input
    private static JTextField inputValue = new JTextField();
    private static JTextField inputTime = new JTextField();
    private static JTextField inputDate = new JTextField();
    private static JTextField inputFilterPhone = new JTextField();
    private static JTextField inputComment = new JTextField();
    private static JComboBox<String> SelecFilterType = new JComboBox<String>();
    private static JComboBox<String> SelecType = new JComboBox<String>();

    // info block
    private JLabel labelFirstName = new JLabel("First name: ");
    private static JLabel FirstName = new JLabel();

    private JLabel labelLastName = new JLabel("Last name: ");
    private static JLabel LastName = new JLabel();

    private JLabel labelPhone = new JLabel("Phone: ");
    private static JLabel Phone = new JLabel();

    private static JButton buttonCreatTransaction = new JButton("Creat transaction");
    private JButton buttonAllTransaction = new JButton("All transaction");
    private JButton buttonSelect = new JButton("Select person by list");
    private JButton buttonDelete = new JButton("Delete");
    private JButton buttonExportToExcel = new JButton("Export to Excel");
    public static String[] getColumnName() {
        return columnName;
    }
    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        TransactionListView.data = data;
    }

    public static JTextField getInputComment() {
        return inputComment;
    }
    public static JButton getButtonCreatTransaction() {
        return buttonCreatTransaction;
    }

    public static JTextField getInputValue() {
        return inputValue;
    }

    public static JTextField getInputTime() {
        return inputTime;
    }

    public static JTextField getInputDate() {
        return inputDate;
    }

    public static JTextField getInputFilterPhone() {
        return inputFilterPhone;
    }

    public static JComboBox<String> getSelecFilterType() {
        return SelecFilterType;
    }

    public static JComboBox<String> getSelecType() {
        return SelecType;
    }

    public TransactionListView() {
        setLayout(new BorderLayout());
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());
        // layout chính
        Boder boderCenter = new Boder();
        Boder boderRight = new Boder();

        add(boderCenter,BorderLayout.CENTER);
        add(boderRight,BorderLayout.WEST);

        boderCenter.add(blockTable(),BorderLayout.CENTER);
        boderRight.add(blockTool(),BorderLayout.WEST);



        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boderCenter.removeAll();
                boderCenter.add(new ClientListView(),BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        buttonExportToExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thời gian hiện tại
                LocalDateTime currentTime = LocalDateTime.now();

                // Định dạng theo "yyyy-MM-dd_HH-mm"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
                String file = ".xlsx";
                String formattedDateTime = currentTime.format(formatter)+file;

                try {
                    ExcelExporter.exportToExcel(table,columnName,formattedDateTime);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Xuất ra file excel !");
            }
        });

        buttonAllTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boderCenter.removeAll();
                boderCenter.add(blockTable(),BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
    }

    private JScrollPane createTable() {


        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {
                },
                columnName
        ){
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
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
        List<model.Transaction> transactionList = TransactionDAO.getInstance().getAll();
        Object[][] data = transactionList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getComment(),
                        s.getType().getType(),
                        s.getQuantity(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(),1),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(),2),
                        s.getPerson().getName()+" "+s.getPerson().getLastName(),
                        s.getPerson().getPhone(),
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

        table.getColumnModel().getColumn(1).setMinWidth(250); // Cột ID
//        table.getColumnModel().getColumn(1).setMaxWidth(300); // Cột ID

        table.getColumnModel().getColumn(2).setMinWidth(180); // Cột ID
        table.getColumnModel().getColumn(2).setMaxWidth(190); // Cột ID

        table.getColumnModel().getColumn(3).setMinWidth(80); // Cột ID
        table.getColumnModel().getColumn(3).setMaxWidth(90); // Cột ID

        table.getColumnModel().getColumn(4).setMinWidth(90); // Cột ID
        table.getColumnModel().getColumn(4).setMaxWidth(100); // Cột ID

        table.getColumnModel().getColumn(5).setMinWidth(100); // Cột ID
        table.getColumnModel().getColumn(5).setMaxWidth(120); // Cột ID

        table.getColumnModel().getColumn(8).setMinWidth(30); // Cột ID
        table.getColumnModel().getColumn(8).setMaxWidth(50); // Cột ID

        JScrollPane scrollPane = new JScrollPane(table);
        // Đặt layout cho table_Panel là BorderLayout
        return scrollPane;
    }

    public static void loadData(){
        // Lấy model của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Xóa hết dữ liệu hiện có trong bảng
        model.setRowCount(0);
        // lấy dữ liệu từ sever
        List<model.Transaction> transactionList = TransactionDAO.getInstance().getAll();
        Object[][] data = transactionList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getComment(),
                        s.getType().getType(),
                        s.getQuantity(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(),1),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(),2),
                        s.getPerson().getName()+" "+s.getPerson().getLastName(),
                        s.getPerson().getPhone(),
                        s.getFlag(),
                }
        ).toArray(Object[][]::new);
        ClientListView.setData(data);
        setData(data);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }

    private JPanel blockCreatNewTransaction(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        int top = 5;
        int bot = 2;
        int bot2 = 15;
        int left = 10;
        int right = 10;
        Grid grid = new Grid();
        grid.GridAddCustom(labelType,0,0,left,right,top,bot,1);
        grid.GridAddCustom(SelecType,0,1,left,right,top,bot,1);

        grid.GridAddCustom(labelVale,0,2,left,right,top,bot,1);
        grid.GridAddCustom(inputValue,0,3,left,right,top,bot,1);
        SelecType.setPreferredSize(new Dimension(200, 20));
        inputValue.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelTime,0,4,left,right,top,bot,1);
        grid.GridAddCustom(inputTime,0,5,left,right,top,bot,1);
        grid.GridAddCustom(labelDate,0,6,left,right,top,bot,1);
        grid.GridAddCustom(inputDate,0,7,left,right,top,bot,1);
        grid.GridAddCustom(labelComment,0,8,left,right,top,bot,1);
        grid.GridAddCustom(inputComment,0,9,left,right,top,bot,1);

        inputTime.setPreferredSize(new Dimension(200, 20));
        inputDate.setPreferredSize(new Dimension(200, 20));
        inputComment.setPreferredSize(new Dimension(200, 20));
        // thêm data cho boder box
        String[] selectList = TransactionsTypeDAO.getInstance().getAll().stream()
                .map(s -> s.getType())
                .toArray(String[]::new);
        SelecType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));
        SelecFilterType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));
        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }

    private JPanel blockFilter() {
        int top = 10;
        int bot = 10;
        int bot2 = 15;
        int left = 10;
        int right = 0;
        int filterBarHeight = 50; // Đặt chiều cao cho filterBar ở đây

        Boder boder = new Boder();
        boder.setPreferredSize(new Dimension(0, filterBarHeight)); // Đặt chiều cao cho boder

        Grid grid = new Grid();
        grid.GridAddCustom(labelFilerByType, 0, 0, left, 0, top, bot, 1);
        grid.GridAddCustom(SelecFilterType, 1, 0, left, 0, top, bot, 1);
        grid.GridAddCustom(labelFilerByPhone, 2, 0, left, 0, top, bot, 1);
        grid.GridAddCustom(inputFilterPhone, 3, 0, left, 0, top, bot, 1);
        grid.GridAddCustom(buttonFilter, 4, 0, left, 0, top, bot, 1);
        SelecFilterType.setPreferredSize(new Dimension(200, 20));
        inputFilterPhone.setPreferredSize(new Dimension(200, 20));
        boder.add(grid, BorderLayout.CENTER);
        return boder;
    }

    private JPanel blockInforPerson(){
        Boder jPanel = new Boder();
        Grid grid = new Grid();
        int left =10;
        int right =10;
        int top =10;
        int bot =10;
        // đặt kích thước
        jPanel.setPreferredSize(new Dimension(300, 200));
        grid.GridAddCustom(labelFirstName,0,0,left,right,top,bot,1);
        grid.GridAddCustom(FirstName,1,0,left,right,top,bot,1);

        grid.GridAddCustom(labelLastName,0,1,left,right,top,bot,1);
        grid.GridAddCustom(LastName,1,1,left,right,top,bot,1);

        grid.GridAddCustom(labelPhone,0,2,left,right,top,bot,1);
        grid.GridAddCustom(Phone,1,2,left,right,top,bot,1);

        grid.GridAddCustom(buttonSelect,0,6,left,right,top,bot,2);
        grid.GridAddCustom(buttonAllTransaction,0,7,left,right,top,bot,2);
        grid.GridAddCustom(buttonCreatTransaction,0,8,left,right,top,bot,2);
        buttonSelect.setPreferredSize(new Dimension(150, 20));
        buttonAllTransaction.setPreferredSize(new Dimension(150, 20));
        buttonCreatTransaction.setPreferredSize(new Dimension(150, 35));

        jPanel.add(grid,BorderLayout.CENTER);
        return  jPanel;
    }

    private JPanel blockTool(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(blockCreatNewTransaction());
        jPanel.add(blockInforPerson());
        return jPanel;
    }

    private JPanel blockTable(){
        Boder jPanel = new Boder();
        JScrollPane scrollPane = createTable();
        jPanel.add(blockFilter(),BorderLayout.NORTH);
        jPanel.add(scrollPane,BorderLayout.CENTER);
        jPanel.add(blockBottomTransaction(),BorderLayout.SOUTH);
        return jPanel;
    }

    private JPanel blockBottomTransaction(){
        Boder jPanel = new Boder();
        Grid grid = new Grid();
        Grid grid2 = new Grid();
        grid.GridAddCustom(buttonExportToExcel,0,0,20,20,20,20,1);
        grid2.GridAddCustom(buttonDelete,0,0,20,20,20,20,1);
//        buttonCreatTransaction.setPreferredSize(new Dimension(150, 35));
        buttonExportToExcel.setPreferredSize(new Dimension(150, 35));
        // Đặt màu cho nền của JButton
        buttonExportToExcel.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonExportToExcel.setForeground(Color.WHITE);

        jPanel.add(grid,BorderLayout.EAST);
        jPanel.add(grid2,BorderLayout.WEST);
        return jPanel;
    }

    public static void reloadJpanel(){
        int id = BookingController.getPersonIdSelect();
        Person person = PersonDAO.getInstance().getById(id);
        FirstName.setText(person.getName()); // Đặt giá trị cho JLabel
        LastName.setText(person.getLastName()); // Đặt giá trị cho JLabel
        Phone.setText(person.getPhone()); // Đặt giá trị cho JLabel
        FirstName.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        LastName.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        Phone.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
    }

    public static void setNullData(){
        FirstName.setText(""); // Đặt giá trị cho JLabel
        LastName.setText(""); // Đặt giá trị cho JLabel
        Phone.setText(""); // Đặt giá trị cho JLabel
        FirstName.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        LastName.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        Phone.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        inputValue.setText("");
        inputTime.setText("");
        inputDate.setText("");
        inputFilterPhone.setText("");
        inputComment.setText("");
    }

    public static void main(String[] args) {
        // In mảng columnName
        for (int i = 0; i < columnName.length; i++) {
            System.out.println(columnName[i]);
        }
    }
}
