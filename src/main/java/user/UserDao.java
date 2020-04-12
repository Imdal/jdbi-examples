package user;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMapper(User.class)
public interface UserDao {
            @SqlUpdate("""
                    CREATE TABLE user (
                    	id VARCHAR PRIMARY KEY,
                    	username VARCHAR NOT NULL,
                    	password VARCHAR NOT NULL,
                    	name VARCHAR,
                    	email VARCHAR, 
                    	gender VARCHAR,
                    	dob DATE,
                    	enabled BOOLEAN
                    )
                    """
            )
    public void createTable();

    @SqlUpdate("INSERT INTO user (id,username,password,name,email,gender,dob,enabled) VALUES (:id, :username,:password,:name,:email,:gender,:dob,:enabled)")
    @GetGeneratedKeys
    Long insertUser(@Bind("id") Long id, @Bind("username") String username, @Bind("password") String password, @Bind("name") String name, @Bind("email") String email, @Bind("gender") User.Gender gender,@Bind("dob") LocalDate dob,@Bind("enabled") boolean enabled);

    @SqlUpdate("INSERT INTO user (id,username,password,name,email,gender,dob,enabled) VALUES (:id, :username,:password,:name,:email,:gender,:dob,:enabled)")
    @GetGeneratedKeys
    long insertUser(@BindBean User user);


    @SqlQuery("SELECT * FROM user WHERE id = :id")
    Optional<User> findByID(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    Optional<User> findByUsername(@Bind("username") String username);

    @SqlUpdate("DELETE FROM user (id,username,password,name,email,gender,dob,enabled) VALUES (:id, :username,:password,:name,:email,:gender,:dob,:enabled)")
    void delete(@BindBean User user);


    @SqlQuery("SELECT * FROM user ORDER BY id")
    List<User> list();

}
