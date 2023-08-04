package controller;

import dao.PersonDAO;
import dao.TransactionDAO;
import dao.TransactionsTypeDAO;
import model.Person;
import model.Transaction;
import view.booking.miniView.TransactionListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TransactionController {
    private static JButton buttonNewTransaction = TransactionListView.getButtonCreatTransaction();

    public TransactionController() {
        creatTransaction();
    }
    public static void creatTransaction(){
        buttonNewTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = PersonDAO.getInstance().getById(BookingController.getPersonIdSelect());
                Transaction newTransaction = new Transaction();
                Double value = Double.parseDouble(TransactionListView.getInputValue().getText());
                String type = (String) TransactionListView.getSelecType().getSelectedItem();
                String comment = TransactionListView.getInputComment().getText();
                String day = TransactionListView.getInputDate().getText();
                String hour = TransactionListView.getInputTime().getText();
                Instant instant = InstantDateTimeInfo.getByDayAndHour(day,hour);
                newTransaction.setPerson(person);
                newTransaction.setQuantity(value);
                newTransaction.setComment(comment);
                newTransaction.setType(TransactionsTypeDAO.getInstance().getByName(type));
                newTransaction.setDateCreat(instant);
                newTransaction.setFlag(1);
                TransactionDAO.getInstance().insert(newTransaction);
            }
        });
    }

}
