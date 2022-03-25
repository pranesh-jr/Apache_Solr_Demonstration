package learn.solr.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import learn.solr.search.documents.CourseDetails;
import learn.solr.search.repositories.CourseDetailsRepository;

@RestController
@RequestMapping(path = "/restSearch", produces="application/json")
public class CourseSearch_Rest {

	@Autowired
	CourseDetailsRepository courseDetailsRepository;

	@GetMapping(value = "/All")
	public List<CourseDetails> searchAll() {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepository.findAll()) {
			courseDetailsList.add(courseDetail);
		}
		
		return courseDetailsList;
	}

	@GetMapping(value = "/ByName")
	public List<CourseDetails> searchByName(@RequestParam("term") String name) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepository.findByCourseName(name)) {
			courseDetailsList.add(courseDetail);
		}

		return courseDetailsList;
	}
	
	@GetMapping(value = "/ByUniversity")
	public List<CourseDetails> searchByUniversity(@RequestParam("term") String university) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepository.findByCourseName(university)) {
			courseDetailsList.add(courseDetail);
		}

		return courseDetailsList;
	}
	
	@GetMapping(value = "/BySkill")
	public List<CourseDetails> searchBySkill(@RequestParam("term") String skill) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepository.findByCourseName(skill)) {
			courseDetailsList.add(courseDetail);
		}

		return courseDetailsList;
	}
}
