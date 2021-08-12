package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static jm.task.core.jdbc.util.Util.createSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = "create table if not exists users (" +
            "id bigint not null auto_increment, " +
            "name varchar(32) not null, " +
            "lastName varchar(32) not null, " +
            "age tinyint(3) not null, " +
            "primary key (id))";
    private static final String DROP_TABLE = "drop table if exists users";
    private static final String GET_ALL = "from User";
    private static final String CLEAN_TABLE = "delete from users";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE)
                    .executeUpdate();
            session.getTransaction()
                    .commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE)
                    .executeUpdate();
            session.getTransaction()
                    .commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction()
                    .commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction()
                    .commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<User> result = session.createQuery(GET_ALL)
                    .list();
            session.getTransaction()
                    .commit();
            return result;
        } finally {
            factory.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = createSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(CLEAN_TABLE)
                    .executeUpdate();
            session.getTransaction()
                    .commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
