package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.in.domain.dto.user.FillBalanceDto;
import uz.in.service.basket.BasketService;
import uz.in.service.user.UserService;

@Controller
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;
    private final BasketService basketService;

    @PostMapping(value = "/fill-balance")
    public String fillBalance(@ModelAttribute FillBalanceDto fillBalanceDto, Model model){
        userService.fillBalance(AuthController.currentUserId,fillBalanceDto.getAmount());
        model.addAttribute("balance",userService.getById(AuthController.currentUserId).getBalance());
        return "customer";
    }

    @GetMapping(value = "/products")
    public String boughtProducts(Model model){
        model.addAttribute("balance",userService.getById(AuthController.currentUserId).getBalance());
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }
}
