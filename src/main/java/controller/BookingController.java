package controller;

import dao.*;
import model.*;
import view.booking.BookingView;
import view.booking.miniView.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
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

    private static JButton buttonSubmitNewBooking = BookingView.getButtonSubmitBooking();
    private static int tableIdSelect;
    private static int menuIdSelect;
    private static int personIdSelect;

//    private static BookingsInfo bookingsInfo = new BookingsInfo();

//    public static BookingsInfo getBookingsInfo() {
//        return bookingsInfo;
//    }
//
//    public static void setBookingsInfo(BookingsInfo bookingsInfo) {
//        BookingController.bookingsInfo = bookingsInfo;
//    }

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
        selectTable();
        selectMenu();
        selectPerson();
        submitNewBooking();
    }






    public static void selectTable(){
        // sự kiện click vào bảng menu
        buttonSelectTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableIdSelect = TableListView.getTableIdSelect();
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

                menuIdSelect = MenuListView.getMenuIdSelect();
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

    public static void submitNewBooking(){
        buttonSubmitNewBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(personIdSelect);
                BookingsInfo newInfo = new BookingsInfo();
                String startTimeString = InfoBookingView.getInputStartTime().getText();
                String dateString = InfoBookingView.getInputDate().getText();
                String endTimeString = InfoBookingView.getInputEndTime().getText();
                String depositString = InfoBookingView.getInputDeposit().getText();
                String commentString = InfoBookingView.getInputComment().getText();

                int check = 1;
                if (startTimeString.isEmpty()||endTimeString.isEmpty()||commentString.isEmpty()||personIdSelect ==0||dateString.isEmpty()){
                    if (check==1){
                        JOptionPane.showMessageDialog(null, "You must fill in all the required information before proceeding to make a reservation !", "Notice", JOptionPane.WARNING_MESSAGE);
                    }
                    check =0;
                }
                if (!RegexMatcher.hourCheck(
                        startTimeString,"").equals("")
                        ||!RegexMatcher.hourCheck(endTimeString, "").equals("")
                        ||!RegexMatcher.dayCheck(dateString,"").equals("")
                ){
                    if (check ==1){
                        JOptionPane.showMessageDialog(null,
                                RegexMatcher.hourCheck(startTimeString, "Star time: ")+
                                        RegexMatcher.hourCheck(endTimeString, "End time: ")+
                                        RegexMatcher.dayCheck(dateString,"Date of event: "),
                                "Notice", JOptionPane.WARNING_MESSAGE);
                    }
                    check = 0;
                }
                if (!depositString.equals("")){
                    if (!RegexMatcher.floatCheck(depositString,"").equals("")){
                        if (check ==1){
                            JOptionPane.showMessageDialog(null, RegexMatcher.floatCheck(depositString, "Deposit: "), "Notice", JOptionPane.WARNING_MESSAGE);
                        }
                        check = 0;
                    }
                }
                BookingsInfo bookingsInfo = new BookingsInfo();
                if (check==1){
                    Instant instantStartTime = InstantDateTimeInfo.getByDayAndHour(dateString,startTimeString);
                    Instant instantEndTime = InstantDateTimeInfo.getByDayAndHour(dateString,startTimeString);
                    Instant instantNow = InstantDateTimeInfo.getNow();
                    if (!depositString.isEmpty()){
                        Float floatDeposit = Float.parseFloat(depositString);
                        bookingsInfo.setDeposit(floatDeposit);
                    }
                    System.out.println(instantNow);

                    if (instantStartTime.isBefore(instantNow)) {
                        if (check == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "Incorrect start date for the event. The start date must be after the current date. Please try again",
                                    "Notice",
                                    JOptionPane.WARNING_MESSAGE);
                            check = 0;
                        }
                    }
                    if (instantEndTime.isBefore(instantNow)) {
                        if (check == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "The end date of the event must be after the current date. Please try again.",
                                    "Notice",
                                    JOptionPane.WARNING_MESSAGE);
                            check = 0;
                        }
                    }

                    if(checkInfoTableAndMenu()){
                        Person person = PersonDAO.getInstance().getById(personIdSelect);
                        bookingsInfo.setEnd(instantEndTime);
                        bookingsInfo.setStart(instantStartTime);
                        bookingsInfo.setDateCreat(instantNow);
                        bookingsInfo.setPerson(person);
                        bookingsInfo.setInfo(commentString);
                        bookingsInfo.setFlag(1);
                        BookingsInfoDAO.getInstance().insert(bookingsInfo);
                        getBookings().forEach(s->{
                            s.setInfo(bookingsInfo);
                            s.setFlag(1);
                            BookingDAO.getInstance().insert(s);
                        });
                    }

                }
            }
        });
    }

    public static void selectPerson(){
        buttonSelectPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIdSelect = ClientListView.getMenuIdSelect();
                InfoBookingView.reloadJpanel();
            }
        });
    }

    public static boolean checkInfoTableAndMenu(){
        ArrayList<Booking> bookings = getBookings();
        for (Booking element : bookings) {
            if (element.getTable() == null) {
                JOptionPane.showMessageDialog(null,
                        "The number of tables and the menu must be equal.",
                        "Notice",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (element.getMenuName() == null) {
                JOptionPane.showMessageDialog(null,
                        "The number of tables and the menu must be equal.",
                        "Notice",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

        }
        return true;
    }







}
