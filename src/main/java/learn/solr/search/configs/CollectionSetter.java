package learn.solr.search.configs;

import org.springframework.beans.factory.annotation.Value;

public class CollectionSetter {
	@Value("${solrCollectionName}")
	private String collectionName;

	public String getCollectionName() {
		return collectionName;
	}
}
