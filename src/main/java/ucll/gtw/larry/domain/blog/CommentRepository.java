package ucll.gtw.larry.domain.blog;

import lombok.Getter;

import java.util.*;

public class CommentRepository {
    private Map<Integer, Comment> comments = new TreeMap<>();
    @Getter private PostRepository postRepository;
    @Getter private int lastUpdateTimeStamp = 0;

    public CommentRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private void _changes() {
        lastUpdateTimeStamp = (int)(System.currentTimeMillis()/1000L);
    }

    // C
    public void add(int postId, Comment comment) {
        Post post = getPostRepository().get(postId);

        comment.setPost(post);
        int commentId = comments.size()+1;
        comment.setCommentId(commentId);
        comments.put(comment.getCommentId(), comment);
        post.getComments().add(comment);
        _changes();
    }

    // R
    public Comment get(int commentId) {
        return comments.get(commentId);
    }

    public ArrayList<Comment> getAll() {
        return new ArrayList<>(comments.values());
    }

    public ArrayList<Comment> getFrom(int from) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment p : this.comments.values()) {
            if (p.getCommentId() >= from)
                comments.add(p);
        }
        return comments;
    }

    // U
    public void update(Comment p){
        comments.replace(p.getCommentId(), p);
        _changes();
    }

    // D
    public void remove(int commentId) {
        comments.remove(commentId);
        _changes();
    }
}
