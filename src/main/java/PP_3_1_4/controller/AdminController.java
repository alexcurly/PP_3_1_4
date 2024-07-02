package PP_3_1_4.controller;


import PP_3_1_4.model.User;
import PP_3_1_4.service.RoleService;
import PP_3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String printUsers(ModelMap modelMap) {
        modelMap.addAttribute("usersList", userService.findAll());
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("role", roleService.getAllUser());
        return "adminpanel";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute(value = "user") User user, ModelMap modelMap) {
        modelMap.addAttribute("role", roleService.getAllUser());

        userService.save(user);
        return "redirect:/admin";
    }

// doesnt work method. anyway app doesnt need that adding.
//    @PostMapping("/adduser")
//    public String addUser(@ModelAttribute(value = "user") User user, ModelMap modelMap) {
//        if(userService.save(user)){
//            modelMap.addAttribute("role", roleService.getAllUser());
//        } else {
//            modelMap.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//        }
//        return "redirect:/admin";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setId(id);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";

    }
}
