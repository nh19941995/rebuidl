package controller;

import dao.DishDAO;
import dao.PersonDAO;
import model.Oder;
import model.OderInfo;
import model.Person;
import view.booking.miniView.DishListView;
import view.booking.miniView.OderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OderController {
    Person person = PersonDAO.getInstance().getById(BookingController.getPersonIdSelect());
    OderInfo oderInfo = new OderInfo();
//    private static JButton buttonSelectDish= DishListView.getButtonAddToNewMenu();

//    private static int inputEnterNumber = Integer.parseInt(DishListView.getInputEnterNumber().getText()) ;
//    private static Double inputPrice = Double.parseDouble(DishListView.getInputEnterPrice().getText()) ;


    public OderController() {
        // tạo oderInfo
        oderInfo.setPerson(person);
        oderInfo.setTime(InstantDateTimeInfo.getNow());
        oderInfo.setFlag(1);

//        OderView.getButtonSelectDish().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // kiểm tra đã chọn person chưa
////                OderInfoDAO.getInstance().insert(oderInfo);
//                // tạo oder
//                int dishId = Integer.parseInt(DishListView.getDishIdSelect());
//                Oder oder = new Oder();
//                oder.setDish(DishDAO.getInstance().getById(dishId));
//                oder.setBill(oderInfo);
//                oder.setFlag(1);
////                oder.setPrice(inputPrice);
////                oder.setQuantity(inputEnterNumber);
//                System.out.println(" oder hahahah");
//            }
//        });

    }
}
