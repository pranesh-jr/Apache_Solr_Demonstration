package learn.solr.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@RequestMapping(value = { "/", "/home" })
	public String index(HttpServletRequest request) {
		logger.debug("Request URL {} is responded with Index.jsp", request.getRequestURL());
		return "index";
	}
}
