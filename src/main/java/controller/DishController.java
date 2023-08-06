package controller;

import dao.DishDAO;
import dao.DishTypeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Dish;
import model.DishType;
import view.booking.miniView.DishView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;

public class DishController {



    public static void selectEven(DishView dish) {
        JButton buttonAddNew =  dish.getButtonCreatNewDish();
        JButton buttonSearch =  dish.getButtonSeach();
        JComboBox<String> SelecType = dish.getSelecType();
        JComboBox<String> SelecTypeForNewDish = dish.getSelecTypeForNewDish();

        JTable table = dish.getTable();

        // sự kiện click vào bảng
        buttonAddNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Kiểm tra nếu chỉ là một lần click chuột (clickCount = 1)
                    int row = table.getSelectedRow(); // Lấy chỉ số dòng đã được chọn
                    if (row != -1) { // Kiểm tra xem có dòng nào được chọn không (-1 nghĩa là không có dòng nào được chọn)
                        String id = table.getValueAt(row, 0).toString(); // Lấy giá trị từ ô ở cột đầu tiên (cột ID) của dòng đã chọn
                        dish.setIdSelectDish(Integer.parseInt(id));
                        System.out.println("Bảng DishView đang chọn hàng có id là: "+ id);
                    }else {
                        // thông bào khi không có dòng nào dc chọn
                        JOptionPane.showMessageDialog(null,"You have not selected any dish in the table", "Notice", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });






    }

    public static void creatNewDish(DishView dish){
        JButton buttonAddNew =  dish.getButtonCreatNewDish();
        JTextField inputName = dish.getInputNewDishName();
        JTextField inputPrice = dish.getInputReferencePrice();
        JTextField inputComment = dish.getInputComment();

        // sự kiện click vào bảng
        buttonAddNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = inputName.getText();
                String priceString = inputPrice.getText();
                String comment = inputComment.getText();
                String typeString = (String) dish.getSelecTypeForNewDish().getSelectedItem();
                int check = 1;
                if (name.isEmpty()||priceString.isEmpty()){
                    if (check==1){
                        JOptionPane.showMessageDialog(null, "You must fill in all the required information before proceeding to create a new dish !", "Notice", JOptionPane.WARNING_MESSAGE);
                    }
                    check =0;
                }
                if (!priceString.equals("")){
                    if (!RegexMatcher.numberCheck(priceString,"").equals("")){
                        if (check ==1){
                            JOptionPane.showMessageDialog(null, RegexMatcher.numberCheck(priceString, "Reference: "), "Notice", JOptionPane.WARNING_MESSAGE);
                        }
                        check = 0;
                    }
                }
                if(check == 1){
                    Double price = Double.parseDouble(priceString);
                    Instant instant = InstantDateTimeInfo.getNow();
                    Dish dish = new Dish();
                    dish.setType(DishTypeDAO.getInstance().getByString(typeString));
                    dish.setFlag(1);
                    dish.setDishName(name);
                    dish.setComment(comment);
                    dish.setDateCreat(instant);
                    dish.setPrice(price);
                    DishDAO.getInstance().insert(dish);
                }
            }
        });
        dish.reloadData();

    }
}
