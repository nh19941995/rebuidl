//import dao.DishTypeDAO;
//import model.Dish;
//import model.DishType;
//
//import java.time.Instant;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//public class Test_DishType {
//    public static void main(String[] args) {
//
//        //            tạo mới many
//        Set<Dish> dishes= new LinkedHashSet<>();
//        Dish dish = new Dish();
//        //            thiết lập mối quan hệ và thêm dữ liệu cho bên many
//        dish.setType(new DishType());
//        dish.setDishName("cá kho 3");
//        Instant now = Instant.now();
//        dish.setDateCreat(now);
//        dish.setPrice(500);
//        dish.setFlag(2);
//
////        tạo mới one
//        DishType type = new DishType();
//        type.setType("món chính");
//        type.setFlag(1);
//        type.add(dish);
////            dish.setType(1);
//        entityManager.persist(type);
//
//
////        thêm mới đối tượng vào bảng (không truyền id vào đối tượng nếu không sẽ bị lỗi, bảng sẽ tự thêm id)
////        DishTypeEntityDAO.getInstance().insert(new DishTypeEntity("test",1));
//
////        lấy ra toàn bộ bảng và tả về List
//        DishTypeDAO dao = new DishTypeDAO();
//
//        List<DishType> dishTypes = dao.getAll();
//        for (DishType item: dishTypes){
//            System.out.println(item.toString());
//        }
//
////        lấy ra đối tượng qua id
////        System.out.println("phần tử có id = 2 là: "+ DishType_DAO.getInstance().getById(2).toString());
////        update
////        Set<Dish> dishes= new LinkedHashSet<>();
////        DishType_DAO.getInstance().update(7,new DishType("test 2",1,dishes));
//    }
//}
