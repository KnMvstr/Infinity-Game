package HB_CAPE_MAK.hb_cape_makindu.json_views;

public class JsonViews {

//     USER VIEW
    public interface UserPublicView { }
    public interface UserPrivateView extends UserPublicView, GamerPublicView, ModeratorPublicView  { }
    public interface UserFullView extends UserPublicView, UserPrivateView { }
//    GAMER VIEW
    public interface GamerPublicView { }
//    MODERATOR VIEW
    public interface ModeratorPublicView { }



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
    public interface GenreFullView extends GenrePublicView, GenrePrivateView {}



//    PLATFORM VIEW
    public interface PlatformPublicView {}
    public interface PlatformPrivateView extends PlatformPublicView {}
    public interface PlatformFullView extends PlatformPublicView, PlatformPrivateView {}



//    PUBLISHER VIEW
    public interface PublisherPublicView {}
    public interface PublisherPrivateView extends PublisherPublicView {}
    public interface PublisherFullView extends PublisherPublicView, PublisherPrivateView {}



//    REVIEW VIEW
    public interface ReviewPublicView {}
    public interface ReviewPrivateView extends ReviewPublicView {}
    public interface ReviewFullView extends ReviewPublicView, ReviewPrivateView {}
}
