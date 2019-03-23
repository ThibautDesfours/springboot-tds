package s4.spring.td2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.User;
import s4.spring.td2.repositories.UsersRepository;

@Controller
@RequestMapping("/appli/")
public class LoginController {
	@Autowired
	private UsersRepository userRepo;
	

	@GetMapping({ "", "log" })
	public String log(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public RedirectView login(Model model, HttpServletRequest request, @RequestParam("login") String login, 
			@RequestParam("password") String password,HttpSession session) {
		List<User> users=userRepo.findAll();
		for(User user : users) {
			if(login.equals(user.getLogin()) && password.equals(user.getPassword())) {
				session.setAttribute("user", user);
				model.addAttribute("session",session.getAttribute("user"));
				return new RedirectView("/scripts/");
			}
		}
		return new RedirectView("/logError/");	
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(Model model, HttpServletRequest request, HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}

}
