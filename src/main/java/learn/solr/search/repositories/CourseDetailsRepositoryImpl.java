package learn.solr.search.repositories;

import javax.annotation.Resource;

import org.apache.solr.common.params.FacetParams.FacetRangeInclude;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithNumericRangeParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import learn.solr.search.documents.CourseDetails;

@Service
public class CourseDetailsRepositoryImpl implements CourseDetailsCustomRepository {
	@Resource
	SolrOperations solrTemplate;

	@Override
	public FacetPage<CourseDetails> findAllFacetOnCourseRating(String term, Pageable page) {
		// TODO Auto-generated method stub
		FacetOptions facetOptions = new FacetOptions()
				.addFacetByRange(new FieldWithNumericRangeParameters("Course_Rating", 2, 5, 0.25).setHardEnd(true)
						.setInclude(FacetRangeInclude.ALL));
		facetOptions.setFacetMinCount(0);

		Criteria criteria = new SimpleStringCriteria("*:*");
		SimpleFacetQuery facetQuery = new SimpleFacetQuery(criteria).setFacetOptions(facetOptions);
		FacetPage<CourseDetails> results = solrTemplate.queryForFacetPage("Coursera_Courses", facetQuery,
				CourseDetails.class);
		return results;
	}

	@Override
	public FacetPage<CourseDetails> customFindAllFacetOnSkills(String searchTerm, Pageable pageable) {
		Criteria conditions = createSearchConditions(searchTerm);
		FacetOptions facetOption = new FacetOptions("Skills", "Difficulty_Level");
		FacetQuery query = new SimpleFacetQuery(conditions).setFacetOptions(facetOption.setFacetMinCount(1));
		query.setPageRequest(pageable);
		FacetPage<CourseDetails> results = solrTemplate.queryForFacetPage("Coursera_Courses", query,
				CourseDetails.class);
		return results;
	}

	private Criteria createSearchConditions(String searchWords) {
		String[] searchWord = searchWords.split(" ");
		Criteria conditions = null;
		Criteria nameConditions = new Criteria("Course_Name").contains(searchWords);
		Criteria descConditions = new Criteria("Course_Description").contains(searchWords);
		Criteria urlConditions = new Criteria("Course_URL").contains(searchWords);
		Criteria skillsConditions = new Criteria("Skills").contains(searchWords);
		Criteria univConditions = new Criteria("University").contains(searchWords);

		conditions = nameConditions.or(descConditions).or(urlConditions).or(skillsConditions).or(univConditions);

		return conditions;
	}

}
