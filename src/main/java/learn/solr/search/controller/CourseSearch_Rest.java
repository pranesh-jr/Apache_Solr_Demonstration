package learn.solr.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.solr.search.documents.CourseDetails;
import learn.solr.search.repositories.CourseDetailsRepository;

@RestController
public class CourseSearch_Rest {

	@Autowired
	CourseDetailsRepository courseDetailsRepository;

	@RequestMapping(value = "/searchAll")
	public List<CourseDetails> searchAll() {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepository.findAll()) {
			courseDetailsList.add(courseDetail);
		}

		return courseDetailsList;
	}
}
