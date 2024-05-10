package com.basic.myspringboot.users;

import com.basic.myspringboot.users.vo.UserVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name", "스프링 부트");
        return "leaf";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserVO user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserVO user, BindingResult result, Model model) {
        //검증오류가 있으면?
        if (result.hasErrors()) {
            return "add-user";
        }
        UserEntity userEntity = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        userRepository.save(userEntity);
//        model.addAttribute("users", userRepository.findAll());
//        return "index";
        return "redirect:/index";
    }


}