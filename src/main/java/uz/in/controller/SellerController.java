package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.in.domain.dto.product.ProductCreateDto;
import uz.in.domain.dto.user.FillBalanceDto;
import uz.in.service.product.ProductService;
import uz.in.service.user.UserService;

import java.util.UUID;

@Controller
@RequestMapping(value = "/seller")
@RequiredArgsConstructor
public class SellerController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping(value = "/fill-balance")
    public String fillBalance(@ModelAttribute FillBalanceDto fillBalanceDto, Model model){
        userService.fillBalance(AuthController.currentUserId,fillBalanceDto.getAmount());
        model.addAttribute("id",AuthController.currentUserId);
        model.addAttribute("balance",userService.getById(AuthController.currentUserId).getBalance());
        model.addAttribute("products",productService.myProducts(AuthController.currentUserId));
        return "seller";
    }

    @GetMapping(value = "/products")
    public String showProducts(Model model){
        model.addAttribute("products",productService.myProducts(AuthController.currentUserId));
        return "sellerProducts";
    }

    @PostMapping (value = "/products")
    public String createProduct(@RequestParam(value = "editById", required = false) UUID editById,
                                @ModelAttribute ProductCreateDto productCreateDto,
                                Model model){
        if (editById!=null){
            AdminController.editProduct=productService.getById(editById);
            model.addAttribute("editProduct",AdminController.editProduct);
            return "editProduct";
        }
        else if (productCreateDto!=null) {
            productCreateDto.setSellerId(AuthController.currentUserId);
            int check = productService.add(productCreateDto);
            if (check == 500){
                model.addAttribute("message", "This product is already exists!");
                model.addAttribute("products",productService.myProducts(AuthController.currentUserId));
                return "sellerProducts";
            }
        }
        model.addAttribute("products",productService.myProducts(AuthController.currentUserId));
        return "sellerProducts";
    }
}
