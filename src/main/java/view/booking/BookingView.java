package view.booking;

import view.MenuView;
import view.Tool.Boder;
import view.Tool.Grid;
import view.booking.miniView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingView extends JPanel {
    // Tạo ô input và đặt kích thước mặc định
    JLabel startTimelabel = new JLabel("Start time");
    JLabel endTimelabel = new JLabel("End time");

    JButton buttonSelectTable = new JButton("Select table from list");
    JButton buttonAddNewTable = new JButton("Add new");

    JButton buttonSelectClient = new JButton("Sellect client from list");
    JButton buttonAddNewClient = new JButton("Add new");

    JButton buttonSelectMenu = new JButton("Select menu from list");
    JButton buttonAddNewMenu = new JButton("Add new");
    JButton buttonSubmitBooking = new JButton("Submit all booking");
    // các biến giao tiếp giữa các form
    private static String idClientList;
    private static String idCTableList;
    private static String idMenuList;

    public static String getIdClientList() {
        return idClientList;
    }

    public static void setIdClientList(String idClientList) {
        BookingView.idClientList = idClientList;
    }

    public static String getIdCTableList() {
        return idCTableList;
    }

    public static void setIdCTableList(String idCTableList) {
        BookingView.idCTableList = idCTableList;
    }

    public static String getIdMenuList() {
        return idMenuList;
    }

    public static void setIdMenuList(String idMenuList) {
        BookingView.idMenuList = idMenuList;
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
        leftBooking.setPreferredSize(new Dimension(500, 300));

        // thêm các phần tử vào layout chính

//        JLabel startTimelabel = new JLabel("Start time");
//        JLabel endTimelabel = new JLabel("End time");
//
//        JButton selectTableBTN = new JButton("select table from list");
//        JButton selectClientBTN = new JButton("sellect client from list");
//        JButton selectMenuBTN = new JButton("select menu from list");
//        JButton addToBookingListBTN = new JButton("select table from list");
//        JButton submitBookingBTN = new JButton("select table from list");






        //       thêm grid vào layout chính
        leftBooking.add(setTableAndMenuBlock(),BorderLayout.CENTER);
        centerBooking.add( new MenuListView(),BorderLayout.CENTER);
        leftBooking.add(setInfoBlock(),BorderLayout.SOUTH);





//
//        centerBooking.add(centerLabel,BorderLayout.CENTER);
//        rightBooking.add(rightLabel,BorderLayout.CENTER);
//        leftBooking.add(leftLabel,BorderLayout.CENTER);
//        botBooking.add(botLabel,BorderLayout.CENTER);
//        topBooking.add(topLabel,BorderLayout.CENTER);

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





        buttonSubmitBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getIdClientList());

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
        table.setPreferredSize(new Dimension(450, 200));
        grid.GridAddCustom(buttonSelectMenu,0,1,20,20,20,20,1);
        grid.GridAddCustom(buttonAddNewMenu,1,1,20,20,20,20,1);
        grid.GridAddCustom(buttonSelectTable,0,2,20,20,20,20,1);
        grid.GridAddCustom(buttonAddNewTable,1,2,20,20,20,20,1);

        // đặt kích thước
        buttonSelectTable.setPreferredSize(new Dimension(200, 20));
        buttonSelectMenu.setPreferredSize(new Dimension(200, 20));

        buttonAddNewMenu.setPreferredSize(new Dimension(100, 20));
        buttonAddNewTable.setPreferredSize(new Dimension(100, 20));

        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }

    public JPanel setInfoBlock(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        Grid grid = new Grid();
//        JPanel table = new BookingListView();
//        grid.GridAddCustom(table,0,0,0,0,20,20,2);
        // đặt kích thước
//        table.setPreferredSize(new Dimension(450, 200));

        grid.GridAddCustom(buttonSelectClient,0,1,20,20,20,20,1);
        grid.GridAddCustom(buttonAddNewClient,1,1,20,20,20,20,1);
        grid.GridAddCustom(buttonSubmitBooking,0,2,20,20,20,20,2);

        // đặt kích thước
        buttonSelectClient.setPreferredSize(new Dimension(200, 20));
        buttonSubmitBooking.setPreferredSize(new Dimension(200, 20));

        buttonAddNewClient.setPreferredSize(new Dimension(100, 20));



        jPanel.add(grid,BorderLayout.CENTER);
        return jPanel;
    }
}

