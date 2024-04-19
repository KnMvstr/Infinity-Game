package HB_CAPE_MAK.hb_cape_makindu.json_views;
public class JsonViews {

    //     USER VIEW
    public interface UserPublicView { }
    public interface UserPrivateView extends UserPublicView  { }

    //    GAMER VIEW
    public interface GamerPublicView extends UserPrivateView {}
    public interface GamerPrivateView extends UserPrivateView {}

    //    MODERATOR VIEW
    public interface ModeratorPrivateView extends UserPrivateView { }

    //    GAME VIEW
    public interface GameMinimalView {}
    public interface GamePublicView extends GameMinimalView {}
    public interface GamePrivateView extends GamePublicView, GenreMinimalView, PlatformMinimalView, PublisherMinimalView, ClassificationMinimalView  {}
    public interface GamePlusReview extends GamePrivateView {}

    //     BUSINESS MODEL VIEW
    public interface BusinessModelPublicView extends GamePublicView  {}
    public interface BusinessModelPrivateView extends BusinessModelPublicView  {}


    //     CLASSIFICATION VIEW
    public interface ClassificationMinimalView {}
    public interface ClassificationPublicView extends ClassificationMinimalView{}
    public interface ClassificationPrivateView extends ClassificationPublicView {}
    public interface ClassificationFullView extends ClassificationPrivateView, GameMinimalView {}

    //    GENRE VIEW
    public interface GenreMinimalView {}
    public interface GenrePublicView extends GameMinimalView, GenreMinimalView {}
    public interface GenrePrivateView extends GenrePublicView {}

    //    PLATFORM VIEW
    public interface PlatformMinimalView {}
    public interface PlatformPublicView extends GamePublicView, PlatformMinimalView {}
    public interface PlatformPrivateView extends PlatformPublicView  {}

    //    PUBLISHER VIEW
    public interface PublisherMinimalView {}
    public interface PublisherPublicView extends GamePublicView, PublisherMinimalView {}
    public interface PublisherPrivateView extends PublisherPublicView {}

    //    REVIEW VIEW
    public interface ReviewMinimalView extends GamePublicView {}
    public interface ReviewPublicView extends ReviewMinimalView, UserPublicView {}
    public interface ReviewPrivateView extends ReviewPublicView {}
}