package InfinityGaming.json_views;

public class JsonViews {

//     USER VIEW
    public interface UserPublicView { }
    public interface UserPrivateView extends UserPublicView  { }
//    GAMER VIEW
public interface GamerPublicView extends UserPrivateView {}
    public interface GamerPrivateView extends UserPrivateView {}
//    MODERATOR VIEW
    public interface ModeratorPrivateView extends UserPrivateView { }


//     BUSINESS MODEL VIEW
    public interface BusinessModelPublicView {}
    public interface BusinessModelPrivateView extends BusinessModelPublicView {}
    public interface BusinessModelFullView extends BusinessModelPublicView, BusinessModelPrivateView {}


//     CLASSIFICATION VIEW
    public interface ClassificationPublicView {}
    public interface ClassificationPrivateView extends ClassificationPublicView {}
    public interface ClassificationFullView extends ClassificationPublicView, ClassificationPrivateView {}


//    GAME VIEW
    public interface GamePublicView {}
    public interface GamePrivateView extends GamePublicView {}
    public interface GameFullView extends GamePublicView, GamePrivateView {}


//    GENRE VIEW
    public interface GenrePublicView {}
    public interface GenrePrivateView extends GenrePublicView {}


//    PLATFORM VIEW
    public interface PlatformPublicView {}
    public interface PlatformPrivateView extends PlatformPublicView {}


//    PUBLISHER VIEW
    public interface PublisherPublicView {}
    public interface PublisherPrivateView extends PublisherPublicView {}


//    REVIEW VIEW
    public interface ReviewPublicView {}
    public interface ReviewPrivateView extends ReviewPublicView {}

}
