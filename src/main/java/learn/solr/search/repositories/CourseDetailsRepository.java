package learn.solr.search.repositories;

import org.springframework.data.solr.repository.SolrCrudRepository;

import learn.solr.search.documents.CourseDetails;

public interface CourseDetailsRepository extends SolrCrudRepository<CourseDetails, String> {

}
