package view.booking.miniView;

import controller.InstantDateTimeInfo;
import dao.MenuDAO;
import dao.MenuNameDAO;
import dao.PersonDAO;
import dao.TableListDAO;
import model.*;
import view.Tool.Grid;
import view.booking.BookingView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuListView  extends JPanel {
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private static int idSelectMenu;
    private JButton buttonSelectMenu = new JButton("Choose a Menu for Booking ");

    public static int getIdSelectMenu() {
        return idSelectMenu;
    }

    public static void setIdSelectMenu(int idSelectMenu) {
        MenuListView.idSelectMenu = idSelectMenu;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }


    public MenuListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());

        // Tạo JScrollPane chứa bảng và thêm nó vào JPanel
        JScrollPane scrollPane = createTable();
        loadData();
        this.add(scrollPane, BorderLayout.CENTER);
        add(gridControlMenuList(),BorderLayout.SOUTH);

        // sự kiện click vào bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        setIdSelectMenu(Integer.parseInt(id));
                        System.out.println("Bảng menulist đang chọn hàng có id là: "+ id);
                    }
                }
            }
        });

        buttonSelectMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("gửi dữ liệu menu về Booking list menu");
                ArrayList<Booking> bookings = BookingListView.getBookings();
                MenuName menuName = MenuNameDAO.getInstance().getById(MenuListView.getIdSelectMenu());
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
                BookingListView.setBookings(bookings);
                BookingListView.loadData();
            }


        });

    }

    private JPanel gridControlMenuList(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        // đặt kích thước
        buttonSelectMenu.setPreferredSize(new Dimension(200, 20));
        buttonSelectMenu.setBackground(Color.red);
        Grid grid = new Grid();
        grid.GridAddCustom(buttonSelectMenu,0,0,20,20,20,20,1);


        // Create a LineBorder with a specified color and thickness
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        // Set the border for the JPanel
        jPanel.setBorder(border);



        jPanel.add(grid);
        return jPanel;
    }

    private JScrollPane createTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object [][] {

                },
                new String [] {"ID", "Menu name","Date Creat","Date update", "Price","Status"}
        ){
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
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
        List<MenuName> menuList = MenuNameDAO.getInstance().getAll();
        Object[][] data = menuList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateUpdate(), 2),
                        MenuDAO.getInstance().getTotalPriceByMenuNameID(s.getId()),
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
        List<MenuName> menuList = MenuNameDAO.getInstance().getAll();
        Object[][] data = menuList.stream().map(
                s -> new Object[]{
                        s.getId(),
                        s.getName(),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateCreat(), 2),
                        InstantDateTimeInfo.getTimeStringToInstance(s.getDateUpdate(), 2),
                        MenuDAO.getInstance().getTotalPriceByMenuNameID(s.getId()),
                        s.getFlag(),
                }
        ).toArray(Object[][]::new);

        Object[][] filteredData = Arrays.stream(data)
                .filter(row -> {
                    // Lấy giá trị từ cột thứ 4 (đếm từ 0)
                    Object value = row[4];
                    if (value instanceof Double) {
                        return (Double) value > 0.0;
                    } else if (value instanceof Float) {
                        return (Float) value > 0.0;
                    } else if (value instanceof Integer) {
                        return (Integer) value > 0;
                    } else {
                        // Xử lý các kiểu dữ liệu khác nếu cần
                        return false;
                    }
                })
                .toArray(Object[][]::new);


        setData(filteredData);
        // Thêm dữ liệu mới vào bảng
        for (Object[] rowData : filteredData) {
            model.addRow(rowData);
        }
        // Thông báo cho bảng biết rằng dữ liệu đã thay đổi để nó vẽ lại giao diện
        model.fireTableDataChanged();
    }
}
