package ru.mail.denis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mail.denis.web.controller.validator.UserValidator;
import ru.mail.denis.service.UserService;
import ru.mail.denis.service.model.UserDTO;
import ru.mail.denis.service.model.UserInformationDTO;


/**
 * Created by Denis Monich on 31.05.2017.
 */


@Controller
public class RegistrationController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("userInformation", new UserInformationDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(
            @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            RedirectAttributes ra
    ) {
        userValidator.validate(userDTO, result);
        if (!result.hasErrors()) {
            userService.addUserDTO(userDTO);
            ra.addFlashAttribute("successRegistration", "Registration complete successfully");
            return "redirect:/login";
        } else {
            return "registration";
        }
    }
}


