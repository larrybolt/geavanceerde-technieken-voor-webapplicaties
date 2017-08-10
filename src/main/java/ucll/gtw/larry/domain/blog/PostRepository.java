package ucll.gtw.larry.domain.blog;

import lombok.Getter;

import java.util.*;

public class PostRepository {
    private Map<Integer, Post> posts = new TreeMap<>();
    @Getter private int lastUpdateTimeStamp = 0;

    private void _changes() {
        lastUpdateTimeStamp = (int)(System.currentTimeMillis()/1000L);
    }

    // C
    public void add(Post p) {
        int postId = posts.size()+1;
        p.setPostId(postId);
        posts.put(p.getPostId(), p);
        _changes();
    }

    // R
    public Post get(int postId) {
        return posts.get(postId);
    }

    public ArrayList<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    public ArrayList<Post> getFrom(int from) {
        ArrayList<Post> posts = new ArrayList<>();
        for (Post p : this.posts.values()) {
            if (p.getPostId() >= from)
                posts.add(p);
        }
        return posts;
    }

    // U
    public void update(Post p){
        posts.replace(p.getPostId(), p);
        _changes();
    }

    // D
    public void remove(int postId) {
        posts.remove(postId);
        _changes();
    }
}
