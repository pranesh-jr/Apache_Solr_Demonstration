package learn.solr.search.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import learn.solr.search.documents.CourseDetails;
import learn.solr.search.repositories.CourseDetailsRepository;
import learn.solr.search.repositories.CourseDetailsRepositoryImpl;

@Controller
@RequestMapping("/search")
public class CourseSearch {
	@Autowired
	CourseDetailsRepository courseDetailsRepo;
	@Autowired
	CourseDetailsRepositoryImpl courseDetailsCustomRepo;

	@RequestMapping(value = "/All")
	public String listAllRecords(HttpSession session, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findAll()) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("resultsCount", courseDetailsList.size());
		map.addAttribute("results", courseDetailsList);
		return "resultsPage";
	}

	@RequestMapping(value = "/")
	public String searchAllFields(@RequestParam("term") String term, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findInAllFields(term)) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("results", courseDetailsList);
		map.addAttribute("resultsCount", courseDetailsList.size());
		return "resultsPage";
	}

	@RequestMapping(value = "/byName")
	public String searchByCourseName(@RequestParam("c_name") String term, ModelMap map) {
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (CourseDetails courseDetail : this.courseDetailsRepo.findByCourseName(term)) {
			courseDetailsList.add(courseDetail);
		}
		map.addAttribute("searchTerm", term);
		map.addAttribute("results", courseDetailsList);
		map.addAttribute("resultsCount", courseDetailsList.size());
		return "resultsPage";
	}

	@RequestMapping(value = "/facetSkills")
	public String searchAllFacetOnSkills(@RequestParam("term") String term,
			@PageableDefault(page = 0, size = 15) Pageable pageable, ModelMap map) {
		FacetPage<CourseDetails> facetResults = this.courseDetailsRepo.findAllFacetOnSkills(term, pageable);
		List<CourseDetails> resultsList = facetResults.getContent();
		System.out.println("FacetResultsSize: " + facetResults.getSize());
		for (Page<? extends FacetEntry> facetPage : facetResults.getAllFacets()) {
			System.out.println("FacetPageSize: " + facetPage.getSize());
			System.out.println("FacetPageNumber: " + facetPage.getNumber());
			System.out.println("FacetPageNumberOfElements: " + facetPage.getNumberOfElements());
			System.out.println("FacetPageTotalElements: " + facetPage.getTotalElements());
			for (FacetEntry facetEntry : facetPage) {
				System.out.println(facetEntry.getKey());
				System.out.println(facetEntry.getValue() + " : " + facetEntry.getValueCount());
			}
		}

		FacetPage<CourseDetails> facetResults2 = courseDetailsCustomRepo.customFindAllFacetOnSkills(term, pageable);
		System.out.println("FacetResultsSize: " + facetResults2.getSize());
		for (Page<? extends FacetEntry> facetPage : facetResults2.getAllFacets()) {
			System.out.println("FacetPageSize: " + facetPage.getSize());
			System.out.println("FacetPageNumber: " + facetPage.getNumber());
			System.out.println("FacetPageNumberOfElements: " + facetPage.getNumberOfElements());
			System.out.println("FacetPageTotalElements: " + facetPage.getTotalElements());
			for (FacetEntry facetEntry : facetPage) {
				System.out.println(facetEntry.getKey());
				System.out.println(facetEntry.getValue() + " : " + facetEntry.getValueCount());
			}
		}
		map.addAttribute("results", resultsList);
		map.addAttribute("searchTerm", term);
		map.addAttribute("resultsCount", resultsList.size());
		return "resultsPage";
	}

}
