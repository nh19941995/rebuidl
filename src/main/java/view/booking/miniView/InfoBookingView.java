package view.booking.miniView;

import dao.PersonDAO;
import model.Person;
import view.Tool.Grid;

import javax.swing.*;
import java.awt.*;

public class InfoBookingView extends JPanel {
    private static JLabel labelFirstName = new JLabel("Fist name Client: ");
    private static JLabel labelFirstNameValue = new JLabel("value");

    private static JLabel labelLastName = new JLabel("Last name Client: ");
    private static JLabel labelLastNameValue = new JLabel("value");

    private JLabel labelDeposit = new JLabel("Deposit: ");
    private JLabel labelStartTime = new JLabel("Start time: ");
    private JLabel labelEndTime = new JLabel("End time: ");
    private JTextField inputStartTime = new JTextField();
    private JTextField inputDeposit = new JTextField();
    private JTextField inputEndTime = new JTextField();

    public InfoBookingView() {
        setLayout(new BorderLayout());
        add(block(),BorderLayout.CENTER);
        setBackground(Color.green);
    }

    public JPanel block(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.red);
        Grid grid = new Grid();
        grid.GridAddCustom(labelFirstName,0,0,0,10,5,5,1);
        grid.GridAddCustom(labelFirstNameValue,1,0,10,10,5,5,1);

        grid.GridAddCustom(labelLastName,0,1,0,7,5,5,1);
        grid.GridAddCustom(labelLastNameValue,1,1,7,10,5,5,1);

        grid.GridAddCustom(labelDeposit,0,2,47,10,5,5,1);
        grid.GridAddCustom(inputDeposit,1,2,10,10,5,5,1);
        inputDeposit.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelStartTime,0,3,35,10,5,5,1);
        grid.GridAddCustom(inputStartTime,1,3,10,10,5,5,1);
        inputStartTime.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelEndTime,0,4,38,10,5,5,1);
        grid.GridAddCustom(inputEndTime,1,4,10,10,5,5,1);
        inputEndTime.setPreferredSize(new Dimension(200, 20));

        jPanel.add(grid,BorderLayout.CENTER);
        return  jPanel;
    }

    public void reloadJpanel(){
        int id = Integer.parseInt(ClientListView.getIdSelectInTable());
        Person person = PersonDAO.getInstance().getById(id);
        labelFirstNameValue.setText(person.getName()); // Đặt giá trị cho JLabel
        labelLastNameValue.setText(person.getLastName()); // Đặt giá trị cho JLabel

    }





}
