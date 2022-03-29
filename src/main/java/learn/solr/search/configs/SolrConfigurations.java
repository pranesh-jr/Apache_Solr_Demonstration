package learn.solr.search.configs;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@ComponentScan
@EnableSolrRepositories(basePackages = {
		"learn.solr.search.repositories" }, namedQueriesLocation = "classpath:solr_named_queries.properties")
public class SolrConfigurations {

	@Value("${spring.data.solr.host}")
	String solrHostURL;

	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient.Builder(solrHostURL).build();
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}
}
