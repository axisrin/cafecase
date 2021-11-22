package my.course.cafecase;

import my.course.cafecase.entity.Menu;
import my.course.cafecase.repos.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class GreetingController {

    @Autowired
    private MenuRepo menuRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/greeting")
    public String mainGreet(Model model) {
        Iterable<Menu> menus = menuRepo.findAll();
        model.addAttribute("foods",menus);
        return "main";
    }

    @PostMapping("/greeting")
    public String add(@RequestParam String nameFood, @RequestParam Double costFood,
                      @RequestParam String tagFood, @RequestParam (required = false) boolean isHave, Model model) {
        Menu menu = new Menu(nameFood,costFood,tagFood,isHave);
        menuRepo.save(menu);
        Iterable<Menu> menus = menuRepo.findAll();
        model.addAttribute("foods",menus);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filterTag, Model model) {
        List<Menu> menus = menuRepo.findAll();
        List<Menu> filtredMenus = new ArrayList<Menu>();
        for (Menu curM: menus) {
            if (curM.getTagFood().toLowerCase().contains(filterTag.toLowerCase()))
                filtredMenus.add(curM);
        }
        if (filtredMenus.isEmpty()) {
            filtredMenus = menus;
        }
        System.out.println("Filtred menus is + " + filtredMenus.toString());
        model.addAttribute("foods", filtredMenus);
        return "main";
    }

}
