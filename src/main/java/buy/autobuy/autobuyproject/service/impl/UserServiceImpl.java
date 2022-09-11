package buy.autobuy.autobuyproject.service.impl;

import buy.autobuy.autobuyproject.entity.*;
import buy.autobuy.autobuyproject.repository.*;
import buy.autobuy.autobuyproject.service.UserService;
import buy.autobuy.autobuyproject.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static buy.autobuy.autobuyproject.constant.RegistrationConstant.*;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private InfoUserRepository infoUserRepository;
    private UserRepository userRepository;
    private SetLoginRepository setLoginRepository;
    private SetEmailRepository setEmailRepository;
    private SetPasswordRepository setPasswordRepository;
    private PromoRepository promoRepository;
    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PromoRepository promoRepository, SetPasswordRepository setPasswordRepository, Validator validator, SetLoginRepository setLoginRepository, SetEmailRepository setEmailRepository, InfoUserRepository infoUserRepository) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.infoUserRepository = infoUserRepository;
        this.setLoginRepository = setLoginRepository;
        this.setEmailRepository = setEmailRepository;
        this.setPasswordRepository = setPasswordRepository;
        this.promoRepository = promoRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String addUser(RegistretionForm registretionForm) {
        String result = validateRegistration(registretionForm);

        if (result.equals(OK)) {
            result = userRepository.checkLogin(registretionForm.getLogin()).isPresent() ? LOGIN_EXISTS : OK;
        }
        if (result.equals(OK)) {
            result = userRepository.checkEmail(registretionForm.getEmail()).isPresent() ? EMAIL_EXISTS : OK;
        }
        if (result.equals(OK)) {
            userRepository.addUser(registretionForm.getPassword(), registretionForm.getLogin(),
                    registretionForm.getEmail());
            return OK;
        } else if (result.equals(LOGIN_EXISTS)) {
            return LOGIN_EXISTS;
        } else if (result.equals(EMAIL_EXISTS)) {
            return EMAIL_EXISTS;
        } else if (result.equals(NO_EMAIL)) {
            return NO_EMAIL;
        } else if (result.equals(NO_LOGIN)) {
            return NO_LOGIN;
        } else if (result.equals(NO_PASSWORD)) {
            return NO_PASSWORD;
        } else if (result.equals(PASSWORD_NO_EQUALS)) {
            return PASSWORD_NO_EQUALS;
        }
        return ERROR;
    }

    public String addInfoUser(int id, InfoClient infoClient) {
        infoUserRepository.addInfoUser(id, infoClient.getName(), infoClient.getPhone(),
                infoClient.getSurname(), infoClient.getCountry(), infoClient.getCity(), infoClient.getGender(),
                infoClient.getAge());
        return OK;
    }

    public String validateRegistration(RegistretionForm registretionForm) {
        String result = OK;
        if (!validator.validateEmail(registretionForm.getEmail())) {
            result = NO_EMAIL;
        } else if (!validator.validateLogin(registretionForm.getLogin())) {
            result = NO_LOGIN;
        } else if (!validator.validatePassword(registretionForm.getPassword())) {
            result = NO_PASSWORD;
        } else if (!registretionForm.getPassword().equals(registretionForm.getPasswordRepeated())) {
            result = PASSWORD_NO_EQUALS;
        }
        return result;
    }

    public String findUser(LoginForm loginForm) {
        String result = userRepository.findUser(loginForm.getPassword(), loginForm.getLogin()).isPresent() ? OK : USER_NOT_FIND;

        return result;
    }

    public String showEmail(String login) {

        return userRepository.showEmail(login).orElse("exp");
    }

    public String showPassword(String login) {

        return setPasswordRepository.showPassword(login).orElse("exp");
    }


    public List<Automobile> searchAuto(int id) {

        return promoRepository.searchAuto((id));
    }

    public List<Automobile> watchDeclaration(int id) {

        return promoRepository.watchDeclaration((id));
    }

    public Optional<Integer> deleteDeclaration(int id) {
        return promoRepository.deleteDeclaration(id);
    }
public List<Integer> showIdDeclaration(int idClient){
        List<Integer>result=promoRepository.showIdDeclaration(idClient);
    System.out.println(result);
        return result;

}
    public int findUserid(String login) {
        int result = userRepository.checkLogin(login).orElse(-1);
        return result;
    }


    public String newLogin(String login, String newLogin) {
        return setLoginRepository.newLogin(login, newLogin).isPresent() ? OK : NO_LOGIN;
    }

    public String newEmail(String email, String newEmail) {
        return setEmailRepository.newEmail(email, newEmail).isPresent() ? OK : NO_EMAIL;

    }

    public String updateDeclaration(String type, String newType, String kpp, String newKpp, String colour,
                                    String newColour, String marka, String newMarka, double engine, double newEngine) {
        return promoRepository.UpdateDeclaration(type, newType, kpp, newKpp, colour, newColour, marka, newMarka, engine, newEngine).isPresent() ? OK : NO_UPDATE;
    }

    public String newPassword(String password, String newPassword) {
        return setPasswordRepository.newPassword(password, newPassword).isPresent() ? OK : NO_PASSWORD;

    }


}
