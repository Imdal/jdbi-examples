package user;

import ex9.LegoSet;
import ex9.LegoSetDao;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();
            User user = User.builder().id(6900345).username("imdal").password("password").name("Imre Dalma").email("imdal@mailbox.unideb.hu").dob(LocalDate.parse("1999-12-20")).gender(User.Gender.FEMALE).enabled(true);
            dao.insertUser(user);
            User user2 = User.builder().id(5654231).username("username").password("password").name("name").email("email").dob(LocalDate.parse("2020-04-11")).gender(User.Gender.MALE).enabled(true);
            dao.insertUser(user2);
            dao.findByID((long) 5654231)
            dao.findByUsername("imdal");
            dao.delete(user2);
            dao.list().stream().forEach(System.out::println);

        }


    }
}
