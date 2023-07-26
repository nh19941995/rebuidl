package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomizeTableAppearance {
    public CustomizeTableAppearance(JTable jTable1) {
        // Tạo một đối tượng DefaultTableCellRenderer để tùy chỉnh cách hiển thị của các ô trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Căn giữa chữ trong tất cả các cột của bảng
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh kích thước cột tuỳ theo nội dung trong ô
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
}
