package dao;

import controller.InstantDateTimeInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import model.DishType;
import model.MenuName;
import utils.PersistenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MenuNameDAO implements DAOInterface<MenuName,Integer>{
    private EntityManagerFactory entityManagerFactory;

    public static MenuNameDAO getInstance(){
        return new MenuNameDAO();
    }
    public MenuNameDAO() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    @Override
    public boolean insert(MenuName menuName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(menuName);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int update(MenuName menuName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(menuName);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ArrayList<MenuName> getAll() {
        //        Với truy vấn đọc (SELECT), bạn không cần bắt EntityTransaction.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // Sử dụng JPQL (Java Persistence Query Language) để truy vấn danh sách DishType
            String queryStr = "SELECT d FROM MenuName d";
            ArrayList<MenuName> a  =  new ArrayList<>(entityManager.createQuery(queryStr, MenuName.class).getResultList());
//            a.stream().forEach(s-> System.out.println(s.toString()) );
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public MenuName getById(int menuNameId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(MenuName.class, menuNameId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static MenuName getByStringName( String name) {
        List<MenuName> menuNames = MenuNameDAO.getInstance().getAll();
        // Tạo luồng dữ liệu từ danh sách menuNames
        Optional<MenuName> menuNameOptional = menuNames.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst();

        // Nếu không tìm thấy, tạo mới đối tượng và lấy về ID
        return menuNameOptional.orElseGet(() -> {
            MenuName newMenu = new MenuName();
            newMenu.setName(name);
            newMenu.setFlag(1);
            newMenu.setDateCreat(InstantDateTimeInfo.getNow());
            newMenu.setDateUpdate(InstantDateTimeInfo.getNow());
            MenuNameDAO.getInstance().insert(newMenu);
            return newMenu;
        });
    }
}
