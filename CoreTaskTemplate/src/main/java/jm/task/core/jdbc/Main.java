package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {
        //UserService user = new UserServiceImpl();
        UserDaoHibernateImpl user = new UserDaoHibernateImpl();

        user.createUsersTable();

        user.saveUser("Ivan", "Ivanov", (byte) 35);
        user.saveUser("Sergey", "Petrov", (byte) 19);
        user.saveUser("Anatoliy", "Sidorov", (byte) 21);
        user.saveUser("Anna", "Alekseeva", (byte) 19);

        for (User elm : user.getAllUsers()) {
            System.out.println(elm);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
