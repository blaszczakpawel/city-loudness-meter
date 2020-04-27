package pl.pk.project.pz.sound_intensity.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/")
	public String returnIndex() {
		return "index";
	}
	
	@GetMapping("/about")
	public String returnAbout() {
		return "about";
	}
	
	@GetMapping("/contribute")
	public String returnContribute() {
		return "contribute";
	}
	
	@GetMapping("/APIGuide")
	public String returnAPIGuide() {
		return "APIGuide";
	}
	
	@GetMapping("/error")
	public String ReturnError() {
		return "Error";
	}

}