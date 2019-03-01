package s4.spring.td2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.entities.Groupe;
import s4.spring.td2.entities.Organization;
import s4.spring.td2.repositories.GroupesRepository;
import s4.spring.td2.repositories.OrgaRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	
	@Autowired
	private OrgaRepository orgasRepo;
	@Autowired
	private GroupesRepository repoGroupes;
	
	@GetMapping({"","index"})
	public String index(Model model) {
		List<Organization> orgas = orgasRepo.findAll();
		model.addAttribute("orgas",orgas);
		return "orgas/index";
	}
	
	@PostMapping("submit")
	public RedirectView submit(Organization postedOrga) {
		if(postedOrga.getId()!=0) {
			int id = postedOrga.getId();
			Optional<Organization>opt=orgasRepo.findById(id);
			if(opt.isPresent()) {
				Organization orga=opt.get();
				copyFrom(postedOrga,orga);
				orgasRepo.save(orga);
			}
			else {
				return new RedirectView("/orgas/");
			}
		}
		else {
			orgasRepo.save(postedOrga);
		}
		return new RedirectView("/orgas/");
	}
	

	private void copyFrom(Organization source,Organization dest) {
		dest.setName(source.getName());
		dest.setDomain(source.getDomain());
		dest.setAliases(source.getAliases());
	}
	
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrga() {
		Organization organization = new Organization();
		organization.setName("IUT Ifs");
		organization.setDomain("unicaen.fr");
		organization.setAliases("iutc3.unicaen.fr");
		organization.setCity("Ifs");
		orgasRepo.save(organization);
		return organization+" ajoutée dans la bdd";
			
	}
	@RequestMapping("groupes")
	public String createGroupe() {
		Groupe groupe = new Groupe();
		repoGroupes.save(groupe);
		return "Groupe créé";
	}
	
	@RequestMapping("create/groupes/{id}")
	@ResponseBody
	public String createOrgaWithGroupes(@PathVariable int id) {
		Optional <Organization> optOrga = orgasRepo.findById(id);
		if(optOrga.isPresent()) {
			Organization organization = optOrga.get();
			Groupe groupe = new Groupe();
			groupe.setName("Etudiants");
			organization.addGroupe(groupe);		
			orgasRepo.save(organization);
			return organization+" ajoutée dans la bdd";
		}
		
		return "Organisation inexistante";
			
	}
	
	@GetMapping("orgas/display/{id}")
	public String display(Model model) {
		
		return null;
	}
	
	@GetMapping("new")
	public String frmNew(Model model) {
		model.addAttribute("orga",new Organization());
		return "orgas/frm";
	}
	
	@GetMapping("edit/{id}")
	public String frmEdit(@PathVariable int id,Model model) {
		Optional<Organization> opt=orgasRepo.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("orga",opt.get());
			return "orgas/frm";
		}
		return "orgas/404";
	}
	
	@RequestMapping("404")
	public String notfound() {
		return "404";
	}
}
