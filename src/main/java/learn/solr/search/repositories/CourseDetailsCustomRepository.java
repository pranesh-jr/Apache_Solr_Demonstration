package learn.solr.search.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import learn.solr.search.documents.CourseDetails;

public interface CourseDetailsCustomRepository {

	public FacetPage<CourseDetails> findAllFacetOnCourseRating(String term, Pageable page);
	
	FacetPage<CourseDetails> customFindAllFacetOnSkills(String searchTerm, Pageable pageable);
}
