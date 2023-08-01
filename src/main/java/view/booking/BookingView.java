package view.booking;

import view.Tool.Boder;
import view.Tool.Grid;
import view.booking.miniView.ClientListView;
import view.booking.miniView.DishListView;
import view.booking.miniView.TableListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingView extends JPanel {
    // Tạo ô input và đặt kích thước mặc định
    JLabel startTimelabel = new JLabel("Start time");
    JLabel endTimelabel = new JLabel("End time");
    JButton selectTableBTN = new JButton("select table from list");
    JButton selectClientBTN = new JButton("sellect client from list");
    JButton selectMenuBTN = new JButton("select menu from list");
    JButton addToBookingListBTN = new JButton("select table from list");
    JButton submitBookingBTN = new JButton("Submit all booking");
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
        leftBooking.setPreferredSize(new Dimension(300, 300));

        // thêm các phần tử vào layout chính

//        JLabel startTimelabel = new JLabel("Start time");
//        JLabel endTimelabel = new JLabel("End time");
//
//        JButton selectTableBTN = new JButton("select table from list");
//        JButton selectClientBTN = new JButton("sellect client from list");
//        JButton selectMenuBTN = new JButton("select menu from list");
//        JButton addToBookingListBTN = new JButton("select table from list");
//        JButton submitBookingBTN = new JButton("select table from list");

        //        thêm các phần tử vào grid
        Grid gridSelectTableBTN = new Grid();
        gridSelectTableBTN.GridAdd(selectTableBTN,0,0,10,10,10);
        gridSelectTableBTN.GridAdd(selectClientBTN,0,1,10,10,10);
        gridSelectTableBTN.GridAdd(selectMenuBTN,0,2,10,10,10);
        gridSelectTableBTN.GridAdd(submitBookingBTN,0,3,10,10,10);
        // đặt kích thước
        selectTableBTN.setPreferredSize(new Dimension(200, 20));
        selectClientBTN.setPreferredSize(new Dimension(200, 20));
        selectMenuBTN.setPreferredSize(new Dimension(200, 20));
        submitBookingBTN.setPreferredSize(new Dimension(200, 20));



        //       thêm grid vào layout chính
        leftBooking.add(gridSelectTableBTN,BorderLayout.CENTER);



//
//        centerBooking.add(centerLabel,BorderLayout.CENTER);
//        rightBooking.add(rightLabel,BorderLayout.CENTER);
//        leftBooking.add(leftLabel,BorderLayout.CENTER);
//        botBooking.add(botLabel,BorderLayout.CENTER);
//        topBooking.add(topLabel,BorderLayout.CENTER);

        selectTableBTN.addActionListener(new ActionListener() {
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

        selectClientBTN.addActionListener(new ActionListener() {
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

        selectMenuBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa tất cả các thành phần con khỏi JPanel
                centerBooking.removeAll();
                // Gọi hàm searchTableList() để thực hiện tìm kiếm và cập nhật dữ liệu
                centerBooking.add(new DishListView(),BorderLayout.CENTER);

                // Gọi phương thức revalidate() và repaint() để load lại JPanel
                centerBooking.revalidate();
                centerBooking.repaint();
            }
        });


        submitBookingBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getIdClientList());

            }
        });



    }
}

