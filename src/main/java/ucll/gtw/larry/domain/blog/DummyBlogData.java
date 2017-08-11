package ucll.gtw.larry.domain.blog;

import ucll.gtw.larry.domain.user.UserRepository;

import java.time.LocalDateTime;

public class DummyBlogData {
    public static void addData(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository){
        postRepository.add(new Post(
                userRepository.get(1),
                "De Projectweek",
                "Ik heb dit jaar niet aan de projectweek moeten deelnemen, aangezien ik al reeds 4 projectweken heb deelgenomen, de BUSitWeek, en reis naar Parijs. Ik heb echter wel voor mijn opdracht van Communicatie meegeholpen aan de projectweek, door de eerstejaars Git uit te leggen",
                LocalDateTime.now()
        ));
        postRepository.add(new Post(
                userRepository.get(1),
                "Opbouw van deze website",
                "Bij het op opbouwen van deze website heb ik gebruik gemaakt van verscheidene bibliotheken zoals jBcrypt voor wachtwoorden te hashen, aangezien deze algemeen gebruikt wordt in de ontwikkeling-wereld.",
                LocalDateTime.now()
        ));
        postRepository.add(new Post(
                userRepository.get(1),
                "Mening over Angular",
                "Ik heb in het verleden Angular 1.3 gebruikt, wat aanzienelijk gemakkelijker op te zetten is als Angular (versie 4 tegenwoordig). Ook zijn de concepten eenvoudiger, er is geen sprake van Observables, en de module-structuur is geen vereiste.",
                LocalDateTime.now()
        ));
        postRepository.add(new Post(
                userRepository.get(1),
                "HTTP 1, HTTP 2, Ajax, WebSockets...",
                "En tegenwoordig is er zelfs al sprake om via WebRTC te communiceren als latency een belangrijk punt is. WebRTC kan over UDP gaan wat nog minder overhead betekend.",
                LocalDateTime.now()
        ));
        postRepository.add(new Post(
                userRepository.get(1),
                "Lorem Ipsum",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                LocalDateTime.now()
        ));
        commentRepository.add(1, new Comment( "Demo", "Hello world!", LocalDateTime.now()));
        commentRepository.add(1, new Comment( "", "Hello to you too!", LocalDateTime.now()));
    }
}
