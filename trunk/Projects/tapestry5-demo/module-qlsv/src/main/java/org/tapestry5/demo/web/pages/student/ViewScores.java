package org.tapestry5.demo.web.pages.student;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.Account;
import org.java.demo.model.StudyResult;
import org.java.demo.model.SubjectResult;
import org.java.demo.service.AccountService;
import org.java.demo.service.StudyResultService;
import org.java.demo.util.AppUtil;
import org.java.demo.util.QLSVUtil;
import org.java.demo.util.WebConstant;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.tapestry5.demo.web.pages.base.QLSVPage;

public class ViewScores extends QLSVPage {

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
	private List<StudyResult> studyResults;
	@Property
	private StudyResult aStudyResult;
	@Property
	private SubjectResult aSubjectResult;

	@Inject
	private Block scoreListBlock;

	@SetupRender
	void setupRender() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.getByLoginName(auth.getName());
		studentId = account.getId();
	}

	@OnEvent(value = EventConstants.SUCCESS, component = WebConstant.MAIN_FORM_NAME)
	public Object onSuccess() throws Exception {
		try {
			studyResults = studyResultService.search(studentId, schoolYear, term, subjectId);
			if (!AppUtil.isNullOrEmpty(studyResults)) {
				aStudyResult = studyResults.get(0);
			}
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
}
