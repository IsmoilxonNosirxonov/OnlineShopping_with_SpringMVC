package uz.in.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.in.domain.dto.basket.BasketCreateDto;
import uz.in.domain.entity.product.ProductCategory;
import uz.in.service.basket.BasketService;
import uz.in.service.product.ProductService;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BasketService basketService;

    @GetMapping(value = "/phone")
    public String showPhone(Model model){
        model.addAttribute("phones", productService.getProductsByCategory(ProductCategory.PHONE));
        return "phone";
    }

    @PostMapping(value = "/phone")
    public String buyPhone(@RequestParam(value = "amount", required = false) Integer amount,
                           @RequestParam(value = "productId", required = false) UUID productId, Model model){
        int check = basketService.add(new BasketCreateDto(AuthController.currentUserId, productId, amount, LocalDateTime.now()));
        if(check==500){
            model.addAttribute("message", "Not enough money in balance!");
        }
        else {
            model.addAttribute("message", "Products successfully bought!");
        }
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }

    @GetMapping(value = "/tablet")
    public String showTablet(Model model){
        model.addAttribute("tablets", productService.getProductsByCategory(ProductCategory.TABLET));
        return "tablet";
    }

    @PostMapping(value = "/tablet")
    public String buyTablet(@RequestParam(value = "amount", required = false) Integer amount,
                            @RequestParam(value = "productId", required = false) UUID productId, Model model){
        int check = basketService.add(new BasketCreateDto(AuthController.currentUserId, productId, amount, LocalDateTime.now()));
        if(check==500){
            model.addAttribute("message", "Not enough money in balance!");
        }
        else {
            model.addAttribute("message", "Products successfully bought!");
        }
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }

    @GetMapping(value = "/laptop")
    public String showLaptop(Model model){
        model.addAttribute("latops", productService.getProductsByCategory(ProductCategory.LAPTOP));
        return "laptop";
    }

    @PostMapping(value = "/laptop")
    public String buyLaptop(@RequestParam(value = "amount", required = false) Integer amount,
                            @RequestParam(value = "productId", required = false) UUID productId, Model model){
        int check = basketService.add(new BasketCreateDto(AuthController.currentUserId, productId, amount, LocalDateTime.now()));
        if(check==500){
            model.addAttribute("message", "Not enough money in balance!");
        }
        else {
            model.addAttribute("message", "Products successfully bought!");
        }
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }

    @GetMapping(value = "/monitor")
    public String showMonitor(Model model){
        model.addAttribute("monitors", productService.getProductsByCategory(ProductCategory.MONITOR));
        return "monitor";
    }

    @PostMapping(value = "/monitor")
    public String buyMonitor(@RequestParam(value = "amount", required = false) Integer amount,
                             @RequestParam(value = "productId", required = false) UUID productId, Model model){
        int check = basketService.add(new BasketCreateDto(AuthController.currentUserId, productId, amount, LocalDateTime.now()));
        if(check==500){
            model.addAttribute("message", "Not enough money in balance!");
        }
        else {
            model.addAttribute("message", "Products successfully bought!");
        }
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }

    @GetMapping(value = "/tv")
    public String showTv(Model model){
        model.addAttribute("tvs", productService.getProductsByCategory(ProductCategory.TV));
        return "tv";
    }

    @PostMapping(value = "/tv")
    public String buyTv(@RequestParam(value = "amount", required = false) Integer amount,
                        @RequestParam(value = "productId", required = false) UUID productId, Model model){
        int check = basketService.add(new BasketCreateDto(AuthController.currentUserId, productId, amount, LocalDateTime.now()));
        if(check==500){
            model.addAttribute("message", "Not enough money in balance!");
        }
        else {
            model.addAttribute("message", "Products successfully bought!");
        }
        model.addAttribute("products", basketService.myProducts(AuthController.currentUserId));
        return "customer";
    }
}
