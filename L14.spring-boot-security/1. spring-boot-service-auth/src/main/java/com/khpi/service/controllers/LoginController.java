package com.khpi.service.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LoginController
{
    @GetMapping("/login")
    public String getLoginPage(Authentication authentication,
                               ModelMap model,
                               HttpServletRequest request)
    {
        if (authentication != null) {
            // if user is already logged in, no sense to show login page again
            return "redirect:/users";
        }

        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }

        return "login";
    }
}
