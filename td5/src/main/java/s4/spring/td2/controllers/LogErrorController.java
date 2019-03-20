package s4.spring.td2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logError/")
public class LogErrorController {

	@GetMapping({ "" })
	public String log(Model model) {
		return "errorlog";
	}
}
