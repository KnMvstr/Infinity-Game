package HB_CAPE_MAK.hb_cape_makindu.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/your-logout";
    public final static String URL_REGISTER = "/s-inscrire";
    public final static String URL_GAME = "/game";

    public final static String URL_GAME_NEW = URL_GAME + "/nouveau";
    public final static String URL_REVIEW = "/avis/{id}";
    public final static String URL_REVIEW_MODERATE = URL_REVIEW + "/moderate";
    public final static String URL_REVIEW_MODERATE_PATH = URL_REVIEW_MODERATE + "/{id}/{moderate}";
    public final static String URL_GENRE = "/genre";

    public final static String URL_ABOUT = "/a_propos";

    public final static String URL_EXPORT= "/telecharger-export-excel";



}
