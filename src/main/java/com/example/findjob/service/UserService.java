package com.example.findjob.service;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Adds the user
     * @param user
     * @param str_role
     * @return null if added successfully; string (message) if an error occur
     */
    public String addUser(User user, String str_role) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser != null) {
            return "Пользователь с таким именем уже зарегистрирован";
        }
        user.setActive(true);
        user.setRoles(Stream.of(Role.getRoleFromString(str_role), Role.USER).collect(Collectors.toSet()));
        user.generateAndSetActivationCode();

        userRepo.save(user);

        sendActivationCode(user);
        return null;
    }

    private void sendActivationCode(User user) {
        if (StringUtils.isNotBlank(user.getActivationCode())) {
            String msg = String.format("Hello, %s!\nActivation link: http://localhost:8080/activate/%s", user.getUsername(), user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", msg);
        }
    }

    /**
     * Activate user
     * @param activationCode
     * @return null if user activated successfully; string (message) if an error occur
     */
    public String activateUser(String activationCode) {
        User userByActivationCode = userRepo.findByActivationCode(activationCode);
        if (userByActivationCode == null) {
            return "Неверный код активации";
        }
        userByActivationCode.setActivationCode(null);
        userRepo.save(userByActivationCode);
        return null;
    }

}
