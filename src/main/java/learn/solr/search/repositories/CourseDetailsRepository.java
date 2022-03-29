package learn.solr.search.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import learn.solr.search.documents.CourseDetails;

public interface CourseDetailsRepository extends SolrCrudRepository<CourseDetails, String> {

	public List<CourseDetails> findByCourseName(String courseName);

	public List<CourseDetails> findByCourseDescription(String courseDescription);

	public List<CourseDetails> findByCourseRating(String courseRating);

	public List<CourseDetails> findByDifficultyLevel(String difficultyLevel);

	public List<CourseDetails> findByUniversity(String university);

	public List<CourseDetails> findBySkills(String skills);

	@Query(name = "Coursera_Courses.SearchAllFields")
	public List<CourseDetails> findInAllFields(String searchTerm);

	@Query(name = "Coursera_Courses.SearchAllFields")
	@Facet(fields = { "Skills", "Difficulty_Level" }, minCount = 1)
	public FacetPage<CourseDetails> findAllFacetOnSkills(String searchTerm, Pageable pageable);

}
