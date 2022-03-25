package learn.solr.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping(value ={"/", "/home"})
	public String index() {
		return "index";
	}
}
