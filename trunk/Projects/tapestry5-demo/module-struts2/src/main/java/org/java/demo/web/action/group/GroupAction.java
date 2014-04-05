package org.java.demo.web.action.group;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.Group;
import org.java.demo.service.GroupService;
import org.java.demo.web.action.base.AbstractAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/** Change default name-space (default to package name) */
@Namespace(value = GroupAction.NAME_SPACE)
@Results({ @Result(name = AbstractAction.ACTION_LIST, location = AbstractAction.ACTION_LIST, type = AbstractAction.TYPE_REDIRECT_ACTION),
        @Result(name = AbstractAction.LIST, location = GroupAction.PAGE_LIST),
        @Result(name = AbstractAction.ERROR, location = GroupAction.PAGE_CREATE),
        @Result(name = AbstractAction.INPUT, location = GroupAction.PAGE_CREATE) })
public class GroupAction extends AbstractAction implements ModelDriven<Group> {

    public static final String NAME_SPACE = "/group";
    private static final String MODEL_NAME = "Group"; /* Used in page names */
    private static final String MODEL_DISPLAY_NAME = "Group"; /* Used for localization */

    protected static final String PAGE_CREATE = "create" + MODEL_NAME + ".jsp";
    protected static final String PAGE_LIST = "list" + MODEL_NAME + "s.jsp";
    protected static final String PAGE_VIEW = "view" + MODEL_NAME + ".jsp";

    @Autowired
    private GroupService groupService;

    private String headerText;

    private Group group = new Group();

    private List<Group> dataGrid;

    public List<Group> getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(List<Group> dataGrid) {
        this.dataGrid = dataGrid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    @SkipValidation
    @Action(value = ACTION_CREATE, results = { @Result(name = PREPARE, location = PAGE_CREATE) })
    public String create() throws DataConstraintException, Exception {
        try {
            initDataForCreate();
            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_CREATE)
    public String doCreate() throws DataConstraintException, Exception {
        try {
            this.groupService.save(group);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Create " + MODEL_DISPLAY_NAME + " fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_COPY, results = { @Result(name = PREPARE, location = PAGE_CREATE) })
    public String copy() throws DataConstraintException, Exception {
        try {
            initDataForCopy();
            loadDataModel(group);

            if (group == null) {
                group = new Group();
            } else {

                // Clear all un-copytable fields
                group.setCode(null);
            }

            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_COPY, results = { @Result(name = ACTION_VIEW, location = ACTION_VIEW, type = AbstractAction.TYPE_REDIRECT_ACTION, params = {
            "id", "%{id}" }) })
    public String doCopy() throws DataConstraintException, Exception {
        try {
            this.groupService.save(group);

            pushModel(this.group);
            return ACTION_VIEW;
        } catch (Exception e) {
            addActionError("Copy " + MODEL_DISPLAY_NAME + " fail");
            initDataForCreate();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_EDIT, results = { @Result(name = PREPARE, location = PAGE_CREATE) })
    public String edit() {
        try {
            initDataForEdit();
            loadDataModel(group);

            return PREPARE;
        } catch (Exception e) {
            addActionError("Prepare data fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @Action(value = ACTION_DO_EDIT)
    public String doEdit() {
        try {
            this.groupService.update(group);
            return ACTION_LIST;
        } catch (Exception e) {
            addActionError("Edit " + MODEL_DISPLAY_NAME + " fail");
            initDataForEdit();
            return ERROR;
        }
    }

    @SkipValidation
    @Action(value = ACTION_VIEW, results = { @Result(name = SUCCESS, location = PAGE_VIEW) })
    public String view() {
        try {
            pageTitle = "View " + MODEL_DISPLAY_NAME + " Detail";
            headerText = pageTitle;
            loadDataModel(group);
            return SUCCESS;
        } catch (Exception e) {
            // Add errors
            return SUCCESS;
        }
    }

    @SkipValidation
    @Action(value = ACTION_LIST)
    public String list() {
        this.dataGrid = groupService.findAll();
        initDataForList();
        return LIST;
    }

    private void initDataForCreate() {
        action = ACTION_DO_CREATE;
        pageTitle = "Create New " + MODEL_DISPLAY_NAME;
        headerText = pageTitle;
    }

    private void initDataForCopy() {
        action = ACTION_DO_COPY;
        pageTitle = "Copy " + MODEL_DISPLAY_NAME;
        headerText = pageTitle;
    }

    private void initDataForEdit() {
        action = ACTION_DO_EDIT;
        pageTitle = "Edit " + MODEL_DISPLAY_NAME;
        headerText = pageTitle;
    }

    private void initDataForList() {
        pageTitle = "List " + MODEL_DISPLAY_NAME + "s";
        headerText = pageTitle;
    }

    private void loadDataModel(final Group model) {
        if (model != null && model.getId() != null) {
            this.group = groupService.find(model.getId());
            if (this.group != null) {
                pushModel(this.group);
            }
        }
    }

    @SkipValidation
    @Action(ACTION_DELETE)
    public String delete() {
        try {
            if (group != null && !isNullOrEmpty(group.getId())) {
                groupService.delete(group.getId());
            }

            return ACTION_LIST;
        } catch (Exception e) {
            return ERROR;
        }
    }

    @Override
    public Group getModel() {
        return group;
    }
}
