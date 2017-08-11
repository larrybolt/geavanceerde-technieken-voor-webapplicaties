package ucll.gtw.larry.domain.chat;

import lombok.Data;
import lombok.NonNull;
import ucll.gtw.larry.domain.user.User;

import java.time.LocalDateTime;

@Data public class Message implements Comparable<Message> {
    private int MessageId;
    @NonNull private User from;
    @NonNull private User to;
    @NonNull private String Message;
    @NonNull private LocalDateTime datetime;

    @Override
    public int compareTo(Message Message) {
        return Message.getMessageId()-this.getMessageId();
    }
}
