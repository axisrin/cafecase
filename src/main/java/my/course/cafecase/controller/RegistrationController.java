package my.course.cafecase.controller;

import my.course.cafecase.entity.Role;
import my.course.cafecase.entity.User;
import my.course.cafecase.repos.UserRepo;
import my.course.cafecase.service.MailSender;
import my.course.cafecase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;
    @Autowired

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            User user,
//            @RequestParam("g-recaptcha-response") String captchaResponse,
            Model model) {
//        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
//        CaptchaResponseDTO response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
//
//        if (!response.isSuccess()) {
//            model.addAttribute("message", "Заполните каптчу правильно!");
//            return "registration";
//        }
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User exists Try to change User Name!");
            return "registration";
        }
        List<User> usersFromDb = userRepo.findByEmail(user.getEmail());
        if (!usersFromDb.isEmpty()){
            model.addAttribute("message", "User exists Try to change Mail!");
            return "registration";
        }
        user.setActivationCode(UUID.randomUUID().toString());
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Доброего времени суток, %s! \n" +
                            "Ты зарегистрировался(лась) на сайте нашего кафе CafeCase! \n" +
                            "Пожалуйста перейди по ссылке, чтобы зарегистрироваться \n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation Code", message);
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        model.addAttribute("messageOfLogin", "Пожалуйста, подтвердите аккаунт, перейдя по ссылке на почте!");
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageOfLogin", "Поздравляем! Пользователь активирован!");
        } else {
            model.addAttribute("messageOfLogin", "Сожалеем, но пользователь с данным кодом не найден!");
        }
        return "login";
    }

}
