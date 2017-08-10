package ucll.gtw.larry.domain.blog;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data public class Comment implements Comparable<Comment> {
    private int commentId;
    private Post post;
    @NonNull private String name;
    @NonNull private String comment;
    @NonNull private LocalDateTime datetime;

    public String getName() {
        if (name == null || name.length() == 0)
            return "Anonymous";
        else return name;
    }
    @Override
    public int compareTo(Comment comment) {
        return comment.getCommentId()-this.getCommentId();
    }
}
