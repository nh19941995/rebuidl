package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
//import model.DishType;
import model.DishType;
import model.TableList;
import utils.PersistenceManager;

import java.util.ArrayList;

public class TableListDAO implements DAOInterface<TableList,Integer>{
    private EntityManagerFactory entityManagerFactory;


    public static TableListDAO getInstance(){
        return new TableListDAO();
    }

    public TableListDAO() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    @Override
    public boolean insert(TableList tableList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(tableList);
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
    public int update(TableList tableList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(tableList);
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
    public ArrayList<TableList> getAll() {
        //        Với truy vấn đọc (SELECT), bạn không cần bắt EntityTransaction.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // Sử dụng JPQL (Java Persistence Query Language) để truy vấn danh sách DishType
            String queryStr = "SELECT d FROM TableList d";
            ArrayList<TableList> a  =  new ArrayList<>(entityManager.createQuery(queryStr, TableList.class).getResultList());
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
    public TableList getById(int tableId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TableList tableList = entityManager.find(TableList.class, tableId);
            return tableList;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
