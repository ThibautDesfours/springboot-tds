package s4.spring.td2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("path:(?:index)?}")
	@ResponseBody
	public String index() {
		return "index";
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
}
