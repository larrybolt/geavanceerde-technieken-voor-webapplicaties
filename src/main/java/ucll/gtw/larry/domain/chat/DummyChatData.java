package ucll.gtw.larry.domain.chat;

import ucll.gtw.larry.domain.user.UserRepository;

import java.time.LocalDateTime;

public class DummyChatData {
    public static void addData(MessageRepository messageRepository) {
        messageRepository.add(3, 2, "hello");
        messageRepository.add(2, 3, "Hi! How can I help you?");
    }
}
