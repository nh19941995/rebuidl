package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.DishType;
import utils.PersistenceManager;

import java.util.List;


public class DishDAO {

    private EntityManagerFactory entityManagerFactory;

    public static DishDAO getInstance(){
        return new DishDAO();
    }
    public DishDAO() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public DishType createDishType(DishType dishType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(dishType);
            transaction.commit();
            return dishType;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public DishType updateDishType(DishType dishType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(dishType);
            transaction.commit();
            return dishType;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void deleteDishType(int dishTypeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            DishType dishType = entityManager.find(DishType.class, dishTypeId);
            if (dishType != null) {
                entityManager.remove(dishType);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public DishType getDishTypeById(int dishTypeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(DishType.class, dishTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public List<DishType> getAllDishTypes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String queryStr = "SELECT d FROM DishType d";
            return entityManager.createQuery(queryStr, DishType.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }
}
