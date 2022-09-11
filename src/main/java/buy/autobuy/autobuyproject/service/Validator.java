package buy.autobuy.autobuyproject.service;

import org.springframework.stereotype.Component;

import static buy.autobuy.autobuyproject.constant.Regex.*;

@Component
public class Validator {


    private static boolean validate(String data, String regex) {

        return data != null && data.matches(regex);

    }


    public boolean validateLogin(String login) {

        return validate(login, LOGIN_REGEX);
    }

    public boolean validatePassword(String password) {

        return validate(password, PASSWORD_REGEX);
    }

    public boolean validateEmail(String email) {

        return validate(email, EMAIL_REGEX);
    }

    public boolean validateName(String name) {

        return validate(name, NAME_REGEX);
    }

    public boolean validateCity(String city) {

        return validate(city, CITY_REGEX);
    }

    public boolean validateSurname(String surname) {

        return validate(surname, SURNAME_REGEX);
    }

    public boolean validatePhone(String phone) {

        return validate(phone, PHONE_REGEX);
    }
}
