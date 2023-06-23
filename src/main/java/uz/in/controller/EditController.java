package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.in.domain.dto.product.ProductReadDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.domain.entity.user.UserRole;
import uz.in.service.product.ProductService;
import uz.in.service.user.UserService;
import java.time.LocalDateTime;


@Controller
@RequestMapping(value = "/edit")
@RequiredArgsConstructor
public class EditController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping(value = "/user")
    public String userEdit(@ModelAttribute UserReadDto userReadDto, Model model){
        userReadDto.setId(AdminController.editUser.getId());
        userReadDto.setRole(AdminController.editUser.getRole());
        userReadDto.setAktive(AdminController.editUser.isAktive());
        userReadDto.setCreatedDate(AdminController.editUser.getCreatedDate());
        userReadDto.setUpdatedDate(LocalDateTime.now());
        if (userReadDto!=null){
            userService.update(userReadDto);
        }
        model.addAttribute("users",userService.getAll());
        return "adminUsers";
    }

    @PostMapping(value = "/product")
    public String productEdit(@ModelAttribute ProductReadDto productReadDto, Model model){
        productReadDto.setId(AdminController.editProduct.getId());
        productReadDto.setCategory(AdminController.editProduct.getCategory());
        productReadDto.setSellerId(AdminController.editProduct.getSellerId());
        productReadDto.setCreatedDate(AdminController.editProduct.getCreatedDate());
        productReadDto.setUpdatedDate(LocalDateTime.now());
        if (productReadDto!=null){
            productService.update(productReadDto);
        }
        if(userService.getById(AuthController.currentUserId).getRole().equals(UserRole.ADMIN)){
            model.addAttribute("products",productService.getAll());
            return "adminProducts";
        }
        model.addAttribute("products",productService.myProducts(AuthController.currentUserId));
        return "sellerProducts";
    }
}
