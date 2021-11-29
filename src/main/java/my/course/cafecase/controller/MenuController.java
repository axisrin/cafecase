package my.course.cafecase.controller;

import my.course.cafecase.entity.Menu;
import my.course.cafecase.entity.RealOrder;
import my.course.cafecase.entity.Role;
import my.course.cafecase.entity.User;
import my.course.cafecase.repos.MenuRepo;
import my.course.cafecase.repos.RealOrderRepo;
import my.course.cafecase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private RealOrderRepo realOrderRepo;

    @PostMapping("/delete/{menu}")
    public String deleteFood(
            @RequestParam("foodId") Menu menu,
            Model model) {
        Menu menuFind;
        menuFind = menuRepo.findByIdFood(menu.getIdFood());
        if (menuFind == null)
            System.out.println("Menu null");
        System.out.println(menuFind.getNameFood());
        menuRepo.delete(menuFind);
        return "redirect:/menu";
    }

    @PostMapping("/addToCart/{menu}")
    public String addToCart(
            @RequestParam("foodId") Menu menu,
            @AuthenticationPrincipal User user,
            Model model) {

        RealOrder realOrder = new RealOrder();
        Map<Long, Integer> card = new HashMap<Long, Integer>();

//        System.out.println(user.getUsername());
//        System.out.println(menu.getNameFood());

        if (realOrderRepo.findByClientId(user.getId()) != null) {
            realOrder = realOrderRepo.findByClientId(user.getId());
            if (!realOrder.getOrderMeal().isEmpty()) {
                card = realOrder.getOrderMeal();
                if (card.get(menu.getIdFood()) != null) {
                    int totalFood = card.get(menu.getIdFood());
                    totalFood += 1;
                    card.replace(menu.getIdFood(), totalFood);
                } else {
                    card.put(menu.getIdFood(), 1);
                }
            } else {
                card.put(menu.getIdFood(), 1);
            }
            realOrder.setOrderMeal(card);
            realOrderRepo.save(realOrder);
        } else {
            realOrder.setClientId(user.getId());
            card.put(menu.getIdFood(), 1);
            realOrder.setOrderMeal(card);
            realOrderRepo.save(realOrder);
        }

        return "redirect:/menu";
    }


}
