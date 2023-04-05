package com.example.activity_trackerweek8.controller;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.User;
import com.example.activity_trackerweek8.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAndRegistrationController {
    private UserService userService;

    public LoginAndRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView landingView(){
        ModelAndView newView = new ModelAndView();
        newView.setViewName("index");
        return newView;
    }
    @GetMapping("/register")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto",new UserDto());
        modelAndView.setViewName("Registration");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView Login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/register")
    public String  registerUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {

        User existingUser = userService.getUserByEmail(userDto.getEmail());
       if(existingUser !=null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
           bindingResult.rejectValue("email", "email.exists", "email Exists");
       }
       if(bindingResult.hasErrors()){
           return "redirect:/register";
       }
       userService.registerUsers(userDto);
       return "/login";
    }


}
