package org.tapestry5.demo.web.pages.admin;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.Role;
import org.java.demo.service.RoleService;
import org.java.demo.util.ErrorKey;
import org.tapestry5.demo.web.pages.base.AbstractPage;

public class CreateRole extends AbstractPage {

    @Inject
    private RoleService roleService;

    @Property
    private Role role;

    @Inject
    private Messages messages;

    @InjectComponent(value = "createEntityForm")
    private Form mainForm;

    @Inject
    private Block dataGridBlock;

    @SetupRender
    void setupRender() {
        role = new Role();
    }

    public List<Role> getRoleList() {
        return roleService.findAll();
    }

    public String getDisplayRoleName() {
        return messages.get("account." + role.getName());
    }

    @OnEvent(value = EventConstants.PREPARE_FOR_SUBMIT)
    public void onPrepareForm() {
        if (role == null) {
            role = new Role();
        }
    }

    // @OnEvent(value = EventConstants.VALIDATE_FORM)
    // public void onValidateForm() {
    // }

    @OnEvent(value = EventConstants.FAILURE)
    public void onFailure() {
        mainForm.recordError(messages.get(ErrorKey.COMMON_ERROR));
    }

    @OnEvent(value = EventConstants.SUCCESS)
    public Object onSuccess() throws Exception {
        try {
            roleService.save(role);
        } catch (Exception e) {
            mainForm.recordError(messages.get(ErrorKey.SAVE_ERROR));
        }
        return null;
    }

    // @OnEvent(value = EventConstants.SUBMIT)
    // public Object onSubmit() throws Exception {
    // return null;
    // }

    @OnEvent(value = EventConstants.ACTION, component = "deleteEntity")
    Object onDeleteUser(Long roleId) {
        roleService.delete(roleService.find(roleId));
        return dataGridBlock;
    }
}
