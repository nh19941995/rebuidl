package view.booking.miniView;

import controller.OderController;

import javax.swing.*;
import java.awt.*;

public class OderView extends JPanel {
    DishListView dishListView = new DishListView();
    BillView billView = new BillView();

    public OderView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        dishListView.selectDish();
        add(billView);
        add(dishListView);
        OderController oderController = new OderController();

    }
}
