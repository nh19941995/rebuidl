package view.booking;

import view.Tool.Boder;
import view.Tool.Grid;
import view.booking.miniView.BookingListView;
import view.booking.miniView.InfoBookingView;

import javax.swing.*;
import java.awt.*;

public class BookingView extends JPanel {
    // Tạo ô input và đặt kích thước mặc định
    private static JButton buttonSelectTable = new JButton("Select table from list");
    private static   JButton buttonRemoveARow = new JButton("Remove a row");

    private static JButton buttonSelectClient = new JButton("Sellect client from list");
    private static JButton buttonShowAllBooking = new JButton("Show all booking");

    private static JButton buttonSelectMenu = new JButton("Select menu from list");
    private static JButton buttonAddNewMenu = new JButton("Creat new menu");
    private static JButton buttonSubmitBooking = new JButton("Submit a new booking");


    // infor block
    private static JLabel labelFirstName = new JLabel("Fist name Client :");
    private static JLabel labelFirstNameValue = new JLabel();
    private static JLabel labelLastName = new JLabel("Last name Client :");
    private static JLabel labelLastNameValue = new JLabel();
    private JLabel labelDeposit = new JLabel("Deposit         : ");
    private JLabel labelStartTime = new JLabel("Start time      : ");
    private JLabel labelEndTime = new JLabel("End time        : ");
    private JLabel labelComment = new JLabel("Comment         : ");
    private static JTextField inputStartTime = new JTextField();
    private static JTextField inputDeposit = new JTextField();
    private static JTextField inputEndTime = new JTextField();
    private static JTextField inputComment = new JTextField();



    public static JButton getButtonSubmitBooking() {
        return buttonSubmitBooking;
    }
    public BookingView() {
        setLayout(new BorderLayout());
        //        lauout chính của booking
        Boder centerBooking = new Boder();
        Boder leftBooking = new Boder();
        //        thêm các layout chính vào layout class
        this.add(centerBooking,BorderLayout.CENTER);
        this.add(leftBooking,BorderLayout.WEST);




        Grid grid = new Grid();
        JPanel table = new BookingListView();
        Grid gridTop = new Grid();
        gridTop.GridAddCustom(table,            0,0,0,0,20,20,2);
        // hàng 0
        // đặt kích thước
        table.setPreferredSize(new Dimension(400, 200));
        // hàng 1
        grid.GridAddCustom(buttonSelectMenu, 0,1,20,20,20,20,1);
        grid.GridAddCustom(buttonAddNewMenu, 1,1,20,20,20,20,1);
        // hàng 2
        grid.GridAddCustom(buttonSelectTable,0,2,20,20,20,20,1);
        grid.GridAddCustom(buttonRemoveARow, 1,2,20,20,20,20,1);
//
//        // đặt kích thước
        buttonSelectTable.setPreferredSize(new Dimension(150, 20));
        buttonSelectMenu.setPreferredSize(new Dimension(150, 20));
        buttonAddNewMenu.setPreferredSize(new Dimension(150, 20));
        buttonRemoveARow.setPreferredSize(new Dimension(150, 20));

        // hàng 3
        grid.GridAddCustom(labelFirstName,     0,3,0,0,5,5,1);
        grid.GridAddCustom(labelFirstNameValue,1,3,0,0,5,5,1);

        grid.GridAddCustom(labelLastName,      0,4,0,0,5,5,1);
        grid.GridAddCustom(labelLastNameValue, 1,4,0,0,5,5,1);

        grid.GridAddCustom(labelDeposit,       0,5,0,0,5,5,1);
        grid.GridAddCustom(inputDeposit,       1,5,0,0,5,5,1);
        inputDeposit.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelStartTime,     0,6,0,0,5,5,1);
        grid.GridAddCustom(inputStartTime,     1,6,0,0,5,5,1);
        inputStartTime.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelEndTime,       0,7,0,0,5,5,1);
        grid.GridAddCustom(inputEndTime,       1,7,0,0,5,5,1);
        inputEndTime.setPreferredSize(new Dimension(200, 20));



        // hàng 4
        grid.GridAddCustom(buttonSelectClient,0,8,20,20,20,20,1);
        grid.GridAddCustom(buttonShowAllBooking,1,8,20,20,20,20,1);
        // hàng 5
        Grid gridBot = new Grid();
        gridBot.GridAddCustom(buttonSubmitBooking,0,0,20,20,20,20,0);

        // đặt kích thước
        buttonSelectClient.setPreferredSize(new Dimension(150, 20));
        buttonSubmitBooking.setPreferredSize(new Dimension(150, 20));
        buttonShowAllBooking.setPreferredSize(new Dimension(150, 20));
        leftBooking.add(grid,BorderLayout.CENTER);
        leftBooking.add(gridBot,BorderLayout.SOUTH);
        leftBooking.add(gridTop,BorderLayout.NORTH);
        leftBooking.setPreferredSize(new Dimension(450, 300));


    }
}
