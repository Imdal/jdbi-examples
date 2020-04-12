package user;
import java.time.LocalDate;

@Builder
@Data
public class User {


    public static enum Gender {
        FEMALE,
        MALE
    }

    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Gender gender;
    private LocalDate dob;
    private boolean enabled;

}