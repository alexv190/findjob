package com.example.findjob.service;

import com.example.findjob.domain.Role;
import com.example.findjob.domain.User;
import com.example.findjob.repo.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Пользователь не найден");
        return user;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        sendActivationCodeAfterEmailUpdate(user);
        return null;
    }

    private void sendActivationCodeAfterEmailUpdate(User user) {
        if (StringUtils.isNotBlank(user.getEmail())) {
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


    public void updateUser(User user, Map<String, String> form) {
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (Role.getRoleFromString(key) != null) {
                user.getRoles().add(Role.getRoleFromString(key));
            }
        }

        String password = form.get("password");
        String newEmail = form.get("email");
        String userEmail = user.getEmail();

        boolean emailChanged = !StringUtils.equals(userEmail, newEmail);
        if (emailChanged) {
            user.setEmail(newEmail);

            if (!StringUtils.isEmpty(newEmail)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepo.save(user);
        if (emailChanged)
            sendActivationCodeAfterEmailUpdate(user);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
}
