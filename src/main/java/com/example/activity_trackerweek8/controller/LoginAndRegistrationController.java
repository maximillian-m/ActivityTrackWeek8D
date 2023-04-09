package com.example.activity_trackerweek8.controller;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.User;
import com.example.activity_trackerweek8.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.WebConnection;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAndRegistrationController {
    private UserService userService;

    public LoginAndRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


//this returns the landing page view on opening of page
    @GetMapping("/")
    public ModelAndView landingView() {
        ModelAndView newView = new ModelAndView();
        newView.setViewName("index");
        return newView;
    }



//this returns the register view and model for registration
    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.setViewName("Registration");
        return modelAndView;
    }




// This returns the login page
    @GetMapping("/login")
    public ModelAndView Login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.setViewName("login");
        return modelAndView;
    }




    //This handles the registration of the user and also validates the user
    @PostMapping("/register")
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult) {

        User existingUser = userService.getUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", "email.exists", "email Exists");
        }
        if (bindingResult.hasErrors()) {
            bindingResult.rejectValue("email", "email.exists", "email Exists");
            return "redirect:/register";
        }
        userService.registerUsers(userDto);
        return "/login";
    }




//This handles the login of the user and also validates the user login
    @PostMapping("/login")
    public String loginUsers (@ModelAttribute("userDto") UserDto userDto, Model model, HttpServletRequest httpServletRequest){
        User authentic = userService.loginUser(userDto);
        HttpSession session = httpServletRequest.getSession();
        if(authentic != null){
            session.setAttribute("user_name", authentic.getUsername());
            session.setAttribute("user_id", authentic.getId());
            return "redirect:/todoPage";
        }
            return"/login";

    }
    @GetMapping("/logout")
    public String logoutUsers(HttpSession session){
        if(session.getAttribute("user_id") != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}


