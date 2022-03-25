package learn.solr.search.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import learn.solr.search.documents.CourseDetails;
import learn.solr.search.repositories.CourseDetailsRepository;

@Controller
@RequestMapping("/search")
public class CourseSearch {
	@Autowired
	CourseDetailsRepository courseDetailsRepo;

	@RequestMapping(value = "/All")
	public String listAllRecords(HttpSession session, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findAll()) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("count",this.courseDetailsRepo.count());
		map.addAttribute("results", courseDetailsList);
		return "resultsPage";
	}

	@RequestMapping(value = "")
	public String searchByCourseName(@RequestParam("term") String term, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findInAllFields(term)) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("searchTerm", term);
		map.addAttribute("results", courseDetailsList);
		map.addAttribute("count",this.courseDetailsRepo.count());
		return "resultsPage";
	}

	@RequestMapping(value = "/byName")
	public String searchByCourseNameContaining(@RequestParam("c_name") String term, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findByCourseNameContaining(term)) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("searchTerm", term);
		map.addAttribute("results", courseDetailsList);
		map.addAttribute("count",this.courseDetailsRepo.count());
		return "resultsPage";
	}
	
}
