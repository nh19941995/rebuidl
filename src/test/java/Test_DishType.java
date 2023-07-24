import dao.DishTypeDAO;
import model.DishType;

import java.util.List;

public class Test_DishType {
    public static void main(String[] args) {
//        thêm mới đối tượng vào bảng (không truyền id vào đối tượng nếu không sẽ bị lỗi, bảng sẽ tự thêm id)
//        DishTypeEntityDAO.getInstance().insert(new DishTypeEntity("test",1));

//        lấy ra toàn bộ bảng và tả về List
        DishTypeDAO dao = new DishTypeDAO();

        List<DishType> dishTypes = dao.getAll();
        for (DishType item: dishTypes){
            System.out.println(item.toString());
        }

//        lấy ra đối tượng qua id
//        System.out.println("phần tử có id = 2 là: "+ DishType_DAO.getInstance().getById(2).toString());
//        update
//        Set<Dish> dishes= new LinkedHashSet<>();
//        DishType_DAO.getInstance().update(7,new DishType("test 2",1,dishes));
    }
}
