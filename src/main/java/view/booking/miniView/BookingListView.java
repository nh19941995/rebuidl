package view.booking.miniView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingListView extends JPanel {
    private Object lockObject = new Object();
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private JButton buttonAddToNewBooking  = new JButton("Add to list ");
    private JButton buttonremoveFromNewBooking = new JButton("Remove from list");
    private JLabel BookingList = new JLabel("New booking list");

    public BookingListView() {
        setLayout(new BorderLayout());
        setBackground(Color.cyan);
        // Khởi tạo bảng với mô hình dữ liệu trống
        table.setModel(new DefaultTableModel());
    }
}
