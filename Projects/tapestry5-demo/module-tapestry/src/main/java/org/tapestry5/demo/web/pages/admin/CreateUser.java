package org.tapestry5.demo.web.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.java.demo.model.Account;
import org.java.demo.model.Role;
import org.java.demo.model.User;
import org.java.demo.service.RoleService;
import org.java.demo.service.UserService;
import org.java.demo.util.AppUtil;
import org.java.demo.util.ErrorKey;
import org.java.demo.util.spring.PasswordEncryptor;
import org.tapestry5.demo.web.pages.base.AbstractPage;

public class CreateUser extends AbstractPage {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private Block dataGridBlock;

    @Inject
    private PasswordEncryptor encryptor;

    @InjectComponent(value = "createEntityForm")
    private Form mainForm;

    @Property
    private User user;

    private List<Role> allRoles;

    @Property
    private List<OptionModel> roleModelList;

    @Property
    private List<String> roleValueList;

    @SetupRender
    void setupRender() {
        user = new User();

        roleModelList = new ArrayList<OptionModel>();

        allRoles = roleService.findAll();
        for (Role role : allRoles) {
            OptionModel option = new OptionModelImpl(role.getName(), role.getValue());
            roleModelList.add(option);
        }
    }

    public List<User> getUserList() {
        return userService.findAll();
    }

    @OnEvent(value = EventConstants.PREPARE_FOR_SUBMIT)
    public void onPrepareForm() {
        if (user == null) {
            user = new User();
        }
    }

    @OnEvent(value = EventConstants.VALIDATE)
    public Object onValidateForm() {
        return null;
    }

    @OnEvent(value = EventConstants.FAILURE)
    public void onFailure() {
        mainForm.recordError(getMessages().get(ErrorKey.COMMON_ERROR));
    }
    
    @OnEvent(value = EventConstants.SUCCESS)
    public Object onSuccess() throws Exception {
//        String encryptedPassword = encryptor.encode(user.getPassword());
//        Account.encryptPassword(user, encryptedPassword);
        List<Role> roles = roleService.findByProperty(Role.PROP_VALUE, roleValueList);
        user.setRoles(roles);
        userService.save(user);
        return null;
    }

    @OnEvent(value = EventConstants.ACTION, component = "deleteEntity")
    Object onDeleteUser(Long userId) {
        userService.delete(userId);
        return dataGridBlock;
    }

    public String getAssignedRoles() {
        List<Role> roles = user.getRoles();
        if (AppUtil.isNullOrEmpty(roles)) {
            return "";
        }

        String roleListAsString = "";
        for (Role role : roles) {
            if (!AppUtil.isNullOrEmpty(roleListAsString)) {
                roleListAsString += "<br>";
            }
            roleListAsString += "- " + role.getName();
        }
        return roleListAsString;
    }
}
