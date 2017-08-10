package ucll.gtw.larry.domain.blog;

import ucll.gtw.larry.domain.user.UserRepository;

import java.time.LocalDateTime;

public class DummyBlogData {
    public static void addData(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository){
        for (int i = 1; i <= 5; i++){
            postRepository.add(new Post(
                    userRepository.get(1),
                    "Post title "+i,
                    "Post content "+i,
                    LocalDateTime.now()
            ));
        }
        commentRepository.add(1, new Comment( "Demo", "Hello world!", LocalDateTime.now()));
        commentRepository.add(1, new Comment( "", "Hello to you too!", LocalDateTime.now()));
    }
}
