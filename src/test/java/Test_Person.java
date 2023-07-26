import dao.PersonDAO;
import model.Person;

import java.util.List;

public class Test_Person {
    public static void main(String[] args) {
        List<Person> personList = PersonDAO.getInstance().getAll();
        for (Person item: personList){
            System.out.println(item.toString());
        }
    }
}
