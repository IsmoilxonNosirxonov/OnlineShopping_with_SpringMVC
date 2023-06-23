package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.in.domain.dto.user.SignInDto;
import uz.in.domain.dto.user.UserCreateDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.domain.entity.user.UserRole;
import uz.in.domain.exception.DataNotFoundException;
import uz.in.service.user.UserService;
import java.util.UUID;

@Controller
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    public static UUID currentUserId;

    @GetMapping(value = "/sign-in")
    public String signIn(){
        return "signIn";
    }

    @PostMapping(value = "/sign-in")
    public String signInCheck(@ModelAttribute SignInDto signInDto, Model model){
        try {
            UserReadDto user = userService.signIn(signInDto.getEmail(),signInDto.getPassword());
            currentUserId=user.getId();
            if (!user.isAktive()){
                model.addAttribute("message", "This account is blocked by admin!");
                return "signIn";
            }
            if (user.getRole().equals(UserRole.ADMIN)){
                model.addAttribute("id", user.getId());
                model.addAttribute("balance",user.getBalance());
                return "admin";
            } else if (user.getRole().equals(UserRole.SELLER)) {
                model.addAttribute("id", user.getId());
                model.addAttribute("balance",user.getBalance());
                return "seller";
            }
            model.addAttribute("id", user.getId());
            model.addAttribute("balance",user.getBalance());
            return "customer";
        }catch (DataNotFoundException e){
            model.addAttribute("message", e.getMessage());
            return "signIn";
        }
    }

    @GetMapping(value = "/sign-up")
    public String signUp(){
        return "signUp";
    }

    @PostMapping(value = "/sign-up")
    public String signUpCheck(@ModelAttribute UserCreateDto userCreateDto, Model model){
        if(userCreateDto.getName().length() < 4){
            model.addAttribute("message", "Name length should be minimum 4 characters!");
            return "signUp";
        }
        int check = userService.add(userCreateDto);
        if (check == 500){
            model.addAttribute("message", "This email is already taken!");
            return "signUp";
        }
        model.addAttribute("message", "You have successfully registered!");
        return "signIn";
    }
}
