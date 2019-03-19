package s4.spring.td2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public RedirectView login(HttpServletRequest request, @RequestParam("login") String login, 
			@RequestParam("password") String password) {
		List<User> users=userRepo.findAll();
		for(User user : users) {
			if(login.equals(user.getLogin()) && password.equals(user.getPassword())) {
				return new RedirectView("/scripts/index");
			}
		}
		return new RedirectView("/errorLogin");	
	}

}
