import dao.PersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Booking;
import model.BookingsInfo;
import model.Dish;
import model.DishType;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
//            tạo mới many
//            Set<Dish> dishes= new LinkedHashSet<>();
//            Dish dish = new Dish();

//            tạo mới
            // Tạo Instant từ giá trị thời gian hiện tại
//            Instant nowx = Instant.now();
//            Instant now1 = nowx.plusSeconds(3600);
//            Instant now2 = nowx.plusSeconds(3900);
//            thêm dữ liệu vào
//            BookingsInfo info = new BookingsInfo("adsasd",nowx,now1,now2,10000f,1);
//            Booking booking = new Booking();
//            info.setPerson(PersonDAO.getInstance().getById(1));
//            booking.setFlag(1);
//            info.add(booking);





//            thiết lập mối quan hệ và thêm dữ liệu cho bên many
//            dish.setType(new DishType());
//            dish.setDishName("bánh ngọt");
//            Instant now = Instant.now();
//            dish.setDateCreat(now);
//            dish.setPrice(500);
//            dish.setFlag(2);
//
//            DishType type = new DishType();
//            type.setType("món khai vị");
//            type.setFlag(1);
//            type.add(dish);
//            dish.setType(1);
//            entityManager.persist(info);
//            transaction.commit();
        }finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

//    private static EntityManagerFactory entityManagerFactory;
//
//    public static EntityManager getEntityManager() {
//        if (entityManagerFactory == null) {
//            entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        }
//        return entityManagerFactory.createEntityManager();
//    }
}
