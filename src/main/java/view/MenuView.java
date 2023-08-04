package view;

import view.Tool.Grid;
import view.booking.miniView.DishListView;
import view.booking.miniView.MenuListView;
import view.booking.miniView.NewMenuListView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuView extends JPanel {
    private JTable table = new JTable();
    private DefaultTableModel tableModel;
    private Object[][] data;
    private int width;
    private int height;



    public MenuView() {
        setLayout(new BorderLayout());
        // lấy kích thước jpanel chính
        width = getPreferredSize().width;
        height = getPreferredSize().height;

        add(menuBlock(),BorderLayout.WEST );
        add(dishListBlock(),BorderLayout.CENTER );
    }

    private JPanel menuBlock(){
        JPanel a = new JPanel();


        Dimension preferredSize = new Dimension((width/2), a.getPreferredSize().height);
        a.setPreferredSize(preferredSize);


        a.setLayout(new BorderLayout());
        JPanel menu = new NewMenuListView();
        Grid grid = new Grid();
        grid.GridAddCustom(menu,0,0,0,0,0,0,1);
        a.add(grid,BorderLayout.CENTER);
        return menu;


    }

    private JPanel dishListBlock(){
        JPanel a = new JPanel();
        a.setLayout(new BorderLayout());
        JPanel dishList = new DishListView();


        Grid grid = new Grid();
        grid.GridAddCustom(dishList,0,0,0,0,0,0,1);
        a.add(grid,BorderLayout.CENTER);
        return dishList;
    }
}
