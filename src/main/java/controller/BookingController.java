package controller;

import dao.MenuNameDAO;
import dao.TableListDAO;
import model.Booking;
import model.MenuName;
import model.TableList;
import view.booking.miniView.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookingController {
    // các bảng dữ liệu
    private static JTable jtableTable = TableListView.getTable();
    private static JTable jtableMenu = MenuListView.getTable();
    private static JTable jTableBookingList = BookingListView.getTable();
    private static JTable jTablePerson = ClientListView.getTable();
    // danh sách tổng
    private static ArrayList<Booking> bookings = new ArrayList<>();
    // tạo set chứa id bàn
    private static Set<Integer> setTableId = new HashSet<>();
    private static JButton buttonSelectTable = TableListView.getButtonSelectTable();
    private static JButton buttonSelectMenu = MenuListView.getButtonSelectMenu();
    private static JButton buttonSelectPerson = ClientListView.getButtonSelectPerson();
    private static int tableIdSelect;
    private static int menuIdSelect;
    private static int personIdSelect;

    public static int getPersonIdSelect() {
        return personIdSelect;
    }

    public static void setPersonIdSelect(int personIdSelect) {
        BookingController.personIdSelect = personIdSelect;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public static void setBookings(ArrayList<Booking> bookings) {
        BookingController.bookings = bookings;
    }

    public BookingController() {
        getTableFromList();
        getMenuFromList();
        getPersonFromList();
        selectTable();
        selectMenu();
        selectPerson();












    }
    public static void getTableFromList(){
        // sự kiện click vào bảng table
        jtableTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = jtableTable.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String idTable = jtableTable.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        tableIdSelect = Integer.parseInt(idTable);
                        System.out.println("Table: "+ idTable);
                    }
                }
            }

        });
    }

    public static void getMenuFromList(){
        // sự kiện click vào bảng menu
        jtableMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = jtableMenu.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = jtableMenu.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        menuIdSelect = Integer.parseInt(id);
                        System.out.println("Menu: "+ id);
                    }
                }
            }
        });
    }

    public static void getPersonFromList(){
        // sự kiện click vào bảng
        jTablePerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = jTablePerson.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = jTablePerson.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        personIdSelect = Integer.parseInt(id);
                        System.out.println("Person :"+ id);
                    }
                }
            }
        });
    }

    public static void selectTable(){
        // sự kiện click vào bảng menu
        buttonSelectTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("gửi dữ liệu về Booking list menu");
                // lấy về đối tương table theo id bàn đã chọn từ bảng
                TableList tableObject = TableListDAO.getInstance().getById(tableIdSelect);
                // nếu id bàn đã có trong set
                if (setTableId.contains(tableIdSelect)){
                    // đã tồn tại trong set
                    JOptionPane.showMessageDialog(null, "You can't book a table twice !", "Notice", JOptionPane.WARNING_MESSAGE);
                }else {
                    // chưa tồn tại trong set
                    setTableId.add(tableIdSelect);
                    if (bookings.size() == 0){   // danh sách chưa có phần tử nào thì tạo mới và thêm thuộc tính
                        System.out.println("tạo mới");
                        Booking newBooking = new Booking();
                        newBooking.setTable(tableObject);
                        bookings.add(newBooking);
                    }else {
                        boolean foundEmptyMenuName = false;   // nếu đã có phần tử và có thuộc tính trống thì duyệt qua list và thêm thuộc tính trống cho 1 phần tử và thoát vòng
                        for (Booking booking : bookings) {
                            if (booking.getTable() == null) {
                                booking.setTable(tableObject);
                                foundEmptyMenuName = true;
                                break;
                            }
                        }
                        if (!foundEmptyMenuName) {    // nếu có phần tủ nhưng tất cả đã dc thêm thuộc tính thì tạo mới 1 phần tử và thêm thuộc tính cho nó
                            Booking newBooking = new Booking();
                            newBooking.setTable(tableObject);
                            bookings.add(newBooking);
                        }
                    }
                }
                BookingListView.loadData();
            }
        });
    }

    public static void selectMenu(){
        buttonSelectMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select Menu");
                MenuName menuName = MenuNameDAO.getInstance().getById(menuIdSelect);
                if (bookings.size() == 0){
                    Booking booking = new Booking();
                    booking.setMenuName(menuName);
                    bookings.add(booking);
                }else {
                    boolean foundEmptyMenuName = false;
                    for (Booking booking : bookings) {
                        if (booking.getMenuName() == null) {
                            booking.setMenuName(menuName);
                            foundEmptyMenuName = true;
                            break;
                        }
                    }
                    if (!foundEmptyMenuName) {
                        // Nếu không tìm thấy booking có menuName null, thêm mới một booking với menuName vào danh sách.
                        Booking newBooking = new Booking();
                        newBooking.setMenuName(menuName);
                        bookings.add(newBooking);
                    }
                }
                BookingListView.loadData();
            }
        });
    }

    public static void selectPerson(){
        buttonSelectPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("ClientListView id: "+ getIdSelectInTable());
                // đẩy id person qua class BookingView
//                BookingView.setIdClientList(ClientListView.getIdSelectInTable());
                InfoBookingView.reloadJpanel();
            }
        });
    }







}
