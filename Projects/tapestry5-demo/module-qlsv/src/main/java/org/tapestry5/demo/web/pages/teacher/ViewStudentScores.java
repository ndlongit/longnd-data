package org.tapestry5.demo.web.pages.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.StudyResult;
import org.java.demo.model.SubjectResult;
import org.java.demo.service.AccountService;
import org.java.demo.service.StudyResultService;
import org.java.demo.util.AppUtil;
import org.java.demo.util.QLSVUtil;
import org.java.demo.util.WebConstant;
import org.tapestry5.demo.web.pages.base.QLSVPage;

public class ViewStudentScores extends QLSVPage {

	@Inject
	private AccountService accountService;
	@Inject
	private StudyResultService studyResultService;

	@Property
	private Long subjectId;
	@Property
	private Long studentId;
	@Property
	private String schoolYear;
	@Property
	private String term;
	@Property
	@Persist
	private String result;
	@Property
	private List<StudyResult> studyResults;
	@Property
	private StudyResult aStudyResult;
	@Property
	private SubjectResult aSubjectResult;

	@Inject
	private Block scoreListBlock;

	@SetupRender
	void setupRender() {
		result = null;
	}

	@OnEvent(value = EventConstants.SUCCESS, component = WebConstant.MAIN_FORM_NAME)
	public Object onSuccess() throws Exception {
		try {
			studyResults = studyResultService.search(studentId, schoolYear, term, null);
		} catch (Exception e) {
		}
		return scoreListBlock;
	}

	public List<String> getSchoolYearModel() {
		return QLSVUtil.getSchoolYearList();
	}

	public List<String> getTermModel() {
		return QLSVUtil.getTermList();
	}

	public List<String> getResultModel() {
		List<String> results = new ArrayList<String>();
		results.add("Dau");
		results.add("Rot");
		return results;
	}

	public boolean isShowResult() {
		if (AppUtil.isNullOrEmpty(result)) {
			return true;
		} else {
			if ("Dau".equalsIgnoreCase(result)) {
				return (aSubjectResult.getScore() >= 5);
			} else if ("Rot".equalsIgnoreCase(result)) {
				return (aSubjectResult.getScore() < 5);
			} else {
				return true;
			}
		}
	}
}
