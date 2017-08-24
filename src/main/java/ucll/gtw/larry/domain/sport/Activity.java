package ucll.gtw.larry.domain.sport;

import lombok.Data;
import lombok.NonNull;
import ucll.gtw.larry.domain.user.User;

import java.time.LocalDateTime;

@Data public class Activity {
    private int ActivityId;
    @NonNull private String Day;
    @NonNull private String Time;
    @NonNull private String Sport;
    @NonNull private String Name;
    @NonNull private String Location;

}
