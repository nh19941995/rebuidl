package view.booking.miniView;

import controller.OderController;

import javax.swing.*;
import java.awt.*;

public class OderView extends JPanel {
    DishView dishView = new DishView();
    BillView billView = new BillView();

    public OderView() {
        setLayout(new BorderLayout());


        // Xóa childPane khỏi parentPanel
//        dishView.remove(blockAddNewDish());

        // Cập nhật giao diện người dùng
        dishView.revalidate();
        dishView.repaint();
        add(billView,BorderLayout.WEST);
        add(dishView,BorderLayout.CENTER);

        OderController oderController = new OderController();

    }
}
