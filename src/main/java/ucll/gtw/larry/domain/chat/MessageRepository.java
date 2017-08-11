package ucll.gtw.larry.domain.chat;

import lombok.Getter;
import lombok.NonNull;
import ucll.gtw.larry.domain.user.Role;
import ucll.gtw.larry.domain.user.User;
import ucll.gtw.larry.domain.user.UserRepository;

import java.util.*;

public class MessageRepository {
    private Map<Integer, Message> messages = new TreeMap<>();
    private Map<Integer, Integer> userToSupportMap = new TreeMap<>();
    @Getter private UserRepository userRepository;

    public MessageRepository(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // C
    public void add(Message message) {
        int messageId = messages.size()+1;
        message.setMessageId(messageId);
        messages.put(message.getMessageId(), message);
    }

    public void add(int fromUserId, int toUserId, @NonNull String messagetext) {
        User fromUser = getUserRepository().get(fromUserId);
        User toUser = getUserRepository().get(toUserId);
        Message message = new Message(fromUser, toUser, messagetext, java.time.LocalDateTime.now());
        add(message);
    }

    // R
    public Message get(int messageId) {
        return messages.get(messageId);
    }

    public ArrayList<Message> getAll() {
        return new ArrayList<>(messages.values());
    }

    public ArrayList<Message> getFrom(int from) {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message p : this.messages.values()) {
            if (p.getMessageId() >= from)
                messages.add(p);
        }
        return messages;
    }

    public int getSupportUserIdForUser(int userId){
        if (userToSupportMap.containsKey(userId)){
            return userToSupportMap.get(userId);
        } else {
            // select a support userid

            for (User u : getUserRepository().getAll()) {
                if (u.getRole() == Role.SUPPORT) {
                    userToSupportMap.put(userId, u.getUserId());
                    return u.getUserId();
                }
            }
            // if we cannot find one, return the first user, aka administrator
            return 1;
        }
    }

    public ArrayList<Message> getForUser(@NonNull User user) {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message message : this.messages.values()) {
            if (message.getFrom().equals(user) || message.getTo().equals(user))
                messages.add(message);
        }
        return messages;
    }
    public ArrayList<Message> getForUser(@NonNull User user, int lastMessageId) {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message message : this.messages.values()) {
            if (message.getFrom().equals(user) || message.getTo().equals(user))
                if (message.getMessageId() > lastMessageId)
                    messages.add(message);
        }
        return messages;
    }

    public ArrayList<Message> getForUser(int userId) {
        return getForUser(getUserRepository().get(userId));
    }

    // U
    public void update(Message p){
        messages.replace(p.getMessageId(), p);
    }

    // D
    public void remove(int messageId) {
        messages.remove(messageId);
    }
}
