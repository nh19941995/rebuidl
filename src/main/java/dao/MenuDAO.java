package dao;

import jakarta.persistence.*;
import model.Menu;
import model.MenuName;
import utils.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class MenuDAO implements DAOInterface<Menu,Integer>{
    private EntityManagerFactory entityManagerFactory;

    public static MenuDAO getInstance(){
        return new MenuDAO();
    }
    public MenuDAO() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    @Override
    public boolean insert(Menu menu) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(menu);
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
    public int update(Menu menu) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(menu);
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
    public ArrayList<Menu> getAll() {
        //        Với truy vấn đọc (SELECT), bạn không cần bắt EntityTransaction.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // Sử dụng JPQL (Java Persistence Query Language) để truy vấn danh sách DishType
            String queryStr = "SELECT d FROM Menu d";
            ArrayList<Menu> a  =  new ArrayList<>(entityManager.createQuery(queryStr, Menu.class).getResultList());
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Menu getById(int menuId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Menu.class, menuId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public List<Menu> getMenuByMenuNameID(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String queryStr = "SELECT m FROM Menu m WHERE m.menuName = :id";
            TypedQuery<Menu> query = entityManager.createQuery(queryStr, Menu.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public Double getTotalPriceByMenuNameID(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String queryStr = "SELECT SUM(m.quantity * m.unitPrice) FROM Menu m WHERE m.menuName.id = :id";
            TypedQuery<Double> query = entityManager.createQuery(queryStr, Double.class);
            query.setParameter("id", id);
            Double totalPrice = query.getSingleResult();
            return totalPrice != null ? totalPrice : 0.0; // Trả về 0.0 thay vì 0 nếu không có kết quả
        } catch (NoResultException e) {
            return 0.0; // Trả về 0.0 nếu không có kết quả
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
            entityManager.close();
        }
    }






}
