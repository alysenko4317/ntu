package com.khpi.service.controllers;

import com.khpi.service.security.details.UserDetailsImpl;
import com.khpi.service.transfer.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import static com.khpi.service.transfer.UserDto.from;

@Controller
public class ProfileController
{
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication)
    {
        // зберігає дані про вже авторизованого користувача
        if (authentication == null) {
            return "redirect:/login";
        }

        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();

        // using data transfer object (DTO) to pass only those data which are really required in view model
        UserDto user = from(details.getUser());
        model.addAttribute("user", user);

        return "profile";
    }
}
