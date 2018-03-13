package masterSpringMvc.chapter3.controller;

import masterSpringMvc.chapter3.dto.ProfileForm;
import masterSpringMvc.chapter3.utils.USLocalDateFormatter;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by yangkun on 2018/3/12.
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile",params = {"save"},method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "profile/profilePage";
        }
        System.out.println("save ok "+" "+profileForm.toString());
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localFormat(Locale locale){
        return USLocalDateFormatter.getPattern(locale);
    }

    @RequestMapping(value = "/profile",params = {"addTaste"})
    public String addRow(ProfileForm profileForm){
        profileForm.getTastes().add(null);
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile",params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest request){
        Integer rowId=Integer.valueOf(request.getParameter("removeTaste").trim());
        profileForm.getTastes().remove(rowId);
        return "profile/profilePage";
    }

}
