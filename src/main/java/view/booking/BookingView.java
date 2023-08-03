package view.booking;

import controller.BookingController;
import model.Booking;
import view.MenuView;
import view.Tool.Boder;
import view.Tool.Grid;
import view.booking.miniView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookingView extends JPanel {

    private JButton buttonSelectTable = new JButton("Select table from list");
    private JButton buttonRemoveARow = new JButton("Remove a row");

    private JButton buttonSelectClient = new JButton("Sellect client from list");
    private JButton buttonShowAllBooking = new JButton("Show all booking");

    private JButton buttonSelectMenu = new JButton("Select menu from list");
    private JButton buttonAddNewMenu = new JButton("Creat new menu");
    private static JButton buttonSubmitBooking = new JButton("Submit a new booking");

    public static JButton getButtonSubmitBooking() {
        return buttonSubmitBooking;
    }

    public BookingView() {
        // Xóa phần khởi tạo ở đây, thay vào đó bạn chỉ cần khởi tạo JPanel (BookingView là một JPanel)

        setLayout(new BorderLayout());
        this.setBackground(Color.red);
        JLabel centerLabel = new JLabel ("giữa");
        JLabel leftLabel = new JLabel ("trái");
        JLabel rightLabel = new JLabel ("phải");
        JLabel topLabel = new JLabel ("trên");
        JLabel botLabel = new JLabel ("dưới");


//        lauout chính của booking
        Boder centerBooking = new Boder();
        centerBooking.setBackground(Color.red);
        Boder rightBooking = new Boder();
        rightBooking.setBackground(Color.blue);
        Boder leftBooking = new Boder();
        rightBooking.setBackground(Color.gray);
        Boder botBooking = new Boder();
        botBooking.setBackground(Color.green);
        Boder topBooking = new Boder();
        topBooking.setBackground(Color.yellow);

//        thêm các layout chính vào layout class

        this.add(centerBooking,BorderLayout.CENTER);
        this.add(rightBooking,BorderLayout.EAST);
        this.add(leftBooking,BorderLayout.WEST);
        this.add(botBooking,BorderLayout.SOUTH);
        this.add(topBooking,BorderLayout.NORTH);

        leftBooking.setBackground(Color.RED);
        leftBooking.setPreferredSize(new Dimension(420, 300));
        leftBooking.setBackground(Color.red);

        //       thêm grid vào layout chính
        leftBooking.add(setTableAndMenuBlock(),BorderLayout.NORTH);
        centerBooking.add( new MenuListView(),BorderLayout.CENTER);
        leftBooking.add(setInfoBlock(),BorderLayout.CENTER);


        buttonSelectTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                centerBooking.add(new TableListView(),BorderLayout.CENTER);
                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();
            }
        });

        buttonSelectClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                centerBooking.add(new ClientListView(),BorderLayout.CENTER);

                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();
            }
        });

        buttonSelectMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                centerBooking.add(new MenuListView(),BorderLayout.CENTER);

                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();
            }
        });

        buttonAddNewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                centerBooking.add(new MenuView(),BorderLayout.CENTER);
                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();
            }
        });

        buttonRemoveARow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Booking> bookings = BookingController.getBookings();
                bookings.remove(BookingListView.getIdSelect()-1);
                BookingListView.loadData();
            }
        });

//        buttonSubmitBooking.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(getIdClientList());
//
//
//            }
//        });

        buttonShowAllBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
//                centerBooking.add(new BookingListView(),BorderLayout.CENTER);
                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();

            }
        });
    }

    public JPanel setTableAndMenuBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        Grid grid = new Grid();
        JPanel table = new BookingListView();
        grid.GridAddCustom(table,0,0,0,0,20,20,2);
        // đặt kích thước
        table.setPreferredSize(new Dimension(400, 200));
        grid.GridAddCustom(buttonSelectMenu,0,1,20,20,20,20,1);
        grid.GridAddCustom(buttonAddNewMenu,1,1,20,20,20,20,1);
        grid.GridAddCustom(buttonSelectTable,0,2,20,20,20,20,1);
        grid.GridAddCustom(buttonRemoveARow,1,2,20,20,20,20,1);

        // đặt kích thước
        buttonSelectTable.setPreferredSize(new Dimension(150, 20));
        buttonSelectMenu.setPreferredSize(new Dimension(150, 20));
        buttonAddNewMenu.setPreferredSize(new Dimension(150, 20));
        buttonRemoveARow.setPreferredSize(new Dimension(150, 20));
        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }

    public JPanel setInfoBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        Grid grid = new Grid();
        JPanel info = new InfoBookingView();
        grid.GridAddCustom(info,0,0,0,0,20,20,2);
        // đặt kích thước
//        table.setPreferredSize(new Dimension(450, 200));

        grid.GridAddCustom(buttonSelectClient,0,1,20,20,20,20,1);
        grid.GridAddCustom(buttonShowAllBooking,1,1,20,20,20,20,1);
        grid.GridAddCustom(buttonSubmitBooking,0,2,20,20,20,20,2);

        // đặt kích thước
        buttonSelectClient.setPreferredSize(new Dimension(150, 20));
        buttonSubmitBooking.setPreferredSize(new Dimension(150, 20));

        buttonShowAllBooking.setPreferredSize(new Dimension(150, 20));



        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }
}

