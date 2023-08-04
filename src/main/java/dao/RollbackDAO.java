package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.util.Stack;

public class RollbackDAO {
    private static Stack<Savepoint> savepointStack = new Stack<>();

    public static Savepoint creatRollback() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Connection connection = null;
        Savepoint savepoint = null;
        try {
            connection = entityManager.unwrap(Connection.class);
            connection.setAutoCommit(false);

            // Thực hiện các thao tác cơ sở dữ liệu

            // Tạo điểm rollback cho giao dịch
            savepoint = connection.setSavepoint();
            savepointStack.push(savepoint);

            connection.commit(); // Kết thúc giao dịch, không thực hiện rollback
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback toàn bộ giao dịch nếu có lỗi
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return savepoint;
    }

    public static void rollbackToLastSavepoint() {
        if (!savepointStack.isEmpty()) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Connection connection = null;
            try {
                connection = entityManager.unwrap(Connection.class);
                connection.setAutoCommit(false);

                connection.rollback(savepointStack.pop()); // Rollback đến savepoint gần nhất và loại bỏ nó khỏi stack

                connection.commit();
            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback(); // Rollback toàn bộ giao dịch nếu có lỗi
                    } catch (Exception rollbackException) {
                        rollbackException.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception closeException) {
                        closeException.printStackTrace();
                    }
                }
                entityManager.close();
                entityManagerFactory.close();
            }
        } else {
            System.out.println("Không có điểm rollback để rollback.");
        }
    }
}

