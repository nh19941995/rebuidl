package view.booking.miniView;
import controller.InstantDateTimeInfo;
import dao.PermissionDAO;
import dao.PersonDAO;
import dao.TableTypeDAO;
import model.Permission;
import model.Person;
import view.Tool.Grid;
import view.booking.BookingView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ClientListView extends JPanel {
    // tiêu đề
    private JLabel labelTitle = new JLabel("Person Information");
    private JLabel labelFirstName = new JLabel("First name:");
    private JLabel labelLastName = new JLabel("Last name:");
    private JLabel labelEmail = new JLabel("Email:");
    private JLabel labelAdress = new JLabel("Adress:");
    private JLabel labelBirthday = new JLabel("Birthday:");
    private JLabel labelPhone = new JLabel("Phone number:");
    private JLabel labelPermission = new JLabel("Permission:");
    private JLabel labelfilterByPhone = new JLabel("Search by phone number:");
    // input
    private JTextField inputFirstName = new JTextField();
    private JTextField inputLastName = new JTextField();
    private JTextField inputEmail = new JTextField();
    private JTextField inputAdress = new JTextField();
    private JTextField inputBirthday = new JTextField();
    private JTextField inputPhone = new JTextField();
    private JComboBox<String> SelecType = new JComboBox<String>();
    private JTextField inputfilterByPhone = new JTextField();
    // button
    private JButton buttonAddPerson = new JButton("Add new person");
    private JButton buttonDeletePerson = new JButton("Delete person");
    private JButton buttonUpdatePerson = new JButton("Update person");
    private JButton buttonSearchPerson = new JButton("Search");
    private JButton buttonSelectPerson = new JButton("Select person");

    private JTable table = new JTable();

//    các giá trị
    private static Object[][] data;
    private String firstName;
    private String lasttName;
    private String email;
    private String address;
    private String  birthday;
    private String phone;
    private String permission;
    private static String searchPhone;

    private static String idSelectInTable;

    public static String getIdSelectInTable() {
        return idSelectInTable;
    }

    public static void setIdSelectInTable(String idSelectInTable) {
        ClientListView.idSelectInTable = idSelectInTable;
    }

    public static Object[][] getData() {
        return data;
    }

    public static void setData(Object[][] data) {
        ClientListView.data = data;
    }

    public static String getSearchPhone() {
        return searchPhone;
    }

    public static void setSearchPhone(String searchPhone) {
        ClientListView.searchPhone = searchPhone;
    }
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidName(String name){
        String nameRegex = "^[a-zA-Z ]*$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    private static boolean isValidAddress(String address){
        String addressRegex = "^[a-zA-Z ]*$";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    private static boolean isValidPhone(String phone){
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    private static boolean isValidBirth(String birth){
        String birthRegex = "^(19[0-9]{2}|20[0-2][0-2])-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        Pattern pattern = Pattern.compile(birthRegex);
        Matcher matcher = pattern.matcher(birth);
        return matcher.matches();
    }

    public ClientListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());
        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        this.add(scrollPane, BorderLayout.CENTER);
        // thêm block add person
        this.add(blockAddPerson(), BorderLayout.SOUTH);

        // sự kiện click vào bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        BookingView.setIdClientList(id);
                        setIdSelectInTable(id);
                        System.out.println("Bảng Client đang chọn hàng có id là: "+ id);
                    }
                }
            }
        });

        // các sự kiện

        table.addMouseListener(new java.awt.event.MouseAdapter()  {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });






        buttonAddPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    System.out.println("thêm mới");
                    // lấy dữ liệu từ form
                    firstName = inputFirstName.getText();
                    lasttName = inputLastName.getText();
                    email = inputEmail.getText();
                    address = inputAdress.getText();
                    birthday  = inputBirthday.getText();
                    phone = inputPhone.getText();
                    permission = (String) SelecType.getSelectedItem();

                    Person newPerson = new Person();
                    // bắt đầu thêm
                    newPerson.setLastName(lasttName);
                    newPerson.setName(firstName);
                    newPerson.setUsername("");
                    newPerson.setPassword("");
                    newPerson.setEmail(email);
                    newPerson.setPhone(phone);
                    newPerson.setAddress(address);
                    newPerson.setDateOfBirth(InstantDateTimeInfo.getByStringDate(birthday));
                    Instant now = Instant.now();
                    newPerson.setDateCreat(now);
                    newPerson.setDateUpdate(now);
                    newPerson.setFlag(1);
                    newPerson.setPermission(PermissionDAO.getInstance().getPermissionByString(permission));
                    PersonDAO.getInstance().insert(newPerson);
                    loadData();
                    inputFirstName.setText("");
                    inputLastName.setText("");
                    inputAdress.setText("");
                    inputEmail.setText("");
                    inputBirthday.setText("");
                    inputPhone.setText("");
            }
        });


        buttonUpdatePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("cập nhật");
                // lấy dữ liệu từ form
                firstName = inputFirstName.getText();
                lasttName = inputLastName.getText();
                email = inputEmail.getText();
                address = inputAdress.getText();
                birthday  = inputBirthday.getText();
                phone = inputPhone.getText();
                permission = (String) SelecType.getSelectedItem();
                // gọi đối tượng cũ qua id để update
                Person personUpdate = PersonDAO.getInstance().getById(Integer.parseInt(getIdSelectInTable()));
                personUpdate.setLastName(lasttName);
                personUpdate.setName(firstName);
                personUpdate.setUsername("");
                personUpdate.setPassword("");
                personUpdate.setEmail(email);
                personUpdate.setPhone(phone);
                personUpdate.setAddress(address);
                personUpdate.setDateOfBirth(InstantDateTimeInfo.getByStringDate(birthday));
                personUpdate.setDateCreat(PersonDAO.getInstance().getById(
                        Integer.parseInt(getIdSelectInTable())
                ).getDateCreat());
                Instant now = Instant.now();
                personUpdate.setDateUpdate(now);
                personUpdate.setFlag(1);
                personUpdate.setPermission(PermissionDAO.getInstance().getPermissionByString(permission));
                PersonDAO.getInstance().update(personUpdate);
                // Gọi hàm để tải lại dữ liệu và cập nhật bảng
                loadData();
            }
        });
        // thêm thông báo nếu chưa chọn người
        buttonDeletePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("xóa");
                Person personDelete = PersonDAO.getInstance().getById(Integer.parseInt(ClientListView.getIdSelectInTable()));
                personDelete.setFlag(0);
                PersonDAO.getInstance().update(personDelete);
                // Gọi hàm reloadTableData() để tải lại dữ liệu và cập nhật bảng
                loadData();
            }


        });

        buttonSelectPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("chọn");
            }
        });

        buttonSearchPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tìm kiếm");
                searchPhone = inputfilterByPhone.getText();
                searchByPhone();
            }
        });

    }





    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {

                },
                new String [] {"ID", "Name", "Date of birth", "Phone number", "Email", "Date created", "Permission"}
        ){
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
        };

        // Khởi tạo mô hình dữ liệu cho bảng
        table.setModel(model);
        // lấy dữ liệu từ sever
        List<Person> personList = PersonDAO.getInstance().getAll();
        Object[][] data = personList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName() + " " + s.getLastName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateOfBirth(), 2),
                        s.getPhone(),
                        s.getEmail(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        s.getPermission().getPermissionName(),
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
        List<Person> personList = PersonDAO.getInstance().getAll();
        Object[][] data = personList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName() + " " + s.getLastName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateOfBirth(), 2),
                        s.getPhone(),
                        s.getEmail(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        s.getPermission().getPermissionName(),
                }
        ).toArray(Object[][]::new);

        ClientListView.setData(data);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : data) {
            model.addRow(rowData);
        }

        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }

    private JPanel blockAddPerson(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());


        // đặt kích thước
        inputFirstName.setPreferredSize(new Dimension(150, 20));
        inputLastName.setPreferredSize(new Dimension(150, 20));
        inputEmail.setPreferredSize(new Dimension(200, 20));
        inputAdress.setPreferredSize(new Dimension(230, 20));
        inputBirthday.setPreferredSize(new Dimension(130, 20));
        inputPhone.setPreferredSize(new Dimension(130, 20));
        SelecType.setPreferredSize(new Dimension(200, 20));
        buttonAddPerson.setPreferredSize(new Dimension(150, 20));
        buttonDeletePerson.setPreferredSize(new Dimension(150, 20));
        buttonUpdatePerson.setPreferredSize(new Dimension(150, 20));
        inputfilterByPhone.setPreferredSize(new Dimension(138, 20));
        buttonSelectPerson.setPreferredSize(new Dimension(150, 30));
        // Đặt màu cho nền của JButton
        buttonSelectPerson.setBackground(Color.RED);
        // Đặt màu cho văn bản của JButton
        buttonSelectPerson.setForeground(Color.WHITE);

        // bố trí các phần tử
        Grid grid = new Grid();
        // cột 1
        grid.GridAdd(labelFirstName,0,0,10,10,5);
        grid.GridAdd(inputFirstName,0,1,10,10,0);
        grid.GridAdd(labelLastName,0,2,10,10,5);
        grid.GridAddCustom(inputLastName,0,3,10,10,5,15,1);
        // cột 2
        grid.GridAdd(labelEmail,1,0,10,10,5);
        grid.GridAdd(inputEmail,1,1,10,10,0);
        grid.GridAddCustom(labelPermission,1,2,10,10,5,5,1);
        grid.GridAddCustom(SelecType,1,3,10,10,0,15,1);

        // cột 3
        grid.GridAdd(labelBirthday,2,0,10,10,5);
        grid.GridAdd(inputBirthday,2,1,10,10,0);
        grid.GridAdd(labelPhone,2,2,10,10,5);
        grid.GridAddCustom(inputPhone,2,3,10,10,5,15,1);
        // cột 4
        grid.GridAddCustom(labelfilterByPhone,3,0,10,10,5,5,2);
        grid.GridAddCustom(inputfilterByPhone,3,1,10,10,5,5,1);
        // cột 3-5
        grid.GridAddCustom(buttonSearchPerson,4,1,10,10,5,5,1);
        grid.GridAddCustom(labelAdress,3,2,10,10,5,5,2);
        grid.GridAddCustom(inputAdress,3,3,10,10,5,15,2);

        // cột 5
        grid.GridAdd(buttonDeletePerson,5,1,20,10,3);
        grid.GridAdd(buttonUpdatePerson,5,2,20,10,3);
        grid.GridAddCustom(buttonAddPerson,5,3,20,10,3,15,1);

        // thêm data cho boder box
        String[] selectList = PermissionDAO.getInstance().getAll().stream()
                .map(s -> s.getPermissionName())
                .toArray(String[]::new);
        SelecType.setModel(new javax.swing.DefaultComboBoxModel<>(selectList));
        // nút select
        Grid gridAddPerson = new Grid();
