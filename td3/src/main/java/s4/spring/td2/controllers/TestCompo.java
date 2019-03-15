package s4.spring.td2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class TestCompo {
	
	@Autowired
	private VueJS vue;
	
	@GetMapping("testCompo")
	public String index(Model model) {
		model.addAttribute("vue",vue);
		vue.addMethod("validate", "console.log('validation ...');");
		vue.addData("validateCaption","valider");
		vue.addData("msg", "msg : Dialog message");
		vue.addData("headers","header");
		return "index";
	}
}
