package ru.sbt.teammanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.teammanagement.service.AppUserDetailsService;

@RestController
public class AppUserController {
    private final AppUserDetailsService appUserDetailsService;

    public AppUserController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping("/addAppUser")
    public void addAppUser(String username, String password, String authority){
        appUserDetailsService.addAppUser(username, password, authority);
    }
}
