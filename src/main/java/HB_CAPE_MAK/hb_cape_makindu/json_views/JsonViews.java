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
    public interface GamePublicView extends PlatformPublicView, BusinessModelPublicView, GenrePublicView, PublisherPublicView, ClassificationPublicView {}
    public interface GamePrivateView extends GamePublicView {}

    public interface GamePlusReview extends GamePrivateView {}






    //     BUSINESS MODEL VIEW
    public interface BusinessModelPublicView {}
    public interface BusinessModelPrivateView extends BusinessModelPublicView {}


    //     CLASSIFICATION VIEW
    public interface ClassificationPublicView {}
    public interface ClassificationPrivateView extends ClassificationPublicView {}

    public interface ClassificationFullView extends ClassificationPrivateView {}




    //    GENRE VIEW
    public interface GenreMinimalView {}
    public interface GenrePublicView extends GenreMinimalView {}
    public interface GenrePrivateView extends GenrePublicView {}



    //    PLATFORM VIEW
    public interface PlatformMinimalView {}
    public interface PlatformPublicView extends  PlatformMinimalView{}
    public interface PlatformPrivateView extends PlatformPublicView {}


    //    PUBLISHER VIEW
        public interface PublisherPublicView {}
    public interface PublisherPrivateView extends PublisherPublicView {}


    //    REVIEW VIEW
    public interface ReviewPublicView {}
    public interface ReviewPrivateView extends ReviewPublicView {}

}