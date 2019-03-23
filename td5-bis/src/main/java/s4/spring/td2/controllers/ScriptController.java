package s4.spring.td2.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping({ "","index" })
	public String index(Model model, HttpSession session) {
		model.addAttribute("user",session.getAttribute("user"));
		return "scripts/index";
	}
	@GetMapping({ "new" })
	public String formScript(Model model, HttpSession session) {
		List<Language> languages = langRepo.findAll();
		List<Category> categories = cateRepo.findAll();
		model.addAttribute("languages",languages);
		model.addAttribute("categories",categories);
		model.addAttribute("user",session.getAttribute("user"));
		return "scripts/ajoutScripts";
	}
	
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public String ajoutScript(Model model, HttpSession session, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("content") String content,
			@RequestParam("language") int languageId, @RequestParam("category") int categoryId, 
			@RequestParam("idUser") int idUser) {
		User user = new User(idUser,"","","","");
		Language language = new Language(languageId,"");
		Category category = new Category(categoryId,"");
		Date now = new Date();
		Script script = new Script(language, user, category, title, description, content, now);
		scriptRepo.save(script);
		model.addAttribute("user",session.getAttribute("user"));
		return "scripts/index";
	}
	
	@RequestMapping(value="display", method = RequestMethod.GET)
	public String display(Model model, HttpSession session,@RequestParam("id") int id) {
		List<Script> scripts = scriptRepo.findAll();
		List<Script> scriptsUser = new ArrayList();
		for(Script script : scripts) {
			if(script.getUser().getId() == id) {
				scriptsUser.add(script);
			}
		}
		for(Script script : scriptsUser) {
			scripts.remove(script);
		}
		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("scripts",scripts);
		model.addAttribute("scriptsUser",scriptsUser);
		return "scripts/display";
	}

	@GetMapping(value = "edit/{id}")
	public String edit(Model model, HttpSession session, @PathVariable int id) {
		int indexLanguage=0, indexCategory=0;
		Script script = scriptRepo.getOne(id);
		model.addAttribute("script", script);
		List<Language> languages = langRepo.findAll();
		for(int i=0;i<languages.size();i++) {
			if(languages.get(i).getId() == script.getLanguage().getId()) {
				indexLanguage = i;
				model.addAttribute("language",languages.get(i));
			}
		}
		languages.remove(indexLanguage);
		model.addAttribute("languages",languages);
		List<Category> categories = cateRepo.findAll();
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).getId() == script.getCategory().getId()) {
				indexCategory = i;
				model.addAttribute("category",categories.get(i));
			}
		}
		categories.remove(indexCategory);
		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("categories",categories);
				
		return "scripts/edit";
	}
	
	@RequestMapping(value = "submitEdit", method = RequestMethod.POST)
	public String modifyScript(Model model, HttpSession session, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("content") String content,
			@RequestParam("language") int languageId, @RequestParam("category") int categoryId,
			@RequestParam("id") int id) {
		Script script = scriptRepo.getOne(id);
		Language language = new Language(languageId,"");
		Category category = new Category(categoryId,"");
		script.setCategory(category);
		script.setLanguage(language);
		script.setTitle(title);
		script.setDescription(description);
		script.setContent(content);
		scriptRepo.save(script);
		model.addAttribute("user",session.getAttribute("user"));
		return "scripts/index";
	}
	
}
