package ucll.gtw.larry.domain.blog;

import lombok.Data;
import lombok.NonNull;
import ucll.gtw.larry.domain.user.User;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

@Data public class Post implements Comparable<Post> {
    private int postId;
    @NonNull private User user;
    @NonNull private String title;
    @NonNull private String content;
    @NonNull private LocalDateTime datetime;
    private ArrayList<Comment> comments = new ArrayList<>();

    @Override
    public int compareTo(Post post) {
        return post.getPostId()-this.getPostId();
    }
}
