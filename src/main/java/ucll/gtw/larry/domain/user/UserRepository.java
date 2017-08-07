package ucll.gtw.larry.domain.user;

import lombok.NonNull;
import lombok.val;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> users = new HashMap<Integer, User>();

    public UserRepository() {
        this.createUser(
                new User("user",
                        "simple", "user",
                        "user@example.com",
                        Gender.FEMALE, Role.USER,
                        LocalDate.of(1992, Month.MARCH, 12)
                ),
                "user" // password
        );
        this.createUser(
                new User("support",
                        "support", "user",
                        "support@example.com",
                        Gender.MALE, Role.SUPPORT,
                        LocalDate.of(1993, Month.MARCH, 12)
                ),
                "support" // password
        );
        this.createUser(
                new User("admin",
                        "admin", "user",
                        "admin@example.com",
                        Gender.FEMALE, Role.ADMIN,
                        LocalDate.of(1994, Month.MARCH, 12)
                ),
                "admin" // password
        );
    }

    public void createUser(User user, String password) {
        for (User u : users.values()) {
            if (u.getUserName().equals(user.getUserName())) {
                throw new IllegalArgumentException("Username already in use");
            }
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email already in use");
            }
        }
        val userId = users.size();
        user.setUserId(userId);
        user.hashAndSetPassword(password);
        users.put(userId, user);
    }

    public User get(int userId) {
        return users.get(userId);
    }

    public List<User> getAll() {
        return (List<User>) users.values();
    }

    public User loginUser(@NonNull String username, @NonNull String password) throws InvalidLogin {
        for (User u : users.values()) {
            if (u.getEmail().equals(username) || u.getUserName().equals(username)) {
                if (u.isValidPassword(password)) {
                    return u;
                } else {
                    throw new InvalidLogin("Invalid password");
                }
            }
        }
        throw new InvalidLogin("Invalid username");
    }

    public void update(User user) {
        users.replace(user.getUserId(), user);
    }
}
