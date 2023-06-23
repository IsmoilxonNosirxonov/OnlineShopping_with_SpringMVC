package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.in.domain.dto.product.ProductCreateDto;
import uz.in.domain.dto.product.ProductReadDto;
import uz.in.domain.dto.user.FillBalanceDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.domain.entity.user.UserEntity;
import uz.in.service.product.ProductService;
import uz.in.service.user.UserService;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    public static UserReadDto editUser;
    public static ProductReadDto editProduct;

    @PostMapping(value = "/fill-balance")
    public String fillBalance(@ModelAttribute FillBalanceDto fillBalanceDto, Model model){
        userService.fillBalance(AuthController.currentUserId,fillBalanceDto.getAmount());
        model.addAttribute("balance",userService.getById(AuthController.currentUserId).getBalance());
        return "admin";
    }

    @GetMapping(value = "/users")
    public String showUsers(Model model){
        model.addAttribute("users",userService.getAll());
        return "adminUsers";
    }

    @PostMapping(value = "/users")
    public String userBlock(@RequestParam(value = "block", required = false) UUID block,
                            @RequestParam(value = "unblock", required = false) UUID unblock,
                            @RequestParam(value = "edit", required = false) UUID editById,
                            Model model){
        if (block != null){
            userService.block(block);
        }
        else if (unblock != null) {
            userService.unBlock(unblock);
        }
        if(editById!=null){
            editUser=userService.getById(editById);
            model.addAttribute("editUser",editUser);
            return "editUser";
        }
        model.addAttribute("users",userService.getAll());
        return "adminUsers";
    }

    @GetMapping(value = "/products")
    public String showProducts(Model model){
        model.addAttribute("products",productService.getAll());
        return "adminProducts";
    }

    @PostMapping(value = "/products")
    public String createProduct(@ModelAttribute ProductCreateDto productCreateDto,
                                @RequestParam(value = "edit", required = false) UUID editById, Model model){
        if (editById!=null){
            editProduct=productService.getById(editById);
            model.addAttribute("editProduct",editProduct);
            return "editProduct";
        }
        if(productCreateDto!=null){
            productCreateDto.setSellerId(AuthController.currentUserId);
            int check = productService.add(productCreateDto);
            if (check == 500){
                model.addAttribute("message", "This product is already existed!");
                model.addAttribute("products",productService.getAll());
                return "adminProducts";
            }
        }
        model.addAttribute("products",productService.getAll());
        return "adminProducts";
    }
}
