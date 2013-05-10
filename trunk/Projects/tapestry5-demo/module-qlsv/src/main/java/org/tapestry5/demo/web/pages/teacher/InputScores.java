package org.tapestry5.demo.web.pages.teacher;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.StudyResult;
import org.java.demo.model.SubjectResult;
import org.java.demo.service.StudyResultService;
import org.java.demo.util.AppUtil;
import org.java.demo.util.ErrorKey;
import org.java.demo.util.QLSVUtil;
import org.java.demo.util.WebConstant;
import org.tapestry5.demo.web.pages.base.QLSVPage;

public class InputScores extends QLSVPage {

    private static final String ADD_SCORE_FORM = "addScoreForm";

    @InjectComponent(value = WebConstant.MAIN_FORM_NAME)
    private Form mainForm;

    @Inject
    private Block addScoreBlock;
    @Inject
    private Block scoreListBlock;

    @Property
    @Persist
    private StudyResult studyResult;
    @Property
    private SubjectResult subjectResult;
    @Inject
    private StudyResultService studyResultService;

    @Property
    private SubjectResult aSubjectResult;

    @SetupRender
    public void setupRender() {
        studyResult = new StudyResult();
        subjectResult = new SubjectResult();
    }

    public List<String> getSchoolYearModel() {
        return QLSVUtil.getSchoolYearList();
    }

    public List<String> getTermModel() {
        return QLSVUtil.getTermList();
    }

    @OnEvent(value = EventConstants.PREPARE_FOR_SUBMIT, component = WebConstant.MAIN_FORM_NAME)
    public void onPrepareForm() {
        if (studyResult == null) {
            studyResult = new StudyResult();
        }
    }

    @OnEvent(value = EventConstants.VALIDATE_FORM, component = WebConstant.MAIN_FORM_NAME)
    public Object onValidateForm() {
        return null;
    }

    @OnEvent(value = EventConstants.FAILURE, component = WebConstant.MAIN_FORM_NAME)
    public void onFailure() {
        mainForm.recordError(getMessages().get(ErrorKey.COMMON_ERROR));
    }

    @OnEvent(value = EventConstants.SUCCESS, component = WebConstant.MAIN_FORM_NAME)
    public Object onSuccess() throws Exception {
        try {
            if (AppUtil.isNullOrEmpty(studyResult.getSubjectResults())) {
                mainForm.recordError(getMessages().get("error.minimum.subjectResult"));
            } else {
                studyResultService.save(studyResult);
            }
        } catch (Exception e) {
            mainForm.recordError(getMessages().get(ErrorKey.SAVE_ERROR));
        }
        return null;
    }

    // Adding score
    @OnEvent(value = EventConstants.PREPARE_FOR_SUBMIT, component = ADD_SCORE_FORM)
    public void onPrepareForm2() {
        if (subjectResult == null) {
            subjectResult = new SubjectResult();
        }
    }

    @OnEvent(value = EventConstants.VALIDATE_FORM, component = ADD_SCORE_FORM)
    public Object onValidateForm2() {
        return null;
    }

    @OnEvent(value = EventConstants.FAILURE, component = ADD_SCORE_FORM)
    public void onFailure2() {
        mainForm.recordError(getMessages().get(ErrorKey.COMMON_ERROR));
    }

    @OnEvent(value = EventConstants.SUCCESS, component = ADD_SCORE_FORM)
    public Object onSuccess2() throws Exception {
        studyResult.addSubjectResult(subjectResult);
        subjectResult = new SubjectResult();
        MultiZoneUpdate multiZoneUpdate = new MultiZoneUpdate("addScoreZone", addScoreBlock);
        return multiZoneUpdate.add("scoreListZone", scoreListBlock);
    }
}
