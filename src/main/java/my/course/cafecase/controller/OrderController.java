package my.course.cafecase.controller;

import my.course.cafecase.entity.Menu;
import my.course.cafecase.entity.User;
import my.course.cafecase.repos.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
public class OrderController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MenuRepo menuRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "menu";
    }

    @GetMapping("/menu")
    public String mainGreet(@RequestParam(required = false) String filterTag, Model model) {

        List<Menu> menus = menuRepo.findAll();
        if (filterTag != null) {
            List<Menu> filtredMenus = new ArrayList<Menu>();
            for (Menu curM : menus) {
                if (curM.getTagFood().toLowerCase().contains(filterTag.toLowerCase()))
                    filtredMenus.add(curM);
            }
            if (filtredMenus.isEmpty()) {
                filtredMenus = menus;
                System.out.println("Food with " + filterTag + " didn't found");
                model.addAttribute("filterError", "Food with " + filterTag + " didn't found");
                filterTag = "";
            }
            menus = filtredMenus;
            System.out.println("Filtred menus is + " + filtredMenus.toString());
        }
        model.addAttribute("filterTag", filterTag);
        model.addAttribute("foods",menus);
        return "main";
    }

    @PostMapping("/menu")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String nameFood,
                      @RequestParam Double costFood,
                      @RequestParam String tagFood,
                      @RequestParam("file") MultipartFile file,
                      @RequestParam (required = false) boolean isHave,
                      Model model) throws IOException {
        Menu menu = new Menu(nameFood,costFood,tagFood,user,isHave);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            menu.setFilename(resultFilename);
        }
        menuRepo.save(menu);
        Iterable<Menu> menus = menuRepo.findAll();
        model.addAttribute("foods",menus);
        return "main";
    }

}
