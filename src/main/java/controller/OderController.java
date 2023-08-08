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



    public OderController() {
        // tạo oderInfo
        oderInfo.setPerson(person);
        oderInfo.setTime(InstantDateTimeInfo.getNow());
        oderInfo.setFlag(1);



    }
}
