import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
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
            Set<Dish> dishes= new LinkedHashSet<>();
            Dish dish = new Dish();
//            thiết lập mối quan hệ và thêm dữ liệu cho bên many
            dish.setType(new DishType());
            dish.setDishName("cá kho 3");
            Instant now = Instant.now();
            dish.setDateCreat(now);
            dish.setPrice(500);
            dish.setFlag(2);

            DishType type = new DishType();
            type.setType("món chính");
            type.setFlag(1);
            type.add(dish);
//            dish.setType(1);
            entityManager.persist(type);
            transaction.commit();
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
