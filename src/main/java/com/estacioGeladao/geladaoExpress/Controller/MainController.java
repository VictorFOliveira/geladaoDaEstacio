package com.estacioGeladao.geladaoExpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	 @GetMapping("/")
	    public String hello(Model model) {
	        return "index";
	    }
}
