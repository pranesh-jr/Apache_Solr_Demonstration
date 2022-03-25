package learn.solr.search.repositories;

import java.util.List;

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
	
	@Query(name = "SearchAllFields")
	public List<CourseDetails> findInAllFields(String searchTerm);
	
	public List<CourseDetails> findByCourseNameContaining(String courseName);

}
