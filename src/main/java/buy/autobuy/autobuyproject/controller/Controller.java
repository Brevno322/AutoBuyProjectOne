package buy.autobuy.autobuyproject.controller;

import buy.autobuy.autobuyproject.constant.RegistrationConstant;
import buy.autobuy.autobuyproject.entity.*;
import buy.autobuy.autobuyproject.repository.UserRepository;
import buy.autobuy.autobuyproject.service.impl.PromoServiceImpl;
import buy.autobuy.autobuyproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static buy.autobuy.autobuyproject.constant.RegistrationConstant.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller

public class Controller {
    private UserServiceImpl service;
    private PromoServiceImpl promoService;


    @Autowired
    Controller(UserServiceImpl service, PromoServiceImpl promoService) {

        this.service = service;
        this.promoService = promoService;


    }


    @GetMapping("/registration")
    public String start(Model model) {
        model.addAttribute("registrationForm", new RegistretionForm());
        return "reg.html";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registrationForm") RegistretionForm registretionForm, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("registrationStatus", service.addUser(registretionForm));
        return "reg.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String status = service.findUser(loginForm);
        session.setAttribute("LoginStatus", status);
        if (status.equals("OK")) {
            session.setAttribute("login", loginForm.getLogin());
            return "index.html";
        }
        return "login.html";

    }
    @GetMapping("/logout")
    public String logout() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.invalidate();
        return "index.html";
    }

    @GetMapping("/promo")
    public String promo(Model model) {
        model.addAttribute("promoForm", new Automobile());
        return "podacha.html";
    }
    @PostMapping("/promo")
    public String promo(@ModelAttribute("promoForm") Automobile automobile, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String loginPromo = (String) session.getAttribute("login");
        int id = service.findUserid(loginPromo);
        promoService.addPromo(id, automobile);
        return "podacha.html";
    }

    @GetMapping("/infoUser")
    public String infoUse(Model model) {
        model.addAttribute("infoUser", new InfoClient());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String loginPromo = (String) session.getAttribute("login");
        model.addAttribute("email", service.showEmail(loginPromo));
        int id = service.findUserid(loginPromo);
        List<Automobile> result = service.searchAuto(id);
        model.addAttribute("mount", result.size());
        return "UsAccount.html";
    }

    @PostMapping("/infoUser")
    public String infoUse(@ModelAttribute("infoUser") InfoClient infoClient, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        int id = service.findUserid(login);
        service.addInfoUser(id, infoClient);
        return "UsAccount.html";
    }

    @GetMapping("/setLogin")
    public String setLogin(Model model, String newLogin) {
        model.addAttribute("setLogin1", newLogin);
        return "newLogin.html";
    }

    @PostMapping("/setLogin")
    public String setLogin(@ModelAttribute("setLogin1") String newLogin, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        String statusNewLogin = service.newLogin(login, newLogin);
        if (statusNewLogin.equals(OK)) {
            session.setAttribute("login", newLogin);
            return "index.html";
        }
        return "newLogin.html";
    }

    @GetMapping("/setEmail")
    public String setEmail(Model model, String newEmail) {
        model.addAttribute("setNewEmail", newEmail);
        return "newEmail.html";
    }

    @PostMapping("/setEmail")
    public String setEmail(@ModelAttribute("setNewEmail") String newEmail, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        model.addAttribute("email", service.showEmail(login));
        String email = (String) model.getAttribute("email");
        String result = service.newEmail(email, newEmail);
        if (result.equals(OK)) {
            return "index.html";
        }
        return "newEmail.html";
    }

    @GetMapping("/setPassword")
    public String setPassword(Model model, String newPassword) {
        model.addAttribute("setNewPassword", newPassword);
        return "newPassword.html";
    }

    @PostMapping("/setPassword")
    public String setPassword(@ModelAttribute("setNewPassword") String newPassword, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        model.addAttribute("password", service.showPassword(login));
        String password = (String) model.getAttribute("password");
        String result = service.newPassword(password, newPassword);
        if (result.equals(OK)) {
            return "index.html";
        }
        return "newPassword.html";
    }

    @GetMapping("/declaration")
    public String declaration(Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        int id = service.findUserid(login);
        List<Automobile> result = service.watchDeclaration(id);
        model.addAttribute("declaration", result);

        return "declaration.html";
    }

    @PostMapping("/declaration")
    public String declaration(@ModelAttribute("watchDeclaration") List<Automobile> auto, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        int id = service.findUserid(login);
        System.out.println(id);
        List<Automobile> result = service.watchDeclaration(id);
        System.out.println(result);
        System.out.println(service.watchDeclaration(id));
        model.addAttribute("brand", result.get(0));
        return "declaration.html";
    }

    @GetMapping("/updateDeclaration")
    public String updateDeclaration(Model model, Automobile automobile) {
        model.addAttribute("updateDeclaration", automobile);
        return "updateDeclaration.html";
    }

    @PostMapping("/updateDeclaration")
    public String updateDeclaration(@ModelAttribute("updateDeclaration") Automobile automobile, Model model) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        String login = (String) session.getAttribute("login");
        int id = service.findUserid(login);



        return "updateDeclaration.html";
    }
//    @GetMapping("/deleteDeclaration")
//    public String deleteDeclaration(Model model) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attributes.getRequest().getSession(true);
//        String login = (String) session.getAttribute("login");
//        int id = service.findUserid(login);
//        List<Integer>idDeclaration=service.showIdDeclaration(id);
//        for ( int item:idDeclaration) {
//
//        }
//        return "declaration.html";
//    }
    @GetMapping("/deleteDeclaration/{id}")
    public String rout(@PathVariable(value = "id")int id, Model model){
       service.deleteDeclaration(id);
        return "declaration.html";

    }
}
