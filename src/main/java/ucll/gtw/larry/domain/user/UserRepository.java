package ucll.gtw.larry.domain.user;

import lombok.val;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> users = new HashMap<Integer, User>();

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


    public void update(User user) {
        users.replace(user.getUserId(), user);
    }
}
