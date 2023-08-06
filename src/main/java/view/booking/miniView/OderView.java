package view.booking.miniView;

import javax.swing.*;
import java.awt.*;

public class OderView extends JPanel {
    public OderView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new BillView());
        add(new DishListView());
    }
}