//        grid select person.
        gridAddPerson.GridAddCustom(buttonSelectPerson,0,1,10,10,5,5,2);

        // thêm toàn bộ các phần tử vào layout chính
        jPanel.add(grid,BorderLayout.CENTER);
        jPanel.add(gridAddPerson,BorderLayout.EAST);






        return jPanel;
    }

    public void searchByPhone() {
        String searchPhone = ClientListView.getSearchPhone();
        System.out.println(searchPhone);

        if (!searchPhone.isEmpty()) {
            Object[][] originalData = ClientListView.getData();
            // Tạo luồng dữ liệu từ mảng
            Stream<Object[]> dataStream = Arrays.stream(originalData);
            // Sử dụng filter để lọc dữ liệu theo số điện thoại
            Object[][] filteredData = dataStream.filter(row -> row[3] != null && row[3].equals(searchPhone)).toArray(Object[][]::new);
            setData(filteredData);
            // Thêm dữ liệu mới vào bảng
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            for (Object[] rowData : filteredData) {
                model.addRow(rowData);
            }
        } else {
            // Nếu không nhập số điện thoại, hiển thị lại toàn bộ dữ liệu
            System.out.println("không có số nào phù hợp 1");
            loadData();
        }
    }

    private void tableMouseClicked(MouseEvent evt){
        int rowNumber = table.getSelectedRow();
        TableModel tblModel = table.getModel();



        String fName = tblModel.getValueAt(rowNumber, 1 ).toString();
//        String lName = tblModel.getValueAt(rowNumber, 1 ).toString();
        String birth = tblModel.getValueAt(rowNumber, 2 ).toString();
        String phone = tblModel.getValueAt(rowNumber, 3).toString();
        String email = tblModel.getValueAt(rowNumber, 4 ).toString();
        String address = tblModel.getValueAt(rowNumber, 3).toString();



        inputFirstName.setText(fName);
//        inputLastName.setText(lName);
        inputAdress.setText(address);
        inputEmail.setText(email);
        inputBirthday.setText(birth);
        inputPhone.setText(phone);

    }




}