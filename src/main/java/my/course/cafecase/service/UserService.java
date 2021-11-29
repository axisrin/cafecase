package my.course.cafecase.service;

import my.course.cafecase.entity.Role;
import my.course.cafecase.entity.User;
import my.course.cafecase.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null)
            return false;
        user.setActivationCode(code);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public String updateUser(User user, String newPassword, String oldPassword, String username) {
        if (user.getPassword().equals(oldPassword)) {
            if (!username.isEmpty())
                if (userRepo.findByUsername(username) == null || user.getUsername().equals(username))
                    user.setUsername(username);
                else return "Ошибка, данный username уже занят!";
            if (!newPassword.isEmpty())
                user.setPassword(newPassword);
            userRepo.save(user);
            return "Всё успешно изменено!";
        }
        return "Ошибка, старый пароль  не совпадает!";
    }
}
