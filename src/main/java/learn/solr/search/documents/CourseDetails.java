package learn.solr.search.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "Coursera_Courses")
public class CourseDetails {
	@Id
	@Indexed(name = "id", type = "string")
	private String id;
	@Indexed(name = "Course_Name", type = "string")
	private String courseName;
	@Indexed(name = "Course_Description", type = "string")
	private String courseDescription;
	@Indexed(name = "Course_URL", type = "string")
	private String courseUrl;
	@Indexed(name = "Course_Rating", type = "string")
	private String courseRating;
	@Indexed(name = "Skills", type = "string")
	private String skills;
	@Indexed(name = "Difficulty_Level", type = "string")
	private String difficultyLevel;
	@Indexed(name = "University", type = "string")
	private String university;

	public CourseDetails() {
		super();
	}

	public CourseDetails(String id, String courseName, String courseDescription, String courseUrl, String courseRating,
			String skills, String difficultyLevel, String university) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseUrl = courseUrl;
		this.courseRating = courseRating;
		this.skills = skills;
		this.difficultyLevel = difficultyLevel;
		this.university = university;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseUrl() {
		return courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}

	public String getCourseRating() {
		return courseRating;
	}

	public void setCourseRating(String courseRating) {
		this.courseRating = courseRating;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "CourseDetails [id=" + id + ", courseName=" + courseName + ", courseDescription=" + courseDescription
				+ ", courseUrl=" + courseUrl + ", courseRating=" + courseRating + ", skills=" + skills
				+ ", difficultyLevel=" + difficultyLevel + ", university=" + university + "]";
	}

}
