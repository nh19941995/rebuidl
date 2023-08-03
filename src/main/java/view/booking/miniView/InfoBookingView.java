package view.booking.miniView;
import controller.BookingController;
import dao.PersonDAO;
import model.Person;
import view.Tool.Grid;
import javax.swing.*;
import java.awt.*;


public class InfoBookingView extends JPanel {
    private static JLabel labelFirstName = new JLabel("Fist name Client :");
    private static JLabel labelFirstNameValue = new JLabel();
    private static JLabel labelLastName = new JLabel("Last name Client :");
    private static JLabel labelLastNameValue = new JLabel();
    private JLabel labelDeposit = new JLabel("Deposit         : ");
    private JLabel labelStartTime = new JLabel("Start time      : ");
    private JLabel labelEndTime = new JLabel("End time        : ");
    private JLabel labelComment = new JLabel("Comment         : ");
    private static JTextField inputStartTime = new JTextField();
    private static JTextField inputDeposit = new JTextField();
    private static JTextField inputEndTime = new JTextField();
    private static JTextField inputComment = new JTextField();

    public static JTextField getInputComment() {
        return inputComment;
    }

    public static JTextField getInputStartTime() {
        return inputStartTime;
    }

    public static void setInputStartTime(JTextField inputStartTime) {
        InfoBookingView.inputStartTime = inputStartTime;
    }

    public static JTextField getInputDeposit() {
        return inputDeposit;
    }

    public static void setInputDeposit(JTextField inputDeposit) {
        InfoBookingView.inputDeposit = inputDeposit;
    }

    public static JTextField getInputEndTime() {
        return inputEndTime;
    }

    public static void setInputEndTime(JTextField inputEndTime) {
        InfoBookingView.inputEndTime = inputEndTime;
    }

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
        grid.GridAddCustom(labelFirstName,0,0,0,0,5,5,1);
        grid.GridAddCustom(labelFirstNameValue,1,0,0,0,5,5,1);

        grid.GridAddCustom(labelLastName,0,1,0,0,5,5,1);
        grid.GridAddCustom(labelLastNameValue,1,1,0,0,5,5,1);

        grid.GridAddCustom(labelDeposit,0,2,0,0,5,5,1);
        grid.GridAddCustom(inputDeposit,1,2,0,0,5,5,1);
        inputDeposit.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelStartTime,0,3,0,0,5,5,1);
        grid.GridAddCustom(inputStartTime,1,3,0,0,5,5,1);
        inputStartTime.setPreferredSize(new Dimension(200, 20));

        grid.GridAddCustom(labelEndTime,0,4,0,0,5,5,1);
        grid.GridAddCustom(inputEndTime,1,4,0,0,5,5,1);
        inputEndTime.setPreferredSize(new Dimension(200, 20));

        jPanel.add(grid,BorderLayout.CENTER);
        return  jPanel;
    }

    public static void reloadJpanel(){
        int id = BookingController.getPersonIdSelect();
        Person person = PersonDAO.getInstance().getById(id);
        labelFirstNameValue.setText(person.getName()); // Đặt giá trị cho JLabel
        labelLastNameValue.setText(person.getLastName()); // Đặt giá trị cho JLabel
        labelFirstNameValue.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
        labelLastNameValue.repaint(); // Thông báo cho nhãn vẽ lại để hiển thị nội dung mới
    }
}
