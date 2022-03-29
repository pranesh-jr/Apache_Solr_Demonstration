package learn.solr.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(ServletInitializer.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.debug("Initializing Servlet");
		return application.sources(ApacheSolrDemonstrationApplication.class);
	}

}
