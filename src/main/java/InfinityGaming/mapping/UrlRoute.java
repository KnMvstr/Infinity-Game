package InfinityGaming.mapping;

public class UrlRoute {

//    URL SECURITY
    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/your-logout";
    public final static String URL_REGISTER = "/s-inscrire";

//    URL GAME
    public final static String URL_GAME = "/game";
    public final static String URL_GAME_SLUG = URL_GAME + "/{slug}";
    public final static String URL_GAME_NEW = URL_GAME + "/nouveau";
    public final static String URL_GAME_UPLOAD_IMAGE = URL_GAME + "/upload-image";
    public final static String URL_GAME_UPLOAD_IMAGE_PATH = URL_GAME + "/upload-image/{slug}";

//    URL REVIEW
    public final static String URL_REVIEW = "/avis/{id}";
    public final static String URL_REVIEW_MODERATE = URL_REVIEW + "/moderate";
    public final static String URL_REVIEW_MODERATE_PATH = URL_REVIEW_MODERATE + "/{id}/{moderate}";

//    URL GENRE
    public final static String URL_GENRE = "/genre";
    public final static String URL_ABOUT = "/a_propos";

//    URL EXPORT
    public final static String URL_EXPORT= "/telecharger-export-excel";
}
