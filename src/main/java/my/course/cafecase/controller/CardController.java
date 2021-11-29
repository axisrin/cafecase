package my.course.cafecase.controller;

import my.course.cafecase.entity.PastOrder;
import my.course.cafecase.entity.RealOrder;
import my.course.cafecase.entity.User;
import my.course.cafecase.repos.MenuRepo;
import my.course.cafecase.repos.PastOrderRepo;
import my.course.cafecase.repos.RealOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/cardFood")
public class CardController {

    @Autowired
    private RealOrderRepo realOrderRepo;

    @Autowired
    private PastOrderRepo pastOrderRepo;

    @Autowired
    private MenuRepo menuRepo;

    @GetMapping
    public String getCardFood(
            @AuthenticationPrincipal User user,
            Model model) {

        RealOrder realOrder = new RealOrder();
        Map<Long, Integer> card = new HashMap<Long, Integer>();
        double totalCost = 0;
        double templeCost = 0;

        String messageForReal = "Вы заказали : ";
        String totalCostText = "Итоговая стоимость : ";
        boolean haveCart = true;


        if (realOrderRepo.findByClientId(user.getId()) != null) {
            realOrder = realOrderRepo.findByClientId(user.getId());
            if (!realOrder.getOrderMeal().isEmpty()) {
                card = realOrder.getOrderMeal();
                Set<Long> keysFoodInCard = card.keySet();
                for (Long foodKey : keysFoodInCard) {
                    messageForReal = messageForReal +
                            card.get(foodKey).toString() +
                            " штук " +
                            menuRepo.findByIdFood(foodKey).getNameFood() + "; ";
                            templeCost = menuRepo.findByIdFood(foodKey).getCostFood() * card.get(foodKey);
                            totalCost = totalCost + templeCost;
                }
                totalCostText = totalCostText + totalCost;
                model.addAttribute("messageForReal", messageForReal);
                model.addAttribute("messageTotalCost", totalCostText);
                model.addAttribute("flagHaveCart", haveCart);
                System.out.println(messageForReal);
                System.out.println(totalCostText);
            } else {
                messageForReal = "Вы ничего не заказывали";
                totalCost = 0;
                model.addAttribute("messageForReal", messageForReal);
                model.addAttribute("messageTotalCost", totalCostText);
                System.out.println(messageForReal);
                System.out.println(totalCostText);
            }
        } else {
            messageForReal = "Вы ничего не заказывали";
            totalCost = 0;
            model.addAttribute("messageForReal", messageForReal);
            model.addAttribute("messageTotalCost", totalCostText);
            System.out.println(messageForReal);
            System.out.println(totalCostText);
        }

        PastOrder pastOrder = new PastOrder();
        Map<Long, Integer> pastCard = new HashMap<Long, Integer>();
        boolean noOrders = true;

        if (!pastOrderRepo.findByClientId(user.getId()).isEmpty()) {
            List<PastOrder> pastOrders =  pastOrderRepo.findByClientId(user.getId());
            model.addAttribute("pastOrders", pastOrders);
        } else {
            model.addAttribute("noPastOrders", noOrders);
        }

        PastOrder pastOrderForAdmin = new PastOrder();
        boolean noOrdersForAdmin = true;

        if (!pastOrderRepo.findByIsGotov(false).isEmpty()) {
            List<PastOrder> pastOrdersForAdmin = pastOrderRepo.findByIsGotov(false);
            model.addAttribute("pastOrdersForAdmin", pastOrdersForAdmin);
        } else {
            model.addAttribute("noPastOrdersForAdmin", noOrdersForAdmin);
        }

//        System.out.println(pastOrderRepo.findByIsGotov(false).toString());

        return "cart";
    }

    @PostMapping("/acceptCart")
    public String acceptCart(
            @AuthenticationPrincipal User user,
            @RequestParam("orderCartText") String orderCartText,
            Model model) {

        RealOrder realOrder = new RealOrder();
        Map<Long, Integer> card = new HashMap<Long, Integer>();

        realOrder = realOrderRepo.findByClientId(user.getId());
        realOrderRepo.delete(realOrder);

        PastOrder pastOrder = new PastOrder();
        pastOrder.setPastId(realOrder.getIdOrder());
//        pastOrder.setOrderMeal(realOrder.getOrderMeal());
        pastOrder.setPastOrderText(orderCartText);
//        System.out.println(orderCartText);
        pastOrder.setClientId(user.getId());
        pastOrder.setGotov(false);
        pastOrderRepo.save(pastOrder);
        return "redirect:/cardFood";

    }

    @PostMapping("/clearCart")
    public String acceptCart(
            @AuthenticationPrincipal User user,
            Model model) {

        RealOrder realOrder = new RealOrder();
        Map<Long, Integer> card = new HashMap<Long, Integer>();

        realOrder = realOrderRepo.findByClientId(user.getId());
        realOrderRepo.delete(realOrder);

        return "redirect:/cardFood";
    }

    @PostMapping("/cancelCart")
    public String cancelCart(
            @AuthenticationPrincipal User user,
            @RequestParam("idPastOrder") Long idPastOrder,
            Model model) {

        PastOrder pastOrder = pastOrderRepo.findByIdPastOrder(idPastOrder);
        pastOrderRepo.delete(pastOrder);
        return "redirect:/cardFood";
    }

    @PostMapping("/confirmCart")
    public String confirmCart(
            @AuthenticationPrincipal User user,
            @RequestParam("idPastOrder") Long idPastOrder,
            Model model) {

        PastOrder pastOrder = pastOrderRepo.findByIdPastOrder(idPastOrder);
        pastOrder.setGotov(true);
        pastOrderRepo.save(pastOrder);
        return "redirect:/cardFood";
    }


}
