import dao.DishTypeDAO;
import dao.TableListDAO;
import model.DishType;
import model.TableList;

import java.util.List;

public class Test_TableList {
    public static void main(String[] args) {
//        TableListDAO.getInstance().insert()
//        TableList tableList = TableListDAO.getInstance().getById(1);
//        System.out.println(tableList.getType().getName());
//        tableList.toString();

        List<TableList> tableLists = TableListDAO.getInstance().getAll();
//        for (TableList item: tableLists){
//            System.out.println(item.toString());
//        }
    }
}
