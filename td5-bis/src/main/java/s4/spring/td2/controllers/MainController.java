package s4.spring.td2.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import s4.spring.td2.entities.Script;
import s4.spring.td2.entities.User;
import s4.spring.td2.repositories.UsersRepository;

@Controller
public class MainController {
	@Autowired
	private UsersRepository userRepo;
	
	@GetMapping("/")
	public String frmNew(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		return "appli/";
		
	}
	
}
