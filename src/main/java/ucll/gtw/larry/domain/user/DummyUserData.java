package ucll.gtw.larry.domain.user;

import java.time.LocalDate;
import java.time.Month;

public class DummyUserData {
    public static void addData(UserRepository userRepository) {
        userRepository.createUser(
                new User("admin",
                        "admin", "user",
                        "admin@example.com",
                        Gender.FEMALE, Role.ADMIN,
                        LocalDate.of(1994, Month.MARCH, 12)
                ),
                "admin" // password
        );
        userRepository.createUser(
                new User("support",
                        "support", "user",
                        "support@example.com",
                        Gender.MALE, Role.SUPPORT,
                        LocalDate.of(1993, Month.MARCH, 12)
                ),
                "support" // password
        );
        userRepository.createUser(
                new User("user",
                        "simple", "user",
                        "user@example.com",
                        Gender.FEMALE, Role.USER,
                        LocalDate.of(1992, Month.MARCH, 12)
                ),
                "user" // password
        );
        userRepository.createUser(
                new User("user2",
                        "simple2", "user",
                        "user2@example.com",
                        Gender.MALE, Role.USER,
                        LocalDate.of(1992, Month.MARCH, 12)
                ),
                "user2" // password
        );
    }
}
