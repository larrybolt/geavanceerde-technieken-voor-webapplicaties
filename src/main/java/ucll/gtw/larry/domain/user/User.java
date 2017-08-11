package ucll.gtw.larry.domain.user;

import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data public class User {
    private int userId;
    @NonNull private String userName;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String email;
    @NonNull private Gender gender;
    @NonNull private Role role;
    @NonNull private LocalDate dateOfBirth;
    private LocalDateTime lastOnline = java.time.LocalDateTime.now();
    // hashed password with salt, see https://en.wikipedia.org/wiki/Bcrypt
    @Setter(AccessLevel.PACKAGE) private transient String hashedPassword;

    private static int BCRYPT_LOG_ROUNDS = 12;

    protected void hashAndSetPassword(@NonNull String password) {
        if (password.length() < 4) {
            throw new IllegalArgumentException("Too short password to hash!");
        }
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS));
        setHashedPassword(hashed);
    }

    public boolean isValidPassword(@NonNull String password) {
        if (getHashedPassword() == null) {
            return false;
        }
        return BCrypt.checkpw(password, getHashedPassword());
    }

    public void wasOnline(){
        this.lastOnline = java.time.LocalDateTime.now();
    }
}
