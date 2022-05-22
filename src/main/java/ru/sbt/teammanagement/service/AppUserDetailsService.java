package ru.sbt.teammanagement.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sbt.teammanagement.entities.AppUser;
import ru.sbt.teammanagement.repository.AppUsersRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUsersRepository appUsersRepository;

    public AppUserDetailsService(AppUsersRepository appUsersRepository) {
        this.appUsersRepository = appUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUsersRepository.findAppUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User is not found, username=" + username));
    }

    public void addAppUser(String username, String password, String authority){
        if (appUsersRepository.existsAppUserByUsername(username)){
            throw new RuntimeException("User is already exists, username=" + username);
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        appUser.setAuthority(authority);
        appUsersRepository.save(appUser);
    }
}
