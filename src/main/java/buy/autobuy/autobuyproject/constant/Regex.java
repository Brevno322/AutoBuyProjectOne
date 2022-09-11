package buy.autobuy.autobuyproject.constant;

public class Regex {

    public static final String LOGIN_REGEX = "^[\\w_]{6,20}$";
    public static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final String PASSWORD_REGEX = "^.{8,30}$";
    public static final String PHONE_REGEX = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";
    public static final String CITY_REGEX = "^[\\p{L}_]{1,30}$";
    public static final String NAME_REGEX = "^[\\p{L}_]{1,30}$";
    public static final String SURNAME_REGEX = "^[\\p{L}_]{1,30}$";

}
