import dao.*;
import model.*;

import java.util.List;

public class Test_Booking {
    public static void main(String[] args) {
//        tạo đối tượng
//        Booking booking = new Booking(
//                BookingsInfoDAO.getInstance().getById(1),
//                TableListDAO.getInstance().getById(1),
//                MenuNameDAO.getInstance().getById(1),
//                1);
//        đẩy vào dữ liệu
//        BookingDAO.getInstance().insert(booking);
//        lấy ra toàn bộ
        List<Booking> bookingList = BookingDAO.getInstance().getAll();
        bookingList.stream().forEach(s-> {
            System.out.println("---------------------------------------------------------------");
            System.out.println("ID    : "+s.getTable().getId());
            System.out.println("Số ghế: "+s.getTable().getSeatingCapacity());
            System.out.println("Type  : "+s.getTable().getType().getName());
            System.out.println("Start : "+s.getInfo().getStart());
            System.out.println("End   : "+s.getInfo().getEnd());
            System.out.println("---------------------------------------------------------------");


        });
//        for (Booking item: bookingList){
//            System.out.println(item.toString());
//        }

//        Booking booking =  BookingDAO.getInstance().getById(1);
//        booking.toString();
    }

    public static class Test_Person {
    }
}
