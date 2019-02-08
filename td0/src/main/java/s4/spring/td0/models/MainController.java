package s4.spring.td0.models;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("elements")
public class MainController {
	
	@ModelAttribute("elements")
	public List<Element> getItems(){
		Element elm = new Element();
		List<Element> elms = new ArrayList<>();
		elms.add(elm);
		return elms;
	}
 
    @GetMapping("items") 
    public String index(){
        return "index";
    }
    
    @GetMapping("new")
    public String newItem() {
    	return "frmItem";
    }
    
    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam("nom") String nom, @RequestParam int evaluation,@ModelAttribute("elements") List<Element> elements) {
    	Element elm = new Element();
    	elm.setNom(nom);
    	elm.setEvaluation(evaluation);
    	elements.add(elm);
    	return new RedirectView("/items");
    }
    
    @PostMapping("addNewBis")
    public RedirectView addNewBis(HttpServletRequest request) {
    	String nom = request.getParameter("nom");
    	int eval = Integer.valueOf(request.getParameter("evaluation"));
    	Element elm = new Element();
    	elm.setNom(nom);
    	elm.setEvaluation(eval);
    	getItems().add(elm);
    	return new RedirectView("/items");
    }
    
    @PostMapping("items/delete/{index}")
    public RedirectView delete() {
    	
    	return new RedirectView("/items");
    }
    
}
