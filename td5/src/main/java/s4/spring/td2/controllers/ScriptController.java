package s4.spring.td2.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.*;

import s4.spring.td2.repositories.*;

@Controller
@RequestMapping("/scripts/")
public class ScriptController {

	@Autowired 
	private CategoriesRepository cateRepo;
	@Autowired
	private HistoriesRepository histoRepo;
	@Autowired
	private LanguagesRepository langRepo;
	@Autowired
	private ScriptsRepository scriptRepo;
	@Autowired
	private UsersRepository userRepo;
	

	/*
	 * @GetMapping("{path:(?:index)?}") // "?" = il y a 0 ou 1 fois index private
	 * String index() { return "index"; }
	 */

	@GetMapping({ "", "index" })
	public String index(Model model) {
		List<Script> scripts = scriptRepo.findAll();
		model.addAttribute("scripts", scripts);
		return "scripts/index";
	}

	
}
